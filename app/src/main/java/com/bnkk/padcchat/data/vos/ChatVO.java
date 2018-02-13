package com.bnkk.padcchat.data.vos;

import android.util.ArrayMap;

import java.util.Map;

/**
 * Created by E5-575G on 2/3/2018.
 */

public class ChatVO {

    private String chatId;
    private String startedAt;
    private Map<String, UserVO> userList;
    private Map<String, ConversationVO> conversations;

    public String getChatId() {
        return chatId;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public Map<String, UserVO> getUserList() {
        if (userList == null) {
            userList = new ArrayMap<>();
        }
        return userList;
    }

    public Map<String, ConversationVO> getConversations() {
        if (conversations == null) {
            conversations = new ArrayMap<>();
        }
        return conversations;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public void setUserList(Map<String, UserVO> userList) {
        this.userList = userList;
    }

    public void setConversations(Map<String, ConversationVO> conversations) {
        this.conversations = conversations;
    }
}
