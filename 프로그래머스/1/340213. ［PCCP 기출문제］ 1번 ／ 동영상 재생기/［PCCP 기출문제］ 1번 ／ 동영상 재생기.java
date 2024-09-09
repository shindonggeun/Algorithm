class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        // 1. 현재 위치, 오프닝 시작 / 종료 시간, 비디오 전체 길이를 초 단위로 변환하는 과정
        int posSecond = calculateSecond(pos); // 현재 재생 위치를 초 단위로 변환
        int startSecond = calculateSecond(op_start); // 오프닝 시작 시간을 초 단위로 변환
        int endSecond = calculateSecond(op_end); // 오프닝 종료 시간을 초 단위로 변환
        int videoLengthSecond = calculateSecond(video_len); // 비디오 전체 길이를 초 단위로 변환
        
        // 처음 재생 위치가 오프닝 구간에 있는 경우 (오프닝 시작 시간 <= 재생 위치 <= 오프닝 종료 시간)
        if (startSecond <= posSecond && endSecond >= posSecond) {
            posSecond = endSecond; // 오프닝 구간에 있으면 오프닝 종료 위치로 이동
            
            // 만약 재생 위치가 비디오 전체 길이를 넘는 경우
            if (posSecond > videoLengthSecond) {
                posSecond = videoLengthSecond; // 비디오 끝으로 재생 위치를 설정
            }
        }
        
        // 사용자 명령어들을 순차적으로 탐색
        for (String command: commands) {
            // "next" 명령어인 경우 (10초 후로 이동)
            if (command.equals("next")) {
                posSecond += 10; // 현재 위치에서 10초 더해줌
                
                // 현재 위치가 비디오 전체 길이를 넘는 경우
                if (posSecond > videoLengthSecond) {
                    posSecond = videoLengthSecond; // 비디오 끝으로 재생 위치를 설정
                }
            }
            // "prev" 명령어인 경우 (10초 전으로 이동)
            else if (command.equals("prev")) {
                posSecond -= 10; // 현재 위치에서 10초 줄여줌
                
                // 현재 위치가 0초 미만이 된 경우 (즉, 음수값)
                if (posSecond < 0) {
                    posSecond = 0; // 0초 미만이 된 경우 비디오 처음 위치(0초)로 설정
                }
            }
            
            // 현재 재생 위치가 오프닝 구간에 있는 경우 (오프닝 시작 시간 <= 재생 위치 <= 오프닝 종료 시간)
            if (startSecond <= posSecond && endSecond >= posSecond) {
                posSecond = endSecond; // 오프닝 구간에 있으면 오프닝 종료 위치로 이동
            }
        }
        
        // 2. 명령어를 통해 계산된 최종 위치를 mm:ss 형태로 변환하는 과정
        int resultMinute = posSecond / 60; // 총 계산된 초에서 분 단위로 변환
        int resultSecond = posSecond % 60; // 분 단위로 변환하고 남은 초를 계산
        
        String minute = String.format("%02d", resultMinute); // 분을 두 자리로 포맷
        String second = String.format("%02d", resultSecond); // 초를 두 자리로 포맷
        
        StringBuilder sb = new StringBuilder();
        
        // "mm:ss" 형식으로 문자열 생성
        sb.append(minute);
        sb.append(":");
        sb.append(second);
        
        answer = sb.toString(); // StringBuilder를 통해 만들어진 문자열 최종 결과에 저장
        
        return answer;
    }
    
    // "mm:ss" 형식을 초 단위로 변환하는 메서드
    public int calculateSecond(String time) {
        String[] mmss = time.split(":"); // "mm:ss"를 분과 초로 쪼갬
        
        int mm = Integer.parseInt(mmss[0]);
        int ss = Integer.parseInt(mmss[1]);
        
        return mm * 60 + ss;
    }
}