import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	// T 입력값 받음
		int realTime = T;	// 입력받은 T초 realTime 변수에 집어넣음
		
		int oven_A = 300;	// A는 300초
		int oven_B = 60;	// B는 60초
		int oven_C = 10;	// C는 10초
		
		// 각 버튼의 조작 횟수 구하기
		// 최소 버튼 조작이므로 가장 큰 A부터 시작해준다
		int aCount = T / oven_A;	
		T = T % oven_A;
		int bCount = T / oven_B;
		T = T % oven_B;
		int cCount = T / oven_C;
		T = T % oven_C;
		
		// 각 오븐 굽기 시간에 따른 버튼 횟수들을 각각 곱해서 더해준것을 result에 저장
		// 입력받은 T(realTime)과 같은지 비교하기 위해
		int result = aCount * oven_A + bCount * oven_B + cCount * oven_C;
		
		// 오븐을 통해 굽는 시간의 합이 정확히 T(realTime)이 되면 최소버튼 조작 횟수 출력
		if(result == realTime) {
			System.out.println(aCount + " " + bCount + " " + cCount);
		}
		// 아닌경우(시간을 정확히 맞출 수 없는 경우) -1 출력
		else {
			System.out.println(-1);
		}
		
		
		
	}

}
