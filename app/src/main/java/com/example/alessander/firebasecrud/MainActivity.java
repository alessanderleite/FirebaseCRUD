package com.example.alessander.firebasecrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.example.alessander.firebasecrud.modelo.Pessoa;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    EditText editName, editEmail;
    ListView listV_dados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editName = (EditText) findViewById(R.id.editNome);
        listV_dados = (ListView) findViewById(R.id.listV_dados);

        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_novo) {
            Pessoa p = new Pessoa();
            p.setUid(UUID.randomUUID().toString());
            p.setNome(editName.getText().toString());
            p.setEmail(editEmail.getText().toString());
            databaseReference.child("Pessoa").child(p.getUid()).setValue(p);
            limparCampos();
        }
        return true;
    }

    private void limparCampos() {
        editEmail.setText("");
        editName.setText("");
    }
}
