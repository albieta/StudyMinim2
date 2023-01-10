package edu.upc.dsa.andoroid_dsa.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.EditableUserInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    Api APIservice;

    String idUser;
    String username;
    String surname;
    String birthday;
    String email;
    String password;

    TextInputEditText usernameInput;
    TextInputEditText surnameInput;
    TextInputEditText birthdayInput;
    TextInputEditText emailInput;
    TextInputEditText passwordInput;

    TextView usernameTxt;
    TextView surnameTxt;
    TextView birthdayTxt;
    TextView emailTxt;
    TextView passwordTxt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_activity);

        APIservice = RetrofitClient.getInstance().getMyApi();

        this.usernameInput = (TextInputEditText) findViewById(R.id.usernameInput);
        this.surnameInput = (TextInputEditText) findViewById(R.id.surnameInput);
        this.birthdayInput = (TextInputEditText) findViewById(R.id.birthdayInput);
        this.emailInput = (TextInputEditText) findViewById(R.id.emailInput);
        this.passwordInput = (TextInputEditText) findViewById(R.id.passwordInput);

        this.usernameTxt = (TextView) findViewById(R.id.editUsernameTxt);
        this.surnameTxt = (TextView) findViewById(R.id.editSurnameTxt);
        this.birthdayTxt = (TextView) findViewById(R.id.editBirthdayTxt);
        this.emailTxt = (TextView) findViewById(R.id.editEmailTxt);
        this.passwordTxt = (TextView) findViewById(R.id.editPasswordTxt);

        Bundle content = getIntent().getExtras();
        if(content != null){
            this.username = content.getString("username");
            this.surname = content.getString("surname");
            this.birthday = content.getString("birthday");
            this.email = content.getString("email");
            this.password = content.getString("password");
            this.idUser = content.getString("idUser");
            updateTextViews();
        }
    }

    public void applyChanges(View view) {
        if(!usernameInput.getText().toString().equals(""))
            this.username = usernameInput.getText().toString();
        if(!surnameInput.getText().toString().equals(""))
            this.surname = surnameInput.getText().toString();
        if(!birthdayInput.getText().toString().equals(""))
            this.birthday = birthdayInput.getText().toString();
        if(!emailInput.getText().toString().equals(""))
            this.email = emailInput.getText().toString();
        if(!passwordInput.getText().toString().equals(""))
            this.password = passwordInput.getText().toString();

        Call<Void> call = APIservice.updateUser(new EditableUserInformation(idUser, username, surname, birthday, email, password));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {}

            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });

        updateTextViews();
    }

    private void updateTextViews() {
        usernameTxt.setText(this.username);
        surnameTxt.setText(this.surname);
        birthdayTxt.setText(this.birthday);
        emailTxt.setText(this.email);
        passwordTxt.setText(this.password);
    }

    public void Return(View view) {
        Intent intentProfile = new Intent(EditProfileActivity.this, YourProfileActivity.class);
        EditProfileActivity.this.startActivity(intentProfile);
    }
}
