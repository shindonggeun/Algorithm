import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        int N = friends.length;
        Map<String, Integer> friendIdxMap = new HashMap<>();
        int[] giftDegree = new int[N];
        int[][] giftGraph = new int[N][N];
        
        for (int i=0; i<N; i++) {
            friendIdxMap.put(friends[i], i);
        }
        
        for (String gift: gifts) {
            String[] giftName = gift.split(" ");
            giftDegree[friendIdxMap.get(giftName[0])]++;
            giftDegree[friendIdxMap.get(giftName[1])]--;
            giftGraph[friendIdxMap.get(giftName[0])][friendIdxMap.get(giftName[1])]++;
        }
        
        int maxGiftCount = 0;
        
        for (int i=0; i<N; i++) {
            int giftCount = 0;
            for (int j=0; j<N; j++) {
                if (i == j) {
                    continue;
                }
                
                if (giftGraph[i][j] > giftGraph[j][i] || 
                    (giftGraph[i][j] == giftGraph[j][i] && giftDegree[i] > giftDegree[j])) {
                    giftCount++;
                }
            }
            
            maxGiftCount = Math.max(maxGiftCount, giftCount);
        }
        
        answer = maxGiftCount;
        
        return answer;
    }
}