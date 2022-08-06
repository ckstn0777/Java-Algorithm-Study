package interviews.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 문제 : 배열과 숫자 하나가 주어졌을 때, 배열 안의 원소 세 개를 더해서 주어진 숫자를 만들 수 있는지 확인하라.
 * 배열 안의 원소는 중복이 가능하다.
 */
public class Greedy_4 {
    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(11, 2, 5, 7, 3);
        System.out.println(hasThreeSum(A, 21));
    }

    /**
     * 해결 방법 : 가장 무식한 방법으로 삼중 루프를 사용해서 모든 가능한 경우를 살펴보는 것이다. 시간 복잡도는 O(n^3)가 된다.
     * 해시 테이블을 이용해서 시간 복잡도는 O(n^2)로 개선해볼 수도 있다. 배열의 원소를 해시테이블에 저장한 다음, 원소쌍을 순회하면서
     * t - (A[i] + A[j])가 해시테이블에 있는지 여부를 확인하는 것이다. 단, 공간복잡도는 O(n)이 된다.
     *
     * 여기서 권장하는 방법은 입력 배열을 정렬한 다음 A[j] + A[k] = t - A[i]를 만족하는 j와 k를 찾는 것이다.
     * j, k를 0과 n-1부터 시작하면서 A[0] + A[n - 1] < t - A[i]라면 0을 1로 옮겨간다. 반대로 A[0] + A[n - 1] > t -A[i]라면
     * n - 1을 n - 2로 옮겨가면 된다. 결국 시간복잡도는 O(n^2), 공간복잡도는 O(1)로 해결할 수 있다.
     */
    public static boolean hasThreeSum(List<Integer> A, int t) {
        Collections.sort(A);

        for (Integer a : A) {
            int start = 0, end = A.size() - 1;
            while (start <= end) {
                if (A.get(start) + A.get(end) < t - a) {
                    start += 1;
                } else if (A.get(start) + A.get(end) == t - a){
                    return true;
                } else { // A.get(start) + A.get(end) > t - a
                    end -= 1;
                }
            }
        }
        return false;
    }
}
