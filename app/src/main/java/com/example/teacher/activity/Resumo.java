package com.example.teacher.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.teacher.R;

public class Resumo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
    }
    public void fecharTeclado(View view){
        InputMethodManager fecharteclado = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        fecharteclado.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}