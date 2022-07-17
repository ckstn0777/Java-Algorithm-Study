package interviews.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 문제 : 배열 A가 주어졌을 때, i번째 위치에서 최대 A[i]만큼 앞을 갈 수 있다고 생각해보자.
 * 이 때, 첫번째 위치에서 시작해 마지막 위치까지 도달 가능한지 여부를 판별하는 것이 목표.
 */
public class Array_4 {
    public static void main(String[] args) {
        //List<Integer> maxAdvanceSteps = new ArrayList<>(Arrays.asList(3, 3, 1, 0, 2, 0, 1)); // true
        List<Integer> maxAdvanceSteps = new ArrayList<>(Arrays.asList(3, 2, 0, 0, 2, 0, 1)); // false

        System.out.println(canReachEnd(maxAdvanceSteps));
    }

    /**
     * 내가 푼 코드 : 정답 코드와 거의 비슷하지만 살짝 아쉽네.
     */
    public static boolean canReachEnd_myCode(List<Integer> maxAdvanceSteps) {
        int maxReach = maxAdvanceSteps.get(0);
        for (int i = 1; i < maxAdvanceSteps.size() && i <= maxReach; i++) {
            maxReach = Math.max(maxReach, i + maxAdvanceSteps.get(i));
        }

        return maxReach >= maxAdvanceSteps.size() - 1;
    }

    /**
     * 해법 : 우선 시도해볼 수 있는 방법은 현재 위치에서 최대한 멀리 나가는 것이지만, 이 방법대로 움직인다면 중간에 더 멀리
     * 나갈 수 있는 위치를 지나칠 수 있기 때문에 해법이 될 수 없다. 결국 최소한 배열 A에 있는 모든 값을 체크해봐야 한다.
     * i 위치에서는 최대 i + A[i]까지 움직일 수 있다는 점을 고려해서 구현하면 된다.
     */
    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int furthestReachSoFar = 0, lastIndex = maxAdvanceSteps.size() - 1;
        for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastIndex; i++) {
            furthestReachSoFar = Math.max(furthestReachSoFar, i + maxAdvanceSteps.get(i));
        }
        return furthestReachSoFar >= lastIndex;
    }
}
