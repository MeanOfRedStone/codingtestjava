package samsungswexpert;

/*
 (1) map의 좌표
 0 <= x, y <= 100 까지이다

 (2) 꼭지점만 드래곤 커브의 일부이면 된다. 정사각형처럼 뒤덮을 필요는 없다
 */

import java.io.*;
import java.util.*;

public class Baekjoon15685 {
    static int[][] map;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        map = new int[101][101];

        int N = Integer.parseInt(br.readLine());
    
        for(int i = 0; i < N; i++) {
            String[] curveInput = br.readLine().split(" ");
            //0 <= x : 시작점 row <= 100 -> 반대로 사용해야 함
            int x = Integer.parseInt(curveInput[0]);
            //0 <= y : 시작점 col <= 100 -> 반대로 사용해야 함
            int y = Integer.parseInt(curveInput[1]);
            //0 <= d : 시작 방향 <= 3 -> 반대로 사용되는 것 주의
            int d = Integer.parseInt(curveInput[2]);
            //0 <= g : 세대 <= 10
            int g = Integer.parseInt(curveInput[3]);

            makeDragonCurve(x, y, d, g);
        }

        int answer = findSquare();

        System.out.println(answer);
    }

    public static void makeDragonCurve(int col, int row, int d, int g) {
        List<int[]> dragonCurve = new ArrayList<>();
        //시작 점 넣어주고 지도에 기록
        dragonCurve.add(new int[] {row, col});
        map[row][col] = 1;

        //0세대 선분 넣어주고 기록
        int[] firstLine = {row + dx[d], col + dy[d]};
        dragonCurve.add(firstLine);
        map[row + dx[d]][col + dy[d]] = 1;

        //2번째 선분부터 90도 회전해서 넣어주고 기록
        for(int i = 1; i <= g; i++) {
            int[] startPoint = dragonCurve.get(dragonCurve.size() - 1);
            int startRow = startPoint[0];
            int startCol = startPoint[1];

            for(int j = dragonCurve.size() - 1; j >= 1; j--) {
                int[] lastPoint = dragonCurve.get(j);
                int lastRow = lastPoint[0];
                int lastCol = lastPoint[1];

                int[] secondToLastPoint = dragonCurve.get(j - 1);
                int secondToLastRow = secondToLastPoint[0];
                int secondToLastCol = secondToLastPoint[1];

                int rowDifference = secondToLastRow - lastRow;
                int colDifference = secondToLastCol - lastCol;

                startRow = startRow + colDifference;
                startCol = startCol - rowDifference;

                dragonCurve.add(new int[] {startRow, startCol});
                map[startRow][startCol] = 1;
            }
        }
    }

    public static int findSquare() {
        int squareCount = 0;

        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                int point1 = map[i][j];
                int point2 = map[i][j + 1];
                int point3 = map[i + 1][j];
                int point4 = map[i + 1][j + 1];

                if(point1 == 1 && point2 == 1 && point3 == 1 && point4 == 1) {
                    squareCount++;
                }
            }
        }

        return squareCount;
    } 
}
