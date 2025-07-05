package com.example.todoapp.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.R;
import com.example.todoapp.model.dao.UsuarioDao;
import com.example.todoapp.model.entities.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText inputNovoUsuario;
    private EditText inputNovaSenha;
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inputNovoUsuario = findViewById(R.id.inputNovoUsuario);
        inputNovaSenha = findViewById(R.id.inputNovaSenha);
        usuarioDao = new UsuarioDao(this);
    }

    public void salvarUsuario(View view) {
        String usuario = inputNovoUsuario.getText().toString().trim();
        String senha = inputNovaSenha.getText().toString().trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (usuarioDao.usuarioExiste(usuario)) {
            Toast.makeText(this, "Usuário já existe", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario novoUsuario = new Usuario(usuario, senha);
        long id = usuarioDao.inserir(novoUsuario);

        if (id > 0) {
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            finish(); // Voltar para tela de login
        } else {
            Toast.makeText(this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
        }
    }

    public void voltarLogin(View view) {
        finish();
    }
}
