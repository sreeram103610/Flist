package com.maadlabs.flist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.maadlabs.flist.model.AnonymousConsumer;
import com.maadlabs.flist.model.Consumer;
import com.maadlabs.flist.util.LoginUtil;
import com.maadlabs.flist.util.MyUtil;


public class LoginActivity extends FragmentActivity implements OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_GOOGLE = 1;
    private static final String TAG = "AuthResult";
    SignInButton mGoogleSignInButton;
    Button mAnonymousSignInButton;  // skip button
    Button mSkipButton;

    GoogleSignInOptions mGoogleSignInOptions;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    private Consumer mCurrentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initReferences();
        initListeners();
        initData();
    }

    private void initData() {

        mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                            .enableAutoManage(this, this)
                            .addApi(Auth.GOOGLE_SIGN_IN_API, mGoogleSignInOptions)
                            .build();
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    private void initListeners() {

        mGoogleSignInButton.setOnClickListener(this);
        mAnonymousSignInButton.setOnClickListener(this);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                if (user != null) {
                    mCurrentUser = new AnonymousConsumer(user);     // signed in
                    preferences.edit().putBoolean(LoginUtil.USER_LOGGED_IN, true).apply();
                    moveToDashboard();
                } else {
                    mCurrentUser = null;    // signed out. Not really needed.
                    preferences.edit().putBoolean(LoginUtil.USER_LOGGED_IN, false).apply();
                }
            }
        };
    }

    private void initReferences() {

        mGoogleSignInButton = (SignInButton) findViewById(R.id.googleSignInButton);
        mAnonymousSignInButton = (Button) findViewById(R.id.anonymousLoginButton);
        mSkipButton = (Button) findViewById(R.id.skipLoginButton);

        mGoogleSignInButton.setSize(SignInButton.SIZE_WIDE);
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.googleSignInButton:
                if (!MyUtil.isConnectedToNetwork(getBaseContext())) {
                    Toast.makeText(getBaseContext(), getString(R.string.login_activity_check_internet), Toast.LENGTH_SHORT).show();
                    return;
                }
                googleSignIn();
                break;
            case R.id.anonymousLoginButton:
                if (!MyUtil.isConnectedToNetwork(getBaseContext()))
                    return;
                anonymousSignIn();
                break;
            case R.id.skipLoginButton:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthListener);
            if (mFirebaseAuth.getCurrentUser() != null)
                mFirebaseAuth.signOut();
        }
    }

    private void anonymousSignIn() {

        mFirebaseAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), getString(R.string.signed_in_as_john_doe), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), getString(R.string.sign_in_failed), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void googleSignIn() {

        Intent signinIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signinIntent, RC_GOOGLE);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getBaseContext(), getString(R.string.sign_in_failed), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RC_GOOGLE:
                googlePostSignIn(Auth.GoogleSignInApi.getSignInResultFromIntent(data));
                break;
        }
    }

    public void moveToDashboard() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void googlePostSignIn(GoogleSignInResult result) {

        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            Toast.makeText(getBaseContext(), account.getEmail(), Toast.LENGTH_LONG).show();
            preferences.edit().putBoolean(LoginUtil.USER_LOGGED_IN, true).apply();
            moveToDashboard();

        } else {
            Toast.makeText(getBaseContext(), "login failed", Toast.LENGTH_LONG).show();
            preferences.edit().putBoolean(LoginUtil.USER_LOGGED_IN, false).apply();
        }
    }
}

