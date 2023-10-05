import java.util.*;
import java.io.*;

public class Main {
    static int targetChannel;
    static int minButtonPushCount;
    static HashSet<Integer> brokenButtons;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        targetChannel = Integer.parseInt(br.readLine());
        int numBrokenButtons = Integer.parseInt(br.readLine());
        brokenButtons = new HashSet<>();

        if (numBrokenButtons > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < numBrokenButtons; i++) {
                brokenButtons.add(Integer.parseInt(st.nextToken()));
            }
        }

        minButtonPushCount = Math.abs(targetChannel - 100); // 초기값 설정: + 또는 - 버튼만을 사용해서 이동하는 경우

        for (int channel = 0; channel <= 1000000; channel++) {
            int pressCount = calculateButtonPressCount(channel);
            if (pressCount != -1) {
                minButtonPushCount = Math.min(minButtonPushCount, pressCount + Math.abs(channel - targetChannel));
            }
        }

        System.out.println(minButtonPushCount);
    }

    static int calculateButtonPressCount(int channel) {
        if (channel == 0) {
            return brokenButtons.contains(0) ? -1 : 1;
        }

        int buttonPressCount = 0;
        while (channel > 0) {
            int lastDigit = channel % 10;
            if (brokenButtons.contains(lastDigit)) {
                return -1; // 고장난 버튼이 포함된 경우 -1 반환
            }
            buttonPressCount++;
            channel /= 10;
        }

        return buttonPressCount;
    }
}
