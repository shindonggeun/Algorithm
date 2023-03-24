import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder strS = new StringBuilder(st.nextToken());
		st = new StringTokenizer(br.readLine());
		StringBuilder strT = new StringBuilder(st.nextToken());
		
		// 문자열 S와 문자열 T의 길이가 같아질때 까지 반복
		while(strS.length() != strT.length()) {
			// 문자열 T의 맨 마지막 글자가 'A'인 경우 
			if(strT.charAt(strT.length()-1) == 'A') {
				strT.deleteCharAt(strT.length()-1);	// 문자열 T의 맨 마지막 글자 삭제
			}
			// 문자열 T의 맨 마지막 글자가 'B'인 경우
			else if(strT.charAt(strT.length()-1) == 'B') {
				strT.deleteCharAt(strT.length()-1);	// 문자열 T의 맨 마지막 글자 삭제
				strT.reverse();						// 문자열 T 뒤집음
			}
		}
		
		// 참고: StrungBuilder의 equals() 메서드는 참조하고 있는 주소값을 비교하므로 ==연산자와 같다고 보면 된다
		// StingBuilder에서 String으로 변환한뒤 equals() 메서드를 써줘야 함!
		if(strS.toString().equals(strT.toString())) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
	}

}