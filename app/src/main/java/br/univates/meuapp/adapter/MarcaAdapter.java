package br.univates.meuapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.univates.meuapp.CadastroLinguagensActivity;
import br.univates.meuapp.R;
import br.univates.meuapp.model.Marca;

public class MarcaAdapter extends ArrayAdapter<Marca> {

    private final Context context;
    private final ArrayList<Marca> elementos;

    public MarcaAdapter(Context context, ArrayList<Marca> elementos){
        super(context, R.layout.item_lista_linguagem, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try{
            Marca objeto = elementos.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_lista_linguagem, parent, false);

            TextView nome = rowView.findViewById(R.id.lblNome_item_linguagem);

            nome.setText(objeto.getNome());

            return rowView;

        }catch (Exception ex){
            Log.e("ERRO_ADAPTER", ex.getMessage());
            return null;
        }

    }
}
