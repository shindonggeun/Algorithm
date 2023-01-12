import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(st.nextToken());
        
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=i; j++) {
                sb.append("*");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        
    }
}