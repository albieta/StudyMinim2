package edu.upc.dsa.andoroid_dsa.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.Purchase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GadgetsOfTheUser extends AppCompatActivity implements RecyclerClickViewListener {
    Api APIservice;
    String idUser;
    String name;
    private RecyclerView recyclerViewGadgets;
    private RecyclerViewAdapterGadgetsOwned adapterGadgets;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gadgets_user_profile);
        this.getUserIdFromPreviousActivity();
        this.updateLabel();
        recyclerViewGadgets = (RecyclerView) findViewById(R.id.recyclerRankingUsers);
        Log.d("DDDD", "" + recyclerViewGadgets);
        recyclerViewGadgets.setLayoutManager(new LinearLayoutManager(this));
        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<List<Gadget>> call = APIservice.purchasedGadgets(this.idUser);
        try {
            adapterGadgets = new RecyclerViewAdapterGadgetsOwned(call.execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerViewGadgets.setAdapter(adapterGadgets);
    }

    @Override
    public void recyclerViewListClicked(int position) {

    }

    public void getUserIdFromPreviousActivity(){
        SharedPreferences sharedPreferences = getSharedPreferences("userIdAndUsername", Context.MODE_PRIVATE);
        this.idUser = sharedPreferences.getString("userId", null).toString();
        this.name = sharedPreferences.getString("name", null).toString();
    }

    public void updateLabel(){
        String update_title =getString(R.string.updating_title_user);
        update_title="Gadgets of "+this.name+" !";
        EditText e = (EditText) findViewById (R.id.Edit_Title_Update1);
        e.setText(update_title);
    }

    public void ReturnToProfile(View view){
        Intent intentRegister = new Intent(GadgetsOfTheUser.this, YourProfileActivity.class);
        GadgetsOfTheUser.this.startActivity(intentRegister);
    }

    public void deletePurchase(View view) {
        Call<Void> call = APIservice.deletePurchase(new Purchase(this.idUser, view.getTag().toString()));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {}

            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });

        adapterGadgets.deleteGadget(view.getTag().toString());
    }
}
