package org.example;

import java.util.ArrayList;
/**
 * набор данных, связанных с вопросами
 */
public class QARecord {

    /* разделы */
    public String sect;

    /* массив тем */
    public ArrayList<String> qArr=new ArrayList<>();

    /* массив ответов */
    public ArrayList<String> aArr=new ArrayList<>();
}
