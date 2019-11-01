package com.example.my_sqlite_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Main2Activity extends AppCompatActivity {
    private EditText et_codigo, et_descripcion, et_precio;
    private Button btn_guardar, btn_consultaCodigo, btn_consultaDescripcion, btn_eliminar, btn_actualizar;
    private TextView tv_resultado;
    boolean inputEt=false;
    boolean inputEd=false;
    boolean input1=false;
    int resultadoInsert=0;

    String senal = "";
    String codigo = "";
    String descripcion = "";
    String precio = "";

    MantoSQLite manto = new MantoSQLite();
    boolean estadoGuarda = false;
    boolean estadoEliminar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setTitleTextColor(getResources().getColor(R.color.mycolor1));
        toolbar.setTitleMargin(0, 0, 0, 0);
        toolbar.setSubtitle("MySQL~2019");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.mycolor));
        toolbar.setTitle("Mireya Garcia ");
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          WindowManager.LayoutParams.FLAG_FULLSCREEN);

        et_codigo = (EditText) findViewById(R.id.etcodigo);
        et_descripcion = (EditText) findViewById(R.id.etdescripcion);
        et_precio = (EditText) findViewById(R.id.etprecio);
        btn_guardar = (Button) findViewById(R.id.btnGuardar);
        btn_consultaCodigo = (Button) findViewById(R.id.btnConsultar);
        btn_consultaDescripcion = (Button) findViewById(R.id.btnConsultar1);
        btn_eliminar = (Button) findViewById(R.id.btnEliminar);
        btn_actualizar = (Button) findViewById(R.id.btnModificar);
        tv_resultado = (TextView) findViewById(R.id.tv_resultado);

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                senal = bundle.getString("senal");
                codigo = bundle.getString("codigo");
                descripcion = bundle.getString("descripcion");
                precio = bundle.getString("precio");
                if (senal.equals("1")) {
                    et_codigo.setText(codigo);
                    et_descripcion.setText(descripcion);
                    et_precio.setText(precio);
                    //finish();
                }else if(senal.equals("2")){

                }
            }
        }catch (Exception e){

        }

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("Campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }
                if(et_descripcion.getText().toString().length()==0){
                    et_descripcion.setError("Campo obligatorio");
                    inputEd = false;
                }else {
                    inputEd=true;
                }
                if(et_precio.getText().toString().length()==0){
                    et_precio.setError("Campo obligatorio");
                    input1 = false;
                }else {
                    input1=true;
                }

                if (inputEt && inputEd && input1){
                    String codigo = et_codigo.getText().toString();
                    String descripcion = et_descripcion.getText().toString();
                    String precio = et_precio.getText().toString();
                    estadoGuarda = manto.guardar1(Main2Activity.this, codigo, descripcion, precio);
                    if(estadoGuarda){
                        Toast.makeText(Main2Activity.this, "Registro Almacenado Correctamente.", Toast.LENGTH_SHORT).show();
                        limpiarDatos();
                    }
                }


            }
        });
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }

                if(inputEt){
                    String codigo = et_codigo.getText().toString();
                    manto.eliminar(Main2Activity.this, codigo);

                    et_codigo.setText(null);

                }
            }
        });


        btn_consultaCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Begin...
                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }

                if(inputEt) {
                    String codigo = et_codigo.getText().toString();
                    manto.consultarcodigo(Main2Activity.this, codigo);
                    limpiarDatos();
                    et_codigo.requestFocus();
                }

            }
        });
        btn_consultaDescripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_descripcion.getText().toString().length()==0){
                    et_descripcion.setError("Campo obligatorio");
                    inputEd = false;
                }else {
                    inputEd=true;
                }
                if(inputEd){
                    String descripcion = et_descripcion.getText().toString();
                    manto.consultarDescripcion(Main2Activity.this, descripcion);
                    limpiarDatos();
                    et_descripcion.requestFocus();
                }

            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    public void limpiarDatos(){
        et_codigo.setText(null);
        et_descripcion.setText(null);
        et_precio.setText(null);
        et_codigo.requestFocus();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_limpiar) {
            et_codigo.setText(null);
            et_descripcion.setText(null);
            et_precio.setText(null);
            return true;
        }else if(id == R.id.action_listaArticulos){
            //Intent spinnerActivity = new Intent(Main2Activity.this, ConsultaSpinner.class);
            //startActivity(spinnerActivity);
            return true;
        }else if(id == R.id.action_listaArticulos1){
            //Intent listViewActivity = new Intent(Main2Activity.this, ListViewArticulos.class);
            //startActivity(listViewActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
