import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		String[] serialNumber = new String[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			serialNumber[i] = st.nextToken();
		}
		
		Arrays.sort(serialNumber, new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				// 문자열 A와 B의 길이가 서로 같은 경우
				if(s1.length() == s2.length()) {
					String s1_num = s1.replaceAll("[^0-9]", "");	// 문자열 A에서 숫자만 추출
					String s2_num = s2.replaceAll("[^0-9]", "");	// 문자열 B에서 숫자만 추출
					int sum1 = 0;
					int sum2 = 0;
					
					for(int i=0; i<s1_num.length(); i++) {
						sum1 += s1_num.charAt(i) - '0';
					}
					for(int i=0; i<s2_num.length(); i++) {
						sum2 += s2_num.charAt(i) - '0';
					}
					// 문자열에서 숫자만 추출해서 각 자리수의 합이 같지 않은 경우 (2번 조건)
					if(sum1 != sum2) {
						return sum1 - sum2;			// 작은합 가지는 순으로 정렬(오름차순 정렬)
					}
					// 문자열에서 숫자만 추출해서 각 자리수의 합이 같은 경우 (3번 조건) 
					else {
						return s1.compareTo(s2);	// 문자열 사전순으로 정렬(오름차순 정렬)
					}
				}
				// 문자열 A와 B의 길이가 다른경우 (1번 조건)
				else {
					return s1.length() - s2.length();	// 문자열 길이 짧은것 순으로 정렬(오름차순 정렬)
				}
			}
			
		});
		
		StringBuilder sb = new StringBuilder();
		for(String serial: serialNumber) {
			sb.append(serial).append("\n");
		}
		System.out.println(sb);
	}

}