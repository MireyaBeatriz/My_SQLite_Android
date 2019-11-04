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
    Dto datos = new Dto();


}