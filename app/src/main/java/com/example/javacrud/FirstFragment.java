package com.example.javacrud;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.javacrud.databinding.FragmentFirstBinding;
import com.example.javacrud.infrastructure.data.DbContext;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinnerFornecedores = binding.spinnerFornecedores;

        DbContext dbContext = new DbContext(view.getContext());
        Cursor cursor = dbContext.listFornecedoresNome();

        List<String> listaFornecedores = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int columnNome = cursor.getColumnIndex("nome");
                String nomeFornecedor = cursor.getString(columnNome);
                listaFornecedores.add(nomeFornecedor);
            } while (cursor.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, listaFornecedores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFornecedores.setAdapter(adapter);

        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        Button btnSalvar = binding.buttonSave;
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nometxt = binding.editName;
                EditText marcatxt = binding.editBrand;
                EditText precotxt = binding.editPrice;
                Spinner fornecedoresNome = binding.spinnerFornecedores;

                String nome = nometxt.getText().toString();
                String marca = marcatxt.getText().toString();
                String preco = String.valueOf(precotxt.getText());
                String fornecedorSelecionadoNome = (String) fornecedoresNome.getSelectedItem();

                if(nometxt.length() == 0 && marca.length() == 0  && preco.length() == 0 )
                    Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                else{

                    //OBJ Produto

                    DbContext dbContext = new DbContext(view.getContext());

                    if(dbContext.salvarProduto(nome, marca, Double.valueOf(preco), dbContext.getProdutoPorFornedorNome(fornecedorSelecionadoNome))) {
                        nometxt.setText("");
                        marcatxt.setText("");
                        precotxt.setText("");

                        Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }else{
                        Snackbar.make(view, "Erro ao salvar", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}