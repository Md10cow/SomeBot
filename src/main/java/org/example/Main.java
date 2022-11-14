package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * основа
 * создано 06.11.2022
 * переключатель режима между телеграмом и консолью
 */
public class Main {
    public static void main(String[] args) throws TelegramApiException {
        int workMode=1;
        switch(workMode){
            case 1:
                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                botsApi.registerBot(new TelegramBot());
            case 2:
                ConsoleBot bot=new ConsoleBot();
                bot.botStart();
        }
    }
}