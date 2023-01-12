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
            sb.append("Case #" + i +": " + a + " + " + b + " = " + (a+b)).append("\n");
        }
        
        System.out.println(sb);
        
        
        
    }
}