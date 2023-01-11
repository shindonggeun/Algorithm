import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int dan = Integer.parseInt(st.nextToken());
        
        // n*1 부터 n*9 까지 출력하기
        for(int i=1; i<=9; i++) {
            System.out.println(dan + " * " + i + " = " + dan*i);
        }
    }
}