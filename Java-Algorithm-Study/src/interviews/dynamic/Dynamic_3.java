package interviews.dynamic;

/**
 * 문제 : 2차원 배열의 왼쪽 위에서 오른쪽 아래까지 순회할 수 있는 방법을 구하라. 단, 배열에서는 오른족이나 아래로만 움직일 수 있다.
 */
public class Dynamic_3 {

    public static void main(String[] args) {
        System.out.println(numberOfWays(20, 20));
    }


    /**
     * 해결방법 : i > 0이고 j > 0일 때, (i, j)가 도달할 수 있는 방법은 (i - 1, j) 혹은 (i, j - 1)에서 출발하는 방법 뿐이다.
     * 캐시를 사용하지 않고 구현한다면 시간 복잡도가 기하급수적으로 늘어날 것이다. 따라서 (i, j)까지의 경로의 개수를 행렬에 캐시로 저장한다.
     * 시간복잡도와 공간복잡도 모두 O(nm)이다.
     */
    public static int numberOfWays(int n, int m) {
        return computeNumberOfWaysToXY(n - 1, m - 1, new int[n][m]);
    }

    public static int computeNumberOfWaysToXY(int x, int y, int[][] numberOfWays) {
        if (x == 0 || y == 0) { // 탈출조건
            return 1;
        }

        if (numberOfWays[x][y] == 0) { // 캐시에 저장되어있는 경우는 더 이상 볼 필요 없음
            numberOfWays[x][y] = computeNumberOfWaysToXY(x - 1, y, numberOfWays)
                    + computeNumberOfWaysToXY(x, y - 1, numberOfWays);
        }

        return numberOfWays[x][y];
    }
}
