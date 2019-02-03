import org.apache.commons.lang3.StringUtils;

import java.util.function.Consumer;

public class StringOperations {

    private String input;

    public String transformWithStrategy(String input, StringOperationSelector selector) {
        this.input = input;
        selector.accept(this);
        return this.input;
    }

    public String transformWithSwitch(String input, StringOperationSelector selector){
        this.input = input;
        switch (selector) {
            case UPPER_CASE:
                this.upperCase();
                break;
            case LOWER_CASE:
                this.lowerCase();
                break;
            case CAPITALIZE:
                this.capitalize();
                break;
        }
        return this.input;
    }

    private void lowerCase() {
        input =  StringUtils.lowerCase(input);
    }

    private void upperCase() {
        input =  StringUtils.upperCase(input);
    }

    private void capitalize() {
        input = StringUtils.capitalize(input);
    }

    private enum StringOperationSelector implements Consumer<StringOperations> {
        UPPER_CASE(StringOperations::upperCase),
        LOWER_CASE(StringOperations::lowerCase),
        CAPITALIZE(StringOperations::capitalize);

        private Consumer<StringOperations> consumer;

        StringOperationSelector(Consumer<StringOperations> consumer){
            this.consumer = consumer;
        }

        @Override
        public void accept(StringOperations stringOperations) {
            this.consumer.accept(stringOperations);
        }
    }
}
