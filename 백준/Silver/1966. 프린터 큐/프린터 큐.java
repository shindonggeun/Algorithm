import java.util.*;
import java.io.*;

public class Main {

	static class Document {
		int importance;
		int idx;
		
		public Document(int importance, int idx) {
			this.importance = importance;
			this.idx = idx;
		}
	}
	
	static int N;
	static int M;
	static Queue<Document> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			queue = new LinkedList<>();
			List<Integer> importanceList = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				int importance = Integer.parseInt(st.nextToken());
				
				queue.add(new Document(importance, i));
				importanceList.add(importance);
			}
			
			int count = 0;
			while (!queue.isEmpty()) {
				Document now = queue.poll();
				
				if (now.importance < Collections.max(importanceList)) {
					queue.add(now);
				}
				else {
					count++;
					importanceList.remove(Integer.valueOf(now.importance));	
					
					if (now.idx == M) {
						sb.append(count).append("\n");
						break;
					}
				}
			}
		}
		
		System.out.print(sb);

	}

}