package interviews.search;

import java.util.Arrays;
import java.util.List;

/**
 * 문제 : 원소가 중복되지 않고 정렬되어 있는 배열이 주어졌을 때, 인덱스 i의 값이 i와 같은 원소를 반환하는 효율적인 알고리즘 설계하라
 * 여러 개인 경우 한개만 반환하면 되는 듯하다.
 */
public class Search_2 {
    public static void main(String[] args) {
        // -2, 0, 2, 99, 101, 104
        List<Integer> A = Arrays.asList(-2, 0, 2, 3, 6, 7, 9);
        System.out.println(searchEntryEqualToItsIndex(A));
    }

    /**
     * 해결 방법 : 무식한 방법은 배열을 전부 탐색하면서 i번째 원소의 값이 i와 같은지 확인하는 것이다. 이 경우 시간복잡도는 O(n)이다.
     * 주어진 배열이 정렬되어있고 중복된 원소가 없다는 점을 이용하면 이진 탐색으로 O(log n) 안에 해결할 수 있다.
     * 왜냐면 인덱스의 크기가 늘어날 수록 원소값과 인덱스는 적어도 하나씩 증가하기 때문에 A[j] > j라면, j 이후의 원소는 절대 정답이 될 수 없다. (반대도 마찬가지)
     */
    public static int searchEntryEqualToItsIndex(List<Integer> A) {
        int left = 0, right = A.size() - 1;

        while (left <= right) {
            int mid = left + ((right - left) / 2);
            int difference = A.get(mid) - mid;

            if (difference == 0) {
                return mid;
            } else if (difference > 0) {
                right = mid - 1;
            } else { // difference < 0
                left = mid + 1;
            }
        }
     
        return -1;
    }
}
