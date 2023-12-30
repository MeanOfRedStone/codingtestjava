package samsungswexpert;

/*
 (1) office 깊은복사, 얕은 복사

 (2) 빈공간 구하기 최솟값 비교에 필요한 변수

 (3) 이동 변수 따로 만들어서 설정
 */

import java.io.*;
import java.util.*;

public class Baekjoon15683 {

    static int cctvCount;
    static int[] cctvsDirection;
    static int[][] office;
    static int N;
    static int M;
    static int blank;
    static List<int[]> cctvLocation;
    static int[][] copiedOffice;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int copiedAnswer;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] officeInput = br.readLine().split(" ");
        //1 <= N, M <= 8
        N = Integer.parseInt(officeInput[0]);
        M = Integer.parseInt(officeInput[1]);

        office = new int[N][M];
        //cctv 최대 개수 : 8대
        cctvCount = 0;
        
        cctvLocation = new ArrayList<>();

        blank = 0;

        for(int i = 0; i < N; i++) {
            String[] squareInput = br.readLine().split(" ");
            for(int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(squareInput[j]);
                if(office[i][j] > 0 && office[i][j] < 6) {
                    cctvCount++;
                    cctvLocation.add(new int[] {i, j});
                    continue;
                }

                if(office[i][j] == 0) {
                    blank++;
                }
            }
        }

        cctvsDirection = new int[cctvCount];
        answer = Integer.MAX_VALUE;

        for(int i = 0; i < 4; i++) {
            directionCombination(i, 0);
        }
        
        System.out.println(answer);
    }

    public static void directionCombination(int direction, int depth) {
        if(depth == cctvCount) {
            findBlindSpot();
            return;
        }

        cctvsDirection[depth] = direction;
        
        for(int i = 0; i < 4; i++) {
            directionCombination(i, depth + 1);
        }
    }

    public static void findBlindSpot() {
        copiedOffice = new int[N][M];
        for(int a = 0; a < N; a++) {
            for(int b = 0; b < M; b++) {
                copiedOffice[a][b] = office[a][b];
            }
        }

        copiedAnswer = blank;
        
        for(int i = 0; i < cctvCount; i++) {
            int[] cctv = cctvLocation.get(i);
            int direction = cctvsDirection[i];

            fillArea(cctv, direction);
        }

        answer = Math.min(copiedAnswer, answer);
    }

    public static void fillArea(int[] cctv, int direction) {
        int row = cctv[0];
        int col = cctv[1];
        int cctvType = copiedOffice[row][col];
        
        if(cctvType == 1) {
            int moveRow = row;
            int moveCol = col;

            //1. 정방향
            while(true) {
                moveRow = moveRow + dx[direction];
                moveCol = moveCol + dy[direction];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }

            return;
        }

        if(cctvType == 2) {
            //1. 정방향
            int moveRow = row;
            int moveCol = col;

            while(true) {
                moveRow = moveRow + dx[direction];
                moveCol = moveCol + dy[direction];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }

            //2. 반대방향
            moveRow = row;
            moveCol = col;

            while(true) {
                moveRow = moveRow - dx[direction];
                moveCol = moveCol - dy[direction];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }

            return;
        }

        if(cctvType == 3) {
            //1. 정방향
            int moveRow = row;
            int moveCol = col;

            while(true) {
                moveRow = moveRow + dx[direction];
                moveCol = moveCol + dy[direction];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }

            //2. 90도 회전
            moveRow = row;
            moveCol = col;

            while(true) {
                moveRow = moveRow + dx[(direction + 1) % 4];
                moveCol = moveCol + dy[(direction + 1) % 4];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }

            return;
        }

        if(cctvType == 4) {
            //1. 정방향
            int moveRow = row;
            int moveCol = col;

            while(true) {
                moveRow = moveRow + dx[direction];
                moveCol = moveCol + dy[direction];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }

            //2. 90도 회전
            moveRow = row;
            moveCol = col;

            while(true) {
                moveRow = moveRow + dx[(direction + 1) % 4];
                moveCol = moveCol + dy[(direction + 1) % 4];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }

            //3. 90도 회전 반대 방향
            moveRow = row;
            moveCol = col;

            while(true) {
                moveRow = moveRow - dx[(direction + 1) % 4];
                moveCol = moveCol - dy[(direction + 1) % 4];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }
            
            return;
        }

        if(cctvType == 5) {
            //1. 정방향
            int moveRow = row;
            int moveCol = col;

            while(true) {
                moveRow = moveRow + dx[direction];
                moveCol = moveCol + dy[direction];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }

            //2. 90도 회전
            moveRow = row;
            moveCol = col;

            while(true) {
                moveRow = moveRow + dx[(direction + 1) % 4];
                moveCol = moveCol + dy[(direction + 1) % 4];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }

            //3. 90도 회전 반대 방향
            moveRow = row;
            moveCol = col;

            while(true) {
                moveRow = moveRow - dx[(direction + 1) % 4];
                moveCol = moveCol - dy[(direction + 1) % 4];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }

            //4. 반대방향
            moveRow = row;
            moveCol = col;

            while(true) {
                moveRow = moveRow - dx[direction];
                moveCol = moveCol - dy[direction];

                //분기1 : 이동한 위치가 사무실의 인덱스를 벗어날 때
                if(moveRow >= N || moveCol >= M || moveRow < 0 || moveCol < 0) {
                    break;
                }
                
                //분기2 : 이동한 위치가 벽일 때
                if(copiedOffice[moveRow][moveCol] == 6) {
                    break;
                }

                //분기3 : 이동한 위치가 빈 공간일 때
                if(copiedOffice[moveRow][moveCol] == 0) {
                    copiedOffice[moveRow][moveCol] = -1;
                    copiedAnswer--;
                }
            }

            return;
        }
    }
}

// 4(1) 16(2) 64(3) 256(4) 1024(5) 4096(6) 16384(7) 65536(8)
