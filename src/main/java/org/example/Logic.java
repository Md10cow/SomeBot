package org.example;

public class Logic {
    public String parseMessage(String msg) {
        String answer;
        if(msg.equals("/help"))
            answer = "Echo Bot";
        else {
            answer = msg;
        }
        return answer;
    }
}
