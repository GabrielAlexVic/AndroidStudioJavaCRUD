package com.example.javacrud.domain.models.dao;

import android.content.ContentValues;
import android.content.Context;

import com.example.javacrud.infrastructure.data.DbGateway;

public class FornecedorDAO {
    private final String TABLE_FORNECEDORES = "Fornecedores";
    private DbGateway gw;

    public FornecedorDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean salvar(String nome, String endereco, String telefone){
        ContentValues cv = new ContentValues();
        cv.put("Nome", nome);
        cv.put("Endereço", endereco);
        cv.put("Telefone", telefone);
        return gw.getDatabase().insert(TABLE_FORNECEDORES, null, cv) > 0;
    }

}
