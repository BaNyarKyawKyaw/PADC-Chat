package com.bnkk.padcchat.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bnkk.padcchat.delegates.ChatItemDelegate;

/**
 * Created by E5-575G on 2/3/2018.
 */

public class ChatsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ChatItemDelegate mDelegate;

    public ChatsViewHolder(View itemView, ChatItemDelegate chatItemDelegate) {
        super(itemView);
        mDelegate = chatItemDelegate;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mDelegate.onTapChat();
    }
}
