package interviews.greedy;

import java.util.Arrays;
import java.util.List;


/**
 * 문제 : 모든 도시를 방문한 뒤 시작 도시로 되돌아오려고 한다. 도시는 환형으로 연결되어 있고, 각 도시에서는 특정 양의 가스를 구할 수 있다.
 * 모든 도시에서 얻을 수 있는 가스의 총 양은 환형 도로를 한 번 순회하는 데 필요한 가스의 양과 같다.
 * 어떤 도시에서 가스 탱크가 비어 있는 상태로 시작해 환형 도로를 한번 순회한 다음, 다시 해당 도시로 돌아올 수 있으면 그 도시를 풍부한 도시라고 한다.
 * 풍부한 도시는 반디스 존재한다고 가정하고 어떻게 하면 풍부한 도시를 효율적으로 찾을 수 있을지를 생각하라.
 */
public class Greedy_6 {

    public static void main(String[] args) {
        // 책에서 처럼 A, B, C, D, E, F, G 도시가 있다고 가정
        List<Integer> gallons = Arrays.asList(50, 20, 5, 30, 25, 10, 10);
        List<Integer> distances = Arrays.asList(900, 600, 200, 400, 600, 200, 100); // 마일
        System.out.println(findAmpleCity(gallons, distances));
    }

    private static final int MPG = 20; // 1 gallons = 20 마일


    /**
     * 해결방법 : 무식한 방법은 각 도시에서 시작해서 도로를 순회해보는 것이다. 이 경우 시간복잡도는 O(n^2)이다.
     * 그리디 알고리즘을 생각해보면서, 예를 들어 가스를 가장 많이 얻을 수 있는 도시에서 시작하면 어떨지 생각해볼 수 있다. 하지만 중간에 연료가 부족해질 수 있을 것이다.
     * 이 문제의 정답은 도시 간 도로를 순회하면서 사용한 가스의 양을 그래프로 그려보면 어떤 사실을 알 수 있다. 바로 진입 당시의 가스 양이 최소가 되는 도시가 항상 같다는 것이다.
     * A에서 출발하거나 D에서 출발하거나 환형이기 때문에 항상 D가 진입 당시 가스 양이 최소가 된다. 따라서 D가 풍부한 도시가 된다.
     *
     * 시간 복잡도는 O(n)이고 공간복잡도는 O(1)로 해결할 수 있다. (이번 문제는 가설을 잘 세워야 하는게 관건이었군...)
     */
    private static class CityAndRemainingGas {
        public Integer city;
        public Integer remainingGallons;

        public CityAndRemainingGas(Integer city, Integer remainingGallons) {
            this.city = city;
            this.remainingGallons = remainingGallons;
        }
    }

    public static int findAmpleCity(List<Integer> gallons, List<Integer> distances) {
        int remainingGallons = 0;
        CityAndRemainingGas min = new CityAndRemainingGas(0, 0);

        for (int i = 1; i < gallons.size(); i++) {
            remainingGallons += gallons.get(i - 1) - distances.get(i - 1) / MPG;
            if (remainingGallons < min.remainingGallons) {
                min = new CityAndRemainingGas(i, remainingGallons);
            }
        }

        return min.city;
    }
}
