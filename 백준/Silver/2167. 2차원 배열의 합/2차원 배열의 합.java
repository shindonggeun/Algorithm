import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken()) - 1;
			int startY = Integer.parseInt(st.nextToken()) - 1;
			int endX = Integer.parseInt(st.nextToken())- 1;
			int endY = Integer.parseInt(st.nextToken()) - 1;
			
			int sum = 0;
			
			for(int i=startX; i<=endX; i++) {
				for(int j=startY; j<=endY; j++) {
					sum += map[i][j];
				}
			}
			
			sb.append(sum).append("\n");
		}
		System.out.print(sb);

	}

}
