import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String str = st.nextToken();
        
        str = str.replace("c=", " ").replace("c-", " ").replace("dz=", " ").replace("d-", " ").replace("lj", " ").replace("nj", " ").replace("s=", " ").replace("z=", " ");
        
        System.out.println(str.length());
    }
}