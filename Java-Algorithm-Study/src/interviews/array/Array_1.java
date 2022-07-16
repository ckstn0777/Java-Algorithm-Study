package interviews.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * - 문제 : 배열 A와 인덱스 i가 주어졌을때, A[i](피벗)보다 작은 원소, 피벗과 같은 원소, 피벗보다 큰 원소 순으로
 * 원소를 재배열하는 프로그램을 작성하라. (흔히 이런걸 '네덜란드 국기 문제'라고 부른다고 함)
 * - 주의 : 정렬문제가 아니므로 예를 들어 피벗보다 작은(큰) 원소 순서가 정렬되어있지 않아도 문제가 없다.
 */
public class Array_1 {
    public static enum Color {RED, WHITE, BLUE}

    public static void main(String[] args) {
        List<Color> A = Arrays.asList(Color.WHITE, Color.RED, Color.BLUE,
                Color.WHITE, Color.BLUE, Color.RED);
        System.out.println(dutchFlagPartition_final(3, A));
    }

    /**
     * 해법1. 일단 단순히 풀어보자.
     * - pivot 보다 작은 건 왼쪽으로 옭긴다. 마찬가지로 pivot 보다 크면 오른쪽으로 옮긴다.
     * - 시간복잡도는 O(n)이며, 공간복잡도는 O(1)이다.
     */
    public static List<Color> dutchFlagPartition_1(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);

        // 첫번째 단계. 피벗보다 작은 원소는 왼쪽으로 이동
        int smaller = 0;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).ordinal() < pivot.ordinal()) {
                Collections.swap(A, i, smaller);
                smaller += 1;
            }
        }
        // 두번째 단계. 피벗보다 큰 원소는 오른쪽으로 이동
        int larger = A.size() - 1;
        for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivot.ordinal(); i--) {
            if (A.get(i).ordinal() > pivot.ordinal()) {
                Collections.swap(A, i, larger);
                larger -= 1;
            }
        }

        return A;
    }

    /**
     * 해법 final. 구현방법이 조금 까다롭지만 수행시간을 좀 더 줄일 수 있는 방법 (조금 까다로운게 아닌데...?)
     * smaller, equal, larger 를 이용해서 4구간으로 나눠서 분류시킨다. 뭔가 하나씩 확정시켜 나가는 느낌.
     * - 0~smaller : 피벗보다 작은 그룹
     * - smaller~equal : 피벗과 같은 그룹
     * - equal~larger : 아직 미분류 그룹
     * - lager~끝까지 : 피벗보다 큰 그룹
     */
    public static List<Color> dutchFlagPartition_final(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);

        int smaller = 0, equal = 0, larger = A.size();

        // 분류되지 않은 원소가 있는 동안 계속 순회
        while (equal < larger) {
            if (A.get(equal).ordinal() < pivot.ordinal()) {
                Collections.swap(A, smaller, equal);
                smaller += 1;
                equal += 1;
            } else if (A.get(equal).ordinal() == pivot.ordinal()) {
                equal += 1;
            } else {
                larger -= 1;
                Collections.swap(A, equal, larger);
            }
            // System.out.println("A = " + A);
        }

        return A;
    }
}
