package interviews.heap;

import java.util.*;

/**
 * 문제 : 지구가 (0, 0, 0)의 위치에 있는 은하계를 상상해보자. 은하계에는 대략 10^12개의 별이 존재하고 별들의 위치가 파일에 저장되어있다고 하자.
 * 지구와 가장 가까운 별 k개를 찾으려면 어떻게 해야 할까?
 */
public class Heap_4 {


    public static void main(String[] args) {
        Star star1 = new Star(2, 2, 2);
        Star star2 = new Star(4, 4, 4);
        Star star3 = new Star(1, 1, 1);
        Star star4 = new Star(5, 5, 5);

        List<Star> stars = Arrays.asList(star1, star2, star3, star4);

        System.out.println(findClosestKStars(stars.iterator(), 2));
    }

    public static class Star implements Comparable<Star> {
        private double x, y, z;

        public Star(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        // (0, 0, 0)으로부터의 거리 계산
        public double distance() { return Math.sqrt(x * x + y * y + z * z); }

        @Override
        public int compareTo(Star o) {
            return Double.compare(this.distance(), o.distance());
        }

        @Override
        public String toString() {
            return "Star{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }
    }

    /**
     * 해결방법 : 만약 램의 크기에 제한이 없다면 모든 자료를 배열에 넣은 다음, 정렬을 해서 k개의 원소를 찾으면 된다.
     * 하지만, 현실적으로 데이터의 개수가 굉장히 많은 경우에는 램에 모두 저장할 수 없다는 문제가 있다. (중요!)
     *
     * 직관적으로 생각해보면 첫 k개의 별을 최대힙에 삽입한다음, 이후 새로운 별을 하나씩 살펴보면서 갱신해나가면 된다.
     * 시간복잡도는 O(n log k)이고, 공간복잡도는 O(k)이다.
     */
    public static List<Star> findClosestKStars(Iterator<Star> stars, int k) {
        // 현재까지 중에서 가장 가까운 별 k개를 저장할 maxHeap
        PriorityQueue<Star> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        while (stars.hasNext()) {
            // 각각의 별을 최대힙에 추가한다. 만약 최대힙의 크기가 k보다 커지면, 최대힙에서 최대 원소를 삭제한다.
            Star star = stars.next();
            maxHeap.add(star);

            if (maxHeap.size() == k + 1) {
                maxHeap.remove(); // 아 그래서 최대힙이구나. 최대값을 내보내야하니까...
            }
        }

        List<Star> orderedStars = new ArrayList<Star>(maxHeap);
        // PriorityQueue는 최대 원소가 가장 앞에 온다는 사실을 보장해 줄 뿐 나머지 원소들의 순서는 보장되지 않는다.
        Collections.sort(orderedStars);
        return orderedStars;
    }
}
