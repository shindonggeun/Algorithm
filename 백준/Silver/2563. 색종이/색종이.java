import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 색종이 수 저장
	
		boolean[][] paper = new boolean[101][101];	// 100 * 100 정사각형 모양의 도화지 선언 (0 ~ 100)
		int area = 0;	// 넓이를 나타내는 변수
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());	// 좌표평면상 x좌표
			int y = Integer.parseInt(st.nextToken());	// 좌표평면상 y좌표
			
			// (x, y)부터 (x+9, y+9)까지의 영역을 하나씩 체크해주면서 넓이에 더해주는 형식으로
			for(int j=x; j<x+10; j++) {
				for(int k=y; k<y+10; k++) {
					// 해당 좌표가 체크 안된 경우
					if(!paper[j][k]) {
						paper[j][k] = true;	// 해당 좌표 체크해준 뒤
						area++;	// 넓이 증가
					}
				}
			}
		}
		System.out.println(area);	
		
		

	}

}
