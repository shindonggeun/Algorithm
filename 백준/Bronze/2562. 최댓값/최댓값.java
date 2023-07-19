import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[9];	// 서로 다른 9개의 자연수 저장하기 위한 배열
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<9; i++) {
			int num = Integer.parseInt(br.readLine());
			arr[i] = num;
			max = Math.max(max, arr[i]);
		}
		
		int index = 0;	
		for(int i=0; i<9; i++) {
			if(max == arr[i]) {
				index = i;
			}
		}
		
		System.out.println(max);		// 최대값
		System.out.println(index+1);	// 몇번째 수인지

	}

}
