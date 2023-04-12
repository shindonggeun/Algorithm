import java.util.*;
import java.io.*;

public class Main {
	
	static int[] arr;	// 선택하고자 하는 대상 집합(숫자들)
	static int[] output;	// 대상숫자를 담아올 배열
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int testCase = Integer.parseInt(st.nextToken());	// 테스트케이스 입력
			// 테스트케이스가 0이 입력되면 무한반복 빠져나옴
			if(testCase == 0) {
				break;
			}
			
			arr = new int[testCase];
			output = new int[testCase];
			
			for(int i=0; i<testCase; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, testCase, 0);	// dfs 호출 시작
			sb.append("\n");
		}
		System.out.println(sb);

	}
	
	// 조합 메서드
	public static void dfs(int depth, int T, int idx) {
		// 로또번호 6개 선택 완료되면 로또번호들 출력한 뒤 재귀호출 종료
		if(depth == 6) {
			for(int i=0; i<6; i++) {
				sb.append(output[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		// 대상 집합을 주어진 인덱스부터 순회하며 숫자를 하나 선택함
		for(int i=idx; i<T; i++) {
			output[depth] = arr[i];
			// 자신 이전의 수를 중복선택하지 않도록 인덱스를 +1하여 재귀호출함
			dfs(depth+1, T, i+1);
		}
	}

}