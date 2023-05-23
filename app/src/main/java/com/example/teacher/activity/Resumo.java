package com.example.teacher.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teacher.R;
import com.example.teacher.adapter.AdapterAgenda;
import com.example.teacher.config.ConfiguracaoFirebase;
import com.example.teacher.helper.Base64Custom;
import com.example.teacher.model.Agenda;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Resumo extends AppCompatActivity {

    private Agenda agenda;
    private TextView textNome, textMateria, textLocal, textData, textHora, textResumo;
    private Button buttonSalvar, buttonCancelar;
    private DatabaseReference agendaRef;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        textNome = findViewById(R.id.txtNome1);
        textMateria = findViewById(R.id.txtMateria1);
        textLocal = findViewById(R.id.txtLocal1);
        textData = findViewById(R.id.txtData1);
        textHora = findViewById(R.id.txtHora1);
        textResumo = findViewById(R.id.txtResumo);
        buttonSalvar = findViewById(R.id.btnSalvar);
        buttonCancelar = findViewById(R.id.btnCancelar);
       agendaRef = ConfiguracaoFirebase.getFirebaseDatabase().child("agendaProfessor");


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            agenda = (Agenda) extras.getSerializable("agenda");

            textNome.setText(agenda.getCurso());
            textMateria.setText(agenda.getMateria());
            textLocal.setText(agenda.getLocal());
            textData.setText(agenda.getData());
            textHora.setText(agenda.getHora());
            textResumo.setText(agenda.getResumo());

            buttonSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String resumo = textResumo.getText().toString();
                    String idUsuario = agenda.getId();
                    if (resumo.isEmpty()) {
                        Toast.makeText(Resumo.this, "Preencha o resumo!", Toast.LENGTH_SHORT).show();
                    } else {
                        agenda.setResumo(resumo);
                        agenda.salvarResumo(idUsuario);

                        Toast.makeText(Resumo.this, "Agenda atualizada com sucesso!", Toast.LENGTH_SHORT).show();
                        abrirTelaAgenda();
                        finish();
                    }
                }
            });
        }
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaAgenda();
                finish();
            }
        });
    }

    public void fecharTeclado(View view) {
        InputMethodManager fecharteclado = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        fecharteclado.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void abrirTelaAgenda() {
        startActivity(new Intent(this, AgendaActivity.class));
    }
}
