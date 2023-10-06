package searching;

/*
 나이트가 최소 몇 번만에 움직일 수 있는지
 -> BFS 하고 초기화 시키자
 -> 왔던 길 굳이 또 올필요 없잖아 최솟값이니깐(visited 쓰자)

 -> 방문 배열 없이 무한 이동 시키고 9 도달하면 종료
 */

/*
 4 0 0 0 2 0 0 0
 0 0 1 0 0 0 3 0
 0 0 0 0 0 3 0 0
 0 2 0 2 0 0 0 0
 0 0 0 0 0 3 0 0
 3 0 3 0 3 0 0 0
 0 0 0 0 0 0 0 0
 9 0 0 0 0 0 0 0
 */

import java.util.*;
public class Baekjoon7562 {
    //체스판
    static int map[][];
    //체스판 크기
    static int l;
    //나이트의 위치
    static int row;
    static int col;
    //목표 위치
    static int rowGoal;
    static int colGoal;
    //나이트의 이동
    static int dx[] = {2, 1, 2, 1, -2, -1, -2, -1};
    static int dy[] = {-1, -2, 1, 2, -1, -2, 1, 2};

    //이동 확인
    static boolean visited[][];

    //테스트케이스마다 필요한 방문횟수
    static ArrayList<Integer> visit;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        visit = new ArrayList<>();
        int t = sc.nextInt();
        for(int i = 0; i < t; i++){
            l = sc.nextInt();

            map = new int[l][l];
            visited = new boolean[l][l];

            //knight의 위치;
            row = sc.nextInt();
            col = sc.nextInt();
            map[row][col] = 10;

            //목표 위치
            rowGoal = sc.nextInt();
            colGoal = sc.nextInt();
            map[rowGoal][colGoal] = -1;

            BFS(row, col);
        }

        for(int x : visit){
            System.out.println(x);
        }
        
    }

    public static void BFS(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        //시작점 방문 표시
        visited[x][y] = true;
        //시작점을 큐에 넣어줌
        q.add(new int[] {x, y});
        
        if(row == rowGoal && col == colGoal){
            visit.add(0);
        } else{
            //큐가 빌때까지 계속방문
            while(!q.isEmpty()){
                //큐에서 위치를 받아줌
                int now[] = q.poll();
                //현재 위치의 좌표
                int rowNow = now[0];
                int colNow = now[1];


                //나이트가 현재 위치에서 이동할 수 있는 곳을 다시 큐에 넣어줌
                for(int i = 0; i < 8; i++){
                    //나이트가 이동할 위치
                    int rowMove = rowNow + dx[i];
                    int colMove = colNow + dy[i];

                    if(rowMove >= 0 && rowMove <l && colMove >= 0 && colMove < l && !visited[rowMove][colMove]){
                        //다른 위치에서 방문못하게 체크
                        visited[rowMove][colMove] = true;
                        //목표 위치 도달하면 종료
                        if(map[rowMove][colMove] == - 1){
                            map[rowMove][colMove] = map[rowNow][colNow] + 1;
                            int count = map[rowMove][colMove] - 10;
                            visit.add(count);
                            while(!q.isEmpty()){
                                q.poll();
                            }
                            break;            
                        }
                        //지난 위치에서 이동한 거리를 표시해준다
                        map[rowMove][colMove] = map[rowNow][colNow] + 1;
                        //큐에 넣어준다
                        q.add(new int[] {rowMove, colMove});
                    }
                }
            }
        }
    }
}
