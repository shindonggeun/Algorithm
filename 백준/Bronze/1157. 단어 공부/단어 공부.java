import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String str = st.nextToken();
        str = str.toUpperCase();
        int[] alpabet = new int[26];    // 알파벳 개수 26개        

        for(int i=0; i<str.length(); i++) {
            // 문자의 아스키코드값 65 ~ 90 사이에 있으면('A' ~ 'Z')
            if(str.charAt(i) >= 65 && str.charAt(i) <= 90) {
                alpabet[str.charAt(i) - 65]++;    // 알파벳의 인덱스로 접근해 알파벳 개수 카운트
            }
        }
        
        int max = 0;
        char ch = '?';
        
        for(int i=0; i<alpabet.length; i++) {
            if(alpabet[i] > max) {
                max = alpabet[i];
                ch = (char) (i + 65);
            }
            else if(alpabet[i] == max) {
                ch = '?';
            }
        }
        System.out.println(ch);
    }
}