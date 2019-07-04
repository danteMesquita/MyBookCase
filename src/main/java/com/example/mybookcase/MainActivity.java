package com.example.mybookcase;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.example.mybookcase.Adapters.LivroAdapter;
import com.example.mybookcase.Models.LivroModel;
import com.example.mybookcase.Util.ExceptionHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            ListView listaLivrosMainView = (ListView) findViewById(R.id.activity_main_lista_de_livros);
            ArrayList<LivroModel> listaLivroModel = new LivroModel().listarTodoslivros(getApplicationContext());

            ArrayAdapter adapter = new LivroAdapter(this, listaLivroModel);
            listaLivrosMainView.setAdapter(adapter);


            listaLivrosMainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    int idLivro = parseInt(view.getTag().toString());
                    LivroModel livro = new LivroModel().buscaPorId(idLivro, getApplicationContext());
                    Intent it = new Intent(MainActivity.this, RegisterBookActivity.class);
                    it.putExtra("_id", livro._id + "");
                    it.putExtra("liv_c_titulo", livro.liv_c_titulo);
                    it.putExtra("liv_c_genero", livro.liv_c_genero);
                    it.putExtra("liv_n_numeroPaginas", livro.liv_n_numeroPaginas + "");
                    it.putExtra("liv_n_paginaAtual", livro.liv_n_paginaAtual + "");
                    it.putExtra("liv_n_valor", livro.liv_n_valor + "");
                    it.putExtra("liv_c_lista", livro.liv_c_lista);
                    it.putExtra("liv_usu_n_id", livro.liv_usu_n_id + "");

                    startActivity(it);
                }
            });





            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(MainActivity.this, RegisterBookActivity.class);
                    it.putExtra("Adicionar", "Adicionar");
                    startActivity(it);
                }
            });
        }catch(Exception ex) {
            Toast.makeText(this, "ERRO6541 " + new ExceptionHandler(ex).exceptionMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sair) {
            finish();
        }

        if (id == R.id.paraLer) {
            ListView listaLivrosMainView = (ListView) findViewById(R.id.activity_main_lista_de_livros);
            ArrayList<LivroModel> listaLivroModel = new LivroModel().listarTodoslivrosDaListaParaLer(getApplicationContext());

            ArrayAdapter adapter = new LivroAdapter(this, listaLivroModel);
            listaLivrosMainView.setAdapter(adapter);

            listaLivrosMainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    int idLivro = parseInt(view.getTag().toString());
                    LivroModel livro = new LivroModel().buscaPorId(idLivro, getApplicationContext());
                    Intent it = new Intent(MainActivity.this, RegisterBookActivity.class);
                    it.putExtra("_id", livro._id + "");
                    it.putExtra("liv_c_titulo", livro.liv_c_titulo);
                    it.putExtra("liv_c_genero", livro.liv_c_genero);
                    it.putExtra("liv_n_numeroPaginas", livro.liv_n_numeroPaginas + "");
                    it.putExtra("liv_n_paginaAtual", livro.liv_n_paginaAtual + "");
                    it.putExtra("liv_n_valor", livro.liv_n_valor + "");
                    it.putExtra("liv_c_lista", livro.liv_c_lista);
                    it.putExtra("liv_usu_n_id", livro.liv_usu_n_id + "");

                    startActivity(it);
                }
            });
        }

        if (id == R.id.lendo) {
            ListView listaLivrosMainView = (ListView) findViewById(R.id.activity_main_lista_de_livros);
            ArrayList<LivroModel> listaLivroModel = new LivroModel().listarTodoslivrosDaListaLendo(getApplicationContext());

            ArrayAdapter adapter = new LivroAdapter(this, listaLivroModel);
            listaLivrosMainView.setAdapter(adapter);
            listaLivrosMainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    int idLivro = parseInt(view.getTag().toString());
                    LivroModel livro = new LivroModel().buscaPorId(idLivro, getApplicationContext());
                    Intent it = new Intent(MainActivity.this, RegisterBookActivity.class);
                    it.putExtra("_id", livro._id + "");
                    it.putExtra("liv_c_titulo", livro.liv_c_titulo);
                    it.putExtra("liv_c_genero", livro.liv_c_genero);
                    it.putExtra("liv_n_numeroPaginas", livro.liv_n_numeroPaginas + "");
                    it.putExtra("liv_n_paginaAtual", livro.liv_n_paginaAtual + "");
                    it.putExtra("liv_n_valor", livro.liv_n_valor + "");
                    it.putExtra("liv_c_lista", livro.liv_c_lista);
                    it.putExtra("liv_usu_n_id", livro.liv_usu_n_id + "");

                    startActivity(it);
                }
            });
        }

        if (id == R.id.jaLidos) {
            ListView listaLivrosMainView = (ListView) findViewById(R.id.activity_main_lista_de_livros);
            ArrayList<LivroModel> listaLivroModel = new LivroModel().listarTodoslivrosDaListaJaLidos(getApplicationContext());

            ArrayAdapter adapter = new LivroAdapter(this, listaLivroModel);
            listaLivrosMainView.setAdapter(adapter);
            listaLivrosMainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    int idLivro = parseInt(view.getTag().toString());
                    LivroModel livro = new LivroModel().buscaPorId(idLivro, getApplicationContext());
                    Intent it = new Intent(MainActivity.this, RegisterBookActivity.class);
                    it.putExtra("_id", livro._id + "");
                    it.putExtra("liv_c_titulo", livro.liv_c_titulo);
                    it.putExtra("liv_c_genero", livro.liv_c_genero);
                    it.putExtra("liv_n_numeroPaginas", livro.liv_n_numeroPaginas + "");
                    it.putExtra("liv_n_paginaAtual", livro.liv_n_paginaAtual + "");
                    it.putExtra("liv_n_valor", livro.liv_n_valor + "");
                    it.putExtra("liv_c_lista", livro.liv_c_lista);
                    it.putExtra("liv_usu_n_id", livro.liv_usu_n_id + "");

                    startActivity(it);
                }
            });
        }

        if (id == R.id.verTodoslivros) {
            ListView listaLivrosMainView = (ListView) findViewById(R.id.activity_main_lista_de_livros);
            ArrayList<LivroModel> listaLivroModel = new LivroModel().listarTodoslivros(getApplicationContext());

            ArrayAdapter adapter = new LivroAdapter(this, listaLivroModel);
            listaLivrosMainView.setAdapter(adapter);
            listaLivrosMainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    int idLivro = parseInt(view.getTag().toString());
                    LivroModel livro = new LivroModel().buscaPorId(idLivro, getApplicationContext());
                    Intent it = new Intent(MainActivity.this, RegisterBookActivity.class);
                    it.putExtra("_id", livro._id + "");
                    it.putExtra("liv_c_titulo", livro.liv_c_titulo);
                    it.putExtra("liv_c_genero", livro.liv_c_genero);
                    it.putExtra("liv_n_numeroPaginas", livro.liv_n_numeroPaginas + "");
                    it.putExtra("liv_n_paginaAtual", livro.liv_n_paginaAtual + "");
                    it.putExtra("liv_n_valor", livro.liv_n_valor + "");
                    it.putExtra("liv_c_lista", livro.liv_c_lista);
                    it.putExtra("liv_usu_n_id", livro.liv_usu_n_id + "");

                    startActivity(it);
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

}
