import java.util.*;
import java.io.*;

public class Main {
	
	static int count = 0;	// 부분 문자열이 몇번 등장하는지 저장해주는 변수
	static List<Integer> list = new ArrayList<>();	// 부분 문자열이 등장하는 위치(인덱스)를 저장해주는 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();	// 전체 문자열 입력
		String pattern = br.readLine();	// 비교할 패턴 문자열 입력
		kmp(input, pattern);	// kmp 알고리즘 이용
		
		StringBuilder sb = new StringBuilder();
		sb.append(count).append("\n");
		
		for(int i: list) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
	
	// 부분 문자열의 부분 일치 테이블 생성해주는 메서드
	public static int[] makeTable(String pattern) {
		int length = pattern.length();	// 패턴 문자열의 길이
		int[] table = new int[length];	// 문자열 부분 일치 테이블 이용할 수 있게끔 배열 선언
		
		int idx = 0;	// 부분 일치 테이블을 채우기 위한 인덱스 변수를 초기화
		// 패턴 문자열을 처음부터 끝까지 순회합니다.
		for(int i=1; i<length; i++) {
			// 현재 위치의 문자와 이전에 일치한 부분에서 다음 문자와 비교
			// 일치하는 문자가 발생한 경우 (idx > 0), 연속적으로 더 일치하지 않으면 idx를 이전 위치로 돌아가게끔 조정
			while(idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];	// 현재 인덱스와 일치하지 않을 경우, 이전 위치로 돌아가게끔
			}
			
			// 현재 위치의 문자와 이전에 일치한 부분과 일치하는 경우 => 일치한 부분의 길이를 증가
			if(pattern.charAt(i) == pattern.charAt(idx)) {
				idx++;	// idx 증가시킴
				table[i] = idx;	// 부분 일치 테이블 현재 위치 i에 해당 길이 저장 (현재 위치에 부분 일치 길이 저장)
			}
		}
		return table;
	}
	
	// KMP 알고리즘을 이용한 부분 문자열 찾는 메서드
	public static void kmp(String input, String pattern) {
		int[] table = makeTable(pattern);	// 문자열 패턴을 이용하여 부분 일치 테이블 생성
		
		int inputLength = input.length();	// 입력받은 문자열의 길이
		int patternLength = pattern.length();	// 문자열 패턴의 길이
		
		int idx = 0;
		for(int i=0; i<inputLength; i++) {
			while(idx > 0 && input.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			
			if(input.charAt(i) == pattern.charAt(idx)) {
				// idx가 부분 문자열의 마지막 문자에 도달한 경우 => 부분 문자열 찾은거임 
				if(idx == patternLength-1) {
					count++;	// 부분 문자열 발견했으므로 개수 증가
					list.add(i-idx+1);	// 부분 문자열이 시작하는 위치 리스트에 저장
					idx = table[idx];	// 다음 부분 문자열을 찾기 위해 idx 조정
				}
				// idx가 부분 문자열의 마지막 문자가 아닌 경우
				else {
					idx++;	// 다음 문자와 부분 문자열의 다음 문자를 비교하기 위해 idx 증가
				}
			}
		}
	}

}
