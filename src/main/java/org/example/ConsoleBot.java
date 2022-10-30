package org.example;

import java.util.Scanner;

public class ConsoleBot {
    public void botStart(){
        Scanner in=new Scanner(System.in);
        while(true){
            String msg=in.nextLine();
            cOut(msg);
        }
    }
    public void cOut(String text){
        System.out.println(text);
    }
}
