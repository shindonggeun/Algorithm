import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 세로 길이
	static int M;	// 가로 길이
	static int R;	// 회전 수
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 세로 길이 입력
		M = Integer.parseInt(st.nextToken());	// 가로 길이 입력
		R = Integer.parseInt(st.nextToken());	// 회전수 입력
		map = new int[N][M];
		
		// 2차원 배열에 저장할 수 있도록 입력받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int rotationGroup = Math.min(N, M) / 2;	// 회전을 돌려야하는 그룹의 개수 구해주기
		
		// 주어진 회전수만큼 배열 회전시켜주기
		for(int i=0; i<R; i++) {
			rotation(rotationGroup);
		}
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	public static void rotation(int count) {
		for(int i=0; i<count; i++) {
			// 돌려야하는 사각형 배열의 최대 길이 구해주기
			int n_maxLength = N - i - 1;	// 세로
			int m_maxLength = M - i - 1;	// 가로
			
			int temp = map[i][i];	// 마지막에 저장할 임시변수
			
			// 오른쪽에서 왼쪽으로 이동하는 방향(좌) (위쪽 변)
			for(int k=i; k<m_maxLength; k++) {
				map[i][k] = map[i][k+1];
			}
			
			// 아래에서 위로 올라가는 방향(상) (오른쪽 변)
			for(int k=i; k<n_maxLength; k++) {
				map[k][m_maxLength] = map[k+1][m_maxLength];
			}
			
			// 왼쪽에서 오른쪽으로 이동하는 방향(우) (아래쪽 변)
			for(int k=m_maxLength; k>i; k--) {
				map[n_maxLength][k] = map[n_maxLength][k-1];
			}
			
			//위에서 아래로 이동하는 방향(하) (왼쪽 변)
			for(int k=n_maxLength; k>i; k--) {
				map[k][i] = map[k-1][i];
			}
			
			map[i+1][i] = temp;	// 맨처음에 쓴 임시변수를 마지막에 저장해줄 수 있도록
		}
	}

}
