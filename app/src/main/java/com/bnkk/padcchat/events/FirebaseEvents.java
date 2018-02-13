package com.bnkk.padcchat.events;

import com.bnkk.padcchat.data.vos.ConversationVO;
import com.bnkk.padcchat.data.vos.UserVO;

import java.util.List;

/**
 * Created by E5-575G on 2/13/2018.
 */

public class FirebaseEvents {

    public static class EmptyResponseEvent {

    }

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class UserDataLoadedEvent {
        private List<UserVO> loadedUser;

        public UserDataLoadedEvent(List<UserVO> loadedUser) {
            this.loadedUser = loadedUser;
        }

        public List<UserVO> getLoadedUser() {
            return loadedUser;
        }
    }

    public static class ChatDataLoadedEvent {
        private List<ConversationVO> loadedConversations;

        public ChatDataLoadedEvent(List<ConversationVO> loadedConversations) {
            this.loadedConversations = loadedConversations;
        }

        public List<ConversationVO> getLoadedConversations() {
            return loadedConversations;
        }
    }
}
