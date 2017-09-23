package com.example.verel.readwrite;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtLastname;
    TextView readingText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = (EditText) findViewById(R.id.txtName);
        txtLastname = (EditText) findViewById(R.id.txtLastname);
        readingText = (TextView) findViewById(R.id.tvReadingText) ;
    }

    public void write(View view){
        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();

            File f = new File(ruta_sd.getAbsolutePath(), "readwrite.txt");
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            new FileOutputStream(f));
            String text = txtName.getText().toString()+":"+ txtLastname.getText().toString();
            fout.write(text);
            fout.close();
            txtName.setText("");
            txtLastname.setText("");
            readingText.setText("");
            Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Problem writing file");
        }
    }

    public void read(View view){
        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();

            File f = new File(ruta_sd.getAbsolutePath(), "readwrite.txt");

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(f)));

            String texto = fin.readLine();
            StringTokenizer tokens = new StringTokenizer(texto, ":");
            String name = tokens.nextToken();
            String lastname = tokens.nextToken();
            readingText.setText("Name: " + name+ " \nLastname: "+ lastname);
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Problem reading the SD card");
        }
    }
}
