package com.example.gerenciadorcelular;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MarcaList extends AppCompatActivity {

    LocalDatabase db;
    List<Marca> marcas;
    ListView listViewMarcas;
    Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_marcas);
        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewMarcas = findViewById(R.id.listViewMarcas);
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, MarcaView.class);
        preencheMarcas();
    }

    private void preencheMarcas() {
        marcas = db.marcaModel().getAll();
        ArrayAdapter<Marca> marcasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, marcas);
        listViewMarcas.setAdapter(marcasAdapter);
        listViewMarcas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Marca marcaselecionada = marcas.get(position);
                edtIntent.putExtra("MARCA_SELECIONADA_ID", marcaselecionada.marcaID);
                startActivity(edtIntent);
            }
        });
    }

    public void cadastrarMarca(View view) {

        startActivity(edtIntent);

    }

    public void voltar(View view) {

        finish();

    }
}
