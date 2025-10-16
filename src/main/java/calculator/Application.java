package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    private static final String defaultSeparatorRegex = "[,:]";
    private static final String customSeparatorPrefix = "//";
    private static final String customSeparatorSuffix = "\\n";

    public static void main(String[] args) {
        String input = readInputString();

        String[] tokens = input.startsWith(customSeparatorPrefix)
            ? splitWithCustomSeparator(input) : splitWithSeparator(input);
    }

    private static String readInputString() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        Console.close();

        return input;
    }

    private static String[] splitWithSeparator(String input) {
        return input.split(defaultSeparatorRegex);
    }

    private static String[] splitWithCustomSeparator(String input) {
        String withoutPrefix = input.replaceFirst(customSeparatorPrefix, "");
        int suffixStartIndex = withoutPrefix.indexOf(customSeparatorSuffix);

        String customSeparator = withoutPrefix.substring(0, suffixStartIndex);
        String cleanInput = withoutPrefix.substring(suffixStartIndex + 2);

        return cleanInput.split(customSeparator);
    }
}
