package com.example.mybookcase;

import android.app.Application;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mybookcase.DataBase.DataBaseHelper;
import com.example.mybookcase.Models.UsuarioModel;
import com.google.android.material.snackbar.Snackbar;

public class RegistrarActivity extends AppCompatActivity {

    TextView textViewUserName;
    TextView textViewPassword;
    TextView textViewConfirmPassword;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        this.textViewUserName = (TextView) findViewById(R.id.textViewUserName);
        this.textViewPassword = (TextView) findViewById(R.id.textViewPassword);
        this.textViewConfirmPassword = (TextView) findViewById(R.id.textViewConfirmPassword);
        this.btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String username = textViewUserName.getText().toString();
                    String password = textViewPassword.getText().toString();
                    String confirmPassword = textViewConfirmPassword.getText().toString();

                    if(password.equals(confirmPassword)) {
                        if(validaDados(v)) {
                            UsuarioModel usuarioModel = new UsuarioModel(username, password);
                            usuarioModel.Adicionar(usuarioModel, getApplicationContext());
                            Boolean usuarioExiste = false;

                            for(UsuarioModel item : new UsuarioModel().listarTodosUsuarios(getApplicationContext())) {
                                if(usuarioModel.usu_c_username.equals(item.usu_c_username)) {
                                    usuarioExiste = true;
                                    Toast.makeText(RegistrarActivity.this, "Usuário já existe", Toast.LENGTH_LONG).show();
                                    break;
                                }
                            }

                            if(!usuarioExiste) {
                                Intent it = new Intent(RegistrarActivity.this, LoginActivity.class);
                                startActivity(it);
                                Toast.makeText(RegistrarActivity.this, R.string.register_success_message, Toast.LENGTH_LONG).show();
                            }
                        }
                    }else {
                        Toast.makeText(RegistrarActivity.this, R.string.register_error_message, Toast.LENGTH_LONG).show();
                    }
                }catch(Exception ex) {
                    Toast.makeText(RegistrarActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean validaDados(View view) {
        try{
            boolean valida = true;
            String message = "";

            if(this.textViewConfirmPassword.getText().toString().equals("") || this.textViewConfirmPassword.getText().equals(null)) {
                valida = false;
                message = getString(R.string.alert_username_empty);
            }

            if(this.textViewPassword.getText().toString().equals("") || this.textViewPassword.getText().equals(null)) {
                valida = false;
                message = getString(R.string.alert_password_empty);
            }

            if(this.textViewUserName.getText().toString().equals("") || this.textViewUserName.getText().equals(null)) {
                valida = false;
                message = getString(R.string.alert_username_empty);
            }

            if(!valida)
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();

            return valida;
        }catch (Exception ex) {
            throw ex;
        }
    }

}
