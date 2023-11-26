package com.example.javacrud.infrastructure.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbContext extends SQLiteOpenHelper {

    private Context _context;
    private static final String dbNome = "Fornecedores.db";
    private static final int dbVersion = 1;
    private String tabelaFornecedorNome = "Fornecedores";
    private String tabelaProdutosNome = "Produtos";

    public DbContext(Context context) {
        super(context, dbNome, null, dbVersion);
    }

    private final String tabelaFornecedor = "CREATE TABLE " + tabelaFornecedorNome + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT , endereco TEXT, telefone TEXT);";
    private final String tabelaProduto = "CREATE TABLE " + tabelaProdutosNome + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT , marca TEXT, preco REAL);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabelaFornecedor);
        db.execSQL(tabelaProduto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tabelaFornecedorNome);
        onCreate(db);
    }

    public SQLiteDatabase getSQLiteDb(){
        return this.getReadableDatabase();
    }

}
