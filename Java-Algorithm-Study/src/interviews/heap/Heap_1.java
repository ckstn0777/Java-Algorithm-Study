package interviews.heap;

import java.util.*;

/**
 * 문제 : 여러분에게 500개의 파일이 주어져있다. 각 파일에는 회사마다 주식 거래가 시간순으로 정렬되어있다. 이런 파일 500개를 하나로 합쳐서 시간순으로 정렬된
 * 하나의 파일을 만들어야 한다. 좀 더 추상적인 형태로 바꿔보면, 정렬된 시퀀스의 집합이 입력으로 주어졌을 때, 이들을 하나의 정렬된 시퀀스로 합치는 프로그램을 작성하라.
 * 단, 알고리즘 시간복잡도가 최소가 되도록 효율적으로 작성하라.
 */
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
     * 1차 복습(22.08.27) : 복습을 안보고 하려고 했는데 잘 안되서 정답코드를 확인해봤다. 확실히 Iterator를 왜 사용해야 하는지,
     * 그리고 PriorityQueue를 Comparator 재설정하는 법과 사용법 등... 이런거는 외울 필요성을 느꼈다. (코테 때 자동완성은 없잖아ㅠ. 자바로 볼 건 아니지만)
     */
    public static List<Integer> my_mergeSortedArrays(List<List<Integer>> sortedArrays) {
        // ...
        return null;
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
