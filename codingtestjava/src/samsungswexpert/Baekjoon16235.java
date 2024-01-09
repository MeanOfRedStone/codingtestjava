package samsungswexpert;

/*
 <디버깅 포인트>
 1. 배열의 특정 항목 기준으로 List정렬할때
 -> Collections.sort(list, (o1, o2) -> o1[?] - o2[?])
 
 2. 나무 삭제할때 어떻게 배열 문제 해결할지?
 -> 삭제할 인덱스를 내림차순 정렬해 인덱스 큰 항목부터 삭제하면 변하지 않는다

 3. 가을에 나무 생길때 자신이 있는 자리에는 새로운 나무 생기지 않음

 4. 나무가 죽을때는 양분 흡수하지 못한 채 바로 죽는다

 5. Deque과 Queue 사용
 -> deadTree를 따로 관리하는게 큼
 -> List와 Queue를 poll해서 사용하는 것의 시간 차이

 6. Queue 삭제할때마다 size 줄어들어서 for문 붕괴되는 것 어떻게 해결?
 -> i 뒤로 보내서
 */

import java.io.*;
import java.util.*;

public class Baekjoon16235 {
    static int N;
    static int M;
    static int K;
    static int[][] map;
    static int[][] A;
    static Deque<int[]> trees;
    static Queue<int[]> deadTrees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] basicInput = br.readLine().split(" ");
        //땅의 크기 1 <= N <= 10
        N = Integer.parseInt(basicInput[0]);
        //나무의 수 1 <= M <= N^2
        M = Integer.parseInt(basicInput[1]);
        //목표 년수 1<= K <= 1000
        K = Integer.parseInt(basicInput[2]);

        //지면
        map = new int[N + 1][N + 1];

        for(int i = 1; i < N + 1; i++) {
            for(int j = 1; j < N + 1; j++) {
                map[i][j] = 5;
            }
        }

        //지면에 뿌려지는 양분의 양
        A = new int[N + 1][N + 1];

        for(int i = 1; i < N + 1; i++) {
            String[] aInput = br.readLine().split(" ");
            for(int j = 1; j < N + 1; j++) {
                A[i][j] = Integer.parseInt(aInput[j - 1]);
            }
        }

        //나무의 정보
        trees = new LinkedList<>();
        for(int i = 0; i < M; i++) {
            String[] treeInput = br.readLine().split(" ");
            //나무의 위치
            int treeRow = Integer.parseInt(treeInput[0]);
            int treeCol = Integer.parseInt(treeInput[1]);
            int treeAge = Integer.parseInt(treeInput[2]);

            trees.add(new int[] {treeRow, treeCol, treeAge});
        }

        deadTrees = new LinkedList<>();

        int answer = raisePlants(K);

        System.out.println(answer);
    }

    public static int raisePlants(int year) {
        for(int i = 0; i < year; i++) {
            spring();
            summer();
            fall();
            winter();
        }

        return trees.size();
    }
    public static void spring() {
    
        if(trees.isEmpty()) {
            return;
        }

        for(int i = 0; i < trees.size();) {
            int[] tree = trees.poll();
            int treeRow = tree[0];
            int treeCol = tree[1];
            int treeAge = tree[2];

            if(map[treeRow][treeCol] - treeAge < 0) {
                deadTrees.add(new int[] {treeRow, treeCol, treeAge});
                continue;
            }
            //왜 i를 늦게 증가시키는가
            i++;
            map[treeRow][treeCol] -= treeAge;
            treeAge++;
            trees.add(new int[] {treeRow, treeCol, treeAge});
        }
    }

    public static void summer() {
        if(deadTrees.isEmpty()) {
            return;
        }

        for(int i = 0; i < deadTrees.size();) {
            int[] deadTree = deadTrees.poll();
            int treeRow = deadTree[0];
            int treeCol = deadTree[1];
            int treeAge = deadTree[2];

            map[treeRow][treeCol] += treeAge / 2;
        }
    }

    public static void fall() {
        if(trees.isEmpty()) {
            return;
        }

        Queue<int[]> addTree = new LinkedList<>();
        
        for(int[] tree : trees) {
            //tree 인덱스 루프 -> map 배열 범위 안이면 새로운 int[] tree List에 넣어줌
            int treeAge = tree[2];

            if(treeAge % 5 == 0) {
                addTree.add(tree);
            }
        }

        while(!addTree.isEmpty()) {
            int[] tree = addTree.poll();

            int treeRow = tree[0];
            int treeCol = tree[1];
            for(int i = -1; i <= 1; i++) {
                for(int j = -1; j <=1; j++) {
                    if(i == 0 && j == 0) {
                        continue;
                    }

                    if(treeRow + i < 1 || treeRow + i > N || treeCol + j < 1 || treeCol + j > N) {
                        continue;
                    }

                    trees.addFirst(new int[] {treeRow + i, treeCol + j, 1});
                }
            }
        }
    }

    public static void winter() {
        for(int i = 1; i < N + 1; i++) {
            for(int j = 1; j < N + 1; j++) {
                map[i][j] += A[i][j];
            }
        }
    }
}
