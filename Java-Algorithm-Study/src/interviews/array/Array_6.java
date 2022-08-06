package interviews.array;

import java.util.Arrays;
import java.util.List;

/**
 * 문제 : 시간에 따른 주식 가격 정보가 들어 있는 배열이 주어졌을 때, 한 주를 한 번 사고팔아서 남길 수 있는 최대 이익을 구해보자
 */
public class Array_6 {
    public static void main(String[] args) {
        List<Double> prices = Arrays.asList(310.0, 315.0, 275.0, 295.0, 260.0, 270.0, 290.0, 230.0, 255.0, 250.0);
        System.out.println(computeMaxProfit(prices));
    }

    /* 나중에 풀 때는 코드를 좀 줄여보도록 하자... */
    public static double my_computeMaxProfit(List<Double> prices) {
        double min = prices.get(0), max = 0;
        double result = 0;
        for (int i = 1; i < prices.size() - 1; i++) {
            if (prices.get(i - 1) <= prices.get(i)) {
                max = prices.get(i);
                result = Math.max(result, (max - min));
            } else {
                min = prices.get(i);
                max = 0;
            }
        }
        return result;
    }

    /**
     * 해결 방법 : 최대 이윤은 현재까지의 최저가와 현재가의 차이 중에서 가장 차이가 큰 값이 된다.
     * 시간복잡도는 O(n)이고, 공간복잡도는 O(1)이 된다.
     */
    public static double computeMaxProfit(List<Double> prices) {
        double minPrice = Double.MAX_VALUE, maxProfit = 0.0;
        for (Double price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }
}