package programmers;

import java.util.*;

/**
 * 문제 : https://school.programmers.co.kr/learn/courses/30/lessons/92334
 */
public class Programmers_92334 {
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};

        System.out.println(solution(id_list, report, 2));
    }

    public static List<Integer> solution(String[] id_list, String[] report, int k) {
        // 신고자 - 신고당한 ID 저장
        Map<String, Set<String>> userToReportUsers = new HashMap<>();

        // 신고당한 ID - 신고한 ID 저장
        Map<String, Set<String>> reportUserToUsers = new HashMap<>();

        for (int i = 0; i < report.length; i++) {
            String fromUser = report[i].split(" ")[0];
            String toUser = report[i].split(" ")[1];

            if (!userToReportUsers.containsKey(fromUser)) {
                Set<String> table = new HashSet<>();
                table.add(toUser);
                userToReportUsers.put(fromUser, table);
            } else {
                userToReportUsers.get(fromUser).add(toUser);
            }

            if (!reportUserToUsers.containsKey(toUser)) {
                Set<String> table = new HashSet<>();
                table.add(fromUser);
                reportUserToUsers.put(toUser, table);
            } else {
                reportUserToUsers.get(toUser).add(fromUser);
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < id_list.length; i++) {
            int count = 0;
            if (userToReportUsers.containsKey(id_list[i])) {
                Iterator<String> it = userToReportUsers.get(id_list[i]).iterator();
                while (it.hasNext()) {
                    if (reportUserToUsers.get(it.next()).size() >= k) {
                        count += 1;
                    }
                }
            }
            answer.add(count);
        }

        return answer;
    }
}
