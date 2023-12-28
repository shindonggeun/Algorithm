import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[][] dist;
	static final int INF = 1000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		dist = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			if(fromVertex == -1 && toVertex == -1) {
				break;
			}
			
			dist[fromVertex][toVertex] = 1;
			dist[toVertex][fromVertex] = 1;
		}
		
		// 플로이드-워셜 알고리즘 이용
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		int[] maxScoreArr = new int[N+1];
		int minScore = INF;
		// 회장 후보 찾기
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(dist[i][j] != INF) {
					maxScoreArr[i] = Math.max(maxScoreArr[i], dist[i][j]);
				}
			}
			minScore = Math.min(minScore, maxScoreArr[i]);
		}
		
		List<Integer> candidateList = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			if(maxScoreArr[i] == minScore) {
				candidateList.add(i);
			}
		}
		
		System.out.println(minScore + " " + candidateList.size());
		StringBuilder sb = new StringBuilder();
		Collections.sort(candidateList);

		for(int candidate: candidateList) {
			sb.append(candidate).append(" ");
		}
		System.out.println(sb);
	}

}
