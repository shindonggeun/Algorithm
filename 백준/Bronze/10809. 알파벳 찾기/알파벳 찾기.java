import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String str = st.nextToken();    // 소문자로만 이루어진 문자열
        int[] alpabet = new int[26];    // 알파벳 소문자 개수 26개
        
        // 알파벳 배열값 -1로 초기화
        for(int i=0; i<alpabet.length; i++) {
            alpabet[i] = -1;
        }
        
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if(alpabet[ch - 'a'] == -1) {
                alpabet[ch - 'a'] = i;
            } 
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<alpabet.length; i++) {
            sb.append(alpabet[i] + " ");
        }
        System.out.println(sb);
    }
}