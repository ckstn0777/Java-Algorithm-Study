package interviews.sort;

import java.util.*;

/**
 * 문제 : 매일 여러 개의 이벤트가 있고 각 이벤트는 시작 시간과 끝나는 시간이 있다고 가정하자.
 * 이벤트 집합이 주어졌을 때, 동시에 발생할 수 있는 이벤트의 최대 개수를 구하는 프로그램을 작성하라.
 */
public class Sort_5 {
    public static void main(String[] args) {
        List<Event> A = Arrays.asList(
            new Event(1, 5),
            new Event(6, 10),
            new Event(11, 13),
            new Event(14, 15),
            new Event(2, 7),
            new Event(8, 9),
            new Event(12, 15),
            new Event(4, 5)
        );

        System.out.println(findMaxSimultaneousEvents(A));
    }

    public static class Event {
        public int start, finish;

        public Event(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    /**
     * 해결방법 : 무식한 방법은 모든 구간마다 확인해보거나 이벤트 시작 혹은 끝나는 시점마다 확인해보는 것이다.
     * 이 경우는 해당 점을 지나는 모든 이벤트의 개수를 세야 하므로 O(n^2)이 된다.
     *
     * 이벤트가 서로 인접해 있다는 특징을 살려서 모든 점을 시간이 증가하는 순서대로 정렬할 수 있다면 수행시간을 개선할 수 있다.
     * 즉, 카운트 변수를 사용해서 각 점을 지나는 이벤트의 개수를 점차적으로 추적해나간다. (크.. 좋았다)
     * 이 경우 시간 복잡도는 정렬시간 + 정렬된 배열 순회 시간 = O(nlogn)이 걸린다. 공간복잡도는 O(n)이다.
     */

    public static class Endpoint implements Comparable<Endpoint> {
        public int time;
        public boolean isStart; // true면 시작, false면 종료 시점

        public Endpoint(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Endpoint e) {
            if (time != e.time) {
                return Integer.compare(time, e.time); // 오름차순으로 정렬
            }
            // 시간이 같다면 시작점이 앞에 오도록 한다.
            return isStart && !e.isStart ? -1 : !isStart && e.isStart ? 1: 0;
        }
    }


    public static int findMaxSimultaneousEvents(List<Event> A) {
        // 모든 점을 포함하는 배열을 만든다.
        List<Endpoint> E = new ArrayList<>();
        for (Event event : A) {
            E.add(new Endpoint(event.start, true));
            E.add(new Endpoint(event.finish, false));
        }

        // 시간순으로 점의 위치 정렬. 점의 위치가 같다면 시작점을 끝점보다 앞에 오도록 한다.
        Collections.sort(E);

        // 동시에 발생하는 이벤트의 개수를 추적한다.
        int maxNumSimultaneousEvents = 0, numSimultaneousEvents = 0;
        for (Endpoint endpoint : E) {
            if (endpoint.isStart) {
                numSimultaneousEvents += 1;
                maxNumSimultaneousEvents
                        = Math.max(numSimultaneousEvents, maxNumSimultaneousEvents);
            } else {
                numSimultaneousEvents -= 1;
            }
        }
        return maxNumSimultaneousEvents;
    }
}
