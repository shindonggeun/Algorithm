class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int posSecond = calculateSecond(pos);
        int startSecond = calculateSecond(op_start);
        int endSecond = calculateSecond(op_end);
        int videoLengthSecond = calculateSecond(video_len);
        
        if (startSecond <= posSecond && endSecond >= posSecond) {
            posSecond = endSecond;
            
            // 동영상 길이를 넘는 경우 동영상의 끝으로 이동
            if (posSecond > videoLengthSecond) {
                posSecond = videoLengthSecond;
            }
        }
        
        for (String command: commands) {
            if (command.equals("next")) {
                posSecond += 10;
                // 동영상 길이를 넘는 경우 동영상의 끝으로 이동
                if (posSecond > videoLengthSecond) {
                    posSecond = videoLengthSecond;
                }
            }
            else if (command.equals("prev")) {
                posSecond -= 10;
                
                if (posSecond < 0) {
                    posSecond = 0;
                }
            }
            
            if (startSecond <= posSecond && endSecond >= posSecond) {
                posSecond = endSecond;
            }
        }
        
        int resultMinute = posSecond / 60;
        int resultSecond = posSecond % 60;
        
        String minute = String.format("%02d", resultMinute);
        String second = String.format("%02d", resultSecond);
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(minute);
        sb.append(":");
        sb.append(second);
        
        answer = sb.toString();
        
        return answer;
    }
    
    public int calculateSecond(String time) {
        String[] mmss = time.split(":");
        
        int mm = Integer.parseInt(mmss[0]);
        int ss = Integer.parseInt(mmss[1]);
        
        return mm * 60 + ss;
    }
}