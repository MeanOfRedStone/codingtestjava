package searching;



/*
 <깊이 우선 탐색> : 그래프 완전 탐색 기법 중 하나이다.
 <출제 유형> : 단절점 찾기, 단절선 찾기, 사이클 찾기, 위상 정렬 등
 */
/*
 <문제 23>
 방향 없는 그래프가 주어졌을 때 연결 요소의 개수를 구하는 프로그램을 작성하시오.
 */

 /*
  <출제 포인트>
  (1) DFS(깊이 우선 탐색)에 대한 이해 :   시작 정점에서 한방향으로 계속 가다가 더 이상 갈 수 없게 되면 가장 가까운 갈림길로 돌아와서 
                                        다른 방향으로 다시 탐색을 진행하는방법 예) 트리 순회와 유사 
  (2) 연결 요소에 대한 이해 : 연결 요소 == 그래프가 몇 개로 단절되었는가?
  -> 탐색이 아예 중단된 것을 찾는 문제이다.
  (3) 방향 없는 그래프에 대한 이해 : 양방향 모두 이동할 수 있는 그래프를 의미한다.
  (3) Java로 재귀 호출 할 수 있는가?  
  (4) Java 자료구조 에 대한 이해
  -1 ArrayList에 대한 이해: 메모리가 가변적인 List를 말한다.
  (5) 자바 라이브러리 이해
  -1 BufferedReader :   버퍼를 사용하는 입력은, 키보드의 입력이 있을 때마다 한 문자씩 버퍼로 전송한다. 버퍼가 가득 차거나 혹은 
                        개행 문자가 나타나면 버퍼의 내용을 한 번에 프로그램에 전달한다.
                        예) String s = br.readLine(); 으로 입력 받음 -> 무조건 String으로만 입력됨
  -2 throws IOException : BufferReader 사용하기 위해서 -> Buffered Reader 라이브러리는 입력이 멈추면 오류 발생 -> 이것을 막아주기 위해서 사용한다.
  -3 StringTokenizer :  BufferedReader를 통해 읽어온 데이터는 개행문자 단위(Line 단위)로 나누어진다. 만약 이를 공백 단위로 데이터를 가공하고자 하면 
                        따로 작업을 해주어야 한다. 
                        StringTokenizer의 nextToken() 함수를 쓰면 readLine()을 통해 입력 받은 값을 공백 단위로 구분하여 순서대로 호출할 수 있다.
                        사실 스캐너 띄어쓰기랑 똑같긴 함 BufferedReader 써서 속도 빠르게 입력받으려고 쓰는 것임
                        (주의점 : 반복문 마다 String Tokenizer 실행 해주어야함)
  
  -4 for each 문 이해 for(변수타입 변수 이름: 배열이름) : 배열의 항목 수만큼 실행부분 반복. 반복이 이루어질 때마다 항목을 순서대로 꺼내어 변수에 자동으로 대입
                        */

/*
 <그래프> - 깊이 우선 탐색
 */


import java.util.*;
import java.io.*;

public class Question23 {
    //밑에 함수에서 쓰려고
    static ArrayList<Integer>[] A;
    static boolean visited[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());
        
        //main 밖에서 선언한 ArrayList의 크기를 정해준다.
        A = new ArrayList[n+1]; //연결 리스트가 위치할 인접 리스트(노드의 리스트이다)
        visited = new boolean[n+1];

        for(int i = 1; i < n+1; i++){
            //인접 리스트의 노드 연결 초기화 
            A[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine()); //bufferedreader에서 예외나므로 st는 계속 생성
            int s = Integer.valueOf(st.nextToken());
            int e = Integer.valueOf(st.nextToken());
            //양방향 그래프이므로 모두 넣어준다.
            A[s].add(e);
            A[e].add(s);
        }

        int count = 0;
        for(int i = 1; i < n+1; i++){
            if(!visited[i]){ // 방문하지 않은 노드가 없을 때까지 반복하기
                count++;//연결요소 찾는 것
                DFS(i); //DFS 함수 자체에서 계속 재귀 호출 생김
            }
        }
        System.out.println(count);
    }

    static void DFS(int v){
         if(visited[v]){
            return; //재귀 호출이 끝나는 지점
        }
        visited[v] = true;
        for(int i : A[v]){ //A[v]컨테이너의 시작부터 끝까지 
            if(visited[i] == false){
                DFS(i);
            }
        }
    }
       
}