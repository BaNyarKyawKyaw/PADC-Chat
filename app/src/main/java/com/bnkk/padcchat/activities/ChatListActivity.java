package com.bnkk.padcchat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bnkk.padcchat.ChatApp;
import com.bnkk.padcchat.R;
import com.bnkk.padcchat.adapters.ChatsAdapter;
import com.bnkk.padcchat.components.SmartRecyclerView;
import com.bnkk.padcchat.data.models.ChatModel;
import com.bnkk.padcchat.data.vos.UserVO;
import com.bnkk.padcchat.delegates.ChatItemDelegate;
import com.bnkk.padcchat.events.FirebaseEvents;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatListActivity extends BaseActivity
        implements ChatItemDelegate, GoogleApiClient.OnConnectionFailedListener {

    protected static final int RC_GOOGLE_SIGN_IN = 12345;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_chat_list)
    SmartRecyclerView rvChatList;

    private ChatsAdapter mChatsAdapter;

    protected GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        rvChatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mChatsAdapter = new ChatsAdapter(getApplicationContext(), this);
        rvChatList.setAdapter(mChatsAdapter);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("") //TODO to add the request Token form Firebase
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            processGoogleSignInResult(result);
        }
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
        if (ChatModel.getObjInstance().isUserAuthenticate()) {
            Intent intent = ChatDetailsActivity.newIntent(getApplicationContext());
            startActivity(intent);
        } else {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserDataLoaded(FirebaseEvents.UserDataLoadedEvent event) {
        mChatsAdapter.appendNewData(event.getLoadedUser());
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "onConnectionFailed : " + connectionResult.getErrorMessage(), Toast.LENGTH_LONG).show();
    }

    private void processGoogleSignInResult(GoogleSignInResult signInResult) {
        if (signInResult.isSuccess()) {
            // Google Sign-In was successful, authenticate with Firebase
            GoogleSignInAccount account = signInResult.getSignInAccount();
            //TODO to Continue from here
        } else {
            // Google Sign-In failed
            Log.e(ChatApp.LOG_TAG, "Google Sign-In failed.");
            Snackbar.make(rvChatList, "Your Google sign-in failed.", Snackbar.LENGTH_LONG).show();
        }
    }
}
