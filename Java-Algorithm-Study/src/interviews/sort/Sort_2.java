package interviews.sort;

import java.util.Arrays;
import java.util.List;

/**
 * 문제 : 정수값이 정렬된 배열 두 개가 있다. 그중 하나의 배열은 배열 뒤에 충분히 많은 공간이 있어서 두 배열을 정렬된 순서로 합쳐서 저장하는데 사용할 수 있다.
 * 즉, 첫번째 배열의 끝에는 두번째 배열을 모두 넣을 수 있을만큼 충분한 공간이 있다고 가정해도 좋다. 이때, 첫번째 배열에다가 두 배열을 정렬된 순서로 합쳐 반환하도록 해라.
 */
public class Sort_2 {
    public static void main(String[] args) {
        int m = 3, n = 4;
        List<Integer> A = Arrays.asList(5, 13, 17, null, null, null, null, null); // 빈공간을 null로 생각하자
        List<Integer> B = Arrays.asList(3, 7, 11, 19);

        mergeTwoSortedArrays(A, m, B, n);
        System.out.println(A);
    }

    /**
     * 해결방법 : 이 문제에서는 첫번째 배열에 결과값을 쓰는 부분이 까다롭다. 무작정 첫번째 배열에 결과를 저장한다면, 두번째 배열의 값이 첫번째 배열 값보다 작을때,
     * 첫번째 배열의 모든 값을 계속해서 오른쪽으로 한칸씩 옮겨줘야 하는 문제가 있다. 이때 시간복잡도는 O(mn)이 된다.
     * 하지만 조금만 생각한다면, 배열 뒤쪽은 비어져있기 때문에 뒤에서부터 처리한다면 아직 처리하지 않은 원소 위에 덮어쓰는 일은 절대 없다.
     */
    public static void mergeTwoSortedArrays(List<Integer> A, int m, List<Integer> B, int n) {
        int a = m - 1, b = n - 1, writeIdx = m + n - 1;

        while (a >=0 && b >=0) {
            A.set(writeIdx, A.get(a) > B.get(b) ? A.get(a--) : B.get(b--)); // 이럴때 후위연산 쓰면 진짜 좋네.
            writeIdx -= 1;
        }

        // while (a >=0)은 고려하지 않음. 왜냐면 A 에다가 저장하는 것이기도 하고, 이미 정렬된 상태니까.
        while (b >= 0) {
            A.set(writeIdx, B.get(b--));
            writeIdx -= 1;
        }
    }
}
