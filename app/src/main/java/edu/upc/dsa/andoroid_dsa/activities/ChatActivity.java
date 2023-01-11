package edu.upc.dsa.andoroid_dsa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.EditableUserInformation;
import edu.upc.dsa.andoroid_dsa.models.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class ChatActivity  extends AppCompatActivity {
    Api APIservice;

    String username;
    Integer num;

    TableLayout tableLayout;
    TextInputEditText messageInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);

        APIservice = RetrofitClient.getInstance().getMyApi();

        this.tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        this.messageInput = (TextInputEditText) findViewById(R.id.messageInput);

        Bundle content = getIntent().getExtras();
        if(content != null){
            this.username = content.getString("username");
        }

        this.num = 0;
        Call<List<Message>> call = APIservice.getMessages(this.num);
        try {
            buildTable(call.execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildTable(List<Message> messages) {
        assert messages != null;

        for (Message message : messages) {
            View tableRow = LayoutInflater.from(this).inflate(R.layout.chat_message, null, false);

            TextView playerUsername = tableRow.findViewById(R.id.playerUsername);
            TextView messageSent = tableRow.findViewById(R.id.messageSent);

            playerUsername.setText(message.getPlayer());
            messageSent.setText(message.getText());

            tableLayout.addView(tableRow);
        }
        this.num += messages.size();
    }

    public void Return(View view) {
        Intent intentDashBoard = new Intent(ChatActivity.this, DashBoardActivity.class);
        ChatActivity.this.startActivity(intentDashBoard);
    }

    public void sendMessage(View view) {
        if(!messageInput.getText().toString().equals("")){
            Call<Void> call = APIservice.addMessage(new Message(this.username, messageInput.getText().toString()));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {}

                @Override
                public void onFailure(Call<Void> call, Throwable t) {}
            });
            ArrayList<Message> messages = new ArrayList<>();
            messages.add(new Message(this.username, messageInput.getText().toString()));
            buildTable(messages);
        }
    }

    public void refreshMessages(View view) {
        updateTable();
    }

    private void updateTable() {
        Call<List<Message>> call = APIservice.getMessages(this.num);
        try {
            buildTable(call.execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
