package interviews.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * 문제 : 피보나치 수열을 구하는 문제를 통해 동적 프로그래밍의 기본 개념을 살펴보자.
 * 피보나치 수열은 F(0) = 0, F(1) = 1 이 주어지며 F(n) = F(n - 1) + F(n - 2) 규칙을 따른다.
 * 그냥 무식하게 재귀적으로 풀면 F(i)를 반복적으로 호출하기 때문에 시간 복잡도는 기하급수적으로 증가한다.
 */
public class Dynamic_Ex_1 {
    public static void main(String[] args) {
        System.out.println(fibonacci_1(10));
        System.out.println(fibonacci_2(10));
    }

    /**
     * 해법 1. 중간 결과를 캐시에 저장해놓는다.
     * 시간복잡도는 n에 선형적으로 비례하지만, 추가적인 공간복잡도는 O(n)이 된다.
     */
    public static int fibonacci_1(int n) {
        Map<Integer, Integer> cache = new HashMap<>();

        if (n <= 1) {
            return n;
        } else if (!cache.containsKey(n)) {
            cache.put(n, fibonacci_1(n - 2) + fibonacci_1(n - 1));
        }

        return cache.get(n);
    }

    /**
     * 해법 2. 상향식 방식으로 캐시를 반복적으로 채워 공간복잡성을 줄이고, 캐시를 재사용할 수 있게 한다.
     * 이 경우 O(n) 시간과 O(1) 공간안에 답을 찾을 수 있다.
     */
    public static int fibonacci_2(int n) {
        if (n <= 1) {
            return n;
        }

        int fMinus2 = 0;
        int fMinus1 = 1;
        for (int i = 2; i <= n; i++) {
            int f = fMinus2 + fMinus1;
            fMinus2 = fMinus1;
            fMinus1 = f;
        }

        return fMinus1;
    }
}
