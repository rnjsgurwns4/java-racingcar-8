package racingcar;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String[] names = getNames();

        int[] scores = new int[names.length];

        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = getCount();

        System.out.println("실행 결과");
        doRacing(count, scores, names);

        String winners = getWinners(scores, names);
        System.out.print("최종 우승자 : " + winners);


    }

    public static String[] getNames() {
        String input = readLine();

        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }

        String[] rawNames = input.split(",");
        String[] processedNames = new String[rawNames.length]; // 정리된 이름을 담을 새 배열

        for (int i = 0; i < rawNames.length; i++) {
            String trimmedName = rawNames[i].trim();

            if (trimmedName.isEmpty()) throw new IllegalArgumentException("이름이 공백이거나 쉼표(,)가 연속으로 입력될 수 없습니다.");
            if (trimmedName.length() >= 6) throw new IllegalArgumentException("이름은 5자 이하만 가능합니다. (오류: " + trimmedName + ")");

            processedNames[i] = trimmedName;
        }

        return processedNames;
    }


    public static int getCount() {
        String input = readLine();

        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값이 숫자가 아닙니다. (입력: " + input + ")");
        }
    }


    public static void doRacing(int count, int[] score, String[] names) {
        for (int i = 0; i < count; i++) {
            doOneRacing(score, names);
            System.out.println();
        }
    }

    public static void doOneRacing(int[] score, String[] names) {
        for (int j = 0; j < score.length; j++) {
            if (pickNumberInRange(0, 9) >= 4) score[j] += 1;
            String temp = "-".repeat(score[j]);
            System.out.println(names[j] + " : " + temp);
        }
    }


    public static String getWinners(int[] scores, String[] names) {

        List<String> winners = new ArrayList<>();
        int maxScore = Integer.MIN_VALUE;

        for (int i = 0; i < scores.length; i++) {
            maxScore = compareScore(scores[i], maxScore, winners, names[i]);
        }

        return String.join(", ", winners);
    }

    public static int compareScore(int score, int maxScore, List<String> winners, String name) {
        if (score > maxScore) {
            maxScore = score;
            winners.clear();
            winners.add(name);

        } else if (score == maxScore) {
            winners.add(name);
        }
        return maxScore;
    }
}

