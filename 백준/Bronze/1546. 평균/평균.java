import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int num = Integer.parseInt(st.nextToken());
		double[] score = new double[num];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<num; i++) {
			score[i] = Double.parseDouble(st.nextToken());
		}
		
		Arrays.sort(score);	// 배열 오름차순 정렬
		
		double max_score = score[score.length - 1];	// 배열의 마지막 원소가 최대값
		double result = 0.0;	// 평균값 초기화
        
		// 성적 조작하기
		for(int i=0; i<score.length; i++) {
			score[i] = (score[i] / max_score) * 100;
			result+=score[i];
		}
		
		result = result / score.length;
		System.out.println(result);
        
        
    }
}