package com.example.mybookcase.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybookcase.Models.LivroModel;
import com.example.mybookcase.R;

import java.util.ArrayList;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class LivroAdapter   extends ArrayAdapter<LivroModel> {
    private final Context context;
    private final ArrayList<LivroModel> elementos;

    public LivroAdapter(Context context, ArrayList<LivroModel> elementos) {
        super(context, R.layout.activity_main_item_da_lista_alunos, elementos);
        this.context = context;
        this.elementos= elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.activity_main_item_da_lista_alunos, parent, false);
            rowView.setTag(elementos.get(position)._id);


            TextView textViewTitulo = (TextView) rowView.findViewById(R.id.textViewTitulo);
            TextView textViewStatus = (TextView) rowView.findViewById(R.id.textViewStatus);
            TextView textViewGenero = (TextView) rowView.findViewById(R.id.textViewGenero);
            TextView textViewNumeroPaginas = (TextView) rowView.findViewById(R.id.textViewNumeroPaginas);
            TextView textViewPaginaAtual = (TextView) rowView.findViewById(R.id.textViewPaginaAtual);
            TextView textViewPreco = (TextView) rowView.findViewById(R.id.textViewPreco);
            ProgressBar progressBar = (ProgressBar) rowView.findViewById(R.id.progressBar);

            ImageView imagem = (ImageView) rowView.findViewById(R.id.bookImageView);
            imagem.setImageResource(R.drawable.icon);

            String titulo = elementos.get(position).liv_c_titulo;
            String status = elementos.get(position).liv_c_lista;
            String genero = elementos.get(position).liv_c_genero;

            int numeroPaginas = elementos.get(position).liv_n_numeroPaginas;
            String numeroPaginasString = numeroPaginas + "";

            int paginaAtual = elementos.get(position).liv_n_paginaAtual;
            String paginaAtualString = paginaAtual + "";

            String preco = (elementos.get(position).liv_n_valor + "");
            int percentaualConcluidoLivroInt = 0;

            if(numeroPaginas > 0) {
                Float paginaAtualFloat = parseFloat(paginaAtualString);
                Float numeroPaginasFloat = parseFloat(numeroPaginasString);
                Float percentualConcluido = (paginaAtualFloat / numeroPaginasFloat) * 100;
                if(Math.round(percentualConcluido) > 0) {
                    percentaualConcluidoLivroInt = Math.round(percentualConcluido);
                }
            }


            textViewTitulo.setText("Título: " + titulo);
            textViewStatus.setText("Status: " + status);
            textViewGenero.setText("Gênero: " + genero);
            textViewNumeroPaginas.setText("Número Páginas: " + numeroPaginasString);
            textViewPaginaAtual.setText("Página Atual: " + paginaAtualString);
            textViewPreco.setText("Preço: " + preco);

            if(status.equals("Para ler")) {
                progressBar.setVisibility(View.GONE);
            }

            progressBar.setMax(100);
            progressBar.setProgress(percentaualConcluidoLivroInt);

            return rowView;

        }catch(Exception ex) {
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}

