import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int K;	// 하루에 감소하는 중량
	static int[] arr;	// 운동키트에 따른 중량 번호 저장할 배열
	static boolean[] visited;	// dfs 이용할떄 방문배열
	static int[] output;	// 운동키트번호 순열 담을 배열
	static HashMap<Integer, Integer> map;	// key: 운동 키트 번호, value: 중량 증가량
	static int count = 0;	// 경우의 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		output = new int[N];
		visited = new boolean[N];
		map = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i-1] = i;
			map.put(i, Integer.parseInt(st.nextToken()));
		}
		
		dfs(0);	
		System.out.println(count);
	}
	
	// 순열이므로 dfs 이용
	public static void dfs(int depth) {
		// 깊이 조건을 만족하면 (N개의 숫자 뽑은 경우) 종료
		if(depth == N) {
			int result = 500;	// 중량 500으로 설정
			for(int i=0; i<N; i++) {
				result += (map.get(output[i]) - K);
				// 중량 500 미만이면 반복문 빠져나옴
				if(result < 500) {
					break;
				}
			}
			// 위의 반복문 다 돌려서 중량 500이상이 되는 경우 경우의수 증가
			if(result >= 500) {
				count++;
			}
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = arr[i];
				dfs(depth + 1);
				visited[i] = false;
			}
		}
		
	}

}