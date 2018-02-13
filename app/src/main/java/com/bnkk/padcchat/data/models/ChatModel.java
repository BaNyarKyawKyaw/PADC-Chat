package com.bnkk.padcchat.data.models;

import android.content.Context;
import android.util.Log;

import com.bnkk.padcchat.ChatApp;
import com.bnkk.padcchat.data.vos.ChatVO;
import com.bnkk.padcchat.data.vos.ConversationVO;
import com.bnkk.padcchat.data.vos.UserVO;
import com.bnkk.padcchat.events.FirebaseEvents;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by E5-575G on 2/3/2018.
 */

public class ChatModel {

    private static ChatModel objInstance;

    private static final String CHATS = "chats";
    private static final String REGISTERED_USER = "registeredUsers";

    private List<UserVO> registeredUserList;
    private List<ChatVO> chatList;

    private ChatModel() {
        registeredUserList = new ArrayList<>();
    }

    public static ChatModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new ChatModel();
        }
        return objInstance;
    }

    public void startLoadingChats() {
        DatabaseReference chatsDBR = FirebaseDatabase.getInstance().getReference();
        DatabaseReference chatsNodeDBR = chatsDBR.child(CHATS);
        chatsNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot chatDSS : dataSnapshot.getChildren()) {
                    ChatVO chat = chatDSS.getValue(ChatVO.class);
                    chatList.add(chat);
                }

                Log.d(ChatApp.LOG_TAG, "chatList size : " + chatList.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(ChatApp.LOG_TAG, "Chat call Cancelled");
            }
        });
    }

    public void startLoadingRegisteredUser() {
        DatabaseReference chatsDBR = FirebaseDatabase.getInstance().getReference();
        DatabaseReference registeredUserNodeDBR = chatsDBR.child(REGISTERED_USER);
        registeredUserNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot registeredUserDSS : dataSnapshot.getChildren()) {
                    UserVO user = registeredUserDSS.getValue(UserVO.class);
                    registeredUserList.add(user);
                }

                FirebaseEvents.UserDataLoadedEvent event = new FirebaseEvents.UserDataLoadedEvent(registeredUserList);
                EventBus.getDefault().post(event);

                Log.d(ChatApp.LOG_TAG, "registeredUserList size : " + registeredUserList.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(ChatApp.LOG_TAG, "Registered User call Cancelled");
            }
        });
    }

    public List<UserVO> getRegisteredUserList() {
        return registeredUserList;
    }
}
