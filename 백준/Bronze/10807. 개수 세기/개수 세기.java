import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int num = Integer.parseInt(st.nextToken());
        int[] arr = new int[num];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<num; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int result = Integer.parseInt(st.nextToken());
        int count = 0;
        for(int i=0; i<num; i++) {
            if(result == arr[i]) {
                count++;
            }
        }
        
        System.out.println(count);
    }
}