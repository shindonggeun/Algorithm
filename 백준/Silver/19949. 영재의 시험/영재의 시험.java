import java.util.*;
import java.io.*;

public class Main {

	static int[] answer;	// 문제 정답을 담을 배열
	static int[] mySolution;	// 영재가 쓴 정답을 담을 배열
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		answer = new int[10];
		mySolution = new int[10];
		
		for(int i=0; i<10; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}
		
		backTracking(0);
		System.out.println(count);
	}
	
	public static void backTracking(int depth) {
		// 10문제 다 푼 경우
		if(depth == 10) {
			int cnt = 0;	// 정답 수
			for(int i=0; i<10; i++) {
				// 영재가 푼 해당 문제가 답안인 경우
				if(answer[i] == mySolution[i]) {
					cnt++;	// 정답처리
				}
			}
			// 영재가 쓴 답안에서 5개이상 맞은 경우
			if(cnt >= 5) {
				count++;
			}
			return;
		}
		
		// 문제 5지선다
		for(int i=1; i<=5; i++) {
			// 2문제 이상 풀었을 때
			if(depth >= 2) {
				// 문제 2개가 연속된 답인 경우 
				if(mySolution[depth - 1] == i && mySolution[depth - 2] == i) {
					continue;	// 다시 반복문으로
				}
			}
			mySolution[depth] = i;
			backTracking(depth + 1);
		}
	}

}
