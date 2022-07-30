package interviews.dynamic;

import java.util.Arrays;
import java.util.List;

/**
 * 문제 : 최종 점수와 각 게임에서 낼 수 있는 점수가 주어졌을 때, 주어진 최종 점수를 만들 수 있는 조합의 개수를 구하라
 */
public class Dynamic_1 {
    public static void main(String[] args) {
        int finalScore = 12;
        List<Integer> individualPlayScores = Arrays.asList(2, 3, 7);

        System.out.println(numCombinationsForFinalScore(finalScore, individualPlayScores));
    }

    /**
     * 해결 방법 : 무식한 방법으로 모든 가능한 점수의 수열을 모두 나열한 뒤에 서로 다른 조합의 수열의 개수를 세는 방법
     * 하지만 이 방법은 수열의 개수가 굉장히 많을 것이므로 시간복잡도가 굉장히 높다. 우리가 원하는건 단지 조합의 개수이다.
     *
     * 간단하게 각 경기에 2점 혹은 3점을 낼 수 있고 총 6점을 만드는 조합의 수를 계산한다고 해보자.
     * 이는 곰곰히 생각해보면 (2점으로 6점을 만드는 방법) + (2, 3점으로 3점을 만드는 방법) 이 된다.
     * A[1][6] = A[0][6] + A[1][3] 이 되는데 이를 일반화 하면 A[i][j] = A[i - 1][j] + A[i][j - 추가된 점수(3)]
     */
    public static int numCombinationsForFinalScore(int finalScore,
                                                   List<Integer> individualPlayScores) {
        int[][] newCombinationsForScore = new int[individualPlayScores.size()][finalScore + 1];
        for (int i = 0; i < individualPlayScores.size(); i++) {
            newCombinationsForScore[i][0] = 1; // 0점이 될 수 있는 방법의 개수는 기본 1
            for (int j = 1; j <= finalScore; j++) {
                int withoutThisPlay = i - 1 >= 0 ? newCombinationsForScore[i - 1][j] : 0;
                int withThisPlay = j >= individualPlayScores.get(i)
                        ? newCombinationsForScore[i][j - individualPlayScores.get(i)]
                        : 0;

                newCombinationsForScore[i][j] = withoutThisPlay + withThisPlay;
            }
        }
        return newCombinationsForScore[individualPlayScores.size() - 1][finalScore];
    }
}
