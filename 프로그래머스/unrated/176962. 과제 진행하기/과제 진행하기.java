import java.util.*;

class Solution {
    
    static class HomeWork {
        String name;    // 과제 이름
        int start;
        int playtime;
        
        public HomeWork(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
    
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        // 우선순위 큐 이용 (큐에 집어넣을 때 커스텀 정렬해서 자동으로 정렬하게끔)
        PriorityQueue<HomeWork> pq = new PriorityQueue<>(new Comparator<HomeWork>() {
            @Override
            public int compare(HomeWork h1, HomeWork h2) {
                // 과제 시작 시간 순으로 오름차순 정렬
                return h1.start - h2.start; 
            }    
        });
        
        for(String[] plan: plans) {
            String name = plan[0];
            String[] hhmm = plan[1].split(":");
            int hour = Integer.parseInt(hhmm[0]);
            int minute = Integer.parseInt(hhmm[1]);
            int playTime = Integer.parseInt(plan[2]);
            int startTime = changeTimeMinute(hour, minute);
            
            pq.add(new HomeWork(name, startTime, playTime));
        }
        
        HomeWork nowHomeWork = pq.poll();
        int nowStart = nowHomeWork.start;
        int nowPlaytime = nowHomeWork.playtime;
        int idx = 0;
        
        Stack<HomeWork> stack = new Stack<>();  // 잠시 멈춰둔 과제들 저장할 수 있도록 Stack 이용
        
        while(true) {
            if(!pq.isEmpty() && (nowStart + nowPlaytime > pq.peek().start)) {
                stack.push(new HomeWork(nowHomeWork.name, nowStart, nowPlaytime - (pq.peek().start - nowStart)));
                nowStart = pq.peek().start;
                nowHomeWork = pq.poll();   // 새로운 과제 시작하기
            }
            else {
                answer[idx++] = nowHomeWork.name;
                nowStart += nowHomeWork.playtime;
                    
                if(!pq.isEmpty() && nowStart==pq.peek().start) {
                    nowHomeWork = pq.poll();
                    nowStart = nowHomeWork.start;
                    nowPlaytime = nowHomeWork.playtime;
                }
                else if(!stack.isEmpty()) {
                    nowHomeWork = stack.pop();
                }
                else if(!pq.isEmpty()) {
                    nowHomeWork = pq.poll();
                    
                }
                else {
                    break;
                }
            }
            
        }
        
        return answer;
    }
        
        
    public int changeTimeMinute(int hour, int minute) {
        int changeMinute = hour * 60 + minute;
        return changeMinute;
    }
}