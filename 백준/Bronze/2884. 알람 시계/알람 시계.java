import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int HH = Integer.parseInt(st.nextToken());
        int MM = Integer.parseInt(st.nextToken());
        
        if(MM >= 45) {
            MM = MM - 45;
        }
        else {
            HH--;
            MM = 60 - (45 - MM);
            if(HH < 0) {
            	HH = 23;
            }
        }
        System.out.println(HH + " " + MM);
    }
}