import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int result = 0;
        
        // 주사위 세개 눈 다 같은 경우
        if(a == b && b == c) {
            result = 10000 + (a * 1000);
        }
        // 주사위 눈 2개만 같은 경우
        else if(a == b && a != c) {
            result = 1000 + (a * 100);
        }
        else if(a == c && a != b) {
            result = 1000 + (a * 100);
        }
        else if(b == c && a != b) {
            result = 1000 + (b * 100);
        }
        // 주사위 눈 다 다른 경우
        else if(a != b && a != c && b != c) {
            result = Math.max(a, Math.max(b, c)) * 100;
        }
        System.out.println(result);
    }
}