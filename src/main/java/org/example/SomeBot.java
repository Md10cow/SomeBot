package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SomeBot extends TelegramLongPollingBot {
    private final String botToken = System.getenv("BOT_TOKEN");
    @Override
    public String getBotToken(){
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg=update.getMessage();
        var user = msg.getFrom();
        var text = msg.getText();
        sendMsg(user.getId(),text);
    }

    @Override
    public String getBotUsername() {
        return "kmnbvcsxawersctvybunbot";
    }

    public void sendMsg(Long accptr, String text){
        SendMessage msg = SendMessage.builder().chatId(accptr.toString()).text(text).build();
        try {
            execute(msg);
        } catch(TelegramApiException err){
            throw new RuntimeException(err);
        }
    }
}