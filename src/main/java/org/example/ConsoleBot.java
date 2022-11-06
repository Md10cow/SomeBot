package org.example;

import java.util.Scanner;

public class ConsoleBot {
    Logic bot=new Logic();
    public void botStart(){
        Scanner in=new Scanner(System.in);
        while(true){
            String msg=in.nextLine();
            var nmsg=bot.parseMessage(msg);
            if(!nmsg.equals(""))
                System.out.println(nmsg);
        }
    }
}
