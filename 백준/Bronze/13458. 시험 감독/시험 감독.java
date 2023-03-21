import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] testNum = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			testNum[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());	// 총감독관이 각 시험장의 인원을 관리할 수 있는 인원 수
		int C = Integer.parseInt(st.nextToken());	// 부감독관이 각 시험장의 인원을 관리할 수 있는 인원 수
		
		long count = 0;
		
		for(int i=0; i<N; i++) {
			testNum[i]-=B;
			count++;	// 각 시험장에는 총 감독관 오직 1명씩 필요하므로 필요한 감독관 한명씩 추가 
			
			// 각 시험장에 인원수가 0보다 작거나 같은 경우
			if(testNum[i] <= 0) {
				continue;	// 그다음 시험장의 인원수 확인하기
			}
			else {
				// 총감독관이 관리할 수 있는 인원수를 뺀 시험장의 인원에서 부감독관의 인원으로 나머지 연산 
				if(testNum[i]%C == 0) {
					count += testNum[i]/C; // 나눈 몫만큼 부감독 증가
				}
				else if(testNum[i]%C != 0) {
					count += (testNum[i]/C) + 1; // 안 나눠 떨어지면 +1해서 증가
				}
			}
		}
		System.out.println(count);
		

	}

}