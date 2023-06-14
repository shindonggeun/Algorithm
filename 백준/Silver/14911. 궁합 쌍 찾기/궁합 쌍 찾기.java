import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int result = Integer.parseInt(br.readLine());
		
		String[] arr = input.split(" ");
		int[] numArr = new int[arr.length];
		
		for(int i=0; i<arr.length; i++) {
			numArr[i] = Integer.parseInt(arr[i]);
		}
		Arrays.sort(numArr);
		
		int left = 0;
		int right = numArr.length - 1;
		StringBuilder sb = new StringBuilder();
		int count = 0;
		
		while(left < right) {
			int num = numArr[left] + numArr[right];
			
			if(num == result) {
				sb.append(numArr[left] + " " + numArr[right]).append("\n");
				count++;
				left++;
				right--;
			}
			else if(num > result) {
				right--;
			}
			else if(num < result) {
				left++;
			}
		}
		
		System.out.print(sb);
		System.out.println(count);
	}

}
