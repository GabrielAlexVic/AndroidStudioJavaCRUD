package com.example.javacrud.domain.models.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.javacrud.infrastructure.data.DbContext;
import com.example.javacrud.infrastructure.data.DbGateway;

public class FornecedorDAO {
    private DbGateway gw;

    public FornecedorDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean salvar(String nome, String endereco, String telefone){
        ContentValues cv = new ContentValues();

        cv.put("nome", nome);
        cv.put("endereco", endereco);
        cv.put("telefone", telefone);
        return gw.getDatabase().insert("Fornecedores", null, cv) > 0;
    }

    public Cursor listarFornecedores(Context context){
        DbContext db = new DbContext(context);
        SQLiteDatabase sqLiteDatabase = db.getSQLiteDb();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + "Fornecedores", null);

        return cursor;
    }

}
