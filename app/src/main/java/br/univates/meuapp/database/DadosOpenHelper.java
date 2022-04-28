package br.univates.meuapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.univates.meuapp.foradeuso.Ferramentas;
import br.univates.meuapp.tools.Globais;

public class DadosOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2; //versão do banco de dados
    private static final String NM_BANCO = "banco";
    private Context context;

    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            StringBuilder sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tabelas.TB_LINGUAGENS);
            sql.append("(");
            sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append("nome VARCHAR(30) NOT NULL, ");
            sql.append("cargo TEXT, ");
            sql.append("favorito bit, ");
            sql.append(")");
            db.execSQL(sql.toString());

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Ferramentas.mostrarAlerta(context,"Alerta", "Erro ao criar tabela");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            StringBuilder sql;
            if(newVersion == 2){
                sql = new StringBuilder();
                sql.append(" ALTER TABLE ");
                sql.append(Tabelas.TB_LINGUAGENS);
                sql.append(" ADD COLUMN ");
                sql.append(" favorito BIT ");
                db.execSQL(sql.toString());
            }


        }catch (Exception ex){
            Ferramentas.mostrarAlerta(context,"Alerta", "Erro ao atualizar tabela");
        }
    }
}
