package org.example;

import java.util.HashMap;
import java.util.Map;

/** основа программы, отвечает за логику бота */
public class Logic{
    public static double nan=0x7ff8000000000000L;
    private Map<Long,Uvars> uList=new HashMap<>();

    /**
     * Конвертор string в double
     *
     * @param msg сообщение пользователя
     */
    private double parseArg(String msg) {
        int frac = 0;
        double arg = 0;
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '.' || msg.charAt(i) == ',')
                frac = 1;
            else if (msg.charAt(i) >= '0' && msg.charAt(i) <= '9') {
                if (frac == 0)
                    arg = arg * 10 + msg.charAt(i) - '0';
                else {
                    arg += (msg.charAt(i) - '0') * Math.pow(10, -frac);
                    frac++;
                }
            } else
                return nan;
        }
        return arg;
    }

    /**
     * Калькулятор вкладов и кредитов
     *
     * @param msg сообщение пользователя
     * @param usid id пользователя
     */
    public String parseMessage(String msg, Long usid) {
        String answer;
        Uvars uvars=new Uvars();
        uList.putIfAbsent(usid,uvars);
        uvars=uList.get(usid);
        /* вспомогательная команда для пользователя */
        if(msg.equals("/help"))
            switch(uvars.wmode) {
                case 1:
                    answer = "Калькулятор вкладов и кредитов\n/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню";
                    break;
                case 2:
                    answer = "Подсчитывает итоговую сумму по вкладу (вместе с процентами). Введите то, что просит бот.\n/return - в главное меню";
                    break;
                case 3:
                    answer = "Подсчитывает итоговую сумму по кредиту (вместе с выплатами). Введите то, что просит бот.\n/return - в главное меню";
                    break;
                default:
                    answer = "Здравствуйте, вас приветствует программа, подсчитывающая доходность вкладов или же сумму для выплаты кредита. \n" +
                            "Пожалуйста, выберите, что вы хотите рассчитать (Напишите /vklad или /kredit)";
            }
        /* старт */
        else if(msg.equals("/start")) {
            uvars.rmode = 0;
            uvars.wmode = 0;
            answer = "Здравствуйте, вас приветствует программа, подсчитывающая доходность вкладов или же сумму для выплаты кредита. \n" +
                     "Пожалуйста, выберите, что вы хотите рассчитать (Напишите /vklad или /kredit)";
        }
        /* для будущих задач */
        else if(msg.equals("/calc")) {
            uvars.wmode = 1;
            answer = "/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню";
        }
        /* команда для возвращения */
        else if(msg.equals("/return")){
            switch(uvars.wmode){
                case 1:
                case 2:
                case 3:
                    uvars.wmode=0;
                    answer="Пожалуйста, выберите, что вы хотите рассчитать (Напишите /vklad или /kredit)";
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
            switch(uvars.rmode){
                case 1:
                    uvars.arg1=parseArg(msg);
                    if (uvars.arg1==nan)
                        return "неизвестные символы";
                    switch(uvars.wmode) {
                        case 2:
                            answer = "Введите годовую процентную ставку. "; //вклад
                            break;
                        case 3:
                            answer = "Введите годовую процентную ставку. "; //кредит
                            break;
                        default:
                            answer = "";
                    }
                    uvars.rmode=2;
                    break;
                case 2:
                    uvars.arg2=parseArg(msg);
                    if (uvars.arg2==nan)
                            return "неизвестные символы";
                    switch(uvars.wmode) {
                        case 2:
                            answer = "Введите количество лет, которые будет храниться вклад.";
                            break;
                        case 3:
                            answer = "Введите количество лет, которые будет оплачиваться кредит.";
                            break;
                        default:
                            answer = "";
                    }
                    uvars.rmode=3;
                    break;
                /* подсчёты и вывод */
                case 3:
                    uvars.arg3=parseArg(msg);
                    if (uvars.arg3==nan)
                        return "неизвестные символы";
                    switch(uvars.wmode) {
                        case 2:
                            double msum = uvars.arg1*uvars.arg2*uvars.arg3/100;
                            uvars.rmode = 1;
                            answer = "За " + uvars.arg3 + " лет получите " + Double.toString(msum + uvars.arg1) + " рублей из которых " +
                                    Double.toString(msum) + " являются вашим доходом с вклада.  \n" +
                                    "Введитие сумму кредита, чтобы снова начать работать с калькулятором вкладов. \n" +
                                    "Введите /kredit, чтобы начать работать с калькулятором кредитов. \n" +
                                    "Введите /return, чтобы вернуться в главное меню.";
                            break;
                        case 3:
                            double mper = uvars.arg2 / 1200;
                            double mtime = uvars.arg3 * 12;
                            msum = uvars.arg1 * mper * (1 + 1 / (Math.pow(1 + mper, mtime) - 1));
                            uvars.rmode = 1;
                            answer = "За " + uvars.arg3 + " лет вы выплатите банку " + Double.toString(msum * mtime) + " рублей из которых " +
                                    Double.toString(msum * mtime - uvars.arg1) + " являются переплатой. \n" +
                                    "Введитие сумму кредита, чтобы снова начать работать с калькулятором кредитов. \n" +
                                    "Введите /vklad, чтобы начать работать с калькулятором вкладов. \n" +
                                    "Введите /return, чтобы вернуться в главное меню.";
                            break;
                        default:
                            answer="";
                    }
                    break;
                default:
                    answer="";
            }
        }
        return answer;
    }
}
