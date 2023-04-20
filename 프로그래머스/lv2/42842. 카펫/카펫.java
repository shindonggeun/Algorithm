class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int total = brown + yellow;   // 총 필요한 카펫 수
        
        for(int i=1; i<=total; i++) {
            int row = i;    // 세로
            int col = total / row;  // 가로
            
            // 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 같으므로
            // 세로 길이가 가로보다 긴 경우는 건너뜀
            if(row > col) {
                continue;
            }
            
            if((row - 2) * (col - 2) == yellow) {
                answer[0] = col;    // 가로 저장
                answer[1] = row;    // 세로 저장
                break;
            } 
            
        }
        return answer;
    }
}