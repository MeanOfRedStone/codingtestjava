package samsungswexpert;
        
        /*
         각 시험장마다 밀요한 감독관의 최소 수
         그리디 알고리즘

         <주의 사항. 디버깅할 때 오류였던 부분>
         (1) 예외 사항
         학생수보다 감독관의 감시자수가 더 커서
         !남은 학생수가 음수가 되는 경우

         (2) 변수 타입
         int의 한계 2,147,483,647
         Long 사용 필요
         */
import java.io.*;
import java.util.*;

public class Baekjoon13458 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //시험장수
        int N = Integer.parseInt(br.readLine());
        //시험장 별로 학생수를 등록
        List<Integer> classes = new ArrayList<>();

        //응시자의 수
        String[] studentsInput = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            int A = Integer.parseInt(studentsInput[i]);
            classes.add(A);
        }

        String[] managersInput = br.readLine().split(" ");
        //총감독관이 한 시험장에서 감시할 수 있는 응시자의 수
        int B = Integer.parseInt(managersInput[0]);
        //부감독관이 한 시험장에서 감시할 수 있는 응시자의 수
        int C = Integer.parseInt(managersInput[1]);

        Long totalManagers = (long) 0;
        for(int students : classes){
            int generalManger = 1;
            //총 감독관이 관리하고 남은 학생 수
            int restStudents = students - B;

            if(restStudents < 0){
                totalManagers++;
            } else{
                //남은 인원을 관리하는데 필요한 부감독관의 수
                int managers = restStudents / C;
                if(restStudents % C != 0){
                    managers++;
                }
                totalManagers += generalManger + managers;
            }
        }

        System.out.println(totalManagers);
    }
}
