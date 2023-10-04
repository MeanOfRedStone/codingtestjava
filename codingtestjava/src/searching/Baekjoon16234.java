package searching;

/*
 <출제 포인트>
 (1) 최단 거리 찾기가 아니기 때문에 DFS BFS는 상관 없음
    -> 탐색 알고리즘 뿐만 아니라 추가적으로 flag 활용하는 식이 필요함
 
 (2) 여러번 방문하는 건 알았는데 한번에 방문할때 국경 열리는거 분리되서 가능하다는걸 파악 못함
 -> 이후 한 사이클 끝나고 초기화 하면 됨!
 */

/*  20 ~ 50
    50 30
    20 40

    50 30  (0 0) (0 1) (1 0)
    20 40
 */


import java.util.*;

public class Baekjoon16234 {
    static boolean visited[][];
    static int cnt;
    static int A[][];
    static ArrayList<int[]> union = new ArrayList<>();

    static boolean isMove;
    static int N;
    static int L;
    static int R;

    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        A = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                A[i][j] = sc.nextInt();
            }
        }

        cnt = 0;
        isMove = false;


        //이동이 더이상 일어나지 않을 때까지 모든 지접에서 연결 확인
        while(true){
            isMove = false;
            visited = new boolean[N][N];

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    //방문안한 지점끼리만 연결해서 서로 인구를 이동해준다.
                    if(!visited[i][j]){
                        BFS(i, j);
                    }
                }
            }
            if(!isMove){
                break;
            } else{
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void BFS(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        //방문 시작 노드 추가
        q.offer(new int[] {x, y});
        //방문 표시
        visited[x][y] = true;
        //인구 변경할 목록에 추가
        union.add(new int[] {x, y});

        while(!q.isEmpty()){
            int now[] = q.poll();
            for(int i = 0; i < 4; i++){
                int tempX = now[0] + dx[i];
                int tempY = now[1] + dy[i];

                if(tempX >= 0 && tempY >= 0 && tempX < N && tempY < N){
                    if(!visited[tempX][tempY] && Math.abs(A[now[0]][now[1]] - A[tempX][tempY]) >= L && Math.abs(A[now[0]][now[1]] - A[tempX][tempY]) <= R){
                        q.add(new int[] {tempX, tempY});
                        union.add(new int[] {tempX, tempY});
                        visited[tempX][tempY] = true;
                        isMove =true;
                    }
                }
            }
        }

        int sum = 0;
        //이동이 가능한 국가들의 인구 합을 구함
        for(int i = 0; i < union.size(); i++){
            int move[] = union.get(i);
            sum += A[move[0]][move[1]]; 
        }
        for(int i = 0; i < union.size(); i++){
            int change[] = union.get(i);
            A[change[0]][change[1]] = sum / union.size();
        }
        //union arraylist에서 union에 해당하는 항목 모두 삭제
        union.removeAll(union);

    }
}
