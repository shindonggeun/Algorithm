import java.util.*;
import java.io.*;

public class Solution {
	
	// Integer.MAX_VALUE 사용시 각 정점의 최단거리 계산시 overflow 발생할 수 있음
	static final int INF = 99999;	
    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] adjMatrix = new int[N][N];

            // 입력 처리 부분을 테스트 케이스마다 수행해야 함
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    adjMatrix[i][j] = Integer.parseInt(st.nextToken());
                    // 인접해 있지 않으면서 동시에 자기 자신으로의 인접 정보가 아닌 경우
                    if(adjMatrix[i][j] == 0 && i!=j) {
                    	adjMatrix[i][j] = INF;	// INF (무한대)로 값 초기화
                    }
                }
            }

            // 플로이드-워샬 알고리즘을 이용하여 최단 거리 계산하기 작업
            // 3중 for문 이용
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                    	adjMatrix[i][j] = Math.min(adjMatrix[i][k] + adjMatrix[k][j], adjMatrix[i][j]);
                    }
                }
            }

            int minCC = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int cc = 0;
                for (int j = 0; j < N; j++) {
                    cc += adjMatrix[i][j];
                }

                minCC = Math.min(minCC, cc);
            }

            sb.append("#").append(tc).append(" ").append(minCC).append("\n");
        }
        System.out.print(sb);
    }
}