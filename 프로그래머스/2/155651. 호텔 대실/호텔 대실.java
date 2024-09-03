import java.util.*;

class Solution { 
    
    // 하루의 총 시간을 분 단위로 표현한 상수 (24시간 * 60분 + 청소 시간 10분)
    static final int MAX_TIME = 24 * 60 + 10;
    static final int HOUR = 60; // 1시간을 분 단위로 나타낸 상수
    static final int CLEAN_TIME = 10; // 방 청소에 소요되는 시간 (10분)
    static int[] diffArr; // 차분 배열 (누적합 알고리즘 이용, 각 시각에서 방의 사용 시작 및 종료를 기록한 배열)
    static int[] roomArr; // 누적합 배열 (실제로 방이 사용된 시각을 계산하기 위한 배열)
    
    public int solution(String[][] book_time) {
        int answer = 0;
        
        // 각 배열의 크기는 MAX_TIME + 1로 설정 (0분부터 MAX_TME분까지 다루기 위해)
        roomArr = new int[MAX_TIME + 1]; // [0] ~ [MAX_TIME]
        diffArr = new int[MAX_TIME + 1];
        
        // 예약 정보에 따라 차분 배열에 값 반영
        for (String[] book: book_time) {
            String startTime = book[0]; // 예약 시작 시간
            String endTime = book[1]; // 예약 종료 시간
            
            int startMinute = calculateMinute(startTime); // 시작 시간 분 단위로 변환
            int endMinute = calculateMinute(endTime) + CLEAN_TIME; // 종료 시간 분 단위로 변환한 뒤 청소시간 추가
            
            diffArr[startMinute] += 1; // 시작 시간에 방 사용 증가 표시 (+1 누적)
            // 종료 시간에 방 사용 종료 표시 (-1 누적)
            diffArr[endMinute] -= 1; // 대실 종료시간은 포함되지 않으므로 endMinute + 1이 아닌 endMinute
        }
        
        int sum = 0; // 누적합 계산을 위한 변수
        // 1분부터 최대 시간 (청소시간 더한거까지 포함) 까지 순회하며 누적합 계산
        for (int i=1; i<=MAX_TIME; i++) {
            sum += diffArr[i-1]; // 이전 시각의 차분 배열 값을 누적합에 반영
            roomArr[i] = sum; // 현재 시각에 필요한 방의 수 기록
            // 필요한 최소 방 수 갱신
            answer = Math.max(answer, roomArr[i]); // 배열에 저장된 최대값이 최소로 필요한 방의 값이 됨
        }
        
        return answer; // 최종적으로 필요한 최소 방의 수 반환
    }
    
    // 시간대 문자열 ("HH:MM") 형태의 시간을 분 단위로 변환해주는 함수
    public int calculateMinute(String time) {
        String[] split = time.split(":"); // ":" 기준으로 문자열 분리
        String hour = split[0]; // 시각(hour) 부분
        String minute = split[1]; // 분(minute) 부분
        
        // 시간을 분 단위로 변환 (시각 * 60 + 분)
        int result = Integer.parseInt(hour) * HOUR + Integer.parseInt(minute);
        
        return result;
    }
    
    
}