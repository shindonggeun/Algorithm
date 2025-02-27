import java.util.*;

class Solution {
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Queue<Integer> bridgeQueue = new LinkedList<>(); // 다리 위 트럭들을 관리하는 큐
        int time = 0;
        int currentBridgeWeight = 0; // 현재 다리 위 트럭들의 총 무게
        
        for (int truck: truck_weights) {
            while (true) {
                if (bridgeQueue.size() == bridge_length) {
                    currentBridgeWeight -= bridgeQueue.poll();
                }
                
                if (currentBridgeWeight + truck <= weight) {
                    bridgeQueue.add(truck);
                    currentBridgeWeight += truck;
                    time++;
                    break;
                } 
                else {
                    bridgeQueue.add(0);
                    time++;
                }
            }
        }
        
        answer = time + bridge_length;
        
        return answer;
    }
}