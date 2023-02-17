import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String input = st.nextToken();
		int count = 0;	// 몇번의 변환과정 거쳤는지
		
		// 입력값의 길이가 1이 아닌 경우
		if(input.length() != 1) {
			// 변환한 값의 문자열 길이가 1이 아닐때 까지 반복(숫자 자리수 1의자리로 만드는 과정)
			while(input.length() != 1) {
				int sum = 0;	// 각 자리수의 합
				for(int i=0; i<input.length(); i++) {
					sum += (input.charAt(i) - '0');
				}
				input = Integer.toString(sum);	// 각 자리수의 합 변환해줌
				count++;	// 변환과정 횟수 증가
			}
		}
		int num = Integer.parseInt(input);
		
		System.out.println(count);
		// 변환한 값이 3 또는 6 또는 9인 경우
		if(num == 3 || num == 6 || num == 9) {
			System.out.println("YES");
		}
		// 그 이외의 값
		else {
			System.out.println("NO");
		}
		
	}

}