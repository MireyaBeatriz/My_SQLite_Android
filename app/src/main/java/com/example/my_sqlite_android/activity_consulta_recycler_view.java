package com.example.my_sqlite_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class activity_consulta_recycler_view extends AppCompatActivity {
    RecyclerView recyclerView;

    ProductsAdapter adapter;

    //MantenimientoMySQL manto = new MantenimientoMySQL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_recycleview2);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adapter = new ProductsAdapter(activity_consulta__recycler_view1.this, manto.consultarAllArticulos(getApplicationContext()));
        //recyclerView.setAdapter(adapter);


    }
}
