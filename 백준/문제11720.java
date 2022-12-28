/*
 * 백준 문제
 * 문제제목: 숫자의 합
 * 일시: 20221227
 * 시간제한: 1초
 * 메모리제한: 256MB
 */
package 문자열;

import java.util.*;

public class 문제11720 {	// 제출시에는 -> public class Main 으로 바꿔주자!

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		String str = sc.next();
		sc.close();
		
		int sum = 0;
		
		for(int i=0; i<num; i++) {
			//sum+=str.charAt(i) - '0';	
			sum+=Character.getNumericValue(str.charAt(i));
		}
		
		System.out.println(sum);

	}

}
