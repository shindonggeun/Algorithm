import java.util.*;
import java.io.*;

public class Main {
	
	static int[] checked;	// 점(위치)마다 방문 체크해주는 배열
	static int time;	// 수빈이가 동생을 찾을 수 있는 가장 빠른 시간
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 수빈이가 있는 위치 입력
		int K = Integer.parseInt(st.nextToken());	// 동생이 있는 위치 입력
		checked = new int[100001];	// 수빈이의 현재 위치 범위가 0 ~ 100,000까지 이므로
		
		// 수빈이와 동생 위치가 같은 경우는 0초에 찾을 수 있음
		if(N == K) {
			System.out.println(0);
			return;	// 메인 메서드 종료
		}
		
		// 너비우선탐색 시작 (깊이우선탐색으로 하면 StackOverFlow 발생)
		// 이유: 100000이 넘어서는 지점까지 계속해서 재귀호출을 하기 때문에 (x+1이 100000이 될때까지 한없이 호출됨) 
		bfs(N, K);	
		System.out.println(checked[K] - 1);
	}
	
	public static void bfs(int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);	// 시작위치 큐에 저장
		checked[start] = 1;	// 수빈이 시작 위치 방문 처리
		
		while(!queue.isEmpty()) {
			int number = queue.poll();	// 큐에 저장된 숫자(위치) 뽑아냄
			
			// 큐에서 꺼낸 숫자(위치)가 동생 위치와 같아지는 경우 
			if(number == end) {
				break;	// while문 빠져나옴
			}
			
			// 해당 위치에서 -1한게 양수이면서(위치가 음수일리가 없기 때문) 방문하지 않은 위치인 경우
			if(number-1 >= 0 && checked[number-1] == 0) {
				queue.add(number-1);	// 큐에 탐색한 위치 저장
				checked[number-1] = checked[number] + 1;	// 해당 탐색한 위치 방문처리
			}
			// 해당 위치에서 +1한게 100000이하이면서 방문하지 않은 위치인 경우
			if(number+1 <= 100000 && checked[number+1] == 0) {
				queue.add(number+1);	// 큐에 탐색한 위치 저장
				checked[number+1] = checked[number] + 1;	// 해당 탐색한 위치 방문처리
			}
			// 해당 위치에서 *2한게 100000이하이면서 방문하지 않은 위치인 경우
			if(number*2 <= 100000 && checked[number*2] == 0) {
				queue.add(number*2);	// 큐에 탐색한 위치 저장
				checked[number*2] = checked[number] + 1;	// 해당 탐색한 위치 방문처리
			}
						
		}
	}

}
