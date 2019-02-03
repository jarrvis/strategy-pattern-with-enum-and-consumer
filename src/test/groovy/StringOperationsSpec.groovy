import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification
import spock.lang.Unroll

class StringOperationsSpec extends Specification {

    def "Should uppercase string when transform called with UPPER_CASE selector using strategy"(String input, String output) {
        expect:
            output == new StringOperations().transformWithStrategy(input, StringOperations.StringOperationSelector.UPPER_CASE)
        where:
            input | output
            "test" | "TEST"
            "testing uppercase" | "TESTING UPPERCASE"
    }
    def "Should lowercase string when transform called with LOWER_CASE selector using strategy"(String input, String output) {
        expect:
            output == new StringOperations().transformWithStrategy(input, StringOperations.StringOperationSelector.LOWER_CASE)
        where:
            input | output
            "test" | "test"
            "testing LOWERCASE" | "testing lowercase"
    }

    @Unroll
    def "Should capitalize string when transform called with CAPITALIZE selector using strategy"(String input, String output) {
        expect:
            output == new StringOperations().transformWithStrategy(input, StringOperations.StringOperationSelector.CAPITALIZE)
        where:
            input | output
            "test" | "Test"
            "testing CAPITALIZE" | "Testing CAPITALIZE"

    }

    def "Should capitalize string when transform called with CAPITALIZE selector using switch"(String input, String output) {
        setup:
            def start = System.currentTimeMillis()
        expect:
            output == new StringOperations().transformWithSwitch(input, StringOperations.StringOperationSelector.CAPITALIZE)
        cleanup:
            System.out.println("DEBUG: Logic A took " + (System.currentTimeMillis() - start) + " MilliSeconds")
        where:
            input | output
            "test" | "Test"
            "testing CAPITALIZE" | "Testing CAPITALIZE"
    }

    def "Compare CAPITALIZE time of execution using switch and strategy"() {
        setup:
            def start = System.currentTimeMillis()
        when:
            start = System.currentTimeMillis()
            for(int i = 0; i<1000000; i++)
                new StringOperations().transformWithSwitch(RandomStringUtils.randomAlphabetic(10), StringOperations.StringOperationSelector.LOWER_CASE)
        then:
            System.out.println("DEBUG: Switch logic took " + (System.currentTimeMillis() - start) + " MilliSeconds")

        when:
            start = System.currentTimeMillis()
            for(int i = 0; i<1000000; i++)
                new StringOperations().transformWithStrategy(RandomStringUtils.randomAlphabetic(10), StringOperations.StringOperationSelector.LOWER_CASE)
        then:
            System.out.println("DEBUG: Strategy logic took " + (System.currentTimeMillis() - start) + " MilliSeconds")


    }
}