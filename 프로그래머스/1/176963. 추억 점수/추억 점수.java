import java.util.*;

class Solution {
    
    static Map<String, Integer> scoreMap; // key: 인물 이름, value: 추억 점수
    
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length]; // 결과 배열 생성
        
        scoreMap = new HashMap<>(); // 각 인물 이름에 따른 추억 점수를 저장한 해시맵 생성
        
        // 1. 해시맵에 각 인물의 추억점수를 저장하는 과정
        for (int i=0; i<name.length; i++) {
            scoreMap.put(name[i], yearning[i]);
        }
        
        // 2. 사진들의 추억점수들을 구해서 결과배열에 저장하는 과정
        for (int i=0; i<photo.length; i++) {
            String[] pho = photo[i]; // 해당 사진 뽑아내기
            int totalScore = 0; // 해당 사진의 추억점수 합
            
            // 해당 사진에서 인물들 탐색
            for (String person: pho) {
                // 해당 인물의 추억점수 뽑아내기 (없으면 0)
                totalScore += scoreMap.getOrDefault(person, 0); 
            }
            
            answer[i] = totalScore; // 결과배열에 해당 사진의 추억점수 합 저장
        }
        
        return answer;
    }
}