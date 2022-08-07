package programmers.search;

/**
 * 문제 : https://school.programmers.co.kr/learn/courses/30/lessons/87946
 */
public class Programmers_87946 {
    public static int answer = 0;
    public static boolean[] visit;

    public static void main(String[] args) {
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solution(80, dungeons));
    }

    /**
     * 해결 방법 : (완전탐색) 순열 -> DFS 탐색
     * 던전 총 수가 최대 8개이므로 순열을 계산해보면 n!/(n-r)!이므로 8! 가 된다. 40320이면 충분히 돌릴 수 있겠지...
     */
    public static void dfs(int depth, int k, int[][] dungeons) {
        for (int i = 0; i < dungeons.length; i++) {
            if (!visit[i] && dungeons[i][0] <= k) { // 던전입장 가능
                visit[i] = true;
                answer = Math.max(answer, depth + 1);
                dfs(depth + 1, k - dungeons[i][1], dungeons);
                visit[i] = false;
            }
        }
    }

    public static int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];
        dfs(0, k, dungeons);
        return answer;
    }
}
