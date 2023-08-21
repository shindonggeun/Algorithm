import java.util.*;
import java.io.*;

public class Main {
	
	// 업무정보를 담을 내부 클래스
	static class WorkInfo {
		int workScore;	// 업무 점수
		int time;	// 업무를 해결하는데 걸리는 시간
		int clearTime;	// 업무 지금까지 몇분 했는지 
		
		public WorkInfo(int workScore, int time, int clearTime) {
			this.workScore = workScore;
			this.time = time;
			this.clearTime = clearTime;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// N분 입력받기
		// 스택 이용하기
		// 문제에서 업무를 하던 도중 새로운 업무가 추가된다면, 하던 업무를 중단하고 새로운 업무를 진행한다고 했으므로 스택을 이용하는게 좋다
		Stack<WorkInfo> stack = new Stack<>();	
		int resultScore = 0;	// 업무 평가 최종 결과 점수를 저장할 변수
		
		// N분동안 탐색하기 (N줄 입력받기)
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int workOk = Integer.parseInt(st.nextToken());	// 업무 주어졌는지 여부 판단해주는 변수
			
			// 1이 입력된 경우 업무가 주어짐
			if(workOk == 1) { 
				int workScore = Integer.parseInt(st.nextToken());	// 업무 점수 입력받기
				int T = Integer.parseInt(st.nextToken());	// 업무를 해결하는데 걸리는 시간 T분 입력받기
				
				
				stack.push(new WorkInfo(workScore, T, 1));	// 스택에 해당 업무 정보 저장 (업무 받자마자 바로 시작하므로 clearTime 1로 세팅)
			}
			
			// 스택이 비어있지 않은 경우
			if(!stack.isEmpty()) {
				WorkInfo tempWork = stack.peek();	// 스택에서 최상단에 저장된 값 확인 (최신업무 확인)
				// 업무 해결하는데 걸리는 시간과 업무 지금까지 한 시간과 같은 경우 (즉, 업무 끝냈는지 여부 확인)
				// 업무 해결했으면
				if(tempWork.time == tempWork.clearTime) {
					resultScore += tempWork.workScore;	// 업무 평가 최종 결과 점수 저장
					stack.pop();	// 스택에서 업무 정보 빼냄
				}
				// 업무 해결 못했으면
				else {
					tempWork.clearTime++;	// 업무 이어서 계속
				}
			}
			
		}
		System.out.println(resultScore);
	}

}
