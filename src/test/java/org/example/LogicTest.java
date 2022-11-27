package org.example;
import static java.lang.Double.NaN;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {
    Logic bot = new Logic();

    @Test
    public void parseMessage() {
        int wmode = 0;
        int rmode = 0;
        /*
        assertEquals("Здравствуйте, вас приветствует программа, подсчитывающая доходность вкладов или же сумму для выплаты кредита. \n" +
                "Пожалуйста, выберите, что вы хотите рассчитать (Напишите /vklad или /kredit)", bot.parseMessage("/start"));
        assertEquals("/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню", bot.parseMessage("/calc"));
        assertEquals("", bot.parseMessage("wacagesag"));


        assertEquals("Здравствуйте, вас приветствует программа, подсчитывающая доходность вкладов или же сумму для выплаты кредита. \n" +
                "Пожалуйста, выберите, что вы хотите рассчитать (Напишите /vklad или /kredit)", bot.parseMessage("/help"));
        wmode = 1;
        assertEquals("Калькулятор вкладов и кредитов\n/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню", bot.parseMessage("/help"));
        wmode = 2;
        assertEquals("Подсчитывает итоговую сумму по вкладу (вместе с процентами). Введите то, что просит бот.\n/return - в главное меню", bot.parseMessage("/help"));
        wmode = 3;
        assertEquals("Подсчитывает итоговую сумму по кредиту (вместе с выплатами). Введите то, что просит бот.\n/return - в главное меню", bot.parseMessage("/help"));


        wmode = 0;
        assertEquals("", bot.parseMessage("/return"));
        wmode = 1;
        assertEquals("", bot.parseMessage("/return"));
        wmode = 2;
        assertEquals("/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню", bot.parseMessage("/return"));
        wmode = 3;
        assertEquals("/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню", bot.parseMessage("/return"));


        assertEquals("Принято, введите сумму вклада. \n/return - в главное меню", bot.parseMessage("/vklad"));

        assertEquals("неизвестные символы", bot.parseMessage(String.valueOf(NaN)));
        assertEquals("Введите годовую процентную ставку. ", bot.parseMessage(String.valueOf(1000)));

        assertEquals("неизвестные символы", bot.parseMessage(String.valueOf(NaN)));
        assertEquals("Введите количество лет, которые будет храниться вклад.", bot.parseMessage(String.valueOf(5)));

        assertEquals("неизвестные символы", bot.parseMessage(String.valueOf(NaN)));
        assertEquals("За " + 2.0 + " лет получите " + 1100.0 + " рублей из которых " +
                100.0 + " являются вашим доходом с вклада.  \n" +
                "Введитие сумму кредита, чтобы снова начать работать с калькулятором вкладов. \n" +
                "Введите /kredit, чтобы начать работать с калькулятором кредитов. \n" +
                "Введите /return, чтобы вернуться в главное меню.", bot.parseMessage(String.valueOf(2)));


        assertEquals("Принято, введите сумму кредита. \n/return - в главное меню", bot.parseMessage("/kredit"));

        assertEquals("неизвестные символы", bot.parseMessage(String.valueOf(NaN)));
        assertEquals("Введите годовую процентную ставку. ", bot.parseMessage(String.valueOf(10000)));

        assertEquals("неизвестные символы", bot.parseMessage(String.valueOf(NaN)));
        assertEquals("Введите количество лет, которые будет оплачиваться кредит.", bot.parseMessage(String.valueOf(2)));

        assertEquals("неизвестные символы", bot.parseMessage(String.valueOf(NaN)));
        assertEquals("За " + 3.0 + " лет вы выплатите банку " + 10311.328343684076 + " рублей из которых " +
                311.32834368407566 + " являются переплатой. \n" +
                "Введитие сумму кредита, чтобы снова начать работать с калькулятором кредитов. \n" +
                "Введите /vklad, чтобы начать работать с калькулятором вкладов. \n" +
                "Введите /return, чтобы вернуться в главное меню.", bot.parseMessage(String.valueOf(3)));*/
    }
}