import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "\n");	// 입력값을 엔터단위 끊어서 저장(한줄입력)
        String input = st.nextToken();	// 입력값 저장
        
        st = new StringTokenizer(input, " ");	// 입력값을 띄어쓰기 단위(공백단위로) 끊어서 저장
        System.out.println(st.countTokens());	// 띄어쓰기 단위로 끊은 토큰값 개수 반환
        
        // 이 방법을 사용할 시 공백만 입력하면 str 배열의 길이가 1로 표시됨(빈 문자열을 저장) 
        // 그러므로 틀린 답이 된다
        /*input = input.trim();				// 입력받은 값 앞뒤 공백 없애기
        input = input.replaceAll("\\s+", " ");	// 연속된 공백 하나의 공백으로 치환하기
        
        String[] str = input.split(" ");    // 공백문자구분해서 입력값 잘라내서 String 배열에 저장
        System.out.println(str.length);*/
        
    }
}