package com.example.gerenciadorcelular;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class CelularView extends AppCompatActivity {

    LocalDatabase db;
    private EditText edtModelo;
    private Button btnSalvarCelular, btExcluir;
    private int dbCelularID;
    private Celular dbCelular;
    List<Marca> marcas;
    Spinner spnMarcas;
    ArrayAdapter<Marca> marcasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_celulares);
        db = LocalDatabase.getDatabase(getApplicationContext());
        edtModelo = findViewById(R.id.edtModelo);
        btExcluir = findViewById(R.id.btnExcluirModelo);
        spnMarcas = findViewById(R.id.spnMarcas);
        dbCelularID = getIntent().getIntExtra("CELULAR_SELECIONADO_ID", -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dbCelularID >= 0){
            preencheCelular();
        } else {
            btExcluir.setVisibility(View.GONE);
        }
        preencheMarcas();
    }

    private void preencheCelular() {
        dbCelular = db.celularModel().get(dbCelularID);
        edtModelo.setText(dbCelular.getModelo());
    }

    private void preencheMarcas() {
        marcas = db.marcaModel().getAll();
        marcasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, marcas);
        spnMarcas.setAdapter(marcasAdapter);
        if(dbCelular != null) {
            spnMarcas.setSelection(dbCelular.getMarcaID() -1);
        }
    }

    public void salvarCelular(View view) {
        String modelo = edtModelo.getText().toString();
        String novaMarca = "";

        if(spnMarcas.getSelectedItem() != null){
            novaMarca = spnMarcas.getSelectedItem().toString();
        }

        if(modelo.equals("")){
            Toast.makeText(this, "O modelo é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }

        if(novaMarca.equals("")) {
            Toast.makeText(this, "O celular precisa de uma marca.", Toast.LENGTH_SHORT).show();
            return;
        }

        Celular novoCelular = new Celular();
        novoCelular.setModelo(modelo);
        novoCelular.setMarcaID(marcas.get(spnMarcas.getSelectedItemPosition()).getMarcaID());

        if(dbCelular != null){
            novoCelular.setMarcaID(dbCelularID);
            db.celularModel().updateName(novoCelular.getModelo(), dbCelularID);
            Toast.makeText(this, "Celular atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.celularModel().insertAll(novoCelular);
            Toast.makeText(this, "Celular cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public void excluirCelular(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Celular")
                .setMessage("Deseja excluir esse celular?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void excluir() {
        db.celularModel().delete(dbCelular);
        Toast.makeText(this, "Celular excluído com sucesso.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void voltar(View view) {
        finish();
    }

    public void cadastrarMarca(View view) {
        startActivity(new Intent(this, MarcaView.class));
    }

}
