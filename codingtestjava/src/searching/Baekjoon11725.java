package searching;
/*
 <출제 포인트 및 풀이 평가>
 (1) 기존의 풀이
  - for문과 if문 그리고 ArrayList 의 메소드를 사용해서 노드 삽입 및 루트 확인
  -> 풀리긴 하였으나 시간 초과 발생
  (참고) ArrayList에 isEmpty / contains가 있다.

 (2) ArrayList + DFS 는 트리 만들기의 혁명이다.
 - boolean visted[] 로 인해 양방향 그래프를 만들어도(시간 단축 효과) 먼저 방문한 곳을 제외시켜
   트리처럼 순회(루트 노드 찾는 시간 단축 효과)할 수 있다.
 */
/*
루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.
visted 
| t |  |  |  |  | t |  |

1 6 

1 -> 6 -> 4
2 -> 4
3 -> 5 -> 6
4 -> 1 -> 2 -> 7
5 -> 3
6 -> 1 -> 3
7 -> 4

1 -> 6 -> 3 ->5
   
    -> 4 -> 2
         -> 7

1 -> 6
3 -> 5
6 -> 3

4 -> 1 
2 -> 4
4 -> 7

 



 1 -> 2 -> 4 -> 7
             -> 8
   -> 3 -> 5  -> 9
              -> 10
        -> 6 -> 11
              -
            
*/
import java.util.*;
public class Baekjoon11725 {
    static boolean visited[];
    static ArrayList<Integer>[] tree;
    static int root[];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        tree = new ArrayList[N+1];
        visited = new boolean[N+1];
        root = new int[N+1];

        for(int i = 1; i < N + 1; i++){
            tree[i] = new ArrayList<>();
        }


        for(int i = 0; i < N - 1; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            tree[s].add(e);
            tree[e].add(s);
        }
        // for(int i = 0; i < N - 1; i++){
        //     int s = sc.nextInt();
        //     int e = sc.nextInt();
        //     if(s == 1){
        //         tree[s].add(e);
        //     } else if(e == 1){
        //         tree[e].add(s);
        //     } else{
        //         for(int j = 1; j < N + 1; j++){
        //             if(tree[j].contains(s)){
        //                 tree[s].add(e);
        //                 break;
        //             } else if(tree[j].contains(e)){
        //                 tree[e].add(s);
        //                 break;
        //             }
        //         }
                
        //     }
        // }

        // for(int i = 2; i < N + 1; i++){
        //     for(int j = 1; j < N + 1;j++){
        //         if(tree[j].contains(i)){
        //             System.out.println(j);
        //             break;
        //         }
        //     }
        // }
        DFS(1);
        for(int i = 2; i < N + 1; i++){
            System.out.println(root[i]);
        }

    }

    static void DFS(int i){
        if(visited[i]){
            return;
        }
        visited[i] = true;
        for(int x : tree[i]){
            if(!visited[x]){
                root[x] = i;
                DFS(x);
            }
        }
    }
}
