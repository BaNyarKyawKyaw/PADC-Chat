package com.bnkk.padcchat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bnkk.padcchat.R;
import com.bnkk.padcchat.delegates.ChatItemDelegate;
import com.bnkk.padcchat.viewholders.ChatsViewHolder;

/**
 * Created by E5-575G on 2/3/2018.
 */

public class ChatsAdapter extends RecyclerView.Adapter<ChatsViewHolder> {

    private LayoutInflater mLayoutInflater;
    private ChatItemDelegate mDelegete;

    public ChatsAdapter(Context context, ChatItemDelegate chatItemDelegate) {
        mLayoutInflater = LayoutInflater.from(context);
        mDelegete = chatItemDelegate;
    }

    @Override
    public ChatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View chatView = mLayoutInflater.inflate(R.layout.view_item_chat, parent, false);
        return new ChatsViewHolder(chatView,mDelegete);
    }

    @Override
    public void onBindViewHolder(ChatsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
