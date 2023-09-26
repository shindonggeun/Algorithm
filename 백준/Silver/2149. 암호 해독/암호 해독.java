import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String key = br.readLine();	// key 입력받기
		String encryptedText = br.readLine();	// 암호문 입력받기
		
		// key값 문자에 맞는 열의 암호문 문자열 매칭 시킬 때 해당 매칭 시킬 길이 구하기
		int lines = encryptedText.length() / key.length();	
		
		char[] keyCharArr = key.toCharArray();	
		Arrays.sort(keyCharArr);	// 암호문 복호화할 key값 알파벳 순 정렬
		
		List<String> values = new ArrayList<>();

		// key값 길이 순회
        for (int i = 0; i < key.length(); i++) {
            int startIndex = lines * i;	// 시작 인덱스
            int endIndex = lines * (i + 1);	// 끝 인덱스
            // 열 단위로 문자열 잘라내기
            String substring = encryptedText.substring(startIndex, endIndex);
            values.add(keyCharArr[i] + substring);	// 리스트에 저장 (해당 key값의 맞는 문자 + 암호문 열단위로 자른 문자열)
        }

        Map<Integer, String> plainMap = new HashMap<>();	// key: 해당 인덱스, value: 암호문 복호화시 해당 key값의 맞는 문자 + 암호문 열단위로 자른 문자열) 

        for (int i = 0; i < key.length(); i++) {
            char currentKeyChar = key.charAt(i);	// 현재 key값의 맞는 해당 문자 뽑기
            for (int j = 0; j < values.size(); j++) {
                String value = values.get(j);	
                // 현재 key값의 맞는 해당 문자와 key값에 맞는 해당 암호문 열단위 문자열의 첫번째 문자와 같은 경우
                if (currentKeyChar == value.charAt(0)) {
                    plainMap.put(i, value);	// 복호화 결과를 저장 (해당 key값의 맞는 문자 + 암호문 열 단위)
                    values.remove(j); // 사용된 값을 리스트에서 제거
                    break;	// 다음 key에 대한 복호화를 위해 가장 안쪽 반복문 탈출
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        // 복호화된 평문 출력
        for (int i = 1; i <= lines; i++) {
            for (String value : plainMap.values()) {
            	sb.append(value.charAt(i));
            }
        }
        System.out.println(sb);
    }
}
