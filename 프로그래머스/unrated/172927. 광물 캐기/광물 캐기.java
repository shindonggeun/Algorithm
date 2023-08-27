import java.util.*;

class Solution {
    
    static class Mineral {
        int diamondCount;
        int ironCount;
        int stoneCount;
        
        public Mineral(int diamondCount, int ironCount, int stoneCount) {
            this.diamondCount = diamondCount;
            this.ironCount = ironCount;
            this.stoneCount = stoneCount;
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        List<Mineral> mineralList = new ArrayList<>();
        
        int totalPicks = 0;
        int[][] board = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        for(int pickNum: picks) {
            totalPicks += pickNum;
        }
        
        for(int i=0; i<minerals.length; i+=5) {
            int diamond = 0;
            int iron = 0;
            int stone = 0;
            
            if(totalPicks == 0) {
                break;
            }
            
            for(int k=i; k<i+5; k++) {
                if(k==minerals.length) {
                    break;
                }
                
                String mineralName = minerals[k];
                int mineralIdx = 0;
                if(mineralName.equals("diamond")) {
                    mineralIdx = 0;
                }
                else if(mineralName.equals("iron")) {
                    mineralIdx = 1;
                }
                else {
                    mineralIdx = 2;
                }
                
                diamond += board[0][mineralIdx];
                iron += board[1][mineralIdx];
                stone += board[2][mineralIdx];
            }
            
            mineralList.add(new Mineral(diamond, iron, stone));
            totalPicks--;
        }
        
        Collections.sort(mineralList, new Comparator<Mineral>() {
            @Override
			public int compare(Mineral m1, Mineral m2) {
                return m2.stoneCount - m1.stoneCount;
			}
        });
        
        for(Mineral mineral: mineralList) {
            int diamond = mineral.diamondCount;
            int iron = mineral.ironCount;
            int stone = mineral.stoneCount;
            
            if(picks[0] > 0) {
                answer += diamond;
                picks[0]--;
                continue;
            }
            
            if(picks[1] > 0) {
                answer += iron;
                picks[1]--;
                continue;
            }
            
            if(picks[2] > 0) {
                answer += stone;
                picks[2]--;
                continue;
            }
        }
        
        return answer;
    }
}