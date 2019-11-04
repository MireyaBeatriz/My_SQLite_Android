package com.example.my_sqlite_android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Main2Activity extends AppCompatActivity {
    private EditText et_codigo, et_descripcion, et_precio;
    private Button btn_guardar, btn_consultaCodigo, btn_consultaDescripcion,
            btn_eliminar, btn_actualizar;
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

    AlertDialog.Builder dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitleMargin(0, 0, 0, 0);
        toolbar.setSubtitle("MySQL~2019");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.design_default_color_primary_dark));
        toolbar.setTitle("Mireya Garcia ");
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          WindowManager.LayoutParams.FLAG_FULLSCREEN);

        et_codigo = (EditText) findViewById(R.id.et_codigo);
        et_descripcion = (EditText) findViewById(R.id.et_descripcion);
        et_precio = (EditText) findViewById(R.id.et_precio);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        btn_consultaCodigo = (Button) findViewById(R.id.btn_consultaCodigo);
        btn_consultaDescripcion = (Button) findViewById(R.id.btn_consultaDescripcion);
        btn_eliminar = (Button) findViewById(R.id.btn_eliminar);
        btn_actualizar = (Button) findViewById(R.id.btn_actualizar);
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
                    manto.consultarCodigo(Main2Activity.this, codigo);
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
            Intent spinnerActivity = new Intent(Main2Activity.this, consulta_RecyclerView.class);
            startActivity(spinnerActivity);
            return true;
        }else if(id == R.id.action_salir){
            DialogConfirmacion();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void DialogConfirmacion(){
        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
        String mensaje = "¿Realmente desea salir?";
        dialogo = new AlertDialog.Builder(Main2Activity.this);
        dialogo.setIcon(R.drawable.ic_close);
        dialogo.setTitle("Advertencia");
        dialogo.setMessage(mensaje);
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                /*Intent intent = new Intent(DashboardLuces.this, luces_control_sms.class);
                startActivity(intent);*/
                Main2Activity.this.finishAffinity();
                //Main2Activity.this.finish();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Toast.makeText(getApplicationContext(), "Operación Cancelada.", Toast.LENGTH_LONG).show();
            }
        });
        dialogo.show();
    }


    //Creación de HILOS
    void Hilo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=1; i++){
                    demora();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String cod = getSharedCodigo(Main2Activity.this);
                        String des = getSharedDescripcion(Main2Activity.this);
                        String pre = getSharedPrecio(Main2Activity.this);

                        et_codigo.setText(cod);
                        et_descripcion.setText(des);
                        et_precio.setText(pre);

                        //Toast.makeText(Main2Activity.this, "Código: "+cod + "\nPrecio: "+pre + "\nDescripción: "+des, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }


    private void demora(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){}
    }


    public String getSharedCodigo(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("profeGamez", MODE_PRIVATE);
        String codigo = preferences.getString("codigo","0");
        return codigo;   //return preferences.getString("tiempo", "Sin configurar.");
    }

    public String getSharedDescripcion(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("profeGamez", MODE_PRIVATE);
        String descripcion = preferences.getString("descripcion","Sin descripción");
        return descripcion;   //return preferences.getString("tiempo", "Sin configurar.");
    }

    public String getSharedPrecio(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("profeGamez", MODE_PRIVATE);
        String precio = preferences.getString("precio","0.0");
        return precio;   //return preferences.getString("tiempo", "Sin configurar.");
    }



}
