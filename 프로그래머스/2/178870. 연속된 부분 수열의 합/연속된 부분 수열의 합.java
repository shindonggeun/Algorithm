class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        // 투포인터 알고리즘 이용
        // 시작 인덱스와 끝 인덱스 둘다 배열의 시작에 위치시킴
        int startIdx = 0;   // 시작 인덱스
        int endIdx = 0; // 끝 인덱스
        int minLength = Integer.MAX_VALUE;  // 연속된 부분 수열의 최소 길이
        
        int sum = 0;    // 연속된 부분 수열의 합
        while (true) {            
            // 연속된 부분 수열의 합이 k보다 작은 경우
            if (sum < k) {
                // 끝인덱스가 배열 끝을 넘어간 경우
                if (endIdx == sequence.length) {
                    break;  // 이분탐색 종료
                }
                sum += sequence[endIdx];
                endIdx++;   // 끝 인덱스 증가
            }
            else {
                if (sum == k) {
                    // 현재 연속된 부분 수열의 길이 계산
                    int currentLength = endIdx - startIdx;
                    
                    // 현재 연속된 부분 수열의 길이가 연속된 부분 수열의 최소 길이보다 작은 경우
                    if (currentLength < minLength) {
                        minLength = currentLength;  // 연속된 부분 수열의 최소 길이 갱신
                        answer[0] = startIdx;
                        answer[1] = endIdx - 1;
                    }
                }
                
                sum -= sequence[startIdx];
                startIdx++;
            }
            
        }
        
        return answer;
    }
}