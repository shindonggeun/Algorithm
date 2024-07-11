import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		while (true) {
			String input = br.readLine();
			
			// 입력값이 "end"인 경우
			if (input.equals("end")) {
				break; // 무한반복 빠져나옴
			}
			
			sb.append("<").append(input);
			
			// 해당 입력값이 비밀번호 조건에 부합하는 경우
			if (passwordCheck(input)) {
				sb.append("> is acceptable.");
			}
			// 해당 입력값이 비밀번호 조건에 부합하지 않는 경우
			else {
				sb.append("> is not acceptable.");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 입력값이 비밀번호 조건에 부합하는지 확인하는 메서드
	public static boolean passwordCheck(String input) {
		boolean moCheck = false; // 모음이 포함되어 있는지 체크하는 변수
		int continuousMoCount = 0; // 연속된 모음 개수를 나타내는 변수
		int continuousJaCount = 0; // 연속된 자음 개수를 나타내는 변수
		char previousChar = ' '; // 이전 문자 저장할 변수
		
		// 해당 입력값의 모든 문자 탐색
		for (int i=0; i<input.length(); i++) {
			char ch = input.charAt(i); // 해당 문자 추출
			
			// 해당 문자가 모음인 경우
			if (containMo(ch)) {
				moCheck = true; // 해당 입력값에 모음 포함되어 있음
				continuousMoCount++; // 연속된 모음 개수 증가
				continuousJaCount = 0; // 연속된 자음 개수 0으로 초기화
			}
			// 해당 문자가 모음이 아닌 경우 (자음인 경우)
			else {
				continuousJaCount++; // 연속된 자음 개수 증가
				continuousMoCount = 0; // 연속된 모음 개수 0으로 초기화
			}
			
			// 연속된 모음 개수가 3개 이상이거나 또는 연속된 자음 개수가 3개 이상인 경우
			if (continuousMoCount >= 3 || continuousJaCount >= 3) {
				return false; // 비밀번호 조건에 부합하지 않다는 표시 반환
			}
			
			// 같은 문자가 연속으로 두번 오는 경우
			if (ch == previousChar) {
				// 연속으로 온 문자가 'aa' 또는 'ee'가 아닌 경우
				if (!(ch == 'e' || ch == 'o')) {
					// 비밀번호 조건에 부합하지 않다는 표시 반환
					return false;
				}
			}
			
			// 이전 문자 갱신
			previousChar = ch;
		}
		
		// 위의 과정 끝났으면 모음이 포함되어 있는지 여부 확인한 뒤 해당 여부 반환
		return moCheck;
	}
	
	// 모음이 포함되어 있는지 여부를 체크하는 메서드
	public static boolean containMo(char ch) {
		// 해당 문자가 모음인 경우
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
			return true; // 모음 포함되어 있다는 여부 반환
		}
		// 모음이 포함되어 있지 않다는 여부 반환
		return false;
	}

}