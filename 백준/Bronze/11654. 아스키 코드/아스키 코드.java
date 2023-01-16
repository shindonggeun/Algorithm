import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        char ch = (char) st.nextToken().charAt(0);
        System.out.println((int)ch);
    }
}