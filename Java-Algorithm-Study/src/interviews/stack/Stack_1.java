package interviews.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 문제 : push와 pop 외에 max 연산을 제공하는 스택 클래스를 설계하라. max() 메서드는 스택에 저장된 원소 중에서 가장 값이 큰 원소를 반환한다.
 * 힌트 - 최대값을 기억하기 위해 공간을 추가로 사용해보자.
 */
public class Stack_1 {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(2);
        stack.push(4);
        stack.push(3);
        stack.push(5);
        System.out.println(stack.max());

        stack.pop();
        System.out.println(stack.max());
    }

    /**
     * 해결 방법 : 가장 간단한 방법으로 스택을 배열로 구현했다면 가장 값이 큰 원소를 순회하면서 찾을 수 있다. 시간복잡도는 O(n)이며, 공간복잡도는 O(1)이다.
     * 힙, BST, 해시 테이블과 같은 자료구조를 사용하면 시간복잡도는 O(logn)으로 줄일 수 있지만 공간복잡도가 O(n)으로 늘어나며 코드 또한 복잡해진다.
     *
     * 스택의 최댓값을 기록하기 위해 추가로 M이라는 변수 하나를 사용한다고 했을때, 원소를 삽입할 때마다 M을 갱신하는건 쉽다. 다만, pop연산시 M을 갱신하려고 하면
     * 다시 남아있는 원소를 모두 순회해야 하므로 시간이 꽤 걸린다. 이를 잔머리를 좀 굴려서 스택의 모든 원소에 대해 현재 원소 아래에 있는 원소들 중에서 최댓값이 무엇인지
     * 캐싱하면 된다.
     */
    private static class ElementWithCachedMax {
        public Integer element;
        public Integer max;

        public ElementWithCachedMax(Integer element, Integer max) {
            this.element = element;
            this.max = max;
        }
    }

    /**
     * 시간복잡도는 O(1), 공간복잡도는 O(n)이다. 하지만 여기서 공간복잡도는 더 줄일 수 있다. (아래 참고)
     */
    public static class Stack {
        // (원소, 최댓값) 쌍을 저장한다.
        private Deque<ElementWithCachedMax> elementWithCachedMax = new LinkedList<>();

        public boolean empty() { return elementWithCachedMax.isEmpty(); }

        public Integer max() {
            if (empty()) {
                throw new IllegalStateException("max(): empty stack");
            }
            return elementWithCachedMax.peek().max;
        }

        public Integer pop() {
            if (empty()) {
                throw new IllegalStateException("pop(): empty stack");
            }
            return elementWithCachedMax.removeFirst().element;
        }

        public void push(Integer x) {
            elementWithCachedMax.addFirst(new ElementWithCachedMax(x, Math.max(x, empty() ? x : max())));
        }
    }

    /**
     * 입력에 따라 필요한 공간을 줄일 수 있다. 만약 새로 추가되는 원소 e가 기존의 최대값보다 작다면 e는 절대 최댓값이 될 수 없으므로
     * 캐시에 기록할 필요가 없다. 단, 중복은 주의해야하므로 각 최댓값이 몇 번 등장했는지 기록해 중복을 제거한다...
     */
}
