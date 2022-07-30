package interviews.hashtable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 문제 : ISBN 으로 책의 가격을 찾는 캐시를 만들어라. 탐색, 삽입, 삭제 메서드를 구현해야 한다.
 * 캐시의 원소는 LRU(가장 최근에 사용된 녀석이 위로 가도록. 즉, 가장 오랫동안 사용하지 않은 녀석부터 삭제) 방식으로 삭제할 것.
 * 이미 존재하는 ISBN에 대해선, 삽입을 하더라도 가격이 변하면 안된다. 캐시 정책에 따라 삽입 혹은 탐색 시 가장 최근에 사용한 웑소로 갱신 필요.
 */
public class HashTable_3 {
    public static void main(String[] args) {
        // 편의상 ISBN 을 한 자리 숫자로 대체했다. 캐시 크기도 3으로 작게 해줌.
        LRUCache lruCache = new LRUCache(3);
        lruCache.insert(1, 10000);
        lruCache.insert(2, 20000);
        lruCache.insert(3, 30000);
        lruCache.insert(1, 20000);

        lruCache.insert(4, 30000); // 이러면 결국 2가 삭제됨.
        lruCache.insert(5, 50000); // 3이 삭제됨

        System.out.println(lruCache.isbnToPrice);
    }

    /**
     * 해결 방법 : 첫번째 방법. 그냥 해시테이블을 사용해서 ISBN 키와 책의 가격, 접근 시간을 value 로 기록.
     * 탐색과 삽입은 O(1)만큼 걸리지만 캐시가 꽉 찼을때 LRU에 해당하는 원소를 찾기 위해서는 O(n)만큼이 소요된다.
     *
     * 좀 더 개선된 두번째 방법은 게으른 가비지 컬렉션을 이용하는 것이다. 캐시 크기가 n이면 2n 크기인 해시테이블을 만들어
     * 2n으로 늘어나기 전까지는 어떤 원소도 삭제하지 않는다. 테이블이 꽉 찬다면 그제서야 해시 테이블을 전부 순회하면서 원소가 삽입된
     * 시간의 중앙값을 찾아서 중앙값보다 오래된 원소는 모두 삭제시킨다. 이럴경우 삭제연산은 n번 수행할 때마다 많아야 한번 발생한다.
     *
     * 세번째 방법은 키값을 큐에 저장하는 방법이다. 다만 큐의 중간에 있는 원소를 앞으로 옮길 수 있어야 하므로 연결리스트를 이용해 큐를 구현해야 한다.
     *
     * 마지막 방법은 Java는 LinkedHashMap 클래스를 제공한다. 이 클래스를 이용하면 연결리스트를 따로 구현하지 않아도 된다.
     */
    private static class LRUCache {
        LinkedHashMap<Integer, Integer> isbnToPrice;

        LRUCache(final int capacity) {
            this.isbnToPrice = new LinkedHashMap<Integer, Integer>(capacity, 1.0f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return this.size() > capacity;
                }
            };
        }

        public Integer lookup(Integer key) {
            if (!isbnToPrice.containsKey(key)) {
                return null;
            }
            return isbnToPrice.get(key);
        }

        public void insert(Integer key, Integer value) {
            // 키 값이 존재하지 않는 경우만 값을 추가한다. 즉, 값이 존재하는 경우는 갱신하지 않는다.
            Integer currentValue = isbnToPrice.get(key); // get 을 해야 lru 이 되는 군
            if (!isbnToPrice.containsKey(key)) {
                isbnToPrice.put(key, value);
            }
        }

        public Integer erase(Object key) {
            return isbnToPrice.remove(key);
        }
    }
}
