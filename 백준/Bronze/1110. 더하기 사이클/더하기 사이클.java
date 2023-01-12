import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int copy = num;
        int count = 0;
        
        do {
        	int a = (num % 10) * 10;	// 처음에 입력받은 일의 자리 수를 십의 자리수로 만들기
        	int b = ((num / 10) + (num % 10)) % 10;		// 입력받은 값의 각 자리수 더한 수가 결과값의 일의 자리수로 만들기
        	num = a + b;	// 결과값
        	count++;
        } while(copy != num);
        
        System.out.println(count);
        
        
    }
}