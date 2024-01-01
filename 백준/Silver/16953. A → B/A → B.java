import java.util.*;
import java.io.*;

public class Main {
	
	static int A;
	static int B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		int count = 0;
		boolean isMake = true;	// B를 A로 만들 수 있는지 여부를 판단해주는 변수
		while(B > A) {
			// B가 짝수인 경우
			if(B % 2 == 0) {
				B /= 2;
			}
			// B가 1로 끝나는 경우
			else if(B % 10 == 1) {
				B /= 10;	// 맨 뒤에 1을 제거
			}
			// 위 두 경우가 해당하지 않는 경우 A로 만들 수 없는 경우임
			else {
				isMake = false;	// A로 만들 수 없음
				break;	// 빠져나옴
			}
			
			count++;
		}
		
		if(A == B) {
			System.out.println(count + 1);
		}
		// A와 B가 같아질 수 없거나 또는 B를 A로 만들 수 없는 경우
		else if(A != B || !isMake) {
			System.out.println(-1);
		}
	}

}
