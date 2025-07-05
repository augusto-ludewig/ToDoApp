package com.example.todoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.controller.EditarTarefaActivity;
import com.example.todoapp.model.dao.TarefaDao;
import com.example.todoapp.model.entities.Tarefa;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private List<Tarefa> tarefas;
    private Context context;
    private TarefaDao tarefaDao;

    public TarefaAdapter(Context context, List<Tarefa> tarefas) {
        this.context = context;
        this.tarefas = tarefas;
        this.tarefaDao = new TarefaDao(context);
    }

    static class TarefaViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textTitulo;
        TextView textImportante;
        TextView textPrazo;
        ImageButton buttonEditar;


        public TarefaViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBoxAtividade);
            textTitulo = itemView.findViewById(R.id.textViewTarefa);
            textImportante = itemView.findViewById(R.id.importante);
            textPrazo = itemView.findViewById(R.id.textPrazo);
            buttonEditar = itemView.findViewById(R.id.buttonEditar);
        }
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycle_view_item, parent, false);
        return new TarefaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        Tarefa tarefa = tarefas.get(position);

        holder.textTitulo.setText(tarefa.getTitulo());
        holder.checkBox.setChecked(tarefa.isConcluida());

        // Importante
        holder.textImportante.setText(tarefa.isImportante() ? "Importante" : "Normal");
        holder.textImportante.setTextColor(context.getColor(
                tarefa.isImportante() ? android.R.color.holo_red_dark : android.R.color.darker_gray
        ));

        // Prazo
        if (tarefa.getDataDeConclusao() != null) {
            String prazoFormatado = tarefa.getDataDeConclusao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            holder.textPrazo.setText("Prazo: " + prazoFormatado);
        } else {
            holder.textPrazo.setText("");
        }

        // Concluir e apagar
        holder.checkBox.setOnClickListener(v -> {
            int posicao = holder.getAdapterPosition();
            if (posicao != RecyclerView.NO_POSITION) {
                tarefaDao.deletar(tarefas.get(posicao));
                tarefas.remove(posicao);
                notifyItemRemoved(posicao);
            }
        });

        // Pressionar e segurar para editar
        holder.itemView.setOnLongClickListener(v -> {
            Intent intent = new Intent(context, EditarTarefaActivity.class);
            intent.putExtra("tarefaId", tarefa.getid());
            ((Activity) context).startActivityForResult(intent, 100); // ou usar ActivityResultLauncher
            return true;
        });

        // Clicar no lápis para editar
        holder.buttonEditar.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditarTarefaActivity.class);
            intent.putExtra("tarefaId", tarefa.getid());
            ((Activity) context).startActivityForResult(intent, 100);
        });
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }
}
