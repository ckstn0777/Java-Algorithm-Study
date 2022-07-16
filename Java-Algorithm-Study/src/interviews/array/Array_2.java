package interviews.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * - 문제 : 십집수 D를 나타낸 배열 A가 주어졌을 때, D + 1의 결과를 다시 배열 A로 갱신하는 코드를 작성하라.
 * 단, 작성한 알고리즘은 유한 정밀도 산술로 이루어진 프로그램 언어로도 동작해야 한다.
 * 따라서 무식한 방법으로 배열의 숫자를 정수로 바꾼 후 1만큼 증가시킨 다음, 다시 배열로 만들 수 없다.
 * 프로그램 언어에서 정한 정수의 범위에 한해서만 작동하고, 그 범위를 벗어나는 값에 대해서는 동작하지 않기 때문에.
 */
public class Array_2 {
    public static void main(String[] args) {
        // List<Integer> A = Arrays.asList(9, 9, 9, 9); 이렇게 쓰면 에러난다.
        // 왜냐면 위와 같이 생성하면 진짜 배열이기 때문... 크기가 고정이므로 add, remove 연산은 불가능하다.
        // ArrayList는 배열이지만 좀 차이가 있게 만들어져서 add, remove 연산이 가능함

        List<Integer> A = new ArrayList<>(Arrays.asList(9, 9, 9, 9));
        System.out.println(plusOne(A));
    }

    /**
     * 해결방법 : 배열에 연산을 직접 적용하면 오버플로 문제를 피할 수 있음.
     * 간단하게 최하위 숫자부터 덧셈한 후 올림수를 넘겨주는 방식을 사용하면 된다.
     */
    public static List<Integer> plusOne(List<Integer> A) {
        int n = A.size() - 1;
        A.set(n, A.get(n) + 1); // 마지막 값을 1 증가시킴

        // 증가시켰는데 10이라면 계속 올림
        for (int i = n; i > 0 && A.get(i) == 10; i--) {
            A.set(i, 0);
            A.set(i - 1, A.get(i - 1) + 1);
        }

        // 최상위 숫자가 10이라면 결과 저장을 위해 한자리가 더 필요하다.
        // 깔끔한 방법은 첫번째 항목을 1로 만들고, 배열 끝에는 0을 추가해주는 것.
        if (A.get(0) == 10) {
            A.set(0, 1);
            A.add(0);
            //A.add(0);
        }

        return A;
    }
}
