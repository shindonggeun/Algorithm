import java.util.*;

class Solution {
    
    // 광물 5개 연속으로 캘 때 누적 피로도 점수표를 담은 내부 클래스 
	static class Mineral {
        int diamondTiredScore;
        int ironTiredScore;
        int stoneTiredScore;
        
        public Mineral(int diamondTiredScore, int ironTiredScore, int stoneTiredScore) {
            this.diamondTiredScore = diamondTiredScore;
            this.ironTiredScore = ironTiredScore;
            this.stoneTiredScore = stoneTiredScore;
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        List<Mineral> mineralList = new ArrayList<>();	// 광물 5개 연속으로 캘 때 누적 피로도 점수를 담을 광물 리스트
        
        int totalPicks = 0;	// 총 곡괭이 사용 개수
        // [0] => 다이아몬드 곡괭이를 사용하였을 때 발생하는 피로도 점수표
        // [1] => 철 곡괭이를 사용하였을 때 발생하는 피로도 점수표
        // [2] => 돌 곡괭이를 사용하였을 때 발생하는 피로도 점수표
        int[][] board = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};	
        
        // 곡괭이 사용 횟수 더해주는 작업
        for(int pickNum: picks) {
            totalPicks += pickNum;
        }
        
        // 모든 광물 다 탐색
        for(int i=0; i<minerals.length; i+=5) {
            int diamond = 0;	// 다이아몬드 곡괭이를 사용하였을 때 발생하는 피로도
            int iron = 0;	// 철 곡괭이를 사용하였을 때 발생하는 피로도
            int stone = 0;	// 돌 곡괭이를 사용하였을 때 발생하는 피로도 
            
            // 총 곡괭이 사용횟수가 0이 되는 경우 (더이상 광물 캘 수 없음) 
            if(totalPicks == 0) {
                break;	// 반복문 빠져나옴 
            }
            
            // 광물 5개씩 연속으로 캐기
            for(int k=i; k<i+5; k++) {
            	// 광물캐려는 인덱스가 광물들을 저장한 배열의 길이까지 도달하는 경우
                if(k==minerals.length) {
                    break;	// 반복문 빠져나옴
                }
                
                String mineralName = minerals[k];	// 광물 이름 뽑기
                int mineralIdx = 0;	// 광물 캐기 위한 피로도 점수표 인덱스 접근하기 위한 변수
                
                // 해당 광물이 다이아몬드인 경우
                if(mineralName.equals("diamond")) {
                    mineralIdx = 0;
                }
                // 해당 광물이 철인 경우
                else if(mineralName.equals("iron")) {
                    mineralIdx = 1;
                }
                // 해당 광물이 돌인 경우
                else {
                    mineralIdx = 2;
                }
                
                diamond += board[0][mineralIdx];	// 다이아몬드 곡괭이를 사용하였을 때 피로도에 더해줌
                iron += board[1][mineralIdx];	// 철 곡괭이를 사용하였을 때 피로도에 더해줌
                stone += board[2][mineralIdx];	// 돌 곡괭이를 사용하였을 때 피로도에 더해줌
            }
            
            mineralList.add(new Mineral(diamond, iron, stone));
            totalPicks--;	// 곡괭이 사용했으므로 사용횟수 1씩 줄여줌
        }
        
        // 리스트 커스텀 정렬
        Collections.sort(mineralList, new Comparator<Mineral>() {
            @Override
			public int compare(Mineral m1, Mineral m2) {
                return m2.stoneTiredScore - m1.stoneTiredScore;	// 광물 5개 연속으로 돌 곡괭이를 사용하였을 때 누적피로도 점수 내림차순 정렬
			}
        });
        
        for(Mineral mineral: mineralList) {
            int diamond = mineral.diamondTiredScore;	// 광물 5개 연속으로 다이아몬드 곡괭이를 사용하였을 때 누적 피로도
            int iron = mineral.ironTiredScore;	// 광물 5개 연속으로 철 곡괭이를 사용하였을 때 누적 피로도
            int stone = mineral.stoneTiredScore;	// 광물 5개 연속으로 돌 곡괭이를 사용하였을 때 누적 피로도
            
            // 누적 피로도 계산하는 작업
            // 다이아몬드 곡괭이 0개 초과인 경우 (다이아몬드 곡괭이 사용 가능한 경우)
            if(picks[0] > 0) {
                answer += diamond;	
                picks[0]--;	// 다이아몬드 곡괭이 사용했으니 개수 줄여줌
                continue;
            }
            
            // 철 곡괭이 0개 초과인 경우 (철 곡괭이 사용가능한 경우)
            if(picks[1] > 0) {
                answer += iron;
                picks[1]--;	// 철 곡괭이 사용했으니 개수 줄여줌
                continue;
            }
            
            // 돌 곡괭이 0개 초과인 경우 (돌 곡괭이 사용가능한 경우)
            if(picks[2] > 0) {
                answer += stone;
                picks[2]--;	// 돌 곡괭이 사용했으니 개수 줄여줌
                continue;
            }
        }
        
        return answer;
    }
}