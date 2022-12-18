package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
/**
 * класс, отвечающий за работу бота в телеграме
 */
public class TelegramBot extends TelegramLongPollingBot {
    /** токен и имя бота */
    private String botToken = System.getenv("BOT_TOKEN");
    private String botUserName="kmnbvcsxawersctvybunbot";
    @Override
    public String getBotToken(){
        return botToken;
    }
    /** данные от пользователя */
    private Logic bot=new Logic();
    @Override
    public void onUpdateReceived(Update update) {
        var msg=update.getMessage();
        var usid = msg.getFrom().getId();
        var text = msg.getText();
        var nmsg=bot.parseMessage(text,usid);
        if(!nmsg.equals(""))
            sendMsg(usid,nmsg);
    }
    @Override
    public String getBotUsername() {
        return botUserName;
    }
    /** отправка сообщений */
    public void sendMsg(Long usid, String text){
        SendMessage msg = SendMessage.builder().chatId(usid.toString()).text(text).build();
        try {
            execute(msg);
        } catch(TelegramApiException err){
            throw new RuntimeException(err);
        }
    }
}