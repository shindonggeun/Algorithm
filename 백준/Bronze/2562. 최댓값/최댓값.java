import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 9개의 서로 다른 자연수 저장
        int[] arr = new int[9];
        
        for(int i=0; i<arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int max = 0;
        int index = 0;
        for(int i=0; i<arr.length; i++) {
            if(max < arr[i]) {
                max = arr[i];
                index = i+1;
            }
        }
        
        System.out.println(max);
        System.out.println(index);
    }
}