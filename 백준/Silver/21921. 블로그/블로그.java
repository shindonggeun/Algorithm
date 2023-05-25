import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());	
		
		int[] numArr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 슬라이딩 윈도우 알고리즘 이용
		int sum = 0;
		for(int i=0; i<X; i++) {
			sum += numArr[i];
		}
		
		int maxVisitor = sum;
		int maxCount = 1;
		
		for(int i=X; i<N; i++) {
			sum += numArr[i] - numArr[i-X];
			if(maxVisitor == sum) {
				maxCount++;
			}
			else if(maxVisitor < sum) {
				maxVisitor = sum;
				maxCount = 1;
			}
		}
		// 최대 방문자 수가 0명인 경우 "SAD" 출력
		if(maxVisitor == 0) {
			System.out.println("SAD");
		}
		else {
			System.out.println(maxVisitor);
			System.out.println(maxCount);
		}
	}

}
