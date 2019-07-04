package com.example.mybookcase.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String BANCO = "APPLICATION_BD";
    public static final int VERSAO = 4;

    public DataBaseHelper(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tb_usu_usuario (_id INTEGER primary key autoincrement, usu_c_username TEXT not null UNIQUE , usu_c_password TEXT);");
        db.execSQL("CREATE TABLE tb_liv_livro (_id INTEGER primary key autoincrement, liv_c_titulo TEXT, liv_c_genero TEXT,liv_n_numeroPaginas INTEGER, liv_n_paginaAtual INTEGER, liv_n_valor REAL, liv_c_lista TEXT,liv_usu_n_id INTEGER, liv_blob_imagem BLOB); ");
        db.execSQL("CREATE TABLE tb_lis_lista (_id INTEGER primary key autoincrement, lis_c_nome TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tb_usu_usuario;");
        db.execSQL("DROP TABLE IF EXISTS tb_liv_livro;");
        db.execSQL("DROP TABLE IF EXISTS tb_lis_lista;");
        onCreate(db);
    }



}
