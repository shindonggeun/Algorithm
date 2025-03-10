import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer = {};
        
        // data에서 ext 값이 val_ext보다 작은 데이터만 뽑은 후, sort_by에 해당하는 값을 기준 오름차순 정렬
        int columnIndex = getColumnIndex(ext);
        int[][] filteredData = Arrays.stream(data)
            .filter(row -> row[columnIndex] < val_ext)
            .toArray(int[][]::new);
        
        sortData(filteredData, sort_by);
        
        
        answer = filteredData;
        return answer;
    }
    
    public void sortData(int[][] data, String sort_by) {
        int columnIndex = getColumnIndex(sort_by);
        
        Arrays.sort(data, (a, b) -> Integer.compare(a[columnIndex], b[columnIndex]));
    }
    
    public int getColumnIndex(String column) {
        int index = -1;
        
        if (column.equals("code")) {
            index = 0;
        } 
        else if (column.equals("date")) {
            index = 1;
        }
        else if (column.equals("maximum")) {
            index = 2;
        }
        else if (column.equals("remain")) {
            index = 3;
        }
        
        return index;
    }
}