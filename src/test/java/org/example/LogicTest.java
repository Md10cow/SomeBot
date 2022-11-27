package org.example;
import static java.lang.Double.NaN;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * класс, отвечающий за тесты
 */
public class LogicTest {
    Logic bot = new Logic();

    /** тест для start*/

    @Test
    public void testStart() {
        assertEquals("Здравствуйте, вас приветствует программа, подсчитывающая доходность вкладов или же сумму для выплаты кредита. \n" +
                "Пожалуйста, выберите, что вы хотите рассчитать (Напишите /vklad или /kredit)", bot.parseMessage("/start", 0L));
    }

    /** Проверка бессмысленных сообщений/команд */
    @Test
    public void testSenseless() {
        assertEquals("", bot.parseMessage("wacagesag", 0L));
        assertEquals("", bot.parseMessage("/return", 0L)); //return из 0 позиции
    }

    /** тесты для расчёта вклада и return */
    @Test
    public void testVklad() {
        assertEquals("Принято, введите сумму вклада. \n/return - в главное меню", bot.parseMessage("/vklad", 0L));

        assertEquals("Введите годовую процентную ставку. ", bot.parseMessage(String.valueOf(1000), 0L));

        assertEquals("Введите количество лет, которые будет храниться вклад.", bot.parseMessage(String.valueOf(5), 0L));

        assertEquals("За " + 2.0 + " лет получите " + 1100.0 + " рублей из которых " +
                100.0 + " являются вашим доходом с вклада.  \n" +
                "Введитие сумму кредита, чтобы снова начать работать с калькулятором вкладов. \n" +
                "Введите /kredit, чтобы начать работать с калькулятором кредитов. \n" +
                "Введите /return, чтобы вернуться в главное меню.", bot.parseMessage(String.valueOf(2), 0L));

        /* тест для return во время расчётов */
        assertEquals("Пожалуйста, выберите, что вы хотите рассчитать (Напишите /vklad или /kredit)", bot.parseMessage("/return", 0L));
    }

    /** тесты для расчёта кредита */
    @Test
    public void testKredit() {
        assertEquals("Принято, введите сумму кредита. \n/return - в главное меню", bot.parseMessage("/kredit", 0L));

        assertEquals("Введите годовую процентную ставку. ", bot.parseMessage(String.valueOf(10000), 0L));

        assertEquals("Введите количество лет, которые будет оплачиваться кредит.", bot.parseMessage(String.valueOf(2), 0L));

        assertEquals("За " + 3.0 + " лет вы выплатите банку " + 10311.328343684076 + " рублей из которых " +
                311.32834368407566 + " являются переплатой. \n" +
                "Введитие сумму кредита, чтобы снова начать работать с калькулятором кредитов. \n" +
                "Введите /vklad, чтобы начать работать с калькулятором вкладов. \n" +
                "Введите /return, чтобы вернуться в главное меню.", bot.parseMessage(String.valueOf(3), 0L));
    }
}