class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int humanCount = schedules.length;
        
        for (int i = 0; i < humanCount; i++) {
            int deadline = addMinutes(schedules[i], 10);
            boolean allOnTime = true;
            
            for (int d = 0; d < 7; d++) {
                int dayOfWeek = getDayOfWeek(startday, d);
                
                if (!isWeekday(dayOfWeek)) {
                    continue;
                }
                
                if (timelogs[i][d] > deadline) {
                    allOnTime = false;
                    break;
                }
            }
            
            if (allOnTime) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private int addMinutes(int time, int minutes) {
        int hour = time / 100;
        int min = time % 100;
        
        min += minutes;
        if (min >= 60) {
            hour += min / 60;
            min = min % 60;
        }
        
        return hour * 100 + min;
    }
    
    private boolean isWeekday(int day) {
        return day >= 1 && day <= 5;
    }
    
    private int getDayOfWeek(int startday, int dayIndex) {
        int day = (startday + dayIndex - 1) % 7 + 1;
        return day;
    }
}