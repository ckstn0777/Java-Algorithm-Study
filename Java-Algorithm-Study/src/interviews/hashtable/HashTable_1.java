package interviews.hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * 문제 : 문자열을 구성하는 문자를 재배치해서 회문을 만들 수 있는지 확인하는 프로그램을 작성하라.
 * 여기서 회문이란 앞으로 읽을때와 뒤로 읽을 때가 같은 문자열을 말한다.
 */
public class HashTable_1 {
    public static void main(String[] args) {
        String s = "edified";
        System.out.println(canFormPalindrome(s));
    }

    /**
     * 해결방법 : 문자열이 짝수이면 모든 문자가 짝수 개만큼 있어야 한다. 문자열이 홀수이면 하나를 제외한
     * 나머지가 짝수 개만큼 있으면 된다. 결국 종합하자면 한 문자가 홀수, 나머지는 짝수를 판단하면 된다.
     * 문자열의 길이가 n일 때 시간복잡도는 O(n)이 된다. 문자열을 구성하는 서로 다른 문자의 개수가 c일 때 공간복잡도는 O(c)가 된다.
     */
    public static boolean canFormPalindrome(String s) {
        Set<Character> charsWithOddFrequency = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charsWithOddFrequency.contains(c)) {
                charsWithOddFrequency.remove(c);
            } else {
                charsWithOddFrequency.add(c);
            }
        }

        // 홀수 번 나타난 문자가 최대 1인 경우에만 회문을 만들 수 있다.
        return charsWithOddFrequency.size() <= 1;
    }
}




