package samsungswexpert;

/*
 구현 문제
 (1) 플래그와 조건을 잘 정리해서 구현함

 (2) 시간이 된다면 depth 3 넘어가는 부분은 함수로 따로 빼주는 것도 좋을 듯

 */

/*
<조건>
처음에는 빈 칸 전부 청소되지 않음

<기능>
로봇 청소기는 다음과 같이 작동한다.

1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
  1)바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
  2)바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
3.현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
  1)반시계 방향으로 90도 회전한다.
  2)바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
  3)1번으로 돌아간다.

<출력>
로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력한다.

계속해서 반복
기능 1. 현재 칸 청소
기능 2. 주변 4방향 확인

기능 3. 1)청소할 수 있는 칸이 없는 경우

기능 3. 2)청소할 수 있는 칸이 있는 경우
*/

/* 
visited[][] cleandLocation


void cleanRoom
int cleancount

while(true)
1. 현재 칸 청소 : cleanedLcation표시
2. cleanCount++;

isDirty = false;
3. for(주변 4방향 확인)
  1) 이동할 방향 좌표 표시

  2) 이동 불가능 한 곳이면 continue

  3) 이동할 방향 청소 완료완료되지 않았다면 isDirty = true;


4. isDirty = flase : 청소할 수 있는 칸이 없는 경우
  1) backward direction = 청소기 방향 180도회전해서 이동할 좌표 표시

  2) 벽이라서 움직일 수 없다면 break;

  3) 움직일 수 있다면
  row = backwardRow, col = backwardCol
  continue;

5. isDirty = true
 while(true)
  1) next direction = 반시계로;

  2) nextLocation 이동불가하면
    continue

  3) nextLocation 청소되지 않았다면
   row = nextRow col = nextcol
    break;

 */
import java.io.*;
import java.util.*;

public class Baekjoon14503 {
    static int[][] room;

    static boolean[][] cleanedLocation;

    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    static int N;
    static int M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] roomSize = br.readLine().split(" ");
        N = Integer.parseInt(roomSize[0]);
        M = Integer.parseInt(roomSize[1]);

        String[] robotInformation = br.readLine().split(" ");
        int r = Integer.parseInt(robotInformation[0]);
        int c = Integer.parseInt(robotInformation[1]);
        int d = Integer.parseInt(robotInformation[2]);
        
        room = new int[N][M];
        for(int row = 0; row < N; row++){
            String[] roomInformation = br.readLine().split(" ");
            for(int col = 0; col < M; col++){
                room[row][col] = Integer.parseInt(roomInformation[col]);
            }
        }

        cleanedLocation = new boolean[N][M];

        int answer = cleanRoom(r, c, d);

        System.out.println(answer);
    }

    public static int cleanRoom(int row, int col, int direction){
        int cleanCount = 0;

        while(true){
            // 1. 현재 칸이 청소되지 않은 경우 : cleanedLcation표시 cleanCount 증가
            if(!cleanedLocation[row][col]){
                cleanedLocation[row][col] = true;
                // 2. cleanCount++;
                cleanCount++;
            }
            
            //3. for(주변 4방향 확인)
            boolean isDirty = false;
            for(int nearLocation = 0; nearLocation < 4; nearLocation++){
                //1) 이동할 방향 좌표 표시
                int nearRow = row + dx[nearLocation];
                int nearCol = col + dy[nearLocation];

                // 2) 이동 불가능 한 곳이면 continue
                if(room[nearRow][nearCol] == 1){
                    continue;
                }

                // 3) 이동할 방향 청소 완료완료되지 않았다면 isDirty = true;
                if(!cleanedLocation[nearRow][nearCol]){
                    isDirty = true;
                    break;
                }
            }

            //4. isDirty = true
            if(isDirty){
                while(true){
                    //1) nextDirection 반시계 회전
                    int nextDirection = direction - 1;
                    if(nextDirection == -1){
                        nextDirection = 3;
                    }
                    direction = nextDirection;

                    //2) nextLocation 구하기
                    int nextRow = row + dx[direction];
                    int nextCol = col + dy[direction];

                    //3) nextLocation 이동 불가면 continue
                    //벽이 있는경우
                    if(room[nextRow][nextCol] == 1){
                        continue;
                    }
                    //청소 된 경우
                    if(cleanedLocation[nextRow][nextCol]){
                        continue;
                    }

                    // 3) nextLocation 청소되지 않았다면 이동
                    row = nextRow;
                    col = nextCol;

                    //4) 1번으로 이동
                    break;
                }
            //5. isDirty = flase : 청소할 수 있는 칸이 없는 경우
            } else if(!isDirty){
                //1) backward direction = 청소기 방향 180도회전해서 이동할 좌표 표시
                int backwardDirection = (direction + 2) % 4;
                int backwardRow = row + dx[backwardDirection];
                int backwardCol = col + dy[backwardDirection];

                // 2) 벽이라서 움직일 수 없다면 break;
                if(room[backwardRow][backwardCol] == 1){
                    break;
                }

                //3) 움직일 수 있다면 이동
                row = backwardRow;
                col = backwardCol;
            }
        }

        return cleanCount;
    }
}
