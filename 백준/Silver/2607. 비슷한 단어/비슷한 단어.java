import java.io.*;

public class Main {

	static int N;	// 단어의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String word = br.readLine();	// 첫번째 단어 저장
		
		int count = 0;	// 비슷한 단어의 개수를 저장하는 변수
		
		// 나머지 단어들 입력받기
		for(int i=0; i<N-1; i++) {
			String input = br.readLine();	// 나머지 단어 (비교할 단어)
			
			// 첫번째 단어와 현재 단어가 비슷한지 확인
			if(isSimilarWord(word, input)) {
				count++;	// 비슷한 경우 카운트 증가
			}
		}
		System.out.println(count);
		
	}
	
	// 첫번째 단어와 현재 단어가 비슷한지 판단해주는 메서드
	public static boolean isSimilarWord(String word1, String word2) {
		// [0] ~ [25] 즉, 'A' ~ 'Z'
		int[] count1 = new int[26];	// 첫번째 단어의 각 알파벳 빈도수를 저장하는 배열
		int[] count2 = new int[26];	// 두번재 단어(현재 단어)의 각 알파벳 빈도수를 저장하는 배열
		
		// 첫번재 단어의 각 문자의 대해서 탐색
		for(char ch: word1.toCharArray()) {
			count1[ch - 'A']++;	// 해당 문자의 빈도수 증가
		}
		
		// 두번째 단어의 각 문자의 대해서 탐색
		for(char ch: word2.toCharArray()) {
			count2[ch - 'A']++;	// 해당 문자의 빈도수 증가
		}
		
		int diffCount = 0;	// 차이가 나는 문자의 개수
		// 알파벳 각각에 대해 반복
		for(int i=0; i<26; i++) {
			diffCount += Math.abs(count1[i] - count2[i]);	// 각 문자별로 빈도 수 차이를 계산해서 누적
		}
		
		// 두 단어가 완전히 같거나, 문자 하나를 추가하거나 빼거나 바꿔서 같아질 수 있는 경우를 비슷한 단어라고 함
		
		// 두 단어가 완전히 같은 경우
		if(diffCount == 0) {
			return true;	// 비슷한 단어를 나타내는 true 반환
		}
		
		// 한 문자만 다르고, 길이가 같은 경우
		if(diffCount == 2 && word1.length() == word2.length()) {
			return true;	// 비슷한 단어를 나타내는 true 반환
		}
		
		// 한 문자를 추가하거나 빼서 같아지는 경우
		if(diffCount == 1 && Math.abs(word1.length() - word2.length()) == 1) {
			return true;
		}
		
		// 위의 조건을 만족하지 않는 경우는 false 반환
		return false;
	}

}
