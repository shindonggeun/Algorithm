import java.util.*;
import java.io.*;

public class Main {
	
	static char[] arr;
	static char[] output;
	static boolean[] visited;
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
			permutation(0, arr.length);
		}
		System.out.print(sb);

	}
	
	public static void permutation(int depth, int length) {
		// 해당 깊이가 선택한 문자 수(문자열 길이)까지 된 경우 (종료 조건)
		if(depth == length) {
			sb.append(new String(output)).append("\n");
			return;	// 메서드 종료
		}
		
		for(int i=0; i<length; i++) {
			if(!visited[i] && (i == 0 || arr[i] != arr[i-1] || visited[i-1])) {
				visited[i] = true;
				output[depth] = arr[i];
				permutation(depth+1, length);
				visited[i] = false;
			}
		}
	}

}
