package org.example;

import java.util.Scanner;

public class ConsoleBot {
    Logic Bot=new Logic();
    public void botStart(){
        Scanner in=new Scanner(System.in);
        while(true){
            String msg=in.nextLine();
            System.out.println(Bot.parseMessage(msg));
        }
    }
}
