import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] body = new int[N][2];
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			body[i][0] = weight;	// 몸무게
			body[i][1] = height;	// 키
		}
		
		for(int i=0; i<N; i++) {
			int rank = 1;
			for(int j=0; j<N; j++) {
				if(i == j) {
					continue;
				}
				
				if(body[i][0] < body[j][0] && body[i][1] < body[j][1]) {
					rank++;
				}
			}
			sb.append(rank + " ");
		}
		System.out.println(sb);
		
	}
	

}