package org.example;

import java.util.ArrayList;

public class AuxLogic {
    public double nan=0x7ff8000000000000L;
    private FileReaderClass fileReader;
    public ArrayList<QARecord> parseFile() {
        ArrayList<QARecord> qAArr = new ArrayList<>();
        fileReader = new FileReaderClass();
        fileReader.openFile("QADB.txt");
        String line = fileReader.readLine();
        boolean LLiQflag = true;
        QARecord qrrRec = null;
        while (line != null) {
            if (line.charAt(0) == '%') {
                qrrRec = new QARecord();
                qrrRec.sect = line.substring(1, line.length());
                LLiQflag = true;
                qAArr.add(qrrRec);
            } else if (line.charAt(0) == '?') {
                LLiQflag = true;
                qrrRec.qAdd(line.substring(1, line.length()));
            } else if (line.charAt(0) == '*') {
                if (LLiQflag) {
                    qrrRec.aAdd(line.substring(1, line.length()));
                    LLiQflag = false;
                } else {
                    qrrRec.aSet(qrrRec.aSize() - 1, qrrRec.aGet(qrrRec.aSize() - 1) + "\n" + line.substring(1, line.length()));
                }
            }
            line = fileReader.readLine();
        }
        fileReader.close();
        return qAArr;
    }

    /**
     * Конвертор string в double
     *
     * @param msg сообщение пользователя
     */
    public double parseArg(String msg) {
        if (msg==null)
            return nan;
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
}
