package com.example.teacher.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacher.R;
import com.example.teacher.activity.AgendaActivity;
import com.example.teacher.activity.Login;
import com.example.teacher.activity.Resumo;
import com.example.teacher.config.ConfiguracaoFirebase;
import com.example.teacher.holder.HolderAgenda;
import com.example.teacher.model.Agenda;

import java.util.List;

public class AdapterAgenda extends RecyclerView.Adapter<HolderAgenda>{
    List<Agenda> listaAgenda;
    Context context;
    public AdapterAgenda(List<Agenda> listaAgenda, Context context) {
        this.listaAgenda = listaAgenda;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderAgenda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderAgenda(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_agenda, parent,
                                false));
    }
    @Override
    public void onBindViewHolder(@NonNull HolderAgenda holder, int position) {
        Agenda agenda = listaAgenda.get(position);
        holder.txtNome.setText(agenda.getCurso());
        holder.txtMateria.setText(agenda.getMateria());
        holder.txtLocal.setText(agenda.getLocal());
        holder.txtData.setText(agenda.getData());
        holder.txtHora.setText(agenda.getHora());

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), Resumo.class);
                intent.putExtra("agenda", agenda);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Deseja deletar agenda?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Sim ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            agenda.excluirAgenda(agenda.getId());
                            Toast.makeText(context, "Agenda deletada com sucesso!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

            }
        });
    }
    @Override
    public int getItemCount() {
        return listaAgenda.size();
    }
}