package migdonio1.sharedjapanese.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Objects;

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

        user = sharedPreferencesRead();

        if(!Objects.equals(user.getName(), "none") && !Objects.equals(user.getPassword(), "none")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
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
        user.setPassword(password);
    }

    private void tryLogin() {
        Call<User> call = apiUsers.tryLogin(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResponse = response.body();
                Log.d("log1", String.valueOf(response.body().getName() == null));
                Log.d("log2", String.valueOf(Objects.equals(response.body(), "0")));
                if (Objects.equals(response.body().getName(), null)) {
                    String toastText = "No se encontro la cuenta";
                    Toast.makeText(UserLoginActivity.this, toastText, Toast.LENGTH_SHORT).show();
                    emptyInputs();
                } else {
                    Log.d("log2", String.valueOf(response.body()));
                    String toastText = "Bienvenido ";
                    Toast.makeText(UserLoginActivity.this, toastText, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    sharedPreferencesUpdate(name, password);
                    startActivity(intent);

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UserLoginActivity.this, "Hubo un error en el inicio de sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void emptyInputs() {
        nameEditText.setText("");
        passwordEditText.setText("");
    }

    private void sharedPreferencesUpdate(String name, String password) {
        SharedPreferences settings = getSharedPreferences("log", 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("name", name);
        editor.putString("password", password);

        editor.commit();
    }

    private User sharedPreferencesRead() {
        User userSaved = new User();
        SharedPreferences settings = getSharedPreferences("log", 0); //0 means private mode
        Log.d("login", settings.getString("name", "none"));
        Log.d("login", settings.getString("password", "none"));
        userSaved.setName(settings.getString("name", "none"));
        userSaved.setPassword(settings.getString("password", "none"));
        return userSaved;
    }


}
