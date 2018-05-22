package br.iesb.atividade2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editNome;
    private EditText editTelefone;
    private EditText editEndereco;
    private EditText editIdade;
    private Spinner spinnerSexo;
    private Toast aviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciar();
    }

    private void salvar() {
        if(editNome.getText().toString().isEmpty()){
            editNome.requestFocus();
            aviso("Nome");
        } else if (editTelefone.getText().toString().isEmpty()){
            editTelefone.requestFocus();
            aviso("Telefone");
        } else if (editEndereco.getText().toString().isEmpty()){
            editEndereco.requestFocus();
            aviso("Endereço");
        } else if (editIdade.getText().toString().isEmpty()){
            editIdade.requestFocus();
            aviso("Idade");
        } else {
            SharedPreferences dados = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = dados.edit();

            editor.putString("nome", editNome.getText().toString());
            editor.putString("telefone", editTelefone.getText().toString());
            editor.putString("endereco", editEndereco.getText().toString());
            editor.putString("idade", editIdade.getText().toString());
            editor.commit();

            aviso.makeText(MainActivity.this, "Dados salvo com sucesso!", Toast.LENGTH_LONG).show();
        }
    }

    private void carregar() {
         SharedPreferences dados = getPreferences(Context.MODE_PRIVATE);

        if(dados.getAll().isEmpty()) {

            aviso.makeText(MainActivity.this, "Nenhum dado encontrado!", Toast.LENGTH_LONG).show();
        }else {
            editNome.setText(dados.getString("nome", ""));
            editTelefone.setText(dados.getString("telefone", ""));
            editEndereco.setText(dados.getString("endereco", ""));
            editIdade.setText(dados.getString("idade", ""));
            spinnerSexo.setSelection(Integer.parseInt(dados.getString("sexo", "")));

            aviso.makeText(MainActivity.this, "Dados encontrado!", Toast.LENGTH_LONG).show();
        }
    }

    private void aviso(String campo) {
        aviso.makeText(MainActivity.this, "Campo " + campo + " obrigatório!", Toast.LENGTH_LONG).show();
    }

    private void iniciar() {
        editNome = findViewById(R.id.editNome);
        editTelefone = findViewById(R.id.editTelefone);
        editEndereco = findViewById(R.id.editEndereco);
        editIdade = findViewById(R.id.editIdade);
        spinnerSexo = findViewById(R.id.spinnerSexo);
        Button buttonSalvar = findViewById(R.id.buttonSalvar);
        Button buttonCarregar = findViewById(R.id.buttonCarregar);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        buttonCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregar();
            }
        });
    }
}
