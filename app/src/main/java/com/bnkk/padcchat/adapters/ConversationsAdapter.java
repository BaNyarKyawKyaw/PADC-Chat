package com.bnkk.padcchat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bnkk.padcchat.R;
import com.bnkk.padcchat.viewholders.ConversationsViewHolder;

/**
 * Created by E5-575G on 2/3/2018.
 */

public class ConversationsAdapter extends RecyclerView.Adapter<ConversationsViewHolder> {

    private LayoutInflater mLayoutInflater;

    public ConversationsAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ConversationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View conversationView = mLayoutInflater.inflate(R.layout.view_item_conversation, parent, false);
        return new ConversationsViewHolder(conversationView);
    }

    @Override
    public void onBindViewHolder(ConversationsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
