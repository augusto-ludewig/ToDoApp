package com.example.todoapp.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todoapp.R;
import com.example.todoapp.model.DataModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void efetuarLogin(View v){

        EditText usuarioEditText = findViewById(R.id.inputUsuario);
        EditText senhaEditText = findViewById(R.id.inputSenha);
        String usuario = usuarioEditText.getText().toString();
        String senha = senhaEditText.getText().toString();

        if(usuario.equals(DataModel.getInstance().userDetails.getUsuario()) &&
        senha.equals(DataModel.getInstance().userDetails.getSenha())){
            goToSecondActivity();
        } else if (usuario.equals(DataModel.getInstance().userDetails.getUsuario()) &&
                !senha.equals(DataModel.getInstance().userDetails.getSenha())){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getString(R.string.error));
            builder.setMessage(R.string.senha_incorreta);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    senhaEditText.setText("");
                }
            });
            builder.show();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getString(R.string.error));
            builder.setMessage(R.string.usuario_incorreto);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    usuarioEditText.setText("");
                    senhaEditText.setText("");
                }
            });
            builder.show();
        }

    }

    public void goToSecondActivity(){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("", ""); //Se for passar algo para a segunda tela, passar por aqui atrvés de chave e valor
        startActivity(intent);
    }




}