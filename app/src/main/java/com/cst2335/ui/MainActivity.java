package com.cst2335.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cst2335.chaowu.R;
import com.cst2335.chaowu.databinding.ActivityMainBinding;
import com.cst2335.data.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //connect viewmodel
        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());
        //setContentView(R.layout.activity_main);


        variableBinding.mybutton.setOnClickListener(vw ->
        {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
           model.editString.observe(this, s ->{
               variableBinding.textview.setText("Your edit text has: " + s);
           });

        });


    }
}