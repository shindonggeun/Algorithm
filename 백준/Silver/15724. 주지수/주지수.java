import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int K;
	static int[][] sumMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sumMap = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				int num = Integer.parseInt(st.nextToken());
				sumMap[i][j] = sumMap[i][j-1] + num;
			}
		}
		
		
		K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			int result = 0;
			
			for(int j=startX; j<=endX; j++) {
				result += (sumMap[j][endY] - sumMap[j][startY - 1]);
			}
			sb.append(result).append("\n");
			
		}
		System.out.print(sb);
		
		
	}

}
