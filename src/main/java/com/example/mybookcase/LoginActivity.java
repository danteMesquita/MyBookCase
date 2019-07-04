package com.example.mybookcase;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mybookcase.DataBase.DataBaseHelper;
import com.example.mybookcase.Models.UsuarioModel;
import com.example.mybookcase.Util.CurrenteUserHelper;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    TextView inputEmail;
    TextView inputPassword;
    TextView textViewRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.btnLogin = (Button) findViewById(R.id.btnLogin);
        this.inputEmail = (TextView) findViewById(R.id.inputEmail);
        this.inputPassword = (TextView) findViewById(R.id.inputPassword);
        this.textViewRegistrar = (TextView) findViewById(R.id.textViewRegistrar);


        //ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        //Resources resources = getResources();
        //imageView2.setImageDrawable(resources.getDrawable(R.drawable.icon));
        //imageView2.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon));

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    String username = inputEmail.getText().toString();
                    String password = inputPassword.getText().toString();
                    boolean acessou = false;

                    for (UsuarioModel item : new UsuarioModel().listarTodosUsuarios(getApplicationContext())) {
                        if(username.equals(item.usu_c_username) && password.equals(item.usu_c_password)) {
                            acessou = true;

                            CurrenteUserHelper.getInstance().setCurrentUser(item);

                            Intent it = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(it);
                            break;
                        }
                    }

                    if(!acessou) Toast.makeText(LoginActivity.this, R.string.login_error_message, Toast.LENGTH_LONG).show();


                }catch (Exception ex) {
                    Toast.makeText(LoginActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        textViewRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(it);
            }
        });
    }
}
