package interviews.search;

/**
 * 문제 : 음이 아닌 정수가 주어졌을 때, 제곱한 값이 주어진 정수보다 작거나 같은 정수 중에서 가장 큰 정수를 찾는 프로그램을 작성
 */
public class Search_4 {
    public static void main(String[] args) {
        System.out.println(squareRoot(300));
    }

    /**
     * 해결방법 : 전형적인 이진 탐색 문제
     */
    public static int squareRoot(int k) {
        int left = 0, right = k;

        while (left <= right) {
            int mid = left + ((right - left) / 2);
            int midSquared = mid * mid;

            if (midSquared <= k) {
                left = mid + 1;
                // answer = left
            } else {
                right = mid - 1;
            }
        }
        return left - 1; // left - 1도 가능하지만, 정 생각안나면 그냥 answer 만들어서 반환해주는게 확실한듯
    }
}
