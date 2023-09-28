package searching;

/*
<백준 2667>
<그림 1>과 같이 정사각형 모양의 지도가 있다. 
1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 
철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 
여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 
대각선상에 집이 있는 경우는 연결된 것이 아니다. 
<그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 
지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
*/

/*
 <출제 포인트>
 (1) 그래프 단절점 찾기
  -> BFS DFS 둘다 써도 됨

 (2) 배열과 ArrayList 정렬 헷갈리지 말자
 - Arraylist : Collections.sort()
 - 배열 : Arrays.sort()

 (3) 띄어쓰기 안하고 배열 입력 받는법
 - 처음엔 스트링 이후 변화해서 숫자로
 String strArr = sc.next().split("");
 A[i][j] = Integer.valueOf(strArr[j]);

 */
import java.util.*;
public class Baekjoon2667 {
    //주택 배열
    static int A[][];
    //방문 배열
    static boolean visited[][];
    //방향
    static int[] dx = {1, 0 ,-1 , 0};
    static int[] dy = {0, 1, 0, -1}; 
    static int N;
    static int apartment;
    //ArrayList 하나만 쓸거기 때문에 <Integer> 뒤에 [] 생략
    static ArrayList<Integer> apartmentCount;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        
        A = new int[N][N];
        visited = new boolean[N][N];


        for(int i = 0; i < N; i++){
            String[] strArr = sc.next().split("");
            for(int j = 0; j < N; j++){        
                A[i][j] = Integer.valueOf(strArr[j]);
            }
        }
        apartment = 0;
        apartmentCount = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    BFS(i, j);
                }
            }
        }
        System.out.println(apartment);
        Collections.sort(apartmentCount);
        for(int i : apartmentCount){
            System.out.println(i);
        }
        
    }
    public static void BFS(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        visited[r][c] = true;
        //주택이 존재할 경우
        if(A[r][c] == 1){
            int count = 0;
            apartment += 1;
            q.offer(new int[] {r, c});
            while(!q.isEmpty()){
                //현재위치
                int[] now = q.poll();
                count += 1;
                //현재 위치에 아파트 단지수 입력
                A[now[0]][now[1]] = apartment;
                for(int i = 0; i < 4; i++){
                    int x = now[0] +dx[i];
                    int y = now[1] + dy[i];
                    //현재 위치의 주변 중 주택이 있고 방문하지 않는 곳 단지에 포함
                    if(x < N && y < N && x >= 0 && y >= 0 && A[x][y] == 1 && !visited[x][y]){
                        visited[x][y] = true;
                        q.offer(new int[] {x, y});
                    }
                }
            }
            apartmentCount.add(count);
        }
    }
}
