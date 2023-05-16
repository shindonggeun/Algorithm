import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] bulb = new int[N+2];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			bulb[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		
		// 그리디하게 풀자!!!
		// 불이 모두 꺼진 상태로 만들어주는거를 목표로
		for(int i=0; i<N; i++) {
			// 불이 켜진 버튼인 경우
			if(bulb[i] == 1) {
				count++;
				bulb[i] = 0;
				
				// 불이 켜진 버튼(1)이면 꺼주고(0) 아니면 켜줌(1) (삼항 연산자 이용)
				// 버튼 누르면 오른쪽 두개 버튼까지 영향이 간다
				bulb[i+1] = bulb[i+1] == 1 ? 0 : 1;	
				bulb[i+2] = bulb[i+2] == 1 ? 0 : 1;	
			}
		}
		System.out.println(count);
	}

}
