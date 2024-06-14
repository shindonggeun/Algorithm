import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int Q;
	static int[] parents;
	static int[] color;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		TreeSet<Integer> uncolored = new TreeSet<>();
        for (int i = 1; i <= N; i++) {
        	uncolored .add(i);
        }

        int[] result = new int[N + 1];
        
        for (int i=0; i<Q; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int x = Integer.parseInt(st.nextToken());
        	
        	// 현재 구간에서 색칠되지 않은 칸들을 찾아서 색칠하고 TreeSet에서 제거
            Iterator<Integer> it = uncolored.tailSet(a).iterator();
            while (it.hasNext()) {
                int idx = it.next();
                if (idx > b) break;
                result[idx] = x;
                it.remove();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) {
        	sb.append(result[i]).append(" ");
        }
        
        System.out.println(sb);
		
	}
	

}