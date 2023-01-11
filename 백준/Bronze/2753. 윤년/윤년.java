import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int year = Integer.parseInt(st.nextToken());
        
        // 4의 배수이면서 100의 배수가 아닌 경우 윤년 또는 400의 배수인 경우 윤년
        if(((year % 4 == 0) && (year % 100 != 0)) || year % 400 == 0) {
            System.out.println("1");
        }
        else {
            System.out.println("0");
        }
    }
}