import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        
        int[] wanhoScore = scores[0];
        int rank = 1;
        int maxScore = 0;
        int wanhoTotalScore = wanhoScore[0] + wanhoScore[1];
        
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0]; 
        });
        
        for (int[] score: scores) {
            if (score[1] < maxScore) {
                if (score.equals(wanhoScore)) {
                    rank = -1;
                    break;
                }
            }
            else {
                maxScore = Math.max(maxScore, score[1]);
                int totalScore = score[0] + score[1];
                
                if (totalScore > wanhoTotalScore) {
                    rank++;
                }
            }
        }
        
        answer = rank;
        return answer;
    }
}