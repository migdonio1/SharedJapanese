package migdonio1.sharedjapanese.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import migdonio1.sharedjapanese.MainActivity;
import migdonio1.sharedjapanese.R;
import migdonio1.sharedjapanese.endpoints.UsersApiInterface;
import migdonio1.sharedjapanese.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static migdonio1.sharedjapanese.data.APIConstants.API_ENDPOINT;

public class UserLoginActivity extends AppCompatActivity {
    private String name;
    private String password;

    private EditText nameEditText;
    private EditText passwordEditText;
    private Button userLogin;

    private User user;

    private UsersApiInterface apiUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        nameEditText = (EditText) findViewById(R.id.userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.userPasswordEditText);
    }

    public void onClickLoginSubmitButton(View view) {
        userLogin = (Button) findViewById(R.id.loginButton);
        userLogin.setEnabled(true);

        name = String.valueOf(nameEditText.getText()).trim();
        password = String.valueOf(passwordEditText.getText()).trim();

        if (name.length() == 0 || password.length() == 0) {
            Toast.makeText(UserLoginActivity.this, "Llene todos los campos para poder iniciar sesion", Toast.LENGTH_SHORT).show();
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiUsers = retrofit.create(UsersApiInterface.class);
            createUserModel();
            tryLogin();
        }
    }

    private void createUserModel() {
        user = new User();
        user.setName(name);
        user.setPassword(name);
    }

    private void tryLogin() {
        Call<User> call = apiUsers.tryLogin(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResponse = response.body();

                String toastText = "Bienvenido " + userResponse.getName();
                Toast.makeText(UserLoginActivity.this, toastText, Toast.LENGTH_SHORT).show();
                emptyInputs();
                userLogin.setEnabled(true);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UserLoginActivity.this, "Hubo un error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                userLogin.setEnabled(true);
            }
        });
    }

    private void emptyInputs() {
        nameEditText.setText("");
        passwordEditText.setText("");
    }


}
