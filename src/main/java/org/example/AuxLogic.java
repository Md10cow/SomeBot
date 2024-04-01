package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class AuxLogic {
    public double nan=0x7ff8000000000000L;
    public ArrayList<QARecord> parseFile() throws IOException {
        ArrayList<QARecord> qAArr = new ArrayList<>();
        final boolean[] LLiQflag = {true};
        final QARecord[] qrrRec = {null};
        Files.lines(Path.of("QADB.txt")).forEach(line -> {
            if (line != null) {
                if (line.charAt(0) == '%') {
                    qrrRec[0] = new QARecord();
                    qrrRec[0].sect = line.substring(1, line.length());
                    LLiQflag[0] = true;
                    qAArr.add(qrrRec[0]);
                } else if (line.charAt(0) == '?') {
                    LLiQflag[0] = true;
                    qrrRec[0].qAdd(line.substring(1, line.length()));
                } else if (line.charAt(0) == '*') {
                    if (LLiQflag[0]) {
                        qrrRec[0].aAdd(line.substring(1, line.length()));
                        LLiQflag[0] = false;
                    } else {
                        qrrRec[0].aSet(qrrRec[0].aSize() - 1, qrrRec[0].aGet(qrrRec[0].aSize() - 1) + "\n" + line.substring(1, line.length()));
                    }
                }
            }
        });
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
