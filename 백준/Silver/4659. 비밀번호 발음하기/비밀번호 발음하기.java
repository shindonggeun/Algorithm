import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		while (true) {
			String input = br.readLine();
			
			if (input.equals("end")) {
				break;
			}
			
			sb.append("<").append(input);
			
			if (passwordCheck(input)) {
				sb.append("> is acceptable.");
			}
			else {
				sb.append("> is not acceptable.");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static boolean passwordCheck(String password) {
		boolean moCheck = false;
		int continuousMoCount = 0; // 연속된 모음 개수
		int continuousJaCount = 0; // 연속된 자음 개수
		char previousChar = ' '; // 이전 문자 저장할 변수
		
		for (int i=0; i<password.length(); i++) {
			char ch = password.charAt(i);
			
			if (containMo(ch)) {
				moCheck = true;
				continuousMoCount++;
				continuousJaCount = 0;
			}
			else {
				continuousJaCount++;
				continuousMoCount = 0;
			}
			
			if (continuousMoCount >= 3 || continuousJaCount >= 3) {
				return false;
			}
			
			// 같은 문자가 연속으로 두번 오는 경우
			if (ch == previousChar) {
				// 연속으로 온 문자가 'aa' 또는 'ee'가 아닌 경우
				if (!(ch == 'e' || ch == 'o')) {
					return false;
				}
			}
			
			previousChar = ch;
		}
		
		return moCheck;
	}
	
	public static boolean containMo(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
			return true;
		}
		return false;
	}

}