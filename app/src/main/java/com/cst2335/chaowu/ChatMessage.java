package com.cst2335.chaowu;
public class ChatMessage {
    private String message;
    private String timeSpent;
    private boolean isSentButton;

    ChatMessage (String m, String t, boolean sent){
        message = m;
        timeSpent = t;
        isSentButton = sent;
    }

    public String getMessage(){
        return message;
    }
    public String getTimeSpent(){
        return timeSpent;
    }
    public boolean getIsSentButton(){
        return isSentButton;
    }
}
