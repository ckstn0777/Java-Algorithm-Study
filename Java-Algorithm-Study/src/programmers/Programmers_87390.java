package programmers;

import java.util.*;

/**
 * 문제 : https://school.programmers.co.kr/learn/courses/30/lessons/87390
 */
public class Programmers_87390 {
    public static void main(String[] args) {
        System.out.println(solution(3, 2, 5));
    }

    /**
     * 해결방법 : 규칙만 잘 파악하면 된다. Math.max(행, 열) 이 결국 정답
     */
    public static List<Long> solution(int n, long left, long right) {
        List<Long> answer = new ArrayList<>();

        for (long i = left; i <= right; i++) {
            answer.add(Math.max(i / n, i % n) + 1);
        }

        return answer;
    }
}
