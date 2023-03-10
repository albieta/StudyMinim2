package edu.upc.dsa.andoroid_dsa.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.snackbar.Snackbar;
import com.unity3d.player.UnityPlayerActivity;

import java.io.IOException;
import java.util.Locale;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.User;
import edu.upc.dsa.andoroid_dsa.models.UserId;
import edu.upc.dsa.andoroid_dsa.models.UserInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {
    public CardView yourProfile, gadgetShop, logOut, runGame, ranking, chat;
    public String userId;
    public String username;
    Api APIservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_main);

        SharedPreferences sharedPreferences = getSharedPreferences("userId", Context.MODE_PRIVATE);
        this.userId = sharedPreferences.getString("userId",null).toString();
        this.getUserById(this.userId);
        this.getCardViewsReady();
        setLocale("cat");
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.profiles:
                i=new Intent(this,YourProfileActivity.class);
                startActivity(i);
                break;
            case R.id.gadgetCard:
                saveUserId(this.userId);
                i=new Intent(this,GadgetActivity.class);
                startActivity(i);
                break;
            case R.id.returnBtn:
                i=new Intent(this, PrincipalActivity.class);
                startActivity(i);
                break;
            case R.id.run_card:
                i=new Intent(this, UnityPlayerActivity.class);
                startActivity(i);
                break;
            case R.id.rankingUsers:
                i=new Intent(this,RankingActivity.class);
                startActivity(i);
                break;
            case R.id.chatImg:
                i=new Intent(this,ChatActivity.class);
                Bundle adapterInfo = new Bundle();
                adapterInfo.putString("username", this.username);
                i.putExtras(adapterInfo);
                startActivity(i);
        }
    }
    public void getUserById(String userId){
        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<User> call =APIservice.getUser(this.userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                switch (response.code()){
                    case 201:
                        UserInformation userInformation=new UserInformation(response.body());
                        assert userInformation != null;
                        updateLabel(userInformation);
                        saveVariables(userInformation);
                        saveUserId(userId);
                        Toast.makeText(DashBoardActivity.this,"Correctly received UserInformation", Toast.LENGTH_SHORT).show();
                        break;
                    case 409:
                        Toast.makeText(DashBoardActivity.this,"Could not reach UserInformation!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(DashBoardActivity.this,"NETWORK FAILURE :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateLabel(UserInformation userInformation){
        this.username=userInformation.getName();
        String update_title =getString(R.string.updating_title);
        update_title=this.username+" !";
        EditText e = (EditText) findViewById (R.id.Edit_Title_Update);
        e.setText(update_title);
    }
    public void getCardViewsReady(){
        yourProfile=(CardView) findViewById(R.id.profiles);
        gadgetShop=(CardView) findViewById(R.id.gadgetCard);
        runGame=(CardView) findViewById(R.id.run_card);
        ranking=(CardView) findViewById(R.id.rankingUsers);
        chat=(CardView) findViewById(R.id.chatImg);
        yourProfile.setOnClickListener(this);
        gadgetShop.setOnClickListener(this);
        runGame.setOnClickListener(this);
        ranking.setOnClickListener(this);
        chat.setOnClickListener(this);

    }
    public void saveVariables(UserInformation userInformation) {
        SharedPreferences sharedPreferences= getSharedPreferences("userInformation", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("username",userInformation.getName());
        editor.putString("surname",userInformation.getSurname());
        editor.putString("birthday",userInformation.getBirthday());
        editor.putString("email",userInformation.getEmail());
        editor.putString("password",userInformation.getPassword());
        editor.putString("coins",Integer.toString(userInformation.getCoins()));
        Log.i("SAVING: ",userInformation.getName());
        Log.i("SAVING: ",userInformation.getSurname());
        Log.i("SAVING: ",userInformation.getBirthday());
        Log.i("SAVING: ",userInformation.getEmail());
        Log.i("SAVING: ",userInformation.getPassword());
        Log.i("SAVING. ",Integer.toString(userInformation.getCoins()));
        editor.apply();
    }
    public void saveUserId(String userId){
        SharedPreferences sharedPreferences= getSharedPreferences("userId", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("userId", userId);
        Log.i("SAVING: ",userId);
        editor.apply();
    }

    public void logOut(View view) {
        Intent intentRegister = new Intent(DashBoardActivity.this, LogInActivity.class);
        DashBoardActivity.this.startActivity(intentRegister);
    }

    private void setLocale(String language) {
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language);
        resources.updateConfiguration(configuration, metrics);
        onConfigurationChanged(configuration);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfiguration) {
        super.onConfigurationChanged(newConfiguration);
        //set strings from resources

    }
}
