package com.example.my_sqlite_android;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class consulta_RecyclerView extends AppCompatActivity {

    private static final String URL = "http://mjgl.com.sv/mysqlcrud/Api.php";
    List<Productos> productosList;
    RecyclerView recyclerView;

    ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_recycleview);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productosList = new ArrayList<>();

        //Toast.makeText(this, "Si", Toast.LENGTH_SHORT).show();

        loadProductos();

    }


    private void loadProductos() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText(Consulta_RecyclerView.this, ""+response, Toast.LENGTH_SHORT).show();

                        try {
                            JSONArray array = new JSONArray(response);
                            int totalEncontrados = array.length();
                            Toast.makeText(consulta_RecyclerView.this, "Total: "+totalEncontrados, Toast.LENGTH_SHORT).show();

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject articulosObject = array.getJSONObject(i);

                                //String img = articulosObject.getString("imagen");
                                //Toast.makeText(Consulta_RecyclerView.this, ""+img, Toast.LENGTH_SHORT).show();

                                /*int codigo = articulosObject.getInt("codigo");
                                String descripcion = articulosObject.getString("descripcion");
                                double precio = articulosObject.getDouble("precio");
                                String img = articulosObject.getString("imagen");
                                Productos objeto = new Productos(codigo, descripcion, precio, img);
                                productosList.add(objeto);*/

                                productosList.add(new Productos(
                                        articulosObject.getInt("codigo"),
                                        articulosObject.getString("descripcion"),
                                        articulosObject.getDouble("precio"),
                                        articulosObject.getString("imagen")
                                ));
                            }

                            adapter = new ProductsAdapter(consulta_RecyclerView.this, productosList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(consulta_RecyclerView.this, "Error. Compruebe su acceso a Internet.", Toast.LENGTH_SHORT).show();
            }
        });

        //Volley.newRequestQueue(this).add(stringRequest);
        // MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        MySingleton.getInstance(consulta_RecyclerView.this).addToRequestQueue(stringRequest);
    }




}