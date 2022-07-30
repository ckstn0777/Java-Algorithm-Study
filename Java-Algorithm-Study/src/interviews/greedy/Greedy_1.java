package interviews.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 문제 : 각 노동자는 정확히 두 개의 업무를 할당 받아야 한다. 각 업무를 완료하는데 걸리는 시간은 고정되어 있으며, 각 업무는 서로 독립적이다.
 * 모든 업무를 완료하는데 걸리는 시간이 최소가 되도록 노동자에게 업무를 할당하면 된다.
 */
public class Greedy_1 {
    public static void main(String[] args) {
        List<Integer> taskDurations = Arrays.asList(5, 4, 1, 6, 2, 4);
        System.out.println(optimumTaskAssignment(taskDurations));
    }

    private static class PairedTasks {
        public Integer task1;
        public Integer task2;

        public PairedTasks(Integer task1, Integer task2) {
            this.task1 = task1;
            this.task2 = task2;
        }

        @Override
        public String toString() {
            return "PairedTasks{" +
                    "task1=" + task1 +
                    ", task2=" + task2 +
                    '}';
        }
    }

    /**
     * 해결 방법 : 가장 오래 걸리는 업무를 가장 빨리 끝낼 수 있는 업무와 쌍으로 묶는 것이 타당해보인다.
     * 물론 가장 오래 걸리는 업무와 가장 짧게 걸리는 업무의 합이 늘 최적이 되는 것은 아니다.
     * ex) 1, 8, 9, 10인 경우 모든 업무를 마치는 데 걸리는 시간은 1 + 10이 아닌 8 + 9이다.
     *
     * 뭐, 어쨌거나 결론적으로 업무를 걸리는 시간으로 정렬한 뒤, 가장 짧게 걸리는 업무와 가장 오래 걸리는 업무끼리 차례대로 쌍으로 묶어주면 된다.
     * 시간 복잡도는 정렬하는데 걸리는 시간인 O(n log n)과 같다.
     */
    public static List<PairedTasks> optimumTaskAssignment(List<Integer> taskDurations) {
        Collections.sort(taskDurations);
        List<PairedTasks> optimumAssignments = new ArrayList<>();
        for (int i = 0, j = taskDurations.size() - 1; i < j; i++, j--) {
            optimumAssignments.add(new PairedTasks(taskDurations.get(i), taskDurations.get(j)));
        }
        return optimumAssignments;
    }
}
