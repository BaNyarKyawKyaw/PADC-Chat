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
import com.bnkk.padcchat.delegates.ChatItemDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatListActivity extends BaseActivity implements ChatItemDelegate {

    @BindView(R.id.rv_chat_list)
    SmartRecyclerView rvChatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvChatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        ChatsAdapter chatsAdapter = new ChatsAdapter(getApplicationContext(), this);
        rvChatList.setAdapter(chatsAdapter);
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
}
