import java.util.*;
import java.io.*;

public class Main {
	
	static char[] arr;	// 입력된 문자열을 문자 배열로 변환하여 저장할 배열 (선택하고자 하는 대상)
	static char[] output;	// 순열을 생성하여 저장할 배열 (대상을 담아올 문자)
	static boolean[] visited;	// 방문처리 여부 (백트래킹 사용하기 위해)
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			arr = input.toCharArray();
			output = new char[arr.length];
			visited = new boolean[arr.length];
			Arrays.sort(arr);	// 문자열을 알파벳순으로 정렬
			permutation(0, arr.length);	// 해당 문자열 순열 생성하기
		}
		System.out.print(sb);

	}
	
	// 순열 메서드 (백트래킹)
	public static void permutation(int depth, int length) {
		// 해당 깊이가 선택한 문자 수(문자열 길이)까지 된 경우 (종료 조건)
		if(depth == length) {
			sb.append(new String(output)).append("\n");
			return;	// 메서드 종료
		}
		
		for(int i=0; i<length; i++) {
			// 방문하지 않았으면서 동시에 중복되는 문자를 다룰 때에는 이전 문자가 방문한 상태인 경우
			if(!visited[i] && (i == 0 || arr[i] != arr[i-1] || visited[i-1])) {
				visited[i] = true;	// 방문 처리
				output[depth] = arr[i];	// 결과 배열에 문자 저장
				permutation(depth+1, length);	// 재귀 호출
				visited[i] = false;	// 방문 체크 해제 (백트래킹)
			}
		}
	}

}
