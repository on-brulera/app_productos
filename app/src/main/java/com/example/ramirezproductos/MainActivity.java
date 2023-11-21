package com.example.ramirezproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtId, txtNombre, txtDescripcion, txtStock, txtPrecioUnitario, txtIVA;
    Productos productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtStock = findViewById(R.id.txtStock);
        txtPrecioUnitario = findViewById(R.id.txtPrecioUnitario);
        txtIVA = findViewById(R.id.txtIVA);
        productos = new Productos(this,"productos.db",1);
    }

    public void cmdCreate_onClick(View v){
        try {
            Producto p = productos.Create(
                    Integer.parseInt(txtId.getText().toString()),
                    txtNombre.getText().toString(),
                    txtDescripcion.getText().toString(),
                    Double.parseDouble(txtStock.getText().toString()),
                    Double.parseDouble(txtPrecioUnitario.getText().toString()),
                    Double.parseDouble(txtIVA.getText().toString())
            );

            if (p != null) {
                Toast.makeText(this, "DATOS INSERTADOS", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "ERROR AL INSERTAR DATOS", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error de formato en los datos ingresados", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void cmdReadById_onClick(View v){
        Producto p = productos.Read_ById(Integer.parseInt(txtId.getText().toString()));
        if (p != null){
            txtNombre.setText(p.nombre);
            txtDescripcion.setText(p.descripcion);
            txtStock.setText(p.stock + "");
            txtPrecioUnitario.setText(p.precio_unitario + "");
            txtIVA.setText(p.tasa_iva + "");
            Toast.makeText(this,"ELEMENTO ENCONTRADO", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this,"ELEMENTO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
        }
    }

    public void cmdUpdate_onClick(View v){
        productos.Update(
                Integer.parseInt(txtId.getText().toString()),
                txtNombre.getText().toString(),
                txtDescripcion.getText().toString(),
                Double.parseDouble(txtStock.getText().toString()),
                Double.parseDouble(txtPrecioUnitario.getText().toString()),
                Double.parseDouble(txtIVA.getText().toString())
                );
        Toast.makeText(this,"DATOS ACTUALIZADOS", Toast.LENGTH_SHORT).show();
    }

    public void cmdDelete_onClick(View v){
        productos.Delete(Integer.parseInt(txtId.getText().toString()));
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtId.setText("");
        txtStock.setText("");
        txtPrecioUnitario.setText("");
        txtIVA.setText("");
        Toast.makeText(this,"DATO ELIMINADO", Toast.LENGTH_SHORT).show();
    }
}