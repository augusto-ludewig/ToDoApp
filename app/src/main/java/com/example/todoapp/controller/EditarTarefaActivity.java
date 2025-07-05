package com.example.todoapp.controller;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.R;
import com.example.todoapp.model.dao.TarefaDao;
import com.example.todoapp.model.entities.Tarefa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

public class EditarTarefaActivity extends AppCompatActivity {

    private EditText inputTitulo, inputDescricao, inputDataConclusao;
    private Switch switchImportante;
    private Button botaoSalvar;
    private LocalDateTime dataSelecionada;
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_tarefa);

        inputTitulo = findViewById(R.id.inputTitulo);
        inputDescricao = findViewById(R.id.inputDescricao);
        inputDataConclusao = findViewById(R.id.inputDataConclusao);
        switchImportante = findViewById(R.id.switchImportante);
        botaoSalvar = findViewById(R.id.buttonSalvar);

        inputDataConclusao.setFocusable(false);
        inputDataConclusao.setOnClickListener(v -> abrirDatePicker());

        long id = getIntent().getLongExtra("tarefaId", -1);
        TarefaDao dao = new TarefaDao(this);
        tarefa = dao.getById(id);
        if (tarefa != null) preencherCampos();

        botaoSalvar.setOnClickListener(v -> salvarAlteracoes());
        Button botaoCancelar = findViewById(R.id.buttonCancelar);
        botaoCancelar.setOnClickListener(v -> cancelarEdicao());
    }

    private void preencherCampos() {
        inputTitulo.setText(tarefa.getTitulo());
        inputDescricao.setText(tarefa.getDescricao());
        dataSelecionada = tarefa.getDataDeConclusao();
        if (dataSelecionada != null)
            inputDataConclusao.setText(String.format("%02d/%02d/%04d",
                    dataSelecionada.getDayOfMonth(),
                    dataSelecionada.getMonthValue(),
                    dataSelecionada.getYear()));

        switchImportante.setChecked(tarefa.isImportante());
    }

    private void abrirDatePicker() {
        Calendar calendarioAtual = Calendar.getInstance();
        int ano = calendarioAtual.get(Calendar.YEAR);
        int mes = calendarioAtual.get(Calendar.MONTH);
        int dia = calendarioAtual.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    inputDataConclusao.setText(String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear));
                    dataSelecionada = LocalDateTime.of(LocalDate.of(selectedYear, selectedMonth + 1, selectedDay), LocalTime.of(12, 0));
                },
                ano, mes, dia
        );

        datePickerDialog.show();
    }

    private void salvarAlteracoes() {
        String titulo = inputTitulo.getText().toString();
        String descricao = inputDescricao.getText().toString();
        boolean importante = switchImportante.isChecked();

        if (titulo.isEmpty() || descricao.isEmpty() || dataSelecionada == null) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setDataDeConclusao(dataSelecionada);
        tarefa.setImportante(importante);

        TarefaDao dao = new TarefaDao(this);
        int linhasAfetadas = dao.atualizar(tarefa);
        if (linhasAfetadas > 0) {
            Toast.makeText(this, "Tarefa atualizada com sucesso!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Erro ao atualizar tarefa", Toast.LENGTH_SHORT).show();
        }

    }

    private void cancelarEdicao() {
        Toast.makeText(this, "Edição cancelada", Toast.LENGTH_SHORT).show();
        finish(); // Fecha a tela e volta para a lista
    }
}
