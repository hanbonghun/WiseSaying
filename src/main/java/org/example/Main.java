package org.example;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");
        while(true){
            System.out.print("명령 ) ");
            String cmd = sc.nextLine();
            if(cmd.equals("종료")){
                return;
            }else if (cmd.equals("등록")){
                System.out.print("명언 : ");
                String quote = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();
            }
        }
    }
}