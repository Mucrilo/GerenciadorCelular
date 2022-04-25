package com.example.gerenciadorcelular;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class CelularList extends AppCompatActivity {

    LocalDatabase db;
    List<Celular> celulares;
    ListView listViewCelular;
    Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_celulares);
        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewCelular = findViewById(R.id.listViewCelulares);
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, CelularView.class);
        preencheCelulares();
    }

    private void preencheCelulares() {
        celulares = db.celularModel().getAll();
        ArrayAdapter<Celular> celularesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, celulares);
        listViewCelular.setAdapter(celularesAdapter);

        listViewCelular.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id){
                Celular celularselecionado = celulares.get(position);
                edtIntent.putExtra("CELULAR_SELECIONADO_ID", celularselecionado.celularID);
                startActivity(edtIntent);
            }
        });
    }

    public void cadastrarCelular(View view) {

        startActivity(edtIntent);

    }

    public void voltar(View view) {

        finish();
    }
}
