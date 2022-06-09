package br.univates.meuapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.univates.meuapp.database.DadosOpenHelper;
import br.univates.meuapp.database.Tabelas;
import br.univates.meuapp.model.Cliente;
import br.univates.meuapp.model.Veiculo;
import br.univates.meuapp.tools.Globais;

public class VeiculoController {

    private SQLiteDatabase conexao;
    private Context context;

    public VeiculoController(Context context){
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;
    }


    public Veiculo buscar(int id){
        try{

            Veiculo objeto = null;

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_VEICULOS);
            sql.append(" WHERE id = '"+ id +"'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToNext()){
                objeto = new Veiculo();
                objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                objeto.setId_marca(resultado.getInt(resultado.getColumnIndexOrThrow("id_marca")));
                objeto.setId_modelo(resultado.getInt(resultado.getColumnIndexOrThrow("id_modelo")));
                objeto.setPlaca(resultado.getString(resultado.getColumnIndexOrThrow("placa")));
                objeto.setAno_fabricacao(resultado.getString(resultado.getColumnIndexOrThrow("ano_fabricacao")));
            }

            return objeto;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return null;
        }
    }

    public boolean incluir(Veiculo objeto){
        try{

            ContentValues valores = new ContentValues();
            valores.put("id_marca", objeto.getId_marca());
            valores.put("id_modelo", objeto.getId_modelo());
            valores.put("placa", objeto.getPlaca());
            valores.put("ano_fabricacao", objeto.getAno_fabricacao());

            conexao.insertOrThrow(Tabelas.TB_VEICULOS, null, valores);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

    public boolean alterar(Veiculo objeto){
        try{

            ContentValues valores = new ContentValues();
            valores.put("id_marca", objeto.getId_marca());
            valores.put("id_modelo", objeto.getId_modelo());
            valores.put("placa", objeto.getPlaca());
            valores.put("ano_fabricacao", objeto.getAno_fabricacao());

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(objeto.getId());

            conexao.update(Tabelas.TB_VEICULOS, valores, "id = ?" , parametros);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

    public boolean excluir(int id){
        try{

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(id);

            conexao.delete(Tabelas.TB_VEICULOS, "id = ?", parametros);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }
    public ArrayList<Veiculo> lista(){

        ArrayList<Veiculo> listagem = new ArrayList<>();
        try{

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_VEICULOS);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToFirst()){

                Veiculo objeto;
                do{
                    objeto = new Veiculo();
                    objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                    objeto.setId_marca(resultado.getInt(resultado.getColumnIndexOrThrow("id_marca")));
                    objeto.setId_modelo(resultado.getInt(resultado.getColumnIndexOrThrow("id_modelo")));
                    objeto.setPlaca(resultado.getString(resultado.getColumnIndexOrThrow("placa")));
                    objeto.setAno_fabricacao(resultado.getString(resultado.getColumnIndexOrThrow("ano_fabricacao")));

                    listagem.add(objeto);

                }while (resultado.moveToNext());

            }

            return listagem;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return listagem;
        }
    }
}
