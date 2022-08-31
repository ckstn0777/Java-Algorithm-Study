package interviews.dynamic;

import java.util.Arrays;

/**
 * 문제 : 철자가 틀린 문자열이 주어졌을 때, 맞춤법 검사기는 사전에서 해당 문자열과 가장 가까운 단어를 반환한다.
 * 이처럼 올바른 단어로 변환하는데 필요한 최소 편집 횟수를 구하라. 여기서 편집이란 문자의 삽입, 삭제, 치환을 말한다.
 */
public class Dynamic_2 {
    public static void main(String[] args) {
        String A = "Saturday";
        String B = "Sundays";

        System.out.println(levenshteinDistance(A, B));
    }

    /**
     * 해결방법 : A의 마지막 문자와 B의 마지막 문자가 같은지 아닌지를 나눠서 생각해보자.
     * i) 같은 경우 : E(A[0, a-1], B[0, b-1]) = E(A[0, a-2], B[0, b-2])  ㅇㅋ?
     * ii) 같지 않은 경우 : E(A[0, a-1], B[0, b-1]) = 1 + min(치환, 추가, 삭제)
     *  - 치환 : E(A[0, a-2], B[0, b-2]) // A의 마지막 문자를 B의 마지막 문자로 치환하면 되므로
     *  - 추가 : E(A[0, a-2], B[0, b-1]) // B의 마지막 문자를 A의 맨 뒤에 추가
     *  - 삭제 : E(A[0, a-1], B[0, b-2]) // A의 마지막 문자를 삭제
     * 이렇게 해서 2차원 배열을 만들고 하나씩 채워나가면 다이나믹 프로그래밍으로 해결할 수 있다.
     */
    public static int levenshteinDistance(String A, String B) {
        int[][] distanceBetweenPrefixes = new int[A.length()][B.length()];
        for (int[] row : distanceBetweenPrefixes) {
            Arrays.fill(row, -1);
        }
        return computeDistanceBetweenPrefixes(A, A.length() - 1, B, B.length() - 1,
                distanceBetweenPrefixes);
    }

    private static int computeDistanceBetweenPrefixes(
            String A, int A_idx, String B, int B_idx,
            int[][] distanceBetweenPrefixes) {
        if (A_idx < 0) { // A가 비어있으므로 B의 모든 문자를 추가
            return B_idx + 1;
        } else if (B_idx < 0) { // B가 비어있으므로 A의 모든 문자를 삭제
            return A_idx + 1;
        }

        if (distanceBetweenPrefixes[A_idx][B_idx] == -1) {
            if (A.charAt(A_idx) == B.charAt(B_idx)) {
                distanceBetweenPrefixes[A_idx][B_idx] =
                        computeDistanceBetweenPrefixes(A, A_idx - 1, B, B_idx - 1, distanceBetweenPrefixes);
            } else {
                int substituteLast = computeDistanceBetweenPrefixes(A, A_idx - 1, B, B_idx - 1, distanceBetweenPrefixes);
                int addLast = computeDistanceBetweenPrefixes(A, A_idx - 1, B, B_idx, distanceBetweenPrefixes);
                int deleteLast = computeDistanceBetweenPrefixes(A, A_idx, B, B_idx - 1, distanceBetweenPrefixes);

                distanceBetweenPrefixes[A_idx][B_idx] = 1 + Math.min(substituteLast, Math.min(addLast, deleteLast));
            }
        }

        return distanceBetweenPrefixes[A_idx][B_idx];
    }
}
