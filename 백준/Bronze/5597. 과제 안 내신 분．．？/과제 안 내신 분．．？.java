import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        boolean[] number = new boolean[30];	// 30명의 학생 (1 ~ 30) (1번학생은 0번 인덱스에 있음)
        
        for(int i=0; i<28; i++) {
        	st = new StringTokenizer(br.readLine());
        	int num = Integer.parseInt(st.nextToken());	// ex) 1을 입력시 -> 0번 인덱스를 true로 바꿔야됨
        	number[num-1] = true;
        }
        
        for(int i=0; i<number.length; i++) {
        	if(number[i] == false) {
        		System.out.println(i+1);
        	}
        }
    }
}