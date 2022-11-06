package org.example;

import static java.lang.Double.NaN;

public class Logic {
    int wmode=0;
    int rmode=0;
    double arg1=0;
    double arg2=0;
    double arg3=0;
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
                return NaN;
        }
        return arg;
    }
    public String parseMessage(String msg) {
        String answer;
        if(msg.equals("/help"))
            switch(wmode) {
                case 1:
                    answer = "/vklad - рассчет вкладов\n/credit - рассчет кредитов\n/return - назад";
                    break;
                case 2:
                    answer = "\n/return - назад";
                    break;
                case 3:
                    answer = "выводит:\nежемесячный платеж\nобщую сумму выплат\nпереплату\nвведите через пробел:\nколичество лет\nгодовой процент\nсумму кредита\n/return - назад";
                    break;
                default:
                    answer = "/calc - калькуляторы";
            }
        else if(msg.equals("/calc")) {
            wmode = 1;
            answer = "/vklad - рассчет вкладов\n/credit - рассчет кредитов\n/return - назад";
        }
        else if(msg.equals("/vklad")){
            wmode=2;
            answer = "выводит:\nежемесячный платеж\nобщую сумму выплат\nпереплату\nвведите:\nколичество лет\nгодовой процент\nсумму кредита\n/return - назад";
            rmode=1;
        }
        else if(msg.equals("/return")){
            switch(wmode){
                case 1:
                    wmode=0;
                    break;
                case 2:
                case 3:
                    wmode=1;
                    break;
            }
            rmode=0;
            answer="";
        }
        else if(msg.equals("/credit")){
            wmode=3;
            answer = "выводит:\nежемесячный платеж\nобщую сумму выплат\nпереплату\nвведите:\nколичество лет\nгодовой процент\nсумму кредита\n/return - назад";
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
                            answer = ""; //вклад
                            break;
                        case 3:
                            answer = "годовые проценты"; //кредит
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
                            answer = "";
                            break;
                        case 3:
                            answer = "";
                            break;
                        default:
                            answer = "";
                    }
                    rmode=3;
                    break;
                case 3:
                    arg3=parseArg(msg);
                    if (arg3==NaN)
                        return "неизвестные символы";
                    switch(wmode) {
                        case 2:
                            double msum = arg1*arg2*arg3/100;
                            rmode = 1;
                            answer = Double.toString(msum);
                            break;
                        case 3:
                            double mper = arg2 / 1200;
                            double mtime = arg3 * 12;
                            msum = arg1 * mper * (1 + 1 / (Math.pow(1 + mper, mtime) - 1));
                            rmode = 1;
                            answer = Double.toString(msum) + "\n" + Double.toString(msum * mtime) + "\n" + Double.toString(msum * mtime - arg1);
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
