import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int x = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int n = 0;    // 몇사분면인지 저장하는 변수
        
        // 1사분면 -> x 양수, y 양수
        if(x > 0 && y > 0) {
            n = 1;
        } 
        // 2사분면 -> x 음수, y 양수
        else if(x < 0 && y > 0) {
            n = 2;
        }
        // 3사분면 -> x 음수, y 음수
        else if(x < 0 && y < 0) {
            n = 3;
        }
        // 4분면 -> x 양수, y 음수
        else if(x > 0 && y < 0) {
            n = 4;
        }
        System.out.println(n);
    }
}