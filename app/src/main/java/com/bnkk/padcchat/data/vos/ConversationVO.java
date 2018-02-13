package com.bnkk.padcchat.data.vos;

/**
 * Created by E5-575G on 2/3/2018.
 */

public class ConversationVO {

    private String conversationId;
    private String timestamp;
    private String msg;
    private String byUser;

    public String getConversationId() {
        return conversationId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public String getByUser() {
        return byUser;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setByUser(String byUser) {
        this.byUser = byUser;
    }
}
