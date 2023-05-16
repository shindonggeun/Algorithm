import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str = st.nextToken();
		StringBuilder strSb = new StringBuilder(str);
		
		String palindrome = strSb.reverse().toString();
		
		// 1. 입력 문자열이 펠린드롬인 경우
		if(str.equals(palindrome)) {
			if(palindrome(str)) {
				System.out.println("AKARAKA");
			}
			else {
				System.out.print("IPSELENTI");
			}
		}
		else {
			System.out.print("IPSELENTI");
		}

	}
	
	public static boolean palindrome(String str) {
		if(str.length() == 1) {
			return true;
		}
		
		int length = str.length()/2;
		
		// 접미사 만드는 작업
		String prefix = str.substring(0, length);
		
		// 접두사 만드는 작업
		String suffix = str.substring(str.length()-length, str.length());
		
		// 접미사와 접두사가 같지 않으면 false 반환
		if(!prefix.equals(suffix)) {
			return false;
		}
		
		// 접미사와 접두사 같은 경우
		// 접미사와 접두사의 길이가 1이 될떄까지 재귀로 반복
		if(palindrome(prefix) && palindrome(suffix)) {
			return true;
		}
		else {
			return false;
		}
	}

}
