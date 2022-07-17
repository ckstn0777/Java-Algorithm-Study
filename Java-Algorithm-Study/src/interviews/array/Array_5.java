package interviews.array;

import java.util.Arrays;
import java.util.List;

/**
 * 문제 : 정렬된 배열에서 반복되는 원소를 제거하는 프로그램을 구현하라. 결과는 삭제 후 유효한 원소의 개수를 반환할 것.
 * 예시) [2, 3, 5, 5, 7, 11, 11, 11, 13] -> [2, 3, 5, 7, 11, 13, x, x, x]
 * 유효한 원소 뒤에 나오는 원소들은 어떤 상태이던지 상관없다. 우리가 할 건 유효한 원소 내 반복을 제거하고 개수만 반환하면 됨.
 * 단, 많은 언어에서 삭제 연산이 라이브러리로 주어지지만 라이브러리를 사용하지 말고 구현할 것.
 */
public class Array_5 {
    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(2, 3, 5, 5, 7, 11, 11, 11, 13);
        System.out.println(deleteDuplicates(A));
    }

    /**
     * - 해결방법 1: 공간을 O(n)만큼 추가로 사용할 수 있다면 해시 테이블을 사용해서 쉽게 구현할 수 있다.
     * 즉, 배열 A를 순회하면서 해시 테이블에 원소를 넣고 어떤 원소가 새로운 원소인지 아닌지 확인해서 새로운 원소는 리스트에 넣은 뒤 반환.
     * - 해결방법 2 : 공간을 O(1) 사용하지만 무식하게 배열을 순회하면서 A[i]와 A[i+1]이 같을 때, i + 2 이후 원소를 모두 왼쪽으로 옮기는 방법
     * 다만, 이러면 시간 복잡도가 O(n^2)가 된다.
     * - 해결방법 3 : 모든 부분 배열을 옮기지 않고, 추가공간을 쓰지 않아도 되는 방법. 단순히 원소 하나를 한번만 옮기면 된다.
     * 시간복잡도는 O(n)이고, 공간복잡도는 O(1)이 된다. (오... 생각의 힘인가)
     */
    public static int deleteDuplicates(List<Integer> A) {
        if (A.isEmpty()) {
            return 0;
        }

        int writeIndex = 1;
        for (int i = 1; i < A.size(); i++) {
            // 중복하지 않은 원소라면 유효한 원소를 만들어준 다음, writeIndex 증가
            // 결국 writeIndex가 핵심인 셈이지. 중복제거와 그렇지 않은 부분에 대한 경계 역할이기 때문.
            if (!A.get(writeIndex - 1).equals(A.get(i))) {
                A.set(writeIndex, A.get(i));
                writeIndex += 1;
            }
        }

        System.out.println("A = " + A);
        return writeIndex;
    }
}
