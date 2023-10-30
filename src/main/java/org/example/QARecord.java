package org.example;

import java.util.ArrayList;
/**
 * набор данных, связанных с вопросами
 */
public class QARecord {

    /* разделы */
    public String sect;

    /* массив тем */
    private ArrayList<String> qArr=new ArrayList<>();

    /* массив ответов */
    private ArrayList<String> aArr=new ArrayList<>();

    public void qAdd(String str){
        qArr.add(str);
    }

    public void aAdd(String str){
        aArr.add(str);
    }

    public void aSet(int ind, String str){
        aArr.set(ind,str);
    }

    public void qSet(int ind, String str){
        qArr.set(ind,str);
    }

    public int aSize(){
        return aArr.size();
    }

    public int qSize(){
        return qArr.size();
    }

    public String aGet(int ind){
        return aArr.get(ind);
    }

    public String qGet(int ind){
        return qArr.get(ind);
    }
}
