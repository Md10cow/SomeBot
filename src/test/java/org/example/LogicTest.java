package org.example;
import static java.lang.Double.NaN;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {
    Logic bot = new Logic();
    /** тесты для проверки ввода чисел */
    @Test
    public void testCopy() {
        assertEquals(8.4, bot.parseArg("8.4"));
        assertEquals(8.4, bot.parseArg("8,4"));
        assertEquals(8, bot.parseArg("8"));
        assertEquals(0x7ff8000000000000L, bot.parseArg("~fawa"));
    }

    @Test
    public void parseMessage() {
        /** тесты для start, calc и бессмысленного сообщения */
        bot.wmode = 0;
        bot.rmode = 0;
        assertEquals("Здравствуйте, вас приветствует программа, подсчитывающая доходность вкладов или же сумму для выплаты кредита. \n" +
                "Пожалуйста, выберите, что вы хотите рассчитать (Напишите /vklad или /kredit)", bot.parseMessage("/start"));
        assertEquals("/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню", bot.parseMessage("/calc"));
        assertEquals("", bot.parseMessage("wacagesag"));


        /** тесты для help */
        bot.wmode = 0;
        assertEquals("Здравствуйте, вас приветствует программа, подсчитывающая доходность вкладов или же сумму для выплаты кредита. \n" +
                "Пожалуйста, выберите, что вы хотите рассчитать (Напишите /vklad или /kredit)", bot.parseMessage("/help"));
        bot.wmode = 1;
        assertEquals("Калькулятор вкладов и кредитов\n/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню", bot.parseMessage("/help"));
        bot.wmode = 2;
        assertEquals("Подсчитывает итоговую сумму по вкладу (вместе с процентами). Введите то, что просит бот.\n/return - в главное меню", bot.parseMessage("/help"));
        bot.wmode = 3;
        assertEquals("Подсчитывает итоговую сумму по кредиту (вместе с выплатами). Введите то, что просит бот.\n/return - в главное меню", bot.parseMessage("/help"));


        /** тесты для return */
        bot.wmode = 0;
        assertEquals("", bot.parseMessage("/return"));
        bot.wmode = 1;
        assertEquals("", bot.parseMessage("/return"));
        bot.wmode = 2;
        assertEquals("/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню", bot.parseMessage("/return"));
        bot.wmode = 3;
        assertEquals("/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню", bot.parseMessage("/return"));


        /** тесты для команды vklad и проверки расчётов по вкладу */
        assertEquals("Принято, введите сумму вклада. \n/return - в главное меню", bot.parseMessage("/vklad"));

        assertEquals("неизвестные символы", bot.parseMessage("h1"));
        assertEquals("Введите годовую процентную ставку. ", bot.parseMessage(String.valueOf(1000)));

        assertEquals("неизвестные символы", bot.parseMessage("h1"));
        assertEquals("Введите количество лет, которые будет храниться вклад.", bot.parseMessage(String.valueOf(5)));

        assertEquals("неизвестные символы", bot.parseMessage("h1"));
        assertEquals("За " + 2.0 + " лет получите " + 1100.0 + " рублей из которых " +
                100.0 + " являются вашим доходом с вклада.  \n" +
                "Введитие сумму кредита, чтобы снова начать работать с калькулятором вкладов. \n" +
                "Введите /kredit, чтобы начать работать с калькулятором кредитов. \n" +
                "Введите /return, чтобы вернуться в главное меню.", bot.parseMessage(String.valueOf(2)));


        /** тесты для команды kredit и проверки расчётов по кредиту */
        assertEquals("Принято, введите сумму кредита. \n/return - в главное меню", bot.parseMessage("/kredit"));

        assertEquals("неизвестные символы", bot.parseMessage("h1"));
        assertEquals("Введите годовую процентную ставку. ", bot.parseMessage(String.valueOf(10000)));

        assertEquals("неизвестные символы", bot.parseMessage("h1"));
        assertEquals("Введите количество лет, которые будет оплачиваться кредит.", bot.parseMessage(String.valueOf(2)));

        assertEquals("неизвестные символы", bot.parseMessage("h1"));
        assertEquals("За " + 3.0 + " лет вы выплатите банку " + 10311.328343684076 + " рублей из которых " +
                311.32834368407566 + " являются переплатой. \n" +
                "Введитие сумму кредита, чтобы снова начать работать с калькулятором кредитов. \n" +
                "Введите /vklad, чтобы начать работать с калькулятором вкладов. \n" +
                "Введите /return, чтобы вернуться в главное меню.", bot.parseMessage(String.valueOf(3)));
    }
}