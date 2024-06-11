import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static List<Long> numberList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		numberList = new ArrayList<>();
		
		if (N <= 10) {
			System.out.println(N);
		}
		else {
			for (int i=0; i<10; i++) {
				generateDescedingNumber(i, 1);
			}
			
			if (N >= numberList.size()) {
				System.out.println(-1);
			}
			else {
				Collections.sort(numberList);
				System.out.println(numberList.get(N));
			}
		}
	}
	
	public static void generateDescedingNumber(long num, int idx) {
		if (idx > 10) {
			return;
		}
		
		numberList.add(num);
		
		for (int i=0; i<num%10; i++) {
			generateDescedingNumber(num * 10 + i, idx + 1);
		}
	}

}