package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* основа программы, отвечает за логику бота */
public class Logic{
    private Map<Long,Uvars> uList=new HashMap<>();
    private ArrayList<QARecord> qAArr =null;
    private AuxLogic auxLogic=new AuxLogic();
    public boolean isBotInitialized=false;

    /**
     * Калькулятор вкладов и кредитов, а также банковский помощник
     *
     * @param msg сообщение пользователя
     * @param usid id пользователя
     */
    public String parseMessage(String msg, Long usid) {
        if (!isBotInitialized) {
            qAArr = auxLogic.parseFile();
            isBotInitialized=true;
        }
        String answer;
        Uvars uvars=new Uvars();
        uList.putIfAbsent(usid,uvars);
        uvars=uList.get(usid);
        /* вспомогательная команда для пользователя */
        if(msg.equals("/help"))
            switch(uvars.wmode) {
                case 1:
                    answer = "Даёт ссылку на вопрос по выбранной теме. Введите цифру нужного вам раздела/темы."; //вопрос
                    break;
                case 2:
                    answer = "Подсчитывает итоговую сумму по вкладу (вместе с процентами)." +
                            " Введите то, что просит бот.\n/return - в главное меню";
                    break;
                case 3:
                    answer = "Подсчитывает итоговую сумму по кредиту (вместе с выплатами)." +
                            " Введите то, что просит бот.\n/return - в главное меню";
                    break;
                default:
                    answer = "Здравствуйте, вам приветствует программа, подсчитывающая доходность вкладов, " +
                            "сумму для выплаты кредита или же помогающая в некоторых юридических/финансовых вопросах по работе с банком. \n" +
                            "Пожалуйста, выберите, что хотите сделать (напишите /vklad, /kredit или /vopros) ";
            }
        /* старт */
        else if(msg.equals("/start")) {
            uvars.rmode = 0;
            uvars.wmode = 0;
            answer = "Здравствуйте, вам приветствует программа, подсчитывающая доходность вкладов, " +
                    "сумму для выплаты кредита или же помогающая в некоторых юридических/финансовых вопросах по работе с банком. \n" +
                    "Пожалуйста, выберите, что хотите сделать (напишите /vklad, /kredit или /vopros) ";
        }
        /* команда для списка имеющихся разделов */
        else if(msg.equals("/vopros")) {
            uvars.wmode = 1;
            uvars.rmode = 1;
            answer="На данный момент доступны разделы:\n"; //до списка разделов
            for(int i = 0; i< qAArr.size(); i++)
                answer+=Integer.toString(i+1)+")"+ qAArr.get(i).sect+"\n"; //список разделов
            answer+="Выберите цифру раздела по которому возник вопрос."; //после списка разделов
        }
        /* команда для возвращения */
        else if(msg.equals("/return")){
            switch(uvars.wmode){
                case 1:
                case 2:
                case 3:
                    uvars.wmode=0;
                    uvars.rmode=0;
                    answer="Пожалуйста, выберите, что вы хотите рассчитать или спросить (Напишите /vklad, /kredit или /vopros)";
                    break;
                default:
                    answer="";
            }
            uvars.rmode=0;
        }
        /* запрос данных */
        else if(msg.equals("/vklad")){
            uvars.wmode=2;
            answer = "Принято, введите сумму вклада. \n/return - в главное меню";
            uvars.rmode=1;
        }
        else if(msg.equals("/kredit")){
            uvars.wmode=3;
            answer = "Принято, введите сумму кредита. \n/return - в главное меню";
            uvars.rmode=1;
        }
        else{
            switch (uvars.rmode) {
                case 1:
                    uvars.arg1 = auxLogic.parseArg(msg);
                    if (uvars.arg1 == auxLogic.nan)
                        return "неизвестные символы";
                    switch (uvars.wmode) {
                        case 1:
                            if ((int) uvars.arg1 > qAArr.size() || (int) uvars.arg1 <= 0) {
                                answer = "Такой цифры нет в данном меню." +
                                        " Пожалуйста, напишите одну из доступных цифр.\n"; //текст если число вне диапазона
                            } else {
                                answer = "В этом разделе доступны следующие вопросы:\n"; //текст до списка вопросов
                                for (int i = 0; i < qAArr.get((int)uvars.arg1 - 1).qSize(); i++)
                                    answer += Integer.toString(i + 1) +
                                            ")" + qAArr.get((int) uvars.arg1 - 1).qGet(i) + "\n"; //список вопросов
                                answer += "Выберите цифру темы, на которую хотите получить" +
                                        " ответ или вернитесь обратно с помощью /return"; //текст после списка вопросов
                                uvars.rmode = 2;
                            }
                            break;
                        case 2:
                            answer = "Введите годовую процентную ставку. "; //вклад
                            uvars.rmode = 2;
                            break;
                        case 3:
                            answer = "Введите годовую процентную ставку. "; //кредит
                            uvars.rmode = 2;
                            break;
                        default:
                            answer = "";
                    }
                    break;
                case 2:
                    uvars.arg2 = auxLogic.parseArg(msg);
                    if (uvars.arg2 == auxLogic.nan)
                        return "неизвестные символы";
                    switch (uvars.wmode) {
                        case 1:
                            uvars.arg2 = Integer.parseInt(msg);
                            if ((int) uvars.arg2 > qAArr.get((int) uvars.arg1 - 1).aSize() || (int) uvars.arg2 < 0) {
                                answer = "Такой цифры нет в данном меню. Пожалуйста, напишите одну из доступных цифр.\n"; //число вне диапазона
                            } else if ((int) uvars.arg2 == 0) {
                                answer = "На данный момент доступны разделы:\n"; //до списка разделов
                                for (int i = 0; i < qAArr.size(); i++)
                                    answer += Integer.toString(i + 1) + ")" + qAArr.get(i).sect + "\n"; //список разделов
                                answer += "Выберите цифру раздела по которому возник вопрос."; //после списка разделов
                                uvars.rmode = 1;
                            } else {
                                answer = "По данной теме есть следующие ссылки:\n"; //до списка ответов
                                answer += qAArr.get((int) uvars.arg1 - 1).aGet((int) uvars.arg2 - 1) + "\n"; //список ответов
                                answer += "Напишите 0, если хотите вернуться к выбору раздела или выберите один из" +
                                        " вопросов текущего раздела."; //после списка ответов
                            }
                            break;
                        case 2:
                            answer = "Введите количество лет, которые будет храниться вклад.";
                            uvars.rmode = 3;
                            break;
                        case 3:
                            answer = "Введите количество лет, которые будет оплачиваться кредит.";
                            uvars.rmode = 3;
                            break;
                        default:
                            answer = "";
                    }
                    break;
                /* подсчёты и вывод */
                case 3:
                    uvars.arg3 = auxLogic.parseArg(msg);
                    if (uvars.arg3 == auxLogic.nan)
                        return "неизвестные символы";
                    switch (uvars.wmode) {
                        case 2:
                            double msum = uvars.arg1 * uvars.arg2 * uvars.arg3 / 100;
                            uvars.rmode = 1;
                            answer = "За " + uvars.arg3 + " лет получите " + Double.toString(msum + uvars.arg1) +
                                    " рублей из которых " + Double.toString(msum) + " являются вашим доходом с вклада.  \n" +
                                    "Введитие сумму кредита, чтобы снова начать работать с калькулятором вкладов. \n" +
                                    "Введите /kredit, чтобы начать работать с калькулятором кредитов. \n" +
                                    "Введите /return, чтобы вернуться в главное меню.";
                            break;
                        case 3:
                            double mper = uvars.arg2 / 1200;
                            double mtime = uvars.arg3 * 12;
                            msum = uvars.arg1 * mper * (1 + 1 / (Math.pow(1 + mper, mtime) - 1));
                            uvars.rmode = 1;
                            answer = "За " + uvars.arg3 + " лет вы выплатите банку " + Double.toString(msum * mtime) +
                                    " рублей из которых " + Double.toString(msum * mtime - uvars.arg1) + " являются переплатой. \n" +
                                    "Введитие сумму кредита, чтобы снова начать работать с калькулятором кредитов. \n" +
                                    "Введите /vklad, чтобы начать работать с калькулятором вкладов. \n" +
                                    "Введите /return, чтобы вернуться в главное меню.";
                            break;
                        default:
                            answer = "";
                    }
                    break;
                default:
                    answer = "";
            }
        }
        return answer;
    }
}
