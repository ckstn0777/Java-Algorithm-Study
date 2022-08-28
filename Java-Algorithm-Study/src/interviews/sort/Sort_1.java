package interviews.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 문제 : 검색엔진은 여러 개의 단어가 쿼리로 주어졌을 때 각 단어별로 정렬된 문서 배열을 찾은 뒤 배열 사이의 교집합을 구해서
 * 모든 단어를 포함하는 문서를 찾는다. 여기서 가장 계산 집약적인 단계는 정렬된 배열의 교집합을 찾는 부분이다.
 * 정렬된 배열 두 개가 주어졌을 때, 두 배열에 동시에 존재하는 원소를 새로운 배열 형태로 반환하라.
 */
public class Sort_1 {
    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(2, 3, 3, 5, 5, 6, 7, 7, 8, 12);
        List<Integer> B = Arrays.asList(5, 5, 6, 8, 8, 9, 10, 10);

        System.out.println(intersectTwoSortedArrays(A, B));
    }

    /**
     * 해결방법 : 가장 무식한 방법은 루프 조인이다. 즉, 한 배열의 모든 원소를 순회하면서 다른 배열의 원소와 비교한다. 시간복잡도는 O(mn)이다.
     * 좀 더 최적화하자면, 첫번째 배열을 순회하면서 두번째 배열에서 해당 원소를 찾을 때 이진탐색을 사용해볼 수 있다. O(m log n)이 된다. 하지만 두 배열의 길이가
     * 비슷할 때는 두 배열 모두 정렬되었다는 사실을 사용하지 않았기 때문에 좋은 방법이라 볼 수 없다.
     *
     * 가장 좋은 방법은 입력된 두 배열을 동시에 순서를 증가시키면서 전진시키면 선형 시간에 문제를 풀 수 있다. O(m + n)이 걸린다. ㅇㅇ 쉽네.
     */
    public static List<Integer> intersectTwoSortedArrays(List<Integer> A, List<Integer> B) {
        List<Integer> intersectionAB = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.size() && j < B.size()) {
            // A와 B가 같다면 추가해주는데, 이전에 저장했다면 중복해서 저장되면 안되니까 패스
            if (A.get(i).equals(B.get(j)) && (i == 0 || !A.get(i).equals(A.get(i - 1)))) {
                intersectionAB.add(A.get(i));
                i += 1;
                j += 1;
            } else if (A.get(i) > B.get(j)) {
                j += 1;
            } else { // A.get(i) < B.get(i)
                i += 1;
            }
        }
        return intersectionAB;
    }
}
