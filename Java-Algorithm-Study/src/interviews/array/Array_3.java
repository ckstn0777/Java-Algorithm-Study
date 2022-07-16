package interviews.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 문제 : 정수를 나타내는 배열(혹은 문자열? 숫자?)가 주어졌을 때, 이 둘의 곱셈 결과를 반환하는 함수를 작성하라.
 * 단, 그냥 정수로 해서 곱하는 경우는 오버플로 문제가 발생할 수 있으니 다른 방식을 사용할 것
 */
public class Array_3 {
    public static void main(String[] args) {
        List<Integer> num1 = Arrays.asList(1, 9, 3, 7, 0, 7 ,7 ,2, 1);
        List<Integer> num2 = Arrays.asList(-7, 6, 1, 8, 3, 8, 2, 5, 7, 2, 8, 7);

        System.out.println(multiply(num1, num2));
    }

    /**
     * 해결방법 : 각 자릿수에 있는 숫자를 곱한 뒤 해당 자리에 결과를 적고 마지막에 같은 자리에 있는 숫자들을 모두 더해주는 방법
     * 공간 절약을 위해 곱셈을 할 때마다 그때그때 결과값을 더할 것이다.
     */
    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
        final int sign = num1.get(0) < 0 ^ num2.get(0) < 0 ? - 1 : 1; // xor 비트 연산 - 하나만 음수인 경우 음수
        num1.set(0, Math.abs(num1.get(0))); // 음수를 양수로
        num2.set(0, Math.abs(num2.get(0))); // 음수를 양수로

        // n * m을 한 결과값 자리수는 최대 n + m이 되므로..(해보면 알게됨) n + m 배열을 만들어서 결과값을 저장할 예정
        List<Integer> result = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));

        // 곱셈 구현
        for (int i = num1.size() - 1; i >= 0; i--) {
            for (int j = num2.size() - 1; j >= 0; j--) {
                result.set(i + j + 1, result.get(i + j + 1) + num1.get(i) * num2.get(j));
                result.set(i + j, result.get(i + j) + result.get(i + j + 1) / 10); // 올림
                result.set(i + j + 1, result.get(i + j + 1) % 10); // 나머지
            }
        }

        // 0으로 시작하는 부분을 제거. ex) 10 * 10 = 100인데 result.size는 4이므로 앞에 0 제거 필요
        int first_not_zero = 0;
        while (first_not_zero < result.size() && result.get(first_not_zero) == 0) {
            first_not_zero += 1;
        }
        result = result.subList(first_not_zero, result.size());

        if (result.isEmpty()) { // ex. 0 * 0인 경우
            return Arrays.asList(0);
        }

        result.set(0, result.get(0) * sign); // 결과 부호
        return result;
    }
}
