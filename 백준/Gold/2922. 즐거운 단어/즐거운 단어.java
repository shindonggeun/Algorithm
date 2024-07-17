import java.io.*;

public class Main {
	
	static int N; // 단어의 길이
	static char[] wordArr; // 해당 단어를 문자 배열로 저장
	static long enjoyWordCount; // 즐거운 단어의 개수
	static char[] moArr = {'A', 'E', 'I', 'O', 'U'}; // 모음들을 저장한 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine(); // 입력 단어
		
		N = input.length(); // 단어의 길이 저장
		wordArr = input.toCharArray(); // 해당 단어 문자 배열로 변환
		enjoyWordCount = 0; // 즐거운 단어의 개수 0으로 초기화
		
		// 즐거운 단어를 만들기 위해 백트래킹 메서드 호출 
		generateEnjoyWord(0, 0, 0, 1, 1, input.contains("L"));
		
		System.out.println(enjoyWordCount);
	}
	
	// depth: 탐색하는 문자 개수
	// continuousJaCount: 연속된 자음의 개수
	// continuousMoCount: 연속된 모음의 개수
	// lineJaCount: 해당 '_' 문자에 대신해서 들어갈 'L' 문자를 제외한 자음들의 경우의 수
	// lineMoCount: 해당 '_' 문자에 대신해서 들어갈 모음들의 경우의 수
	// hasL: 'L' 포함 여부
	public static void generateEnjoyWord(int depth, int continuousJaCount, int continuousMoCount, long lineJaCount, long lineMoCount, boolean hasL) {
		// 연속된 자음의 개수가 3개거나 또는 연속된 모음의 개수가 3개가 된 경우 (기저 조건)
		if (continuousJaCount == 3 || continuousMoCount == 3) {
			return; // 즐거운 단어가 될 수 없으므로 해당 실행된 메서드 종료
		}
		
		// 탐색하는 문자의 개수가 해당 단어의 길이와 같아진 경우 (기저 조건)
		// 즉, 모든 문자를 다 확인한 경우
		if (depth == N) {
			// 해당 단어에 'L'이 포함된 경우
			if (hasL) {
				enjoyWordCount += lineJaCount * lineMoCount; // 가능한 경우의 수를 더해줌
			}
			return; // 메서드 종료
		}
		
		char ch = wordArr[depth]; // 현재 선택된 문자
		
		// 현재 선택된 문자가 '_'인 경우
		if (ch == '_') {
			// 해당 밑줄 문자를 모음으로 대체 -> 모음이 들어갈 수 있는 경우의 수 5가지
			generateEnjoyWord(depth+1, 0, continuousMoCount+1, lineJaCount, lineMoCount * 5, hasL);
			
			// 해당 밑줄 문자를 자음으로 대체 -> 'L' 문자를 제외한 자음이 들어갈 수 있는 경우의 수
			generateEnjoyWord(depth+1, continuousJaCount+1, 0, lineJaCount*20, lineMoCount, hasL);
			
			// 해당 밑줄 문자를 'L' 문자로 대체
			generateEnjoyWord(depth+1, continuousJaCount+1, 0, lineJaCount, lineMoCount, true);
		}
		// 현재 선택된 문자가 '_'이 아닌 경우
		else {
			// 해당 문자가 모음인지 체크해서 모음인 경우
			if (checkMo(ch)) {
				// 다음 문자 탐색하도록 메서드 재귀 호출 (연속된 모음 개수 1 증가)
				generateEnjoyWord(depth+1, 0, continuousMoCount+1, lineJaCount, lineMoCount, hasL);
			}
			// 해당 문자가 모음이 아닌 경우
			else {
				// 다음 문자 탐색하도록 메서드 재귀 호출 (연속된 자음 개수 1 증가)
				generateEnjoyWord(depth+1, continuousJaCount+1, 0, lineJaCount, lineMoCount, hasL);
			}
		}
	}
	
	// 해당 문자가 모음인지 체크하는 메서드
	public static boolean checkMo(char ch) {
		for (char mo : moArr) {
			// 해당 문자가 모음인 경우
			if (ch == mo) {
				// true 반환
				return true;
			}
		}
		// 모음이 아닌 경우 false 반환
		return false;
	}
}