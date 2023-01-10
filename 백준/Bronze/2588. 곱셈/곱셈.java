import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int num1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        String num2 = st.nextToken();
        
        // 3자리 자연수
        for(int i=2; i>=0; i--) {
            int result = num1 * Character.getNumericValue(num2.charAt(i));
            System.out.println(result);
        }
        
        System.out.println(num1 * Integer.parseInt(num2));
        
        
    }
}