package interviews.greedy;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 문제 : 어떤 애플리케이션에서는 주어진 수열에서 일정 비율보다 많이 등장하는 원소를 찾아야 하는 경우가 발생한다.
 * 예를 들어, 네트워크 대역폭을 과도하게 사용하거나 HTTP 요청을 가장 많이 한 사용자를 찾아내고 싶은 경우.
 * 이를 간단하게 표현해서 문제로 만들어보면, 어떤 문자열이 주어지고, 특정 문자가 문자열의 절반 이상을 차지(=다수 원소라고 부른다)한다고 했을때 이 문자를 찾아서 반환하라.
 */
public class Greedy_5 {
    public static void main(String[] args) {
        Iterator<String> inputStream = Arrays.asList("b", "a", "c", "a", "a", "b", "a", "c").iterator();
        System.out.println(majoritySearch(inputStream));
    }

    /**
     * 해결 방법 : 간단하게 생각할 수 있는 방법은 해시테이블을 이용하는 것이다. 이 경우는 시간복잡도와 공간복잡도 모두 O(n)이다.
     * 더 나은 알고리즘을 생각해보면 다수 원소 후보자를 첫 번째 엔트리로 초기화한다. 남아있는 엔트리를 순회하면서
     * 현재 후보자와 같은 엔트리를 발견하면 그 개수를 증가시키고, 아니라면 개수를 감소시킨다. 카운트된 개수가 0이 되면 후보자를 그 다음 엔트리로 바꾼다.
     * 시간복잡도는 O(n), 공간복잡도는 O(1)로 해결할 수 있다. 하지만 해당 코드는 다수 원소가 언제나 존재한다는 가정하에 동작한다. (!!)
     */
    public static String majoritySearch(Iterator<String> inputStream) {
        String candidate = "";
        int candidateCount = 0;

        while (inputStream.hasNext()) {
            String it = inputStream.next();
            if (candidateCount == 0) {
                candidate = it;
                candidateCount = 1;
            } else if (candidate.equals(it)) {
                candidateCount += 1;
            } else {
                candidateCount -= 1;
                if (candidateCount == 0) candidate = it; // 책에 없지만 임의 추가함. 왜냐면 ("b", "a", "c", "a")인 경우 "a"가 되어야 하므로...
            }
        }

        System.out.println(candidateCount);
        return candidate;
    }
}
