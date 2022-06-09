package br.univates.meuapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.univates.meuapp.foradeuso.Ferramentas;
import br.univates.meuapp.tools.Globais;

public class DadosOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 15; //versão do banco de dados
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
            sql.append(" ( ");
            sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" nome VARCHAR(30) NOT NULL, ");
            sql.append(" cargo TEXT, ");
            sql.append(" favorito bit, ");
            sql.append(" nota INTEGER ");
            sql.append(" ) ");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tabelas.TB_CLIENTES);
            sql.append(" ( ");
            sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" nome VARCHAR(30) NOT NULL, ");
            sql.append(" telefone VARCHAR(15), ");
            sql.append(" data_nascimento DATE, ");
            sql.append(" cpf VARCHAR(11) ");
            sql.append(" ) ");
            db.execSQL(sql.toString());


            sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tabelas.TB_MARCAS);
            sql.append(" ( ");
            sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" nome VARCHAR(30) NOT NULL ");
            sql.append(" ) ");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tabelas.TB_MODELOS);
            sql.append(" ( ");
            sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" nome VARCHAR(30) NOT NULL ");
            sql.append(" ) ");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tabelas.TB_VEICULOS);
            sql.append(" ( ");
            sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" id_marca INTEGER, ");
            sql.append(" id_modelo INTEGER, ");
            sql.append(" placa VARCHAR(20) NOT NULL, ");
            sql.append(" ano_fabricacao INTEGER, ");
            sql.append(" CONSTRAINT fk_marca FOREIGN KEY (id_marca) REFERENCES marcas (id), ");
            sql.append(" CONSTRAINT fk_modelo FOREIGN KEY (id_modelo) REFERENCES modelos (id) ");
            sql.append(" ) ");
            db.execSQL(sql.toString());

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Ferramentas.mostrarAlerta(context,"Alerta", "Erro ao criar tabelas");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            StringBuilder sql;
            if(newVersion >= 2) {
                try {
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tabelas.TB_LINGUAGENS);
                    sql.append(" ADD COLUMN ");
                    sql.append(" favorito BIT ");
                    db.execSQL(sql.toString());
                } catch (Exception ex) {
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tabelas.TB_LINGUAGENS);
                    sql.append(" ADD COLUMN ");
                    sql.append(" nota INTEGER ");
                    db.execSQL(sql.toString());
                } catch (Exception ex) {
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" CREATE TABLE IF NOT EXISTS ");
                    sql.append(Tabelas.TB_CLIENTES);
                    sql.append(" ( ");
                    sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
                    sql.append(" nome VARCHAR(30) NOT NULL ");
                    sql.append(" ) ");
                    db.execSQL(sql.toString());

                } catch (Exception ex) {
                    Log.e("CREATE_NEW_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tabelas.TB_CLIENTES);
                    sql.append(" ADD COLUMN ");
                    sql.append(" telefone VARCHAR(15) ");
                    db.execSQL(sql.toString());
                } catch (Exception ex) {
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tabelas.TB_CLIENTES);
                    sql.append(" ADD COLUMN ");
                    sql.append(" data_nascimento DATE ");
                    db.execSQL(sql.toString());
                } catch (Exception ex) {
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tabelas.TB_CLIENTES);
                    sql.append(" ADD COLUMN ");
                    sql.append(" cpf VARCHAR(11) ");
                    db.execSQL(sql.toString());
                } catch (Exception ex) {
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" CREATE TABLE IF NOT EXISTS ");
                    sql.append(Tabelas.TB_MARCAS);
                    sql.append(" ( ");
                    sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
                    sql.append(" nome VARCHAR(30) NOT NULL ");
                    sql.append(" ) ");
                    db.execSQL(sql.toString());

                    sql = new StringBuilder();
                    sql.append(" CREATE TABLE IF NOT EXISTS ");
                    sql.append(Tabelas.TB_MODELOS);
                    sql.append(" ( ");
                    sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
                    sql.append(" nome VARCHAR(30) NOT NULL ");
                    sql.append(" ) ");
                    db.execSQL(sql.toString());

                    sql = new StringBuilder();
                    sql.append(" CREATE TABLE IF NOT EXISTS ");
                    sql.append(Tabelas.TB_VEICULOS);
                    sql.append(" ( ");
                    sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
                    sql.append(" id_marca INTEGER, ");
                    sql.append(" id_modelo INTEGER, ");
                    sql.append(" placa VARCHAR(20) NOT NULL, ");
                    sql.append(" ano_fabricacao INTEGER, ");
                    sql.append(" CONSTRAINT fk_marca FOREIGN KEY (id_marca) REFERENCES marcas (id), ");
                    sql.append(" CONSTRAINT fk_modelo FOREIGN KEY (id_modelo) REFERENCES modelos (id) ");
                    sql.append(" ) ");
                    db.execSQL(sql.toString());

                } catch (Exception ex) {
                    Log.e("INSERT_TABLE", ex.getMessage());
                }

            }
        }catch (Exception ex){
            Ferramentas.mostrarAlerta(context,"Alerta", "Erro ao atualizar tabela");
        }
    }
}
