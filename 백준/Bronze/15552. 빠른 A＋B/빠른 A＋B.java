import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();    
        
        // StringBuilder에 한번에 저장한 다음 출력하는게 제일 빠르다
        // 다시 반복문 돌려서 System.out.println()을 통해서 출력을 계속하면 시간 초과남
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())).append('\n');
        }
        
        System.out.println(sb);
    }
}