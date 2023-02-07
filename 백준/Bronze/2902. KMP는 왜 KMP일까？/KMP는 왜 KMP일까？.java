import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str = st.nextToken();
		
		// 정규표현식 이용
		// 대문자 A~Z가 아닌것들은 다 빈문자열로 치환
		str = str.replaceAll("[^A-Z]", "");
		
		System.out.println(str);

	}

}