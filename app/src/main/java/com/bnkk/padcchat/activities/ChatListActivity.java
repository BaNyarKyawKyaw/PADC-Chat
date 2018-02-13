package com.bnkk.padcchat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bnkk.padcchat.R;
import com.bnkk.padcchat.adapters.ChatsAdapter;
import com.bnkk.padcchat.components.SmartRecyclerView;
import com.bnkk.padcchat.data.models.ChatModel;
import com.bnkk.padcchat.data.vos.UserVO;
import com.bnkk.padcchat.delegates.ChatItemDelegate;
import com.bnkk.padcchat.events.FirebaseEvents;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatListActivity extends BaseActivity implements ChatItemDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_chat_list)
    SmartRecyclerView rvChatList;

    private ChatsAdapter mChatsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        rvChatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mChatsAdapter = new ChatsAdapter(getApplicationContext(), this);
        rvChatList.setAdapter(mChatsAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        if (!(ChatModel.getObjInstance().getRegisteredUserList().size() > 0)) {
            ChatModel.getObjInstance().startLoadingRegisteredUser();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapChat() {
        Intent intent = ChatDetailsActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserDataLoaded(FirebaseEvents.UserDataLoadedEvent event) {
        mChatsAdapter.appendNewData(event.getLoadedUser());
    }
}
