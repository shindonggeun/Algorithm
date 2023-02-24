import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String input = st.nextToken();
		
		// 입력값인 문자열 "-" 단위로 쪼개줌
		// ex) "1+2+3-4+5+6" -> [0] = "1+2+3", [1] = "4+5+6"
		String[] cal1 = input.split("-");
		int result = 0;		// 이 식의 최소값이 되는 것
		
		for(int i=0; i<cal1.length; i++) {
			int sum = 0;
			// 문자열을 "+" 단위로 쪼개줌
			// "1+2+3" -> [0] = "1" [1] = "2" [3] = "3"
			String[] cal2 = cal1[i].split("\\+");
			
			for(int j=0; j<cal2.length; j++) {
				sum+=Integer.parseInt(cal2[j]);
			}
			
			// 처음 괄호 묶은것은 더해줘야함 (빼주면 안됨)
			if(i==0) {
				result+=sum;
			}
			// 그다음 괄호묶은것부터는 빼줘야 식의 최소값이 된다
			else {
				result-=sum;
			}
		}
		System.out.println(result);

	}

}