import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int price = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int result = 0;
        
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int won = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            result+=(won*count);
        }
        
        if(price == result) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}