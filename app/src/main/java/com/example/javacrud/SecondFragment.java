package com.example.javacrud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.javacrud.databinding.FragmentSecondBinding;
import com.example.javacrud.infrastructure.data.DbContext;
import com.google.android.material.snackbar.Snackbar;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        Button btnSalvar = binding.buttonSave;
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nometxt = binding.editName;
                EditText enderecotxt = binding.editAddres;
                EditText telefonetxt = binding.editPhone;

                String nome = nometxt.getText().toString();
                String endereco = enderecotxt.getText().toString();
                String telefone = telefonetxt.getText().toString();

                if(nometxt.length() == 0 && endereco.length() == 0  && telefonetxt.length() == 0)
                    Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                else{

                    //OBJ Fornecedor
                    DbContext dbContext = new DbContext(view.getContext());
                    if(dbContext.salvarFornecedor(nome, endereco, telefone)) {
                        nometxt.setText("");
                        enderecotxt.setText("");
                        telefonetxt.setText("");

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