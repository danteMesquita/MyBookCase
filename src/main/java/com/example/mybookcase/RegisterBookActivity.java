package com.example.mybookcase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mybookcase.Models.LivroModel;
import com.example.mybookcase.Util.CurrenteUserHelper;
import com.example.mybookcase.Util.ExceptionHandler;
import com.google.android.material.snackbar.Snackbar;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class RegisterBookActivity extends AppCompatActivity {
    TextView inputTitulo;
    TextView inputGenero;
    TextView inputTotalPaginas;
    TextView inputValor;
    TextView inputNumeroUltimaPaginaLida;
    Spinner comboBoxLista;
    Button btnSalvar;
    Button btnEditar;
    Button btnRemover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register_book);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            this.inputTitulo = (TextView) findViewById(R.id.inputTitulo);
            this.inputGenero = (TextView) findViewById(R.id.inputGenero);
            this.inputTotalPaginas = (TextView) findViewById(R.id.inputTotalPaginas);
            this.inputValor = (TextView) findViewById(R.id.inputValor);
            this.inputNumeroUltimaPaginaLida = (TextView) findViewById(R.id.inputNumeroUltimaPaginaLida);
            this.comboBoxLista = (Spinner) findViewById(R.id.comboBoxLista);
            this.btnSalvar = (Button) findViewById(R.id.btnSalvar);
            this.btnEditar = (Button) findViewById(R.id.btnEditar);
            this.btnRemover = (Button) findViewById(R.id.btnRemover);

            Intent intent = getIntent();
            Object intentURI = intent.getStringExtra("Adicionar");
            if(!intent.equals(null) &&  intentURI == null) {
                String id = intent.getStringExtra("_id");
                String liv_c_titulo = intent.getStringExtra("liv_c_titulo");
                String liv_c_genero = intent.getStringExtra("liv_c_genero");
                String liv_n_numeroPaginas = intent.getStringExtra("liv_n_numeroPaginas");
                String liv_n_paginaAtual = intent.getStringExtra("liv_n_paginaAtual");
                String liv_n_valor = intent.getStringExtra("liv_n_valor");
                String liv_c_lista = intent.getStringExtra("liv_c_lista");

                inputTitulo.setText(liv_c_titulo);
                inputGenero.setText(liv_c_genero);
                inputTotalPaginas.setText(liv_n_numeroPaginas);
                inputValor.setText(liv_n_valor);
                inputNumeroUltimaPaginaLida.setText(liv_n_paginaAtual);

                if(liv_c_lista.equals("Para ler")) {
                    comboBoxLista.setSelection(0);
                }

                if(liv_c_lista.equals("Lendo")) {
                    comboBoxLista.setSelection(1);
                }

                if(liv_c_lista.equals("Já Lidos")) {
                    comboBoxLista.setSelection(2);
                }

                btnSalvar.setVisibility(View.GONE);
                btnRemover.setVisibility(View.VISIBLE);
                btnEditar.setVisibility(View.VISIBLE);
            }else {
                btnRemover.setVisibility(View.GONE);
                btnEditar.setVisibility(View.GONE);
            }

            btnEditar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try{

                        if(validaDados(v)) {
                            int idLivro = parseInt(getIntent().getStringExtra("_id"));
                            LivroModel livroModel = new LivroModel().buscaPorId(idLivro, getApplicationContext());
                            livroModel.liv_c_titulo = inputTitulo.getText().toString();
                            livroModel.liv_c_genero = inputGenero.getText().toString();
                            livroModel.liv_n_numeroPaginas = parseInt(inputTotalPaginas.getText().toString());

                            if(!inputNumeroUltimaPaginaLida.getText().toString().matches("")) {
                                livroModel.liv_n_paginaAtual = parseInt(inputNumeroUltimaPaginaLida.getText().toString());
                            }

                            float valor = parseFloat(inputValor.getText().toString());
                            livroModel.liv_n_valor = valor;
                            livroModel.liv_usu_n_id = CurrenteUserHelper.getCurrentUserId();
                            livroModel.liv_c_lista = comboBoxLista.getSelectedItem().toString();
                            //livroModel.liv_blob_imagem = inputGenero.getText().toString();
                            livroModel.Editar(livroModel, getApplicationContext());

                            Toast.makeText(RegisterBookActivity.this, R.string.edit_book_success_message, Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(RegisterBookActivity.this, MainActivity.class);
                            startActivity(it);

                        }

                    }catch (Exception ex) {
                        Toast.makeText(RegisterBookActivity.this, new ExceptionHandler(ex).exceptionMessage, Toast.LENGTH_SHORT).show();
                    }

                }
            });

            btnSalvar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try{

                        if(validaDados(v)) {
                            LivroModel livroModel = new LivroModel();
                            livroModel.liv_c_titulo = inputTitulo.getText().toString();
                            livroModel.liv_c_genero = inputGenero.getText().toString();
                            livroModel.liv_n_numeroPaginas = parseInt(inputTotalPaginas.getText().toString());

                            if(!inputNumeroUltimaPaginaLida.getText().toString().matches("")) {
                                livroModel.liv_n_paginaAtual = parseInt(inputNumeroUltimaPaginaLida.getText().toString());
                            }

                            float valor = parseFloat(inputValor.getText().toString());
                            livroModel.liv_n_valor = valor;
                            livroModel.liv_usu_n_id = CurrenteUserHelper.getCurrentUserId();
                            livroModel.liv_c_lista = comboBoxLista.getSelectedItem().toString();
                            //livroModel.liv_blob_imagem = inputGenero.getText().toString();
                            livroModel.Adicionar(livroModel, getApplicationContext());

                            Toast.makeText(RegisterBookActivity.this, R.string.register_book_success_message, Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(RegisterBookActivity.this, MainActivity.class);
                            startActivity(it);

                        }

                    }catch (Exception ex) {
                        Toast.makeText(RegisterBookActivity.this, new ExceptionHandler(ex).exceptionMessage, Toast.LENGTH_SHORT).show();
                    }

                }
            });

            btnRemover.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try{
                        int idLivro = parseInt(getIntent().getStringExtra("_id"));
                        LivroModel livroModel = new LivroModel().buscaPorId(idLivro, getApplicationContext());
                        livroModel.Excluir(livroModel, getApplicationContext());
                        Toast.makeText(RegisterBookActivity.this, R.string.delete_book_success_message, Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(RegisterBookActivity.this, MainActivity.class);
                        startActivity(it);
                    }catch (Exception ex) {
                        Toast.makeText(RegisterBookActivity.this, new ExceptionHandler(ex).exceptionMessage, Toast.LENGTH_SHORT).show();
                    }

                }
            });

            comboBoxLista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if(position == 1) {
                        inputNumeroUltimaPaginaLida.setVisibility(View.VISIBLE);
                    }else {
                        inputNumeroUltimaPaginaLida.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });


        }catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "ERROSQWE " + new ExceptionHandler(ex).exceptionMessage, Toast.LENGTH_LONG).show();
        }
    }

    private boolean validaDados(View view) {
        try{
            boolean valida = true;
            String message = "";

            if(comboBoxLista.getSelectedItemPosition() == 1) {
                if(this.inputNumeroUltimaPaginaLida.getText().toString().equals("") || this.inputNumeroUltimaPaginaLida.getText().equals(null)) {
                    valida = false;
                    this.inputTitulo.forceLayout();
                    message = getString(R.string.campoUltimaPaginaNaoPreenchido);
                }
            }

            if(this.inputValor.getText().toString().equals("") || this.inputValor.getText().equals(null)) {
                valida = false;
                this.inputTitulo.forceLayout();
                message = getString(R.string.campoValorNaoPreenchido);
            }

            if(this.inputTotalPaginas.getText().toString().equals("") || this.inputTotalPaginas.getText().equals(null)) {
                valida = false;
                this.inputTitulo.forceLayout();
                message = getString(R.string.campoTotalPaginasNaoPreenchido);
            }

            if(this.inputGenero.getText().toString().equals("") || this.inputGenero.getText().equals(null)) {
                valida = false;
                this.inputTitulo.forceLayout();
                message = getString(R.string.campoGeneroNaoPreenchido);
            }

            if(this.inputTitulo.getText().toString().equals("") || this.inputTitulo.getText().equals(null)) {
                valida = false;
                this.inputTitulo.forceLayout();
                message = getString(R.string.campoTituloNaoPreenchido);
            }

            if(!valida)
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();

            return valida;
        }catch (Exception ex) {
            throw ex;
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
        if (id == R.id.paraLer) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
