import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 1이상 N이하의 정수
	static int[] numArr; // 문제에서 둘째 줄에 들어있는 정수들을 저장하는 배열
	static boolean[] visited; // 각 정수이 방문 여부를 나타내는 배열
	static List<Integer> resultList; // 조건을 만족하는 정수들을 저장하는 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		numArr = new int[N+1]; // [1] ~ [N]
		visited = new boolean[N+1]; // [1] ~ [N]
		
		// 1번부터 N번 정수까지 둘째 줄에 들어가는 정수들 입력받아 저장
		for (int i=1; i<=N; i++) {
			numArr[i] = Integer.parseInt(br.readLine());
		}
		
		resultList = new ArrayList<>(); // 리스트 초기화
		
		// 1번부터 N번 정수까지 탐색
		for (int i=1; i<=N; i++) {
			dfs(i, i); // 해당 정수로부터 깊이우선탐색 실시
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(resultList.size()).append("\n");
		
		for (int result: resultList) {
			sb.append(result).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 해당 정수로부터 계속해서 목표 정수를 찾는 메서드 (깊이 우선 탐색 + 백트래킹)
	// now: 현재 정수
	// target: 목표 정수
	public static void dfs(int now, int target) {
		// 해당 정수(노드)의 값이 목표 정수(노드)와 같은 경우
		// 즉, 현재 정수의 값이 시작 정수와 같은 경우
		if (numArr[now] == target) {
			resultList.add(target); // 결과 리스트에 해당 정수값 추가
		}
		
		visited[now] = true; // 현재 정수값(노드) 방문 처리
		
		// 현재 노드의 가리키는 정수값이 방문처리 안된 경우 (즉, 다음 노드가 방문처리 안된 경우)
		if (!visited[numArr[now]]) {
			dfs(numArr[now], target); // 해당 노드의 가리키는 정수값으로 깊이우선탐색 실시
		}
		
		visited[now] = false; // 현재 정수값(노드) 방문 처리 해제 (백트래킹)
	}

}