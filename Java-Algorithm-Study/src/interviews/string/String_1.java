package interviews.string;

/**
 * 문제 : 정수형 숫자를 입력받으면 문자열을, 반대로 문자열을 입력받으면 정수형 숫자를 반환하는 함수를 작성하라
 * 단, 이때 Java의 parseInt와 같은 라이브러리를 사용하면 안된다.
 */
public class String_1 {
    public static void main(String[] args) {
        int x = -314;
        System.out.println(intToString(x));

        String s = "-314";
        System.out.println(stringToInt(s));
    }

    /**
     * 해결방법 : 어떤 양의 정수 x의 최하위 숫자는 x mod 10로 표현되고, 나머지 숫자는 x / 10으로 표현됨을 이용
     * 나머지 연산을 통해 숫자를 추가한 후 마지막에 역순으로 배열된 문자를 뒤집어 준다.
     */
    public static String intToString(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
        }

        StringBuffer s = new StringBuffer();
        do {
            s.append((char)('0' + Math.abs(x % 10)));
            x /= 10;
        } while (x != 0);

        if (isNegative) {
            s.append('-');
        }
        s.reverse();
        return s.toString();
    }

    /**
     * 해결방법 : 가장 왼쪽의 숫자부터 계산을 시작해서, 중간 결과값에 10을 곱한 뒤 각 자릿수를 더해 나간다.
     */
    public static int stringToInt(String s) {
        int result = 0;
        for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); i++) {
            final int digit = s.charAt(i) - '0';
            result = result * 10 + digit;
        }
        return s.charAt(0) == '-' ? -result : result;
    }
}
