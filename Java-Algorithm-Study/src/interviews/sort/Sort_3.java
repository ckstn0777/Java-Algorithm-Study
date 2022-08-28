package interviews.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 문제 : 배열에서 성을 제외한 중복된 이름을 삭제하는 효율적인 알고리즘을 설계하라. 미국식이라서 (이름, 성) 순이다.
 */
public class Sort_3 {
    public static void main(String[] args) {
        List<Name> names = new ArrayList<>(Arrays.asList(
            new Name("Ian", "Botham"),
            new Name("David", "Gower"),
            new Name("Ian", "Bell"),
            new Name("Ian", "Chappell")
        ));

        eliminateDuplicate(names);
        System.out.println(names);
    }

    public static class Name implements Comparable<Name> {
        private String firstName;
        private String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public int compareTo(Name name) {
            int cmpFirst = firstName.compareTo(name.firstName);
            if (cmpFirst != 0) {
                return cmpFirst; // 오름차순 정렬
            }
            return lastName.compareTo(name.lastName); // 이름이 같다면 성으로 오름차순 정렬
        }

        @Override
        public String toString() {
            return "Name{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

    /**
     * 해결방법 : 무식한 방법은 해시 테이블을 사용하는 것이다. 해시 테이블에 이름을 넣은 뒤 차례대로 순회하면서 결과 배열에 작성하면 된다.
     * 이 때, 시간복잡도는 O(n)이지만 추가 공간을 사용해야 한다.
     *
     * 추천하는 방법으로 입력 배열에 결과를 작성한다면 추가 공간을 사용하지 않아도 된다. 다만 시간복잡도는 정렬로 인해서 O(n log n)이 된다. 대신 공간복잡도는 O(1)이다.
     */
    public static void eliminateDuplicate(List<Name> A) {
        Collections.sort(A); // 정렬부터 해준다.

        // 중복 제거. writeIdx는 저장시킬 위치를 가리키는 포인터네.
        int writeIdx = 0;
        for (int i = 1; i < A.size(); i++) {
            if (!A.get(i).firstName.equals(A.get(writeIdx).firstName)) {
                writeIdx += 1;
                A.set(writeIdx, A.get(i));
            }
        }

        // 배열 크기를 줄인다. (아.. subList 한 다음에 clear 하면 해당 부분 배열이 원본 배열에서 날아가는구나...)
        A.subList(writeIdx + 1, A.size()).clear(); // 불필요한 부분 제거
    }
}
