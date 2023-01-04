import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] one = {1, 2, 3, 4, 5};                     // 5개의 패턴(1번 수포자)
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};           // 8개의 패턴(2번 수포자)
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};   // 10개의 패턴(3번 수포자)
        int[] score = new int[3];
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i=0; i<answers.length; i++) {
            if(answers[i] == one[i % 5]) {  // 문제 정답과 1번 수포자 정답 패턴과 같은 경우
                score[0]++;
            }
            if(answers[i] == two[i % 8]) {  // 문제 정답과 2번 수포자 정답 패턴과 같은 경우
                score[1]++;
            }
            if(answers[i] == three[i % 10]) {   // 문제 정답과 3번 수포자 정답 패턴과 같은 경우
                score[2]++;
            }
        }
        
        // 가장 많이 맞춘 사람 찾기(1~3번 수포자) -> 먼저 2번수포자와 3번 수포자중 최대로 많이 맞춘 사람 찾은 뒤
        // 그다음 1번 수포자와 위에서 나온 수포자랑 최대로 많이 맞춘 사람 비교
        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        if(maxScore == score[0]) {  // 최대 정답수가 1번 수포자 정답 수와 같은 경우
            list.add(1);
        }
        if(maxScore == score[1]) {  // 최대 정답수가 2번 수포자 정답 수와 같은 경우
            list.add(2);
        }
        if(maxScore == score[2]) {  // 최대 정답수가 3번 수포자와 정답 수와 같은 경우
            list.add(3);
        }
        
        answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);    // 인덱스로 접근
        }
        return answer;
    }
}