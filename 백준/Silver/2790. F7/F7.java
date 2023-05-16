import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Integer[] score = new Integer[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			score[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(score, Collections.reverseOrder());	// 점수 내림차순 정렬
		
		int tempScore = score[0] + 1;	
		int result = 1;	// 첫번째 레이서는 우승을 무조건 할 수 있다고 가정해서 초기값 1로 잡아줌
		
		for(int i=1; i<N; i++) {
			// 1등할 가능성이 있으면 우승할 가능성이 있는 사람의 수 증가
			if(score[i] + N >= tempScore) {
				result++;
			}
			// 비교할 점수 갱신해준다 
			tempScore = Math.max(tempScore, score[i] + i + 1);
		}
		System.out.println(result);
		
	}

}
