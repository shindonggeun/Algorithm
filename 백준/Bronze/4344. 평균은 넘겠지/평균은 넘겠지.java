import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<testCase; i++) {
			st = new StringTokenizer(br.readLine());
			int student_num = Integer.parseInt(st.nextToken());
			int[] score = new int[student_num];
			int sum = 0;
			double mean = 0.0;	// 평균값
			int count = 0;		// 평균 넘는 학생 수
			for(int j=0; j<student_num; j++) {
				score[j] = Integer.parseInt(st.nextToken());
				sum+=score[j];
			}
			mean = (double) (sum / student_num);
			
			for(int j=0; j<student_num; j++) {
				if(score[j] > mean) {
					count++;
				}
			}
			String answer = String.format("%.3f", ((double)count / student_num) * 100);	// double / int -> double
			sb.append(answer + "%").append("\n");
		}
		System.out.println(sb);
    }
}