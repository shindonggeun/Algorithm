import java.util.*;

class Solution {
    public int[] solution(int[][] score) {
        int[] answer = new int[score.length];
        ArrayList<Double> result = new ArrayList<>();  // 순위 저장하는 동적 배열 생성
        
        for(int[] sc: score) {
            //System.out.println(Arrays.toString(sc));
            result.add((sc[0] + sc[1]) / 2.0);    // 평균점수 구한거 순위 저장하는 배열에 추가 (int / double -> double)
        }
        //System.out.println(result);
        Collections.sort(result, Collections.reverseOrder());   // ArrayList 내림차순 정렬(순위 저장하는 동적배열)
        //System.out.println(result);
        
        for(int i=0; i<result.size(); i++) {
            double average = (score[i][0] + score[i][1]) / 2.0; // int / double -> double
            //System.out.println(average);
            //System.out.println(result.indexOf(average) + 1);
            answer[i] = result.indexOf(average) + 1;    // 순위 저장하는 동적 배열에서 원하는 평균점수의 index 찾은 뒤 +1 한다(0순위는 따로 없기 떄문)
        }
        
        return answer;
    }
}