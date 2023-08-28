import java.util.*;

class Solution {
    
    // 해당 과제의 정보를 담은 내부 클래스
	static class HomeWork {
		String name; // 과제 이름
		int startTime;	// 과제 시작 시간
		int playTime;	// 과제 마치는데 걸리는 시간

		public HomeWork(String name, int startTime, int playTime) {
			this.name = name;
			this.startTime = startTime;
			this.playTime = playTime;
		}
	}
    
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
		// 우선순위 큐 이용 => 과제 계획들을 시작시간순으로 계획해서 담을 자료구조 
		// 우선순위 커스텀시켜줌 (과제 시작시간순으로 오름차순 정렬)
		PriorityQueue<HomeWork> planPq = new PriorityQueue<>(new Comparator<HomeWork>() {
			@Override
			public int compare(HomeWork h1, HomeWork h2) {
				// 과제 시작시간순으로 오름차순 정렬
				return h1.startTime - h2.startTime;
			}
		});
		Stack<HomeWork> remainStack = new Stack<>();	// 진행중인 과제들을 저장할 자료구조 스택 이용
		
		// 과제 계획들을 담은 배열 탐색하기
		for(String[] plan: plans) {
			String name = plan[0];	// 이름 추출
			String[] hhmm = plan[1].split(":");	// 문자열 ":"으로 잘라서 문자열 배열에 저장
			// 시간과 분 추출
			int hh = Integer.parseInt(hhmm[0]);
			int mm = Integer.parseInt(hhmm[1]);
			
			int startTime = changeTimeMinute(hh, mm);	// 시작시간 분으로 합쳐서 계산 
			int playTime = Integer.parseInt(plan[2]);	// 과제 마치는데 걸리는 시간
			
			planPq.add(new HomeWork(name, startTime, playTime));	// 과제 계획들 우선순위 큐에 저장
		}
		
		int idx = 0;	// 결과 배열에 넣을 수 있게끔 접근하려는 인덱스
		int currentTime = 0;	// 현재시간
		
		// 과제 계획했던것들 (진행중이던 과제)가 비어있지 않았거나 또는 잠시 멈춰둔 과제들을 담은 스택이 비어있지 않은 경우 반복
		// 즉, 해야할 과제들이 있으면 while문 실행
		while(!planPq.isEmpty() || !remainStack.isEmpty()) {
			// 과제 계획했던 것들이 없는 경우 
			if(planPq.isEmpty()) {
				answer[idx++] = remainStack.pop().name;	// 진행중이던 과제에서 빼내서 해당 과제 이름 저장
			}
			// 진행중이던 과제들이 없는 경우 (즉, 진행중이던 과제 마침)
			else if(remainStack.isEmpty()) {
				HomeWork homeWork = planPq.poll();	// 과제 계획했던 것들에서 빼냄 (과제 진행시키기)
				currentTime = homeWork.startTime;	// 현재시간에 계획했던 해당 과제 시작시간 넣어줌
				remainStack.push(homeWork);	// 진행중인 과제 목록에 해당 과제 넣어줌
			}
			// 과제 계획했던것들도 있고, 진행중이던 과제도 있는 경우
			else {
				// 계획했던 과제의 시작시간이 (현재 진행중이던 과제의 현재(시작)시간 + 현재 진행중이던 과제의 마치는데 걸리는 시간)보다 작은 경우
				if(planPq.peek().startTime < currentTime + remainStack.peek().playTime) {
					// 현재 진행중인 과제 마치는데 걸리는 시간을 갱신시켜줌 
					// 현재 진행중이던 과제 마치는데 걸리는 시간 = 현재 진행중이던 과제 마치는데 걸리는 시간 - (계획했던 과제 시작시간 - 현재 진행중이던 과제의 현재(시작)시간)
					remainStack.peek().playTime -= planPq.peek().startTime - currentTime;	
					currentTime = planPq.peek().startTime;	// 계획했던 과제의 시작시간을 현재시간으로 바꿔줌
					remainStack.push(planPq.poll());	// 현재 진행중인 과제 다시 바꿔줌 (계획했던 과제에서 뺴내서)
				}
				// 계획했던 과제의 시작시간이 (현재 진행중이던 과제의 현재(시작)시간 + 현재 진행중이던 과제의 마치는데 걸리는 시간)보다 크거나 같은 경우
				else {
					currentTime += remainStack.peek().playTime;	// 현재 시간 갱신해줌 (현재시간 + 현재 진행중인 과제 마치는데 걸리는 시간)
					answer[idx++] = remainStack.pop().name;	// 현재 진행중인 과제에서 빼내서 해당 과제 이름 저장
				}
			}
		}
		
		return answer;
    }
    
    // 시간, 분을 이용해서 분으로 통합해서 시간 계산해주는 메서드
	public static int changeTimeMinute(int hour, int minute) {
		int changeMinute = hour * 60 + minute;
		return changeMinute;
	}
}