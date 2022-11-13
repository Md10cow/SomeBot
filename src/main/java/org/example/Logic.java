package org.example;

import static java.lang.Double.NaN;
/** основа программы, отвечает за логику бота */
public class Logic {

    /** режимы для опознавания места, где находится пользователь */
    int wmode=0;
    int rmode=0;

    /** аргументы для рассчета вклада/кредита */
    double arg1=0;
    double arg2=0;
    double arg3=0;

    /** конвертация текста в дробь */
    public double parseArg(String msg) {
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
                return NaN;
        }
        return arg;
    }

    /** все вводы и выводы */
    public String parseMessage(String msg) {
        String answer;
        /** вспомогательная команда для пользователя */
        if(msg.equals("/help"))
            switch(wmode) {
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
        /** старт */
        else if(msg.equals("/start")) {
            rmode = 0;
            wmode = 0;
            answer = "Здравствуйте, вас приветствует программа, подсчитывающая доходность вкладов или же сумму для выплаты кредита. \n" +
                     "Пожалуйста, выберите, что вы хотите рассчитать (Напишите /vklad или /kredit)";
        }
        /** для будущих задач */
        else if(msg.equals("/calc")) {
            wmode = 1;
            answer = "/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню";
        }
        /** команда для возвращения */
        else if(msg.equals("/return")){
            switch(wmode){
                case 1:
                    wmode=0;
                    answer="";
                    break;
                case 2:
                case 3:
                    wmode=1;
                    answer="/vklad - рассчет вкладов\n/kredit - рассчет кредитов\n/return - в главное меню";
                    break;
                default:
                    answer="";
            }
            rmode=0;
        }
        /** запрос данных */
        else if(msg.equals("/vklad")){
            wmode=2;
            answer = "Принято, введите сумму вклада. \n/return - в главное меню";
            rmode=1;
        }
        else if(msg.equals("/kredit")){
            wmode=3;
            answer = "Принято, введите сумму кредита. \n/return - в главное меню";
            rmode=1;
        }
        else{
            switch(rmode){
                case 1:
                    arg1=parseArg(msg);
                    if (arg1==NaN)
                        return "неизвестные символы";
                    switch(wmode) {
                        case 2:
                            answer = "Введите годовую процентную ставку. "; //вклад
                            break;
                        case 3:
                            answer = "Введите годовую процентную ставку. "; //кредит
                            break;
                        default:
                            answer = "";
                    }
                    rmode=2;
                    break;
                case 2:
                    arg2=parseArg(msg);
                    if (arg2==NaN)
                            return "неизвестные символы";
                    switch(wmode) {
                        case 2:
                            answer = "Введите количество лет, которые будет храниться вклад.";
                            break;
                        case 3:
                            answer = "Введите количество лет, которые будет оплачиваться кредит.";
                            break;
                        default:
                            answer = "";
                    }
                    rmode=3;
                    break;
                /** подсчёты и вывод */
                case 3:
                    arg3=parseArg(msg);
                    if (arg3==NaN)
                        return "неизвестные символы";
                    switch(wmode) {
                        case 2:
                            double msum = arg1*arg2*arg3/100;
                            rmode = 1;
                            answer = "За " + arg3 + " лет получите " + Double.toString(msum + arg1) + " рублей из которых " +
                                    Double.toString(msum) + " являются вашим доходом с вклада.  \n" +
                                    "Введитие сумму кредита, чтобы снова начать работать с калькулятором вкладов. \n" +
                                    "Введите /kredit, чтобы начать работать с калькулятором кредитов. \n" +
                                    "Введите /return, чтобы вернуться в главное меню.";
                            break;
                        case 3:
                            double mper = arg2 / 1200;
                            double mtime = arg3 * 12;
                            msum = arg1 * mper * (1 + 1 / (Math.pow(1 + mper, mtime) - 1));
                            rmode = 1;
                            answer = "За " + arg3 + " лет вы выплатите банку " + Double.toString(msum * mtime) + " рублей из которых " +
                                    Double.toString(msum * mtime - arg1) + " являются переплатой. \n" +
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
