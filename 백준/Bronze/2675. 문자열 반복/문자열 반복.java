import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int testCase = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<testCase; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());    // 반복 횟수
            String str = st.nextToken();
            String result = "";
            
            for(int j=0; j<str.length(); j++) {
                for(int k=0; k<R; k++) {
                    result+=str.charAt(j);
                }
            }
            sb.append(result).append("\n");         
        }
        System.out.println(sb);
    }
}