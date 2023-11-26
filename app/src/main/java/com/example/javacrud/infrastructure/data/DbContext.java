package com.example.javacrud.infrastructure.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.javacrud.domain.models.Fornecedor;

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
    private final String tabelaProduto =
            "CREATE TABLE " + tabelaProdutosNome + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT , marca TEXT, preco REAL,fornecedorId INTEGER, FOREIGN KEY (fornecedorId) REFERENCES " + tabelaFornecedorNome + "(id));";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabelaFornecedor);
        db.execSQL(tabelaProduto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tabelaFornecedorNome);
        db.execSQL("DROP TABLE IF EXISTS " + tabelaProdutosNome);
        onCreate(db);
    }

    public SQLiteDatabase getSQLiteDb(){
        return this.getReadableDatabase();
    }

    public boolean salvarFornecedor(String nome, String endereco, String telefone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nome", nome);
        cv.put("endereco", endereco);
        cv.put("telefone", telefone);

        return db.insert(tabelaFornecedorNome, null, cv) > 0;
    }

    public Cursor listFornecedoresNome() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"nome"};
        return db.query(tabelaFornecedorNome, columns, null, null, null, null, null);
    }

    public boolean salvarProduto(String nome, String marca, Double preco, int fornecedorId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nome", nome);
        cv.put("marca", marca);
        cv.put("preco", preco);
        cv.put("fornecedorId", fornecedorId);

        return db.insert(tabelaProdutosNome, null, cv) > 0;
    }

    public int getProdutoPorFornedorNome(String nome) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM " + tabelaFornecedorNome + " WHERE nome = ?";

        Cursor cursor = db.rawQuery(query, new String[]{nome});

        int fornecedor = 0;
        if (cursor != null && cursor.moveToFirst()) {
            int fornecedorId = cursor.getColumnIndex("id");
            fornecedor = cursor.getInt(fornecedorId);
            cursor.close();
        }

        return fornecedor;
    }

    public Cursor listProdutosNome() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"nome"};
        return db.query(tabelaProdutosNome, columns, null, null, null, null, null);
    }

}
