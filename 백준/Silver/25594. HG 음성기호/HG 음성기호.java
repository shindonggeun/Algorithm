import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    final static String[] nato = { "aespa", "baekjoon", "cau", "debug", "edge", "firefox", "golang", "haegang", "iu",
            "java", "kotlin", "lol", "mips", "null", "os", "python", "query", "roka", "solvedac", "tod", "unix",
            "virus", "whale", "xcode", "yahoo", "zebra" };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder answer = new StringBuilder();

        int curr = 0;
        while (curr < str.length()) {
            int idx = str.charAt(curr) - 97;
            int natoLength = nato[idx].length();

            if (natoLength > str.length() - curr) {
                System.out.println("ERROR!");
                System.exit(0);
            }
            String tmp = str.substring(curr, curr + natoLength);
            if (tmp.equals(nato[idx])) {
                curr += natoLength;
                answer.append((char) (idx + 97) + "");
            } else {
                System.out.println("ERROR!");
                System.exit(0);
            }
        }
        System.out.println("It's HG!");
        System.out.println(answer);
    }
}
