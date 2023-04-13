import java.io.*;

public class Main {

	static char[] arr;
	static char[] output;
	static boolean[] visited;
	static int totalCount;
	static String answer;
	static int num;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		StringBuilder sb = new StringBuilder();
		
		while((str = br.readLine()) != null) {
			String[] input = str.split(" ");
			arr = new char[input[0].length()];
			output = new char[input[0].length()];
			visited = new boolean[input[0].length()];
			totalCount = 0;
			num = Integer.parseInt(input[1]);
			
			for(int i=0; i<input[0].length(); i++) {
				arr[i] = input[0].charAt(i);
			}
			
			dfs(0);
			if(totalCount < num) {
				sb.append(str + " = " + "No permutation").append("\n");
			}
			else {
				sb.append(str + " = " + answer).append("\n");
			}
		}
		System.out.print(sb);

	}
	// 순열 메서드 이용
	public static void dfs(int depth) {
		if(depth == arr.length) {
			totalCount++;
			if(totalCount == num) {
				answer = new String(output);
			}
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = arr[i];
				dfs(depth+1);
				visited[i] = false;
			}
		}
	}

}
