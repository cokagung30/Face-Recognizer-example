package com.example.facerecognizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facerecognizer.adapter.AUser;
import com.example.facerecognizer.database.DbUser;
import com.example.facerecognizer.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edt_nama, edt_nim;
    Button btn_wajah;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AUser aUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_nama = findViewById(R.id.edt_nama);
        edt_nim = findViewById(R.id.edt_nim);
        recyclerView = findViewById(R.id.rv_user);
        btn_wajah = findViewById(R.id.btn_wajah);

        btn_wajah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nama = edt_nama.getText().toString();
                final String nim = edt_nim.getText().toString();
                if (edt_nama.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Field Nama Kosong !!!", Toast.LENGTH_SHORT).show();
                } else if (edt_nim.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Field NIM Kosong !!!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, DeteksiActivity.class);
                    intent.putExtra("nama", nama);
                    intent.putExtra("nim", nim);
                    startActivity(intent);
                    finish();
                }
            }
        });

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        refreshList();
    }

    private void refreshList(){
        List<User> users = User.findWithQuery(User.class, "SELECT * FROM USER");

        aUser = new AUser(this, users);
        recyclerView.setAdapter(aUser);
    }
}
