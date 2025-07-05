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
import com.example.todoapp.model.dao.UsuarioDao;

public class MainActivity extends AppCompatActivity {

    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usuarioDao = new UsuarioDao(this);

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

        if(usuarioDao.autenticar(usuario, senha)) {
            goToSecondActivity();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getString(R.string.error));
            builder.setMessage("Usuário ou senha incorretos");
            builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                usuarioEditText.setText("");
                senhaEditText.setText("");
            });
            builder.show();
        }
    }

    public void goToSecondActivity(){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    public void abrirTelaCadastro(View view) {
        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
    }
}