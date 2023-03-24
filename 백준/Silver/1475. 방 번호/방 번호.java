import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String input = st.nextToken();
		int count = 0;
		int[] numArr = new int[10];	// 0 ~ 9 까지의 숫자 개수 저장해주는 배열 (6이랑 9 서로 변경 가능)
		
		for(int i=0; i<input.length(); i++) {
			int num = input.charAt(i) - '0';
			
			// 6인 경우는 숫자 9로 카운팅 되게끔
			if(num == 6) {
				numArr[9]++;
			} 
			else {
				numArr[num]++;
			}
		}
		
		// 6과 9는 바꿀 수 있으므로 2로 나눠줌 (나머지가 있는 경우 숫자 한 세트 더 필요하므로 나머지 연산한거 더해줌)
		numArr[9] = numArr[9]/2 + numArr[9]%2;	
		Arrays.sort(numArr);	// 오름차순 정렬
		
		// 가장 많이 사용된 숫자세트 개수 출력
		System.out.println(numArr[9]);

	}

}
