import java.util.*;
import java.io.*;

public class Main {
	
	static class Egg {
		int durability;
		int weight;
		
		public Egg(int durability, int weight) {
			this.durability = durability;
			this.weight = weight;
		}
	}
	
	static int N;
	static Egg[] eggs;
	static int maxBrokenCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int durability = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			eggs[i] = new Egg(durability, weight);
		}
		
		maxBrokenCount = 0;
		eggHit(0);
		
		System.out.println(maxBrokenCount);
	}
	
	public static void eggHit(int depth) {
		if (depth == N) {
			int brokenCount = calculateBrokenEggCount();
			maxBrokenCount = Math.max(maxBrokenCount, brokenCount);
			return;
		}
		
		if (eggs[depth].durability <= 0) {
			eggHit(depth + 1);
			return;
		}
		
		boolean isHit = false;
		
		for (int i=0; i<N; i++) {
			if (i == depth || eggs[i].durability <= 0) {
				continue;
			}
			
			eggs[depth].durability -= eggs[i].weight;
			eggs[i].durability -= eggs[depth].weight;
			
			isHit = true;
			
			eggHit(depth + 1);
			
			eggs[depth].durability += eggs[i].weight;
			eggs[i].durability += eggs[depth].weight;
		}
		
		if (!isHit) {
			eggHit(depth + 1);
		}
	}
	
	public static int calculateBrokenEggCount() {
		int count = 0;
		for (Egg egg: eggs) {
			if (egg.durability <= 0) {
				count++;
			}
		}
		
		return count;
	}

}