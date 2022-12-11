package org.example;
import java.util.Scanner;
/**
 * класс, отвечающий за работу в консоли
 */
public class ConsoleBot {
    Logic bot=new Logic();
    public void botStart(){
        Scanner in=new Scanner(System.in);
        bot.initBot();
        while(true){
            String msg=in.nextLine();
            var nmsg=bot.parseMessage(msg, 0L);
            if(!nmsg.equals(""))
                System.out.println(nmsg);
        }
    }
}
