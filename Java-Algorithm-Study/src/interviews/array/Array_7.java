package interviews.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 문제 : 주식 한 주를 최대 두 번까지 매매할 수 있을 때, 최대 이윤을 구하는 프로그램을 작성하라.
 * 단, 두 번째 주식은 첫 번째 주식을 판 뒤에 구입할 수 있다.
 */
public class Array_7 {
    public static void main(String[] args) {
        List<Double> prices = Arrays.asList(12.0, 11.0, 13.0, 9.0, 12.0, 8.0, 14.0, 13.0, 15.0);
        System.out.println(buyAndSellStockTwice(prices));
    }

    /**
     * 해결 방법 : 첫 번째 주식을 매도한 뒤에 구입해야 하므로 배열 A를 두 부분 배열로 나누어 생각해볼 수 있다.
     * 그리고 문제 6번에 O(n) 알고리즘을 두 배열에 적용하면 결국 시간 복잡도를 O(n^2)으로 해결 가능하다.
     *
     * 여기서 권장하는 방식은 A[0, j]의 최대 이익값과 A[j, n-1]의 최대 이익값을 기록해 놓는 것이다. (j는 1과 n-1 사이의 값)
     * 그리고 현재 이전에 얻은 최대 이익과 현재 이후에 얻은 최대 이익의 합을 더해 최대 이익을 구할 수 있다. M[i] = F[i - 1] + B[i]
     * 시간복잡도는 O(n), 공간복잡도 또한 O(n)으로 해결가능하다. (오우.. 이런 방법이...)
     */
    public static double buyAndSellStockTwice(List<Double> prices) {
        double maxTotalProfit = 0.0;
        List<Double> firstBuySellProfits = new ArrayList<>();

        // 앞으로 읽는 부분
        // 각 날짜마다, 해당 날짜에 주식을 팔았을 때의 최대 이익 값을 구해 놓는다.
        double minPriceSoFar = Double.MAX_VALUE;
        for (int i = 0; i < prices.size(); i++) {
            minPriceSoFar = Math.min(minPriceSoFar, prices.get(i));
            maxTotalProfit = Math.max(maxTotalProfit, prices.get(i) - minPriceSoFar);
            firstBuySellProfits.add(maxTotalProfit);
        }

        // 뒤로 읽는 부분
        // 각 날짜마다, 두 번째 주식을 해당 날짜에 쌋을 때의 최대 이익 값을 구해 놓는다
        double maxPriceSoFar = Double.MIN_VALUE;
        for (int i = prices.size() - 1; i > 0; i--) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
            maxTotalProfit = Math.max(maxTotalProfit,
                    maxPriceSoFar - prices.get(i) + firstBuySellProfits.get(i - 1));
        }
        return maxTotalProfit;
    }
}
