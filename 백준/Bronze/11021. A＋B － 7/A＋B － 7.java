import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(st.nextToken());
        
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append("Case #" + i +": " + (a+b)).append("\n");    // a+b할떄 꼭 괄호를 집어넣어주자 (a+b) 이렇게 안할 시 덧셈 연산이 안되고 문자열처럼 붙여져서 나온다.
        }
        
        System.out.println(sb);
        
    }
}