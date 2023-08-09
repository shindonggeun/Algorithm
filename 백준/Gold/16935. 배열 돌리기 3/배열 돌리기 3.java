import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int R;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 세로 길이
		M = Integer.parseInt(st.nextToken());	// 가로 길이
		R = Integer.parseInt(st.nextToken());	// 연산의 수
		
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			int command = Integer.parseInt(st.nextToken());
			// 1번 연산은 배열을 상하 반전시키는 것
			if(command == 1) {
				flipVertical();
			}
			// 2번 연산은 배열을 좌우 반전시키는 것
			else if(command == 2) {
				flipHorizontal();
			}
			// 3번 연산은 배열을 오른쪽으로 90도 회전시키는 것
			else if(command == 3) {
				rightRotation90();
			}
			// 4번 연산은 배열을 왼쪽으로 90도 회전시키는 것
			else if(command == 4) {
				leftRotation90();
			}
			// 5번 연산은 배열을 그룹으로 나눠서 연산함
			// 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 1 이렇게 각 번수 그룹으로 이동
			else if(command == 5) {
				groupMove1();
			}
			// 6번 연산은 배열을 그룹으로 나눠서 연산함
			// 1 -> 4, 4 -> 3, 3 -> 2, 2 -> 1 이렇게 각 번수 그룹으로 이동
			else if(command == 6) {
				groupMove2();
			}
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
	
	// 배열 상하반전하는 메서드
	public static void flipVertical() {
		int[][] mapCopy = new int[N][M];
		
		// 배열 상하반전 해줄 수 있도록
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 상하반전이므로 행만 바꿔주면 된다
				mapCopy[N-i-1][j] = map[i][j];
			}
		}
		
		map = mapCopy;	// 상하반전한 배열을 가리키도록 설정
	}
	
	// 배열 좌우반전 하는 메서드
	public static void flipHorizontal() {
		int[][] mapCopy = new int[N][M];
		
		// 배열 좌우반전 해줄 수 있도록
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				mapCopy[i][M-j-1] = map[i][j];
			}
		}
		
		map = mapCopy;	// 좌우반전한 배열을 가리키도록 설정
	}
	
	// 배열 오른쪽으로 90도 회전시키는 연산
	public static void rightRotation90() {
		int[][] mapCopy = new int[M][N];	// 배열 오른쪽으로 90도 회전이므로 배열 크기도 변화줌
		
		// 배열 오른쪽으로 90도 회전시켜줄 수 있도록
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				mapCopy[j][N-i-1] = map[i][j];
			}
		}
		
		// 배열의 크기가 달라지므로 크기 맞춰주는 과정 거친다
		int temp = N;
		N = M;
		M = temp;
		
		map = mapCopy;	// 오른쪽으로 90도 회전한 배열을 가리킬 수 있도록
	}
	
	// 배열 왼쪽으로 90도 회전시키는 연산
	public static void leftRotation90() {
		int[][] mapCopy = new int[M][N];	// 배열 왼쪽으로 90도 회전이므로 배열 크기도 변화줌
		
		// 배열 왼쪽으로 90도 회전시켜줄 수 있도록
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				mapCopy[M-j-1][i] = map[i][j];
			}
		}
		
		// 배열의 크기가 달라지므로 크기 맞춰주는 과정 거친다
		int temp = N;
		N = M;
		M = temp;
		
		map = mapCopy;	// 왼쪽으로 90도 회전한 배열을 가리킬 수 있도록
	}
	
	// 배열에서 4그룹으로 나눠서 연산
	// 1 1 1 1 2 2 2 2
	// 1 1 1 1 2 2 2 2
	// 1 1 1 1 2 2 2 2 
	// 4 4 4 4 3 3 3 3
	// 4 4 4 4 3 3 3 3
	// 4 4 4 4 3 3 3 3
	// 4 4 4 4 3 3 3 3
	
	// 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 1
	public static void groupMove1() {
		int[][] mapCopy = new int[N][M];
		
		// 1 -> 2 그룹 옮기는 작업
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				mapCopy[i][M/2+j] = map[i][j];
			}
		}
		
		// 2 -> 3 그룹 옮기는 작업
		for(int i=0; i<N/2; i++) {
			for(int j=M/2; j<M; j++) {
				mapCopy[N/2+i][j] = map[i][j];
			}
		}
		
		// 3 -> 4 그룹 옮기는 작업
		for(int i=N/2; i<N; i++) {
			for(int j=M/2; j<M; j++) {
				mapCopy[i][j-M/2] = map[i][j];
			}
		}
		
		// 4 -> 1 그룹 옮기는 작업
		for(int i=N/2; i<N; i++) {
			for(int j=0; j<M/2; j++) {
				mapCopy[i-N/2][j] = map[i][j];
			}
		}
		map = mapCopy;
	}
	
	// 배열에서 4그룹으로 나눠서 연산
	// 1 1 1 1 2 2 2 2
	// 1 1 1 1 2 2 2 2
	// 1 1 1 1 2 2 2 2 
	// 4 4 4 4 3 3 3 3
	// 4 4 4 4 3 3 3 3
	// 4 4 4 4 3 3 3 3
	// 4 4 4 4 3 3 3 3
	
	// 1 -> 4, 4 -> 3, 3 -> 2, 2 -> 1
	public static void groupMove2() {
		int[][] mapCopy = new int[N][M];
		
		// 1 -> 4 그룹 옮기는 작업
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				mapCopy[N/2+i][j] = map[i][j];
			}
		}
		
		// 4 -> 3 그룹 옮기는 작업
		for(int i=N/2; i<N; i++) {
			for(int j=0; j<M/2; j++) {
				mapCopy[i][M/2+j] = map[i][j];
			}
		}
		
		// 3 -> 2 그룹 옮기는 작업
		for(int i=N/2; i<N; i++) {
			for(int j=M/2; j<M; j++) {
				mapCopy[i-N/2][j] = map[i][j];
			}
		}
		
		// 2 -> 1 그룹 옮기는 작업
		for(int i=0; i<N/2; i++) {
			for(int j=M/2; j<M; j++) {
				mapCopy[i][j-M/2] = map[i][j];
			}
		}
		
		map = mapCopy;
	}
	
	
}
