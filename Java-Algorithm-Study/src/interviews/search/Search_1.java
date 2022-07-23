package interviews.search;

import java.util.Arrays;
import java.util.List;

/**
 * 문제 : 정렬된 배열과 찾고자 하는 키가 주어졌을 대, 해당 키가 첫번째로 등장하는 배열의 인덱스를 찾는 메서드를 작성하라.
 * 찾는 키가 없다면 -1을 반환한다.
 */
public class Search_1 {
    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(-14, -10, 2, 108, 108, 243, 285, 285, 401);
        System.out.println(searchFirstOfK(A, 108));
    }

    /**
     * 해결방법 : 단순하게 이진 탐색을 사용해서 k와 같은 임의의 원소 위치를 찾으면 된다.
     * 그리고 나서 거기서 끝내는게 아니라 좀 더 탐색을 해봐야 한다는게 주의할 점이다.
     */
    public static int searchFirstOfK(List<Integer> A, int k) {
        int left = 0, right = A.size() - 1, result = -1;

        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (A.get(mid) > k) {
                right = mid - 1;
            } else if (A.get(mid) == k) {
                result = mid;
                right = mid - 1; // 아래쪽을 더 탐색해보기 위해.
            } else { // A.get(mid) < k
                left = mid + 1;
            }
        }

        return result;
    }
}
