package interviews.linkedlist;

/**
 * 문제 : 두 개의 연결 리스트가 주어졌을 때, 이를 하나로 합쳐서 반환할 것. 대신 오름차순으로.
 * 또한, 우리가 수정할 수 있는 것은 다음 노드를 가리키는 next 뿐이다.
 */



public class LinkedList_1 {

    public static void main(String[] args) {
        // LinkedList 라이브러리를 사용하지 않는 방식으로 구현
        ListNode<Integer> L1 = new ListNode<>(2, new ListNode<>(5, new ListNode<>(7)));
        ListNode<Integer> L2 = new ListNode<>(3, new ListNode<>(11));

        ListNode<Integer> result = mergeTwoSortedLists(L1, L2);
        while (result != null) {
            System.out.println(result.data);
            result = result.next;
        }
    }


    public static ListNode<Integer> my_mergeTwoSortedLists(ListNode<Integer> L1, ListNode<Integer> L2) {
        // TODO : 나중에 해보기
        return null;
    }


    /**
     * 해결방법 : 두개의 리스트를 앞에서부터 확인하면서 작은 키를 가지고 있는 노드를 선택해 나가면 된다.
     * 시간복잡도 측면에서 최악의 경우는 리스트의 길이와 같고 따라서 O(n + m)이 된다.
     */
    public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1, ListNode<Integer> L2) {
        // 결과를 저장할 변수를 생성한다.
        ListNode<Integer> dummyHead = new ListNode<>(0,null);
        ListNode<Integer> current = dummyHead; // dummyHead를 그냥쓰면 안되고 current라는 포인터를 따로 만들어야 됨.
        ListNode<Integer> p1 = L1, p2 = L2; // 이렇게 p1, p2를 따로 만드는 이유는 L1, L2 에 영향이 없도록..

        while(p1 != null && p2 != null) {
            if (p1.data <= p2.data) {
                current.next = p1;
                p1 = p1.next;
            } else {
                current.next = p2;
                p2 = p2.next;
            }
            current = current.next;
        }

        // p1 혹은 p2에 남은 노드를 덧붙인다.
        current.next = p1 != null ? p1 : p2;
        return dummyHead.next;
    }
}
