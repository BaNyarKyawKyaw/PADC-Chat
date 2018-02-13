package com.bnkk.padcchat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bnkk.padcchat.R;
import com.bnkk.padcchat.data.vos.UserVO;
import com.bnkk.padcchat.delegates.ChatItemDelegate;
import com.bnkk.padcchat.viewholders.ChatsViewHolder;

/**
 * Created by E5-575G on 2/3/2018.
 */

public class ChatsAdapter extends BaseRecyclerAdapter<ChatsViewHolder, UserVO> {

    private ChatItemDelegate mDelegete;

    public ChatsAdapter(Context context, ChatItemDelegate chatItemDelegate) {
        super(context);
        mDelegete = chatItemDelegate;
    }

    @Override
    public ChatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View chatView = mLayoutInflater.inflate(R.layout.view_item_chat, parent, false);
        return new ChatsViewHolder(chatView, mDelegete);
    }
}
