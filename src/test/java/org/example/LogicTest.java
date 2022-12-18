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
        assertEquals("Здравствуйте, вам приветствует программа, подсчитывающая доходность вкладов, " +
                "сумму для выплаты кредита или же помогающая в некоторых юридических/финансовых вопросах по работе с банком. \n" +
                "Пожалуйста, выберите, что хотите сделать (напишите /vklad, /kredit или /vopros) ", bot.parseMessage("/start", 0L));
    }

    /** Проверка бессмысленных сообщений/команд */
    @Test
    public void testSenseless() {
        assertEquals("", bot.parseMessage("wacagesag", 0L));
        assertEquals("", bot.parseMessage("/return", 0L)); //return из 0 позиции
    }

    /** тесты для расчёта вклада*/
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

    /** тесты для вопросов */
    @Test
    public void testVopros(){
        assertEquals("На данный момент доступны разделы:\n1)Документы\n2)Финансовые операции\n3)Иное\n" +
                "Выберите цифру раздела по которому возник вопрос.", bot.parseMessage("/vopros", 0L));

        assertEquals("В этом разделе доступны следующие вопросы:\n1)Документы для получения кредита/вклада\n" +
                "2)Документы для покупки дома с земельным участком\nВыберите цифру темы, на которую хотите" +
                " получить ответ или вернитесь обратно с помощью /return", bot.parseMessage(String.valueOf(1), 0L));

        assertEquals("По данной теме есть следующие ссылки:\n" +
                "https://ibogatyr.ru/blog/oformlenie-doma-kakie-dokumenty-nuzhny/\n" +
                "Напишите 0, если хотите вернуться к выбору раздела или выберите один из" +
                " вопросов текущего раздела.", bot.parseMessage(String.valueOf(2), 0L));
    }
}