package interviews.dynamic;

import java.util.Arrays;
import java.util.List;

/**
 * 문제 : 정수 배열이 주어졌을 때 합이 가장 큰 부분배열을 동적 프로그래밍으로 구하라. (최대부분배열 문제)
 * 모든 부분배열의 합을 구해야 하므로 무식하게 풀면 시간 복잡도는 O(n^3)이 된다. 하지만 추가로 O(n)의 공간을 사용해서
 * 모든 k에 대한 S[k] = Sum(0~k)을 저장할 수 있다면 O(n^2)로 개선할 수 있다. A[i, j] = S[j] - S[i - 1]이므로.
 */
public class Dynamic_Ex_2 {
    public static void main(String[] args) {
        // List<Integer> A = Arrays.asList(904, 40, 523, 12, -335, -385, -124, 481, -31);
        List<Integer> A = Arrays.asList(-54, -13, 40, -10, 20, 30);

        System.out.println(findMaximumSubarray(A));
    }

    /**
     * 동적프로그래밍 해결방법. 최대부분합은 S[j] - minS[k] (k<=j)가 되는 점을 이용한다.
     * 배열을 차례대로 순회할 때마다 합이 가장 작은 S[k]를 추적할 수 있으므로 각 인덱스에 대한 최대 부분합을 찾을 수 있다.
     * 시간 복잡도는 O(n), 공간 복잡도는 O(1)이 된다.
     */
    public static int findMaximumSubarray(List<Integer> A) {
        int minSum = 0, sum = 0, maxSum = 0;
        for (int i = 0; i < A.size(); i++) {
            sum += A.get(i);
            if (sum < minSum) {
                minSum = sum;
            }
            if (sum - minSum > maxSum) {
                maxSum = sum - minSum;
            }
        }
        return maxSum;
    }
}
