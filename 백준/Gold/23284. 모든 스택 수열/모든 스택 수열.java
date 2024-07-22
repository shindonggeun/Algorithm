import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] sequenceArr;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		sequenceArr = new int[N+1];
		visited = new boolean[N+1];
		
		sb = new StringBuilder();
		
		generateStackSequence(1, 0);
		
		System.out.print(sb);
	}
	
	public static void generateStackSequence(int depth, int next) {
		if (depth == N+1) {
			for (int i=1; i<=N; i++) {
				sb.append(sequenceArr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=1; i<=N; i++) {
			if (!visited[i]) {
				if (i > sequenceArr[depth-1] && i < next) {
					break;
				}
				
	            visited[i] = true;
	            sequenceArr[depth] = i;
	            if (i >= next) {
	                generateStackSequence(depth + 1, i + 1);
	            } else {
	                generateStackSequence(depth + 1, next);
	            }
	            visited[i] = false;
			}
		}
	}

}
