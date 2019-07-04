package com.example.mybookcase.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.mybookcase.DataBase.DataBaseHelper;
import com.example.mybookcase.Util.CurrenteUserHelper;
import com.example.mybookcase.Util.ExceptionHandler;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class LivroModel {

    public int _id;
    public String liv_c_titulo;
    public String liv_c_genero;
    public int liv_n_numeroPaginas;
    public int liv_n_paginaAtual;
    public double liv_n_valor;
    public String liv_c_lista;
    public int liv_usu_n_id;
    public Blob liv_blob_imagem;

    public void Adicionar(LivroModel livro, Context context) {
        try{
            ContentValues dataToAddInDatabase = new ContentValues();
            dataToAddInDatabase.put("liv_c_titulo", livro.liv_c_titulo);
            dataToAddInDatabase.put("liv_c_genero", livro.liv_c_genero);
            dataToAddInDatabase.put("liv_n_numeroPaginas", livro.liv_n_numeroPaginas);
            dataToAddInDatabase.put("liv_n_paginaAtual", livro.liv_n_paginaAtual);
            dataToAddInDatabase.put("liv_n_valor", livro.liv_n_valor);
            dataToAddInDatabase.put("liv_c_lista", livro.liv_c_lista);
            dataToAddInDatabase.put("liv_usu_n_id", livro.liv_usu_n_id);

            if(livro.liv_c_lista.equals("Já Lidos")) {
                livro.liv_n_paginaAtual = livro.liv_n_numeroPaginas;
                dataToAddInDatabase.put("liv_n_paginaAtual", livro.liv_n_paginaAtual);
            }
            //dataToAddInDatabase.put("liv_blob_imagem", livro.liv_blob_imagem);

            DataBaseHelper db = new DataBaseHelper(context);
            db.getWritableDatabase().insert("tb_liv_livro", null,dataToAddInDatabase);
            db.close();

        }catch (Exception ex) {
            Toast.makeText(context, "ERRO12312 " + new ExceptionHandler(ex).exceptionMessage, Toast.LENGTH_LONG).show();
        }
    }

    public void Editar(LivroModel livro, Context context) {
        try{
            ContentValues dataToAddInDatabase = new ContentValues();
            dataToAddInDatabase.put("_id", livro._id);
            dataToAddInDatabase.put("liv_c_titulo", livro.liv_c_titulo);
            dataToAddInDatabase.put("liv_c_genero", livro.liv_c_genero);
            dataToAddInDatabase.put("liv_n_numeroPaginas", livro.liv_n_numeroPaginas);
            dataToAddInDatabase.put("liv_n_paginaAtual", livro.liv_n_paginaAtual);
            dataToAddInDatabase.put("liv_n_valor", livro.liv_n_valor);
            dataToAddInDatabase.put("liv_c_lista", livro.liv_c_lista);
            dataToAddInDatabase.put("liv_usu_n_id", livro.liv_usu_n_id);
            //dataToAddInDatabase.put("liv_blob_imagem", livro.liv_blob_imagem);

            if(livro.liv_c_lista.equals("Já Lidos")) {
                livro.liv_n_paginaAtual = livro.liv_n_numeroPaginas;
                dataToAddInDatabase.put("liv_n_paginaAtual", livro.liv_n_paginaAtual);
            }

            DataBaseHelper db = new DataBaseHelper(context);
            db.getWritableDatabase().update("tb_liv_livro", dataToAddInDatabase, "_id=?", new String[]{Integer.toString(livro._id)});
            db.close();

        }catch (Exception ex) {
            throw ex;
        }
    }

    public void Excluir(LivroModel livro, Context context) {
        try{
            DataBaseHelper db = new DataBaseHelper(context);
            db.getWritableDatabase().delete("tb_liv_livro","_id=?", new String[]{Integer.toString(livro._id)});
            db.close();
        }catch (Exception ex) {
            throw ex;
        }
    }

    public LivroModel buscaPorId(int idLivro, Context context) {
        try{
            LivroModel livro = new LivroModel();
            DataBaseHelper db = new DataBaseHelper(context);
            Cursor cursor = db.getReadableDatabase().query(
                    "tb_liv_livro", new String[] {
                            "_id",
                            "liv_c_titulo",
                            "liv_c_genero",
                            "liv_n_numeroPaginas",
                            "liv_n_paginaAtual",
                            "liv_n_valor",
                            "liv_c_lista",
                            "liv_usu_n_id",
                            "liv_blob_imagem",
                    }, "_id = " + idLivro, null, null, null, "_id desc"
            );


            if(cursor.moveToFirst()) {
                do{
                    String id = cursor.getString(cursor.getColumnIndex(("_id")));
                    String liv_c_titulo = cursor.getString(cursor.getColumnIndex(("liv_c_titulo")));
                    String liv_c_genero = cursor.getString(cursor.getColumnIndex(("liv_c_genero")));
                    String liv_n_numeroPaginas = cursor.getString(cursor.getColumnIndex(("liv_n_numeroPaginas")));
                    String liv_n_paginaAtual = cursor.getString(cursor.getColumnIndex(("liv_n_paginaAtual")));
                    String liv_n_valor = cursor.getString(cursor.getColumnIndex(("liv_n_valor")));
                    String liv_c_lista = cursor.getString(cursor.getColumnIndex(("liv_c_lista")));
                    String liv_usu_n_id = cursor.getString(cursor.getColumnIndex(("liv_usu_n_id")));
                    String liv_blob_imagem = cursor.getString(cursor.getColumnIndex(("liv_blob_imagem")));

                    LivroModel LivroModel = new LivroModel();
                    LivroModel._id = parseInt(id);
                    LivroModel.liv_c_titulo = liv_c_titulo;
                    LivroModel.liv_c_genero = liv_c_genero;
                    LivroModel.liv_n_numeroPaginas = parseInt(liv_n_numeroPaginas);
                    LivroModel.liv_n_paginaAtual = parseInt(liv_n_paginaAtual);
                    LivroModel.liv_n_valor = parseDouble(liv_n_valor);
                    LivroModel.liv_c_lista = liv_c_lista;
                    LivroModel.liv_usu_n_id = parseInt(liv_usu_n_id);
                    //LivroModel.liv_blob_imagem = liv_blob_imagem;

                    db.close();
                    return LivroModel;

                }while (cursor.moveToNext());
            }

            return null;
        }catch (Exception ex) {
            return null;
        }
    }

    public ArrayList<LivroModel> listarTodoslivros(Context context){
        try{

            ArrayList<LivroModel> listalivros = new ArrayList<LivroModel>();
            String currentuserId = CurrenteUserHelper.getInstance().getCurrentUserId() + "";
            DataBaseHelper db = new DataBaseHelper(context);
            Cursor cursor = db.getReadableDatabase().query(
                    "tb_liv_livro", new String[] {
                            "_id",
                            "liv_c_titulo",
                            "liv_c_genero",
                            "liv_n_numeroPaginas",
                            "liv_n_paginaAtual",
                            "liv_n_valor",
                            "liv_c_lista",
                            "liv_usu_n_id",
                            "liv_blob_imagem",
                    }, "liv_usu_n_id = " + currentuserId, null, null, null, "_id desc"
            );


            if(cursor.moveToFirst()) {
                do{
                    String id = cursor.getString(cursor.getColumnIndex(("_id")));
                    String liv_c_titulo = cursor.getString(cursor.getColumnIndex(("liv_c_titulo")));
                    String liv_c_genero = cursor.getString(cursor.getColumnIndex(("liv_c_genero")));
                    String liv_n_numeroPaginas = cursor.getString(cursor.getColumnIndex(("liv_n_numeroPaginas")));
                    String liv_n_paginaAtual = cursor.getString(cursor.getColumnIndex(("liv_n_paginaAtual")));
                    String liv_n_valor = cursor.getString(cursor.getColumnIndex(("liv_n_valor")));
                    String liv_c_lista = cursor.getString(cursor.getColumnIndex(("liv_c_lista")));
                    String liv_usu_n_id = cursor.getString(cursor.getColumnIndex(("liv_usu_n_id")));
                    String liv_blob_imagem = cursor.getString(cursor.getColumnIndex(("liv_blob_imagem")));

                    LivroModel LivroModel = new LivroModel();
                    LivroModel._id = parseInt(id);
                    LivroModel.liv_c_titulo = liv_c_titulo;
                    LivroModel.liv_c_genero = liv_c_genero;
                    LivroModel.liv_n_numeroPaginas = parseInt(liv_n_numeroPaginas);
                    LivroModel.liv_n_paginaAtual = parseInt(liv_n_paginaAtual);
                    LivroModel.liv_n_valor = parseDouble(liv_n_valor);
                    LivroModel.liv_c_lista = liv_c_lista;
                    LivroModel.liv_usu_n_id = parseInt(liv_usu_n_id);
                    //LivroModel.liv_blob_imagem = liv_blob_imagem;

                    listalivros.add(LivroModel);

                }while (cursor.moveToNext());
            }

            db.close();
            return listalivros;

        }catch(Exception ex) {
            Toast.makeText(context, "ERRO4432 " + new ExceptionHandler(ex).exceptionMessage, Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public ArrayList<LivroModel> listarTodoslivrosDaListaLendo(Context context){
        try{

            ArrayList<LivroModel> listalivros = new ArrayList<LivroModel>();
            String currentuserId = CurrenteUserHelper.getInstance().getCurrentUserId() + "";
            DataBaseHelper db = new DataBaseHelper(context);
            Cursor cursor = db.getReadableDatabase().query(
                    "tb_liv_livro", new String[] {
                            "_id",
                            "liv_c_titulo",
                            "liv_c_genero",
                            "liv_n_numeroPaginas",
                            "liv_n_paginaAtual",
                            "liv_n_valor",
                            "liv_c_lista",
                            "liv_usu_n_id",
                            "liv_blob_imagem",
                    }, "liv_c_lista = 'Lendo' and liv_usu_n_id = " + currentuserId, null, null, null, "_id desc"
            );


            if(cursor.moveToFirst()) {
                do{
                    String id = cursor.getString(cursor.getColumnIndex(("_id")));
                    String liv_c_titulo = cursor.getString(cursor.getColumnIndex(("liv_c_titulo")));
                    String liv_c_genero = cursor.getString(cursor.getColumnIndex(("liv_c_genero")));
                    String liv_n_numeroPaginas = cursor.getString(cursor.getColumnIndex(("liv_n_numeroPaginas")));
                    String liv_n_paginaAtual = cursor.getString(cursor.getColumnIndex(("liv_n_paginaAtual")));
                    String liv_n_valor = cursor.getString(cursor.getColumnIndex(("liv_n_valor")));
                    String liv_c_lista = cursor.getString(cursor.getColumnIndex(("liv_c_lista")));
                    String liv_usu_n_id = cursor.getString(cursor.getColumnIndex(("liv_usu_n_id")));
                    String liv_blob_imagem = cursor.getString(cursor.getColumnIndex(("liv_blob_imagem")));

                    LivroModel LivroModel = new LivroModel();
                    LivroModel._id = parseInt(id);
                    LivroModel.liv_c_titulo = liv_c_titulo;
                    LivroModel.liv_c_genero = liv_c_genero;
                    LivroModel.liv_n_numeroPaginas = parseInt(liv_n_numeroPaginas);
                    LivroModel.liv_n_paginaAtual = parseInt(liv_n_paginaAtual);
                    LivroModel.liv_n_valor = parseDouble(liv_n_valor);
                    LivroModel.liv_c_lista = liv_c_lista;
                    LivroModel.liv_usu_n_id = parseInt(liv_usu_n_id);
                    //LivroModel.liv_blob_imagem = liv_blob_imagem;

                    listalivros.add(LivroModel);

                }while (cursor.moveToNext());
            }

            db.close();
            return listalivros;

        }catch(Exception ex) {
            Toast.makeText(context, "ERRO4432 " + new ExceptionHandler(ex).exceptionMessage, Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public ArrayList<LivroModel> listarTodoslivrosDaListaParaLer(Context context){
        try{

            ArrayList<LivroModel> listalivros = new ArrayList<LivroModel>();
            String currentuserId = CurrenteUserHelper.getInstance().getCurrentUserId() + "";
            DataBaseHelper db = new DataBaseHelper(context);
            Cursor cursor = db.getReadableDatabase().query(
                    "tb_liv_livro", new String[] {
                            "_id",
                            "liv_c_titulo",
                            "liv_c_genero",
                            "liv_n_numeroPaginas",
                            "liv_n_paginaAtual",
                            "liv_n_valor",
                            "liv_c_lista",
                            "liv_usu_n_id",
                            "liv_blob_imagem",
                    }, "liv_c_lista = 'Para ler' and liv_usu_n_id = " + currentuserId, null, null, null, "_id desc"
            );


            if(cursor.moveToFirst()) {
                do{
                    String id = cursor.getString(cursor.getColumnIndex(("_id")));
                    String liv_c_titulo = cursor.getString(cursor.getColumnIndex(("liv_c_titulo")));
                    String liv_c_genero = cursor.getString(cursor.getColumnIndex(("liv_c_genero")));
                    String liv_n_numeroPaginas = cursor.getString(cursor.getColumnIndex(("liv_n_numeroPaginas")));
                    String liv_n_paginaAtual = cursor.getString(cursor.getColumnIndex(("liv_n_paginaAtual")));
                    String liv_n_valor = cursor.getString(cursor.getColumnIndex(("liv_n_valor")));
                    String liv_c_lista = cursor.getString(cursor.getColumnIndex(("liv_c_lista")));
                    String liv_usu_n_id = cursor.getString(cursor.getColumnIndex(("liv_usu_n_id")));
                    String liv_blob_imagem = cursor.getString(cursor.getColumnIndex(("liv_blob_imagem")));

                    LivroModel LivroModel = new LivroModel();
                    LivroModel._id = parseInt(id);
                    LivroModel.liv_c_titulo = liv_c_titulo;
                    LivroModel.liv_c_genero = liv_c_genero;
                    LivroModel.liv_n_numeroPaginas = parseInt(liv_n_numeroPaginas);
                    LivroModel.liv_n_paginaAtual = parseInt(liv_n_paginaAtual);
                    LivroModel.liv_n_valor = parseDouble(liv_n_valor);
                    LivroModel.liv_c_lista = liv_c_lista;
                    LivroModel.liv_usu_n_id = parseInt(liv_usu_n_id);
                    //LivroModel.liv_blob_imagem = liv_blob_imagem;

                    listalivros.add(LivroModel);

                }while (cursor.moveToNext());
            }

            db.close();
            return listalivros;

        }catch(Exception ex) {
            Toast.makeText(context, "ERRO4432 " + new ExceptionHandler(ex).exceptionMessage, Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public ArrayList<LivroModel> listarTodoslivrosDaListaJaLidos(Context context){
        try{

            ArrayList<LivroModel> listalivros = new ArrayList<LivroModel>();
            String currentuserId = CurrenteUserHelper.getInstance().getCurrentUserId() + "";
            DataBaseHelper db = new DataBaseHelper(context);
            Cursor cursor = db.getReadableDatabase().query(
                    "tb_liv_livro", new String[] {
                            "_id",
                            "liv_c_titulo",
                            "liv_c_genero",
                            "liv_n_numeroPaginas",
                            "liv_n_paginaAtual",
                            "liv_n_valor",
                            "liv_c_lista",
                            "liv_usu_n_id",
                            "liv_blob_imagem",
                    }, "liv_c_lista = 'Já Lidos' and liv_usu_n_id = " + currentuserId, null, null, null, "_id desc"
            );


            if(cursor.moveToFirst()) {
                do{
                    String id = cursor.getString(cursor.getColumnIndex(("_id")));
                    String liv_c_titulo = cursor.getString(cursor.getColumnIndex(("liv_c_titulo")));
                    String liv_c_genero = cursor.getString(cursor.getColumnIndex(("liv_c_genero")));
                    String liv_n_numeroPaginas = cursor.getString(cursor.getColumnIndex(("liv_n_numeroPaginas")));
                    String liv_n_paginaAtual = cursor.getString(cursor.getColumnIndex(("liv_n_paginaAtual")));
                    String liv_n_valor = cursor.getString(cursor.getColumnIndex(("liv_n_valor")));
                    String liv_c_lista = cursor.getString(cursor.getColumnIndex(("liv_c_lista")));
                    String liv_usu_n_id = cursor.getString(cursor.getColumnIndex(("liv_usu_n_id")));
                    String liv_blob_imagem = cursor.getString(cursor.getColumnIndex(("liv_blob_imagem")));

                    LivroModel LivroModel = new LivroModel();
                    LivroModel._id = parseInt(id);
                    LivroModel.liv_c_titulo = liv_c_titulo;
                    LivroModel.liv_c_genero = liv_c_genero;
                    LivroModel.liv_n_numeroPaginas = parseInt(liv_n_numeroPaginas);
                    LivroModel.liv_n_paginaAtual = parseInt(liv_n_paginaAtual);
                    LivroModel.liv_n_valor = parseDouble(liv_n_valor);
                    LivroModel.liv_c_lista = liv_c_lista;
                    LivroModel.liv_usu_n_id = parseInt(liv_usu_n_id);
                    //LivroModel.liv_blob_imagem = liv_blob_imagem;

                    listalivros.add(LivroModel);

                }while (cursor.moveToNext());
            }

            db.close();
            return listalivros;

        }catch(Exception ex) {
            Toast.makeText(context, "ERRO4432 " + new ExceptionHandler(ex).exceptionMessage, Toast.LENGTH_LONG).show();
            return null;
        }
    }

}
