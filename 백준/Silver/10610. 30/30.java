import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String num = st.nextToken();
		int[] numCount = new int[10];	// 0 ~ 9 숫자의 개수 담은 배열
		long total = 0;		// 각 자리수 더해준거 저장하는 변수
		
		// 입력받은 수의 각 자리수 탐색하기
		for(int i=0; i<num.length(); i++) {
			String s = num.substring(i, i+1);	// 입력받은 수의 각 자리수 뽑아내기 (왼쪽부터)
			int tNum = Integer.parseInt(s);
			numCount[tNum]++;
			total+=tNum;	// 각 자리수 더해주기
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 입력받은 수에서 0이 포함되어 있지 않거나 각 자리수 더해준게 3의 배수가 아닌 경우
		// -1 출력하고 종료
		if(num.contains("0") == false || total % 3 != 0) {
			System.out.println("-1");
			return;
		}
		
		// 0 ~ 9 수를 담은 배열에서 나온 숫자들 사용해서 30배수 만들기
		// 나온 수들중 30배수에서 가장 큰 수를 만들어야 하므로 9부터 순회
		for(int i=9; i>=0; i--) {
			// 사용할 숫자가 있으면
			while(numCount[i] > 0) {
				sb.append(i);
				numCount[i]--;	// 숫자 사용했으므로 1개씩 줄이기
			}
		}
		System.out.println(sb);
		
		

	}

}