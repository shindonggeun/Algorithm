import java.util.*;
import java.io.*;

public class Main {
    static int targetChannel;	// 이동하려는 채널
    static int minButtonPushCount;	// 버튼을 최소 몇번을 눌러야하는지 저장할 변수
    static HashSet<Integer> brokenButtons;	// 고장난 버튼(채널 번호)를 저장할 HashSet (집합)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        targetChannel = Integer.parseInt(br.readLine());	// 이동하려는 채널 입력
        int numBrokenButtons = Integer.parseInt(br.readLine());	// 고장난 버튼의 수 입력
        brokenButtons = new HashSet<>();

        // 고장난 버튼의 수가 0보다 큰 경우
        if(numBrokenButtons > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 고장난 버튼들(채널 번호) HashSet에 저장하는 과정
            for(int i=0; i<numBrokenButtons; i++) {
                brokenButtons.add(Integer.parseInt(st.nextToken()));
            }
        }

        // 수빈이가 지금 보고있는 채널 (초기 채널 = 100)
        // 초기값 설정: + 또는 - 버튼만을 사용해서 이동하는 경우
        minButtonPushCount = Math.abs(targetChannel - 100); 

        // 모든 가능한 채널에 대해 계산 (완전탐색 이용)
        for(int channel=0; channel<=1000000; channel++) {
            int pressCount = calculateButtonPressCount(channel);	// 채널 누르는 버튼 횟수 계산
            // 채널까지 버튼 누르는 횟수가 -1인 경우 (즉, 고장난 버튼(채널 번호)인 경우)
            if (pressCount != -1) {
            	// 채널을 누르는 버튼 횟수 (pressCount)와 현재 채널에서 목표 채널로 이동하는 버튼 횟수 |channel - targetChannel|를 합하여 총 버튼 누른 횟수를 계산
            	// 그래서 최소 버튼 누름 횟수와 비교하여 최소 버튼 누름 횟수 갱신
                minButtonPushCount = Math.min(minButtonPushCount, pressCount + Math.abs(channel - targetChannel));
            }
        }

        System.out.println(minButtonPushCount);
    }

    // 채널까지 버튼 누르는 횟수 계산하는 메서드
    public static int calculateButtonPressCount(int channel) {
        // 해당 채널이 0번인 경우
    	if (channel == 0) {
    		// 고장난 버튼(채널 번호) 집합에 0번 채널이 포함된 경우 -1, 아닌경우 1 반환
            return brokenButtons.contains(0) ? -1 : 1;
        }

        int buttonPressCount = 0;	// 임시 버튼 누르는 횟수
        // 채널이 0번보다 큰 경우 반복문 시행 
        // 채널의 각 자릿수를 하나씩 처리하기 위한 작업
        while (channel > 0) {
            int lastDigit = channel % 10;	// 현재 채널의 가장 오른쪽 자릿수 구하기 
            // 현재 채널의 가장 오른쪽 자릿수가 고장난 버튼(채널 번호)의 집합에 포함된 경우
            if (brokenButtons.contains(lastDigit)) {
                return -1; // -1 반환
            }
            buttonPressCount++;	// 버튼 누르는 횟수 증가
            channel /= 10;	// 현재 채널에서 가장 오른쪽 자릿수를 제거하고 다음 자릿수를 구하기 위한 작업
        }

        return buttonPressCount;	// 버튼 누른 횟수 반환
    }
}
