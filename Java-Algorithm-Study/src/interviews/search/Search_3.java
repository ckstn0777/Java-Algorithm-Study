package interviews.search;

import java.util.Arrays;
import java.util.List;

/**
 * 문제 : 배열을 환형으로 시프트했을 때 정렬된 배열을 만들 수 있다면 해당 배열을 환형으로 정렬되었다고 한다.
 * 환형으로 정렬된 배열이 주어졌을 때 가장 작은 원소의 위치를 찾는 O(lon n) 알고리즘을 설계해보라.
 */
public class Search_3 {
    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(378, 478, 550, 631, 103, 203,220, 234, 279, 368);
        System.out.println(searchSmallest(A));
    }

    /**
     * 해결 방법 : 무식한 방법은 배열을 차례대로 읽으면서 최솟값을 찾는 것이다. 이 방법은 O(n)이 된다.
     * 하지만 잘 생각해보면 배열 A의 특별한 속성(환형으로 정렬된 배열)을 사용하면 이진 탐색 알고리즘을 사용할 수 있다.
     */
    public static int searchSmallest(List<Integer> A) {
        int left = 0, right = A.size() - 1;
        while (left < right) {
            int mid = left + ((right - left) / 2);
            if (A.get(mid) > A.get(right)) {
                // 최솟값은 반드시 mid + 1 ~ right 사이 존재 (당연히 중간에 최솟값이 존재해야 성립되므로)
                left = mid + 1;
            } else { // A.get(mid) < A.get(right)
                // 최솟값은 반드시 mid + 1 ~ right 사이에 있을 수 없다. 왜냐면 환형이기 때문...
                // mid와 right사이에 최솟값이 있게 되는 순간 mid가 right보다 커야 하므로 모순
                right = mid;
            }
        }
        return left; // left == right가 되면 루프 종료
    }
}
