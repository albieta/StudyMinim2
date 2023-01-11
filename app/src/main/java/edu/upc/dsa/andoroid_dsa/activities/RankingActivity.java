package edu.upc.dsa.andoroid_dsa.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.User;
import retrofit2.Call;

public class RankingActivity extends AppCompatActivity {

    Api APIservice;
    List<User> users;
    private RecyclerView recyclerViewUsers;
    private RecyclerViewAdapterUser adapterUsers;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_users);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerRankingUsers);
        Log.d("DDDD", "" + recyclerViewUsers);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<List<User>> call = APIservice.rankingUsers();
        try {
            adapterUsers = new RecyclerViewAdapterUser(call.execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerViewUsers.setAdapter(adapterUsers);
    }

    public void ReturnToProfile(View view){
        Intent intentDashBoard = new Intent(RankingActivity.this, DashBoardActivity.class);
        RankingActivity.this.startActivity(intentDashBoard);
    }
}
