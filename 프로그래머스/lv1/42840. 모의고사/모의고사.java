import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] answers) {
        //int[] answer = {};
        int[] one = {1, 2, 3, 4, 5};                     // 5개의 패턴(1번 수포자)
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};           // 8개의 패턴(2번 수포자)
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};   // 10개의 패턴(3번 수포자)
        int[] score = new int[3];
        
        for(int i=0; i<answers.length; i++) {
            if(answers[i] == one[i % 5]) {
                score[0]++;
            }
            if(answers[i] == two[i % 8]) {
                score[1]++;
            }
            if(answers[i] == three[i % 10]) {
                score[2]++;
            }
        }
        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        ArrayList<Integer> list = new ArrayList<>();
        if(maxScore == score[0]) {list.add(1);}
        if(maxScore == score[1]) {list.add(2);}
        if(maxScore == score[2]) {list.add(3);}
        return list;
    }
}