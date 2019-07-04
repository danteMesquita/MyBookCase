package com.example.mybookcase.Models;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

import com.example.mybookcase.DataBase.DataBaseHelper;
import com.example.mybookcase.LoginActivity;
import com.example.mybookcase.MainActivity;
import com.example.mybookcase.Util.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class UsuarioModel {

    public int _id;
    public String usu_c_username;
    public String usu_c_password;

    public UsuarioModel () {}

    public UsuarioModel(String userName, String password) {
        this.usu_c_username = userName;
        this.usu_c_password = password;
    }

    public void Adicionar(UsuarioModel usuario, Context context) {
        try{

            ContentValues dataToAddInDatabase = new ContentValues();
            dataToAddInDatabase.put("usu_c_username", usuario.usu_c_username);
            dataToAddInDatabase.put("usu_c_password", usuario.usu_c_password);

            DataBaseHelper db = new DataBaseHelper(context);
            db.getWritableDatabase().insert("tb_usu_usuario", null,dataToAddInDatabase);
            db.close();

        }catch (Exception ex) {
            Toast.makeText(context, "ERRO001 " + new ExceptionHandler(ex).exceptionMessage, Toast.LENGTH_LONG).show();
        }
    }

    public List<UsuarioModel> listarTodosUsuarios(Context context){
        try{

            ArrayList<UsuarioModel> listaUsuarios = new ArrayList<UsuarioModel>();

            DataBaseHelper db = new DataBaseHelper(context);
            Cursor cursor = db.getReadableDatabase().query("tb_usu_usuario", new String[] {"_id", "usu_c_username", "usu_c_password"}, null, null, null, null, null);

            if(cursor.moveToFirst()) {
                do{
                    String id = cursor.getString(cursor.getColumnIndex(("_id")));
                    String usu_c_username = cursor.getString(cursor.getColumnIndex(("usu_c_username")));
                    String usu_c_password = cursor.getString(cursor.getColumnIndex(("usu_c_password")));

                    UsuarioModel usuarioModel = new UsuarioModel(usu_c_username,usu_c_password);
                    usuarioModel._id = parseInt(id);
                    listaUsuarios.add(usuarioModel);

                }while (cursor.moveToNext());
            }

            db.close();
            return listaUsuarios;

        }catch(Exception ex) {

            throw ex;
        }
    }

}
