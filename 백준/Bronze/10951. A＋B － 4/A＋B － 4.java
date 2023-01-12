import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String str = "";
        // EOF (ctrl + z) 만나면 종료 (문제에서 따로 종료조건이 없으므로 입력한 값이 없을 때 종료)
        while((str=br.readLine()) != null ) {
            st = new StringTokenizer(str);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == 0 && b == 0) {
                break;
            }
            
            sb.append((a+b)).append("\n");
        }
        System.out.println(sb);
        
    }
}