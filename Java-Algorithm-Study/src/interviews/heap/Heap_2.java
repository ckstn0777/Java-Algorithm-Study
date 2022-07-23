package interviews.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 문제 : 배열의 원소가 특정 인덱스까지 증가했다가 감소하고 다시 증가하는 과정이 k번 반복된다면
 * 이 배열을 k-증가-감소라 한다. 이러한 배열을 정렬하는 효율적인 알고리즘을 설계해라.
 */
public class Heap_2 {
    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(57, 131, 493, 294, 221, 339, 418, 452, 442, 190);
        System.out.println(sortKIncreasingDecreasingArray(A));
    }

    /**
     * 역시 무식한 방법으로는 부분적이나마 정렬되어 있다는 사실을 무시한 채 배열을 정렬하면 된다. 이 경우 배열의 길이가 n이면
     * 시간복잡도는 O(n log n)이 된다. 만약 k가 n보다 굉장히 작다면 개선할 여지가 있다.
     * 입력 배열은 결국 증가하는 부분배열과 감소하는 부분배열로 나눌 수 있고, 감소하는 부분배열을 뒤집어서 증가하는 부분배열로 만들어준다.
     * 그리고 힙 문제 1번처럼 풀어주면 끝이다. 시간복잡도는 문제 1과 마찬가지로 O(n log k)가 된다.
     */
    public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
        List<List<Integer>> sortedSubarrays = new ArrayList<>();
        SubarrayType subarrayType = SubarrayType.INCREASING;

        int startIdx = 0;
        for (int i = 1; i <= A.size(); i++) {
            if (i == A.size() // A가 끝났다면 마지막 부분배열을 추가
                || (A.get(i - 1) < A.get(i) && subarrayType == SubarrayType.DECREASING)
                || (A.get(i - 1) >= A.get(i) && subarrayType == SubarrayType.INCREASING)
            ) {
                List<Integer> subList = A.subList(startIdx, i); // 실제 반환 : startIdx ~ (i-1)
                if (subarrayType == SubarrayType.DECREASING) {
                    Collections.reverse(subList);
                }
                sortedSubarrays.add(subList);
                startIdx = i;
                subarrayType = (subarrayType == SubarrayType.INCREASING
                                    ? SubarrayType.DECREASING
                                    : SubarrayType.INCREASING);
            }
        }

        return Heap_1.mergeSortedArrays(sortedSubarrays);
    }

    private static enum SubarrayType { INCREASING, DECREASING }

}
