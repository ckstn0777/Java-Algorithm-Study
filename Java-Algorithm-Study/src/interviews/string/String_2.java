package interviews.string;

/**
 * 문제 : 문자열 하나와 두 개의 정수 b1, b2가 주어졌을 때, 정수의 밑수를 바꾸는 프로그램을 작성하라.
 * 밑수가 b1인 입력 문자열을 밑수가 b2인 문자열로 바꾸면 된다. (2 <= b1, b2 <= 16)
 */
public class String_2 {
    public static void main(String[] args) {
        // 615(7) -> 306(10) -> 1A7(13)
        System.out.println(convertBase("615", 7, 13));
    }

    /**
     * 해결방법 : b1 진수를 10진수로 먼저 바꾼다음, 10진수를 b2진수로 바꾸는 게 가장 좋은 방법...(진수 너무 어려워...)
     */
    public static String convertBase(String numAsString, int b1, int b2) {
        boolean isNegative = numAsString.startsWith("-");
        int numAsInt = 0;
        for (int i = (isNegative ? 1: 0); i < numAsString.length(); i++) {
            numAsInt *= b1; // b1 곱하는게 앞에 나오고 뒤에 변환해서 더하는건 신박한 방식이네.
            numAsInt += Character.isDigit(numAsString.charAt(i)) // 0~9와 A~F를 나눠서 변환
                    ? numAsString.charAt(i) - '0'
                    : numAsString.charAt(i) - 'A' + 10;
        }
        System.out.println("numAsInt = " + numAsInt);
        return (isNegative ? "-" : "") + (numAsInt == 0 ? "0" : constructFromBase(numAsInt, b2));
    }

    /**
     * 10진수를 b2진수로 바꾸는건 더 작은 부분 문제로 간단하게 표현되므로 자연스럽게(?) 재귀를 사용하면 좋다.
     */
    private static String constructFromBase(int numAsInt, int base) {
        return numAsInt == 0
                ? ""
                : constructFromBase(numAsInt / base, base)
                    + (char)(numAsInt % base >= 10 ? 'A' + numAsInt % base - 10
                                                    : '0' + numAsInt % base);
    }
}
