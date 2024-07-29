import java.io.*;

public class Main {
	
	static int N;
	static int[] current;
	static int[] target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		current = new int[N];
		target = new int[N];
		
		String currentInput = br.readLine();
		String targetInput = br.readLine();
		
		
		for (int i=0; i<N; i++) {
			current[i] = currentInput.charAt(i) - '0';
			target[i] = targetInput.charAt(i) - '0';
		}
		
		int result = Math.min(getSwitchCount(false), getSwitchCount(true));
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	
	public static int getSwitchCount(boolean firstSwitch) {
		int[] bulbArr = current.clone();
		int count = 0;
		
		if (firstSwitch) {
			switchPress(bulbArr, 0);
			count++;
		}
		
		for (int i=1; i<N; i++) {
			if (bulbArr[i-1] != target[i-1]) {
				switchPress(bulbArr, i);
				count++;
			}
		}
		
		if (bulbArr[N-1] == target[N-1]) {
			return count;
		}
		
		return Integer.MAX_VALUE;
	}
	
	public static void switchPress(int[] bulbArr, int idx) {
		bulbArr[idx] = 1 - bulbArr[idx];
		
		if (idx > 0) {
			bulbArr[idx-1] = 1 - bulbArr[idx-1];
		}
		
		if (idx < N-1) {
			bulbArr[idx+1] = 1 - bulbArr[idx+1];
		}
	}

}
