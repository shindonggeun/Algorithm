import java.util.*;
import java.io.*;

public class Main {
	
	static int K;
	static int L;
	static Set<String> classRegisterSet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		classRegisterSet = new LinkedHashSet<>();
		
		for (int i=0; i<L; i++) {
			String classNum = br.readLine();
			if (classRegisterSet.contains(classNum)) {
				classRegisterSet.remove(classNum);
			}
			classRegisterSet.add(classNum);
		}
		
		int count = 0;
		
		StringBuilder sb = new StringBuilder();
		for (String classNum: classRegisterSet) {
			if (count == K) {
				break;
			}
			
			sb.append(classNum).append("\n");
			count++;
		}
		
		System.out.print(sb);
	}

}