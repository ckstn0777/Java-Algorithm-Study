package interviews.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 문제 : 닫힌 구간의 집합이 주어졌을 때, 가장 적은 숫자로 모든 구간을 커버할 수 있는 알고리즘 설계하라
 */
public class Greedy_3 {
    public static class Interval {
        public int left, right;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(0, 3));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 4));
        intervals.add(new Interval(6, 9));

        System.out.println(findMinimumVisits(intervals));
    }

    /**
     * 해결방법 : 가장 많은 구간을 지나치는 지점을 그리디하게 선택하면 될까? 그렇지 않다. 예를 들어, 가운데 부분이 가장 많다고 해도
     * 양 옆 사이드가 포함되어있지 않다면 이건 최적이 될 수 없다. 양 옆 사이드 2개를 선택하면 그게 최적일 수도 있으므로.
     *
     * 곰곰히 생각해보면 구간의 오른쪽 끝점의 위치가 가장 작은 것에 집중해야 한다는 것을 알 수 있다. 이 녀석은 반드시 포함되어야 하기 때문이다.
     * 따라서 모든 구간을 오른쪽 끝 지점을 기준으로 정렬하고 이를 순회하면서 커버되지 않은 구간을 발견하는 순간 커버하게 해주면 되는 것이다.
     * 전체 시간 복잡도는 정렬을 하는데 필요한 O(n log n)과 같다.
     */
    public static int findMinimumVisits(List<Interval> intervals) {
        // 오른쪽 끝점을 기준으로 정렬
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.right, o2.right);
            }
        });

        int lastVisitTime = Integer.MIN_VALUE;
        int numVisits = 0;
        for (Interval interval : intervals) {
            // 다음 left가 현재 오른쪽 끝점(lastVisitTime)까지 커버가 가능한지 여부 판단.
            if (interval.left > lastVisitTime) {
                lastVisitTime = interval.right;
                numVisits += 1;
            }
        }
        return numVisits;
    }
}
