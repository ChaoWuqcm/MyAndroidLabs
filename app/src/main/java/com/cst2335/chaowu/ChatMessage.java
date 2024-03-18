package com.cst2335.chaowu;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {
    @ColumnInfo(name="message")
    protected String message;
    @ColumnInfo(name="TimeSent")
    protected String timeSpent;

    @ColumnInfo(name="SendOrReceive")
    protected boolean isSentButton;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    public int id;
    public ChatMessage(){}
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
