package interviews.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 문제 : 몇 개의 동전을 가지고 있다고 할 때, 이 동전들을 조합해서 만들 수 없는 숫자 중에서 가장 작은 숫자를 반환하는 프로그램을 작성하라.
 */
public class Sort_4 {
    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(12, 2, 1, 15, 2, 4);
        System.out.println(smallestNonconstructibleValue(A));
    }

    /**
     * 해결방법 : 몇 개의 구체적인 예를 보면서 일반적인 규칙을 찾을 수 있는 문제이다. 예를 들어 (1, 2, 5)가 있을 때 만들 수 없는 가장 작은 숫자는 4이다.
     * 왜냐면 1 + 2를 하면 3까지는 만들 수 있지만 3(V) + 1 >= 5(u)가 성립되지 않으므로 다음 숫자인 4를 만들 수 없는 것이다. (오 그렇군)
     */
    public static int smallestNonconstructibleValue(List<Integer> A) {
        Collections.sort(A);
        int maxConstructibleValue = 0;
        for (int a : A) {
            if (a > maxConstructibleValue + 1) {
                break;
            }
            maxConstructibleValue += a;
        }
        return maxConstructibleValue + 1;
    }
}
