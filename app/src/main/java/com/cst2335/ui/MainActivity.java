package com.cst2335.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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


        //listener for button
        variableBinding.mybutton.setOnClickListener(vw ->
        {
            model.editString.postValue(variableBinding.myedittext.getText().toString());

        });
        // check box listener
        variableBinding.checkBox.setOnCheckedChangeListener((btn,isChecked) -> {
            model.isSelected.postValue(isChecked);
        });
        //switch listener
        variableBinding.switch1.setOnCheckedChangeListener((btn,isChecked) -> {
            model.isSelected.postValue(isChecked);
        });
        //radiobutton listener
        variableBinding.radioButton.setOnCheckedChangeListener((btn,isChecked) -> {
            model.isSelected.postValue(isChecked);
        });
        variableBinding.myimagebutton.setOnClickListener(vm ->{
            Toast.makeText(MainActivity.this,"The width = "+vm.getWidth()+"and height = "+vm.getHeight(),Toast.LENGTH_SHORT).show();
        });

        //observe
        model.editString.observe(this, s ->{variableBinding.textview.setText("Your edit text has: " + s);});
        model.isSelected.observe(this, selected ->{
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
            Toast.makeText(MainActivity.this,"The value is now: "+selected,Toast.LENGTH_SHORT).show();
        });


    }
}