package com.bnkk.padcchat.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bnkk.padcchat.R;
import com.bnkk.padcchat.data.vos.UserVO;
import com.bnkk.padcchat.delegates.ChatItemDelegate;
import com.bumptech.glide.Glide;

import butterknife.BindView;

/**
 * Created by E5-575G on 2/3/2018.
 */

public class ChatsViewHolder extends BaseViewHolder<UserVO> {

    @BindView(R.id.iv_user_profile)
    ImageView ivUserProfile;

    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    private ChatItemDelegate mDelegate;

    public ChatsViewHolder(View itemView, ChatItemDelegate chatItemDelegate) {
        super(itemView);
        mDelegate = chatItemDelegate;
    }

    @Override
    public void setData(UserVO data) {
        if (data.getProfileUrl() != null) {
            Glide
                    .with(ivUserProfile.getContext())
                    .load(data.getProfileUrl())
                    .into(ivUserProfile);
        }

        if (data.getName() != null) {
            tvUserName.setText(data.getName());
        }
    }

    @Override
    public void onClick(View v) {
        mDelegate.onTapChat();
    }
}
