package interviews.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 문제 : 익명의 편지 텍스트와 잡지 텍스트가 주어졌을 때 해당 잡지를 사용해서 익명의 편지를 작성할 수 있는지 확인하라.
 * 익명의 편지를 쓰는 데 필요한 각각의 문자 개수가 잡지에 등장하는 문자의 개수보다 적다면, 잡지를 사용하여 익명의 편지를 작성할 수 있다.
 */
public class HashTable_2 {
    public static void main(String[] args) {
        String letterText = "letter";
        String magazineText = "magazine text lr";
        System.out.println(my_isLetterConstructibleFromMagazine(letterText, magazineText));
    }

    public static boolean my_isLetterConstructibleFromMagazine(String letterText,
                                                            String magazineText) {
        // letterText 를 먼저 해시테이블에 저장
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        for (int i = 0; i < letterText.length(); i++) {
            char c = letterText.charAt(i);
            if (characterIntegerMap.containsKey(c)) {
                characterIntegerMap.put(c, characterIntegerMap.get(c) + 1);
            } else {
                characterIntegerMap.put(c, 1);
            }
        }

        // magazineText 를 돌면서 해시테이블에 있는 문자 발견 시 마다 제거
        for (int i = 0; i < magazineText.length(); i++) {
            char c = magazineText.charAt(i);
            if (characterIntegerMap.containsKey(c)) {
                characterIntegerMap.put(c, characterIntegerMap.get(c) - 1);
                if (characterIntegerMap.get(c) == 0) {
                    characterIntegerMap.remove(c);
                    if (characterIntegerMap.isEmpty()) return true;
                }
            }
        }

        return false;
    }

    /**
     * 해결방법 : 그냥 무식한 방법으로는 모든 문자에 대해 편지와 잡지에 등장하는 문자의 개수를 센다음에,편지에 문자가 잡지보다 더 많이
     * 등장한다면 false이고 아니면 true를 반환하면 된다.하지만 이 경우에는 편지와 잡지에 모든 문자의 개수를 세서 저장해야 하므로 비효율적이다.
     * 더 나은 방법으로는 편지에 등장하는 문자와 그 횟수를 해시테이블에 저장한 다음, 이후 잡지에 해당 문자가 있으면 -1씩 해주면 된다.
     * 최종적으로 해시 테이블이 비어있다면 true를 반환하면 된다. 시간복잡도는 O(m + n)이 된다.
     */
    public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                            String magazineText) {
        Map<Character, Integer> charFrequencyForLetter = new HashMap<>();
        for (int i = 0; i < letterText.length(); i++) {
            char c = letterText.charAt(i);

            if (charFrequencyForLetter.containsKey(c)) {
                charFrequencyForLetter.replace(c, charFrequencyForLetter.get(c) + 1);
            } else {
                charFrequencyForLetter.put(c, 1);
            }
        }

        for (int i = 0; i < magazineText.length(); i++) {
            char c = magazineText.charAt(i);

            if (charFrequencyForLetter.get(c) != null && charFrequencyForLetter.get(c) > 1) {
                charFrequencyForLetter.replace(c, charFrequencyForLetter.get(c) - 1);
            } else {
                // 신기하게 해시테이블에 해당 키가 없는 상태에서 remove 해도 에러가 나진 않네. 대신 null 을 반환하는 듯.
                charFrequencyForLetter.remove(c);
            }
        }

        return charFrequencyForLetter.isEmpty();
    }
}
