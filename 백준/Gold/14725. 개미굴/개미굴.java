import java.util.*;
import java.io.*;

public class Main {
	
	static class AntNode {
		HashMap<String, AntNode> childs;
		
		public AntNode() {
			this.childs = new HashMap<>();
		}
	}
	
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		AntNode root = new AntNode();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			AntNode current = root;
			
			for(int j=0; j<K; j++) {
				String info = st.nextToken();
				
				if(!current.childs.containsKey(info)) {
					current.childs.put(info, new AntNode());
				}
				current = current.childs.get(info);
			}
		}
		
		antPrint(root, "");
		System.out.print(sb);
	}
	
	public static void antPrint(AntNode root, String str) {
		List<String> list = new ArrayList<>(root.childs.keySet());
		Collections.sort(list);
		
		for(String path: list) {
			sb.append(str).append(path).append("\n");
			antPrint(root.childs.get(path), str+"--");
		}
	}
}
