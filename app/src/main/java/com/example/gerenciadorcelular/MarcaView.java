package com.example.gerenciadorcelular;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MarcaView extends AppCompatActivity {

    LocalDatabase db;
    private EditText edtMarca;
    private Button btnSalvar, btnExcluir;
    private int dbMarcaID;
    private Marca dbMarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_marcas);
        db = LocalDatabase.getDatabase(getApplicationContext());
        edtMarca = findViewById(R.id.edtMarca);
        btnSalvar = findViewById(R.id.btnSalvarMarca);
        btnExcluir = findViewById(R.id.btnExcluirMarca);
        dbMarcaID = getIntent().getIntExtra("MARCA_SELECIONADA_ID", -1);
    }

    protected void onResume() {
        super.onResume();
        if(dbMarcaID >= 0) {
            getDBMarca();
        } else {
            btnExcluir.setVisibility(View.GONE);
        }
    }

    private void getDBMarca() {
        dbMarca = db.marcaModel().get(dbMarcaID);
        edtMarca.setText(dbMarca.getMarca());
    }

    public void salvarMarca(View view) {
        String nomeMarca = edtMarca.getText().toString();

        if (nomeMarca.equals("")) {
            Toast.makeText(this, "é necessário um nome para marca.", Toast.LENGTH_SHORT).show();
            return;
        }

        Marca thisMarca = new Marca();
        thisMarca.setMarca(nomeMarca);

        if (dbMarca != null) {
            thisMarca.setMarcaID(dbMarcaID);
            db.marcaModel().update(thisMarca);
            Toast.makeText(this, "Marca atualizada com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.marcaModel().insertAll(thisMarca);
            Toast.makeText(this, "Marca criada com sucesso.", Toast.LENGTH_SHORT).show();
        }

        finish();

    }

    public void excluirMarca(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Marca")
                .setMessage("Deseja excluir essa marca?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluir() {
        try {
            db.marcaModel().delete(dbMarca);
            Toast.makeText(this, "Marca excluída com sucesso", Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this, "Impossível excluir marca com celulares cadastrados", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void voltar(View view) {

        finish();
    }
}
