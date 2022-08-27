package interviews.heap;

import java.util.*;

/**
 * 문제 : 문제에서는 k와 k-정렬된 배열이 주어진다. 여기서 말하는 k-정렬된 배열은 예를 들어, (3, -1, 2, 6, 4, 5, 8)은 원래
 * 정렬된 배열과 비교했을때, 최대 2만큼 떨어져있는 상태이다. 따라서 이를 2-정렬된 배열이라고 부른다. 이를 효율적으로 정렬하는 프로그램을 작성하라.
 */
public class Heap_3 {
    public static void main(String[] args) {
        List<Integer> sequence = Arrays.asList(3, -1, 2, 6, 4, 5, 8);
        System.out.println(sortApproximateSortedData(sequence.iterator(), 2));
    }

    /**
     * 해결방법 : 1번 문제 풀고 나서 푸니까 쉽군. 어쨌거나 k-정렬된 배열이므로 k 만큼의 최소힙을 만들고 사용하면 그냥 정렬하는 것보다 효율적으로 처리할 수 있다.
     * 시간복잡도는 O(n log k)이고, 공간복잡도는 O(k)이다. 
     */
    public static List<Integer> sortApproximateSortedData(Iterator<Integer> sequence, int k) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // k 만큼 원소를 minHeap에 추가한다. (단, 다음 sequence가 있다면)
        for (int i = 0; i < k && sequence.hasNext(); i++) {
            minHeap.add(sequence.next());
        }

        // 새로운 원소가 들어오면, minHeap에 추가한 뒤 최솟값을 뽑아낸다
        while (sequence.hasNext()) {
            minHeap.add(sequence.next());
            Integer smallest = minHeap.remove(); // poll과 다른점은 값이 더 이상없다면 예외 발생
            result.add(smallest);
        }

        // 원소를 모두 읽었다면, 남아 있는 원소를 뽑아낸다
        while (!minHeap.isEmpty()) {
            Integer smallest = minHeap.remove();
            result.add(smallest);
        }

        return result;
    }
}
