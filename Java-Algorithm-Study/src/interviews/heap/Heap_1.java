package interviews.heap;

import java.util.*;

public class Heap_1 {
    public static void main(String[] args) {
        List<List<Integer>> sortedArrays = new ArrayList<>();
        List<Integer> array1 = Arrays.asList(3, 5, 7);
        List<Integer> array2 = Arrays.asList(0, 6);
        List<Integer> array3 = Arrays.asList(0, 6, 28);

        sortedArrays.add(array1);
        sortedArrays.add(array2);
        sortedArrays.add(array3);

        System.out.println(mergeSortedArrays(sortedArrays));
    }

    /**
     * 값과 배열 인덱스를 저장하기 위한 클래스를 만들어준다.
     */
    public static class ArrayEntry {
        public Integer value;
        public Integer arrayId;

        public ArrayEntry(Integer value, Integer arrayId) {
            this.value = value;
            this.arrayId = arrayId;
        }
    }

    /**
     * 해결방법 : 무식한 방법을 사용하면 무작정 힙에다가 add 한 다음에 결과만 넘겨주면 된다.
     * 하지만 이럴 경우 전체 원소의 개수가 n개일 때 시간 복잡도는 O(n log n)이 된다.
     *
     * 더 좋은 방법은 각각의 시퀀스가 정렬되어있다는 사실을 이용하는 것이다. 즉, 각 시퀀스에서 첫번재 원소를 뽑아낸 뒤 이들 중에서
     * 가장 작은 원소를 골라내는 과정을 반복하면 된다. k가 입력으로 주어진 시퀀스의 개수라고 하면, 최소 힙은 항상 k 만큼의 크기를 가진다.
     * 또한, extract-min 연산과 insert 연산은 모두 O(log k)시간이 걸리므로, 총 O(n log k) 만큼의 시간복잡도를 가진다.
     */
    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());
        for (List<Integer> array : sortedArrays) {
            iters.add(array.iterator());
        }

        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(
                sortedArrays.size(), new Comparator<ArrayEntry>() {
            @Override
            public int compare(ArrayEntry o1, ArrayEntry o2) {
                // o1 < o2 : -1 (o1이 먼저 나옴) // o1 == 02 : 0 // o1 > o2 : 1 (o2가 먼저 나옴)
                // 즉, 최소 힙을 만들 수 있다.
                return Integer.compare(o1.value, o2.value);
            }
        });

        // 배열마다 값을 하나씩 뽑아서 최소힙을 초기화 한다.
        for (int i = 0; i < iters.size(); i++) {
            if (iters.get(i).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(i).next(), i));
            }
        }

        // 이제 최소힙에서 하나씩 뽑아서 결과 리스트에 저장하고,
        // arrayId를 보고 배열에서 값을 뽑아서 다시 최소힙에 넣는 과정을 반복 하면 된다.
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ArrayEntry headEntry = minHeap.poll(); // 가장 최소 값을 뽑아온다.
            result.add(headEntry.value);
            if (iters.get(headEntry.arrayId).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(headEntry.arrayId).next(),
                        headEntry.arrayId));
            }
        }

        return result;
    }
}
