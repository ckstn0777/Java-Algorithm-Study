package interviews.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 문제 : 각 쿼리를 처리하는데 걸리는 시간이 주어진다. 쿼리는 한번에 하나씩만 처리할 수 있고, 각 쿼리를 처리하기 전에 기다리는 시간이 존재한다.
 * 총 대기 시간이 최소화되려면 어떤 순서대로 실행해야 되며 그 결과 총 최소 대기 시간을 반환하면 된다.
 */
public class Greedy_2 {
    public static void main(String[] args) {
        List<Integer> serviceTimes = Arrays.asList(2, 5, 1, 3);
        System.out.println(my_minimumTotalWaitingTime(serviceTimes));
    }


    public static int my_minimumTotalWaitingTime(List<Integer> serviceTimes) {
        Collections.sort(serviceTimes);
        int totalTime = 0, prevTime = 0;
        for (int i = 1; i < serviceTimes.size(); i++) {
            totalTime += (prevTime + serviceTimes.get(i - 1));
            prevTime += serviceTimes.get(i - 1);
        }

        return totalTime;
    }

    /**
     * 해결방법 : 직관적으로 생각해봤을 때 시간이 적게 걸리는 쿼리를 먼저 처리하는게 나아 보인다.
     * 왜냐하면 각 쿼리를 처리하는 데 걸리는 시간은 남아 있는 모든 쿼리의 기다리는 시간에 추가되기 때문이다.
     * 시간 복잡도는 정렬하는데 걸리는 시긴과 같은 O(n log n)이다. 
     */
    public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {
        Collections.sort(serviceTimes);
        int totalWaitingTime = 0;
        for (int i = 0; i < serviceTimes.size(); i++) {
            // 와.. 남아있는 쿼리 개수 * 현재 쿼리 처리 시간으로 더해주는구나.. 좋았다.
            int numRemainingQueries = serviceTimes.size() - (i + 1);
            totalWaitingTime += serviceTimes.get(i) * numRemainingQueries;
        }
        return totalWaitingTime;
    }
}
