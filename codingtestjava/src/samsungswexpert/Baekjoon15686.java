package samsungswexpert;

/*
 (1) 한도치까지 선택하는 DFS + 백트래킹을 사용

 (2) depth == M 에서 끊어야 원하는 결과 나옴
*/

import java.io.*;
import java.util.*;

public class Baekjoon15686 {
    static int[][] city;
    static int N;
    static int M;
    static int chickenshopCount;
    static List<int[]> houses;
    static List<int[]> chickenshops;
    static List<Integer> indexCombination;
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] cityInput = br.readLine().split(" ");
        // 2 <= N <= 50
        N = Integer.parseInt(cityInput[0]);
        // 1 <= M <= 13 남길 치킨집의 수
        M = Integer.parseInt(cityInput[1]);

        city = new int[N][N];

        chickenshopCount = 0;
        houses = new ArrayList<>();
        chickenshops = new ArrayList<>();

        //0 : 빈 칸, 1 : 집, 2 : 치킨집
        // 1 <= 집의 개수 <= 2N , M <= 치킨집의 개수 <= 13 
        for(int i = 0; i < N; i++) {
            String[] buildingInput = br.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(buildingInput[j]);
                if(city[i][j] == 2) {
                    chickenshopCount++;
                    chickenshops.add(new int[] {i, j});
                }

                if(city[i][j] == 1) {
                    houses.add(new int[] {i, j});
                }
            }
        }

        indexCombination = new ArrayList<>();
        answer = Integer.MAX_VALUE;

        for(int i = 0; i < chickenshopCount; i++) {
            selectChickenshop(i, 0);
        }

        System.out.println(answer);
    }

    public static void selectChickenshop(int index, int depth) {
        if(depth == M) {
            return;
        }

        indexCombination.add(index);
        findTotalChickenDistance();

        for(int i = index; i < chickenshopCount; i++) {
            if(i == index) {
                continue;
            }

            selectChickenshop(i, depth + 1);
        }

        indexCombination.remove(indexCombination.size() - 1);
    }

    public static void findTotalChickenDistance() {
        int totalChickenDistance = 0;
        
        for(int[] house : houses) {
            int chickenDistance =  Integer.MAX_VALUE;
            int houseRow = house[0];
            int houseCol = house[1];
            
            for(int chickenIndex : indexCombination) {
                int chickenshopRow = chickenshops.get(chickenIndex)[0];
                int chickenshopCol = chickenshops.get(chickenIndex)[1];

                int distance = Math.abs(houseRow - chickenshopRow) + Math.abs(houseCol - chickenshopCol);
                chickenDistance = Math.min(chickenDistance, distance);
            }

            totalChickenDistance += chickenDistance;
        }

        answer = Math.min(answer, totalChickenDistance);
    }
}
