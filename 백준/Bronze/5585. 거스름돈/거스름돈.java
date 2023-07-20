import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int payMoney = Integer.parseInt(br.readLine());	// 지불할 돈 입력받음
		
		int remainMoney = 1000 - payMoney;	// 잔돈 계산
		int count = 0;	// 잔돈 개수
		
		// 거슬러 줄 잔돈(500엔, 100엔, 50엔, 10엔, 5엔, 1엔) 계산하는 과정
		count += remainMoney / 500;	
		remainMoney %= 500;
		count += remainMoney / 100;
		remainMoney %= 100;
		count += remainMoney / 50;
		remainMoney %= 50;
		count += remainMoney / 10;
		remainMoney %= 10;
		count += remainMoney / 5;
		remainMoney %= 5;
		count += remainMoney / 1;
		
		System.out.println(count);
		

	}

}
