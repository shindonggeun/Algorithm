import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int HH = Integer.parseInt(st.nextToken());
        int MM = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int oven = Integer.parseInt(st.nextToken());
        
        MM = MM + oven;
        while(true) {
            if(MM < 60) {
                break;
            }
            else {
                HH++;
                MM = MM - 60;
                if(HH >= 24) {
                	HH = 0;
                }
            }
        }
        System.out.println(HH + " " + MM);
        
    }
}