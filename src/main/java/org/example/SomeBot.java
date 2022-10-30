package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SomeBot extends TelegramLongPollingBot {
    private String botToken = System.getenv("BOT_TOKEN");
    private String botUserName="kmnbvcsxawersctvybunbot";
    @Override
    public String getBotToken(){
        return botToken;
    }
    private Logic Bot=new Logic();
    @Override
    public void onUpdateReceived(Update update) {
        var msg=update.getMessage();
        var usid = msg.getFrom().getId();
        var text = msg.getText();
        sendMsg(usid,Bot.parseMessage(text));
    }
    @Override
    public String getBotUsername() {
        return botUserName;
    }

    public void sendMsg(Long usid, String text){
        SendMessage msg = SendMessage.builder().chatId(usid.toString()).text(text).build();
        try {
            execute(msg);
        } catch(TelegramApiException err){
            throw new RuntimeException(err);
        }
    }
}