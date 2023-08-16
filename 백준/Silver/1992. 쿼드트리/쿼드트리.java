import java.util.*;
import java.io.*;

public class Main {

	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];	// (0, 0) ~ (N-1, N-1)
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		compress(0, 0, N);	// 시작좌표 (0, 0)에 영상 압축크기는 N으로 시작
		System.out.println(sb);
	}
	
	public static void compress(int x, int y, int size) {
		// 해당 영역이 압축 가능하면 메서드 종료 (종료조건)
		if(isCompressPossible(x, y, size)) {
			sb.append(map[x][y]);	// 해당 영상을 압축한 결과 StringBuilder에 저장
			return;	// 메서드 종료
		}
		
		int newSize = size / 2;	// 압축이 불가능한 경우 사이즈 다시 절반으로 나눔
		
		sb.append("(");	// 4개의 영상으로 각각 쪼갤 시 시작하는 과정을 나타내는 "(" 
		compress(x, y, newSize);					// 왼쪽 위 영상 압축 
		compress(x, y+newSize, newSize);			// 오른쪽 위 영상 압축
		compress(x+newSize, y, newSize);			// 왼쪽 아래 영상 압축
		compress(x+newSize, y+newSize, newSize);	// 오른쪽 아래 영상 압축
		
		sb.append(")");	// 4개 영상으로 쪼갠거 닫는 것을 나타내는 ")"
	}
	
	// 영상 압축 가능한지 여부를 반환해주는 메서드
	public static boolean isCompressPossible(int x, int y, int size) {
		int value = map[x][y];	// 해당 시작좌표의 영상값
		
		// 시작좌표에서 사이즈까지 완전탐색 이용
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				// 시작좌표의 영상값과 다른 경우
				if(map[i][j] != value) {
					return false;	// 압축 불가
				}
			}
		}
		// 위의 과정 다 거쳤는데 영상값 다른게 없으면
		return true;	// 압축 가능
	}

}
