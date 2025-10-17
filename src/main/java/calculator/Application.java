package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;

public class Application {

    private static final String defaultSeparatorRegex = "[,:]";
    private static final String customSeparatorPrefix = "//";
    private static final String customSeparatorSuffix = "\\n";

    public static void main(String[] args) {
        String input = readInputString();

        if(input.equals("")) {
            System.out.println("결과 : " + 0);
            return;
        }

        String[] values = input.startsWith(customSeparatorPrefix)
            ? splitWithCustomSeparator(input) : splitWithSeparator(input);

        validateNumbers(values);

        int sum = sumNumbers(values);
        System.out.println("결과 : " + sum);
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

    private static void validateNumbers(String[] values) {
        for (String value : values)
            if (!isPositiveInteger(value)) throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private static boolean isPositiveInteger(String value) {
        try {
            int num = Integer.parseInt(value);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int sumNumbers(String[] values) {
        return Arrays.stream(values).mapToInt(Integer::parseInt).sum();
    }
}
