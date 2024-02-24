package com.cst2335.chaowu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/** This page takes a user password input and check if the password meet the requirements
 * @author Chao Wu
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /** This holds the text at the centre of the screen*/
    private TextView tv;
    /** This holds the password at the centre of the screen*/
    private EditText et;
    /** This holds the login button*/
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.editTextTextPassword2);
        btn = findViewById(R.id.button);

        btn.setOnClickListener( clk ->{
            String password = et.getText().toString();
            checkPasswordComplexity(password);
            if(checkPasswordComplexity(password)){
                tv.setText("Your password is complex enough!");
            }
            else{
                tv.setText("You shall not pass!");

            }

        });




    }

    /** This function use to check the complexity for a password
     *
     * @param pw The String object that we are checking
     * @return Return true if the password is complex enough, false it is not complex enough
     */
    boolean checkPasswordComplexity(String pw){
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;

        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for (int i=0;i<pw.length();i++){
            char c =pw.charAt(i);
            if(Character.isUpperCase(c)) foundUpperCase = true;
            else if(Character.isLowerCase(c)) foundLowerCase = true;
            else if (Character.isDigit(c)) foundNumber = true;
            else if (isSpecialCharacter(c)) foundSpecial = true;
        }

        if(!foundUpperCase)
        {
            Toast.makeText(this,"missing an upper case letter", Toast.LENGTH_SHORT).show();// Say that they are missing an upper case letter;
            return false;
        }
        else if( ! foundLowerCase)
        {
            Toast.makeText(this,"missing a lower case letter", Toast.LENGTH_SHORT).show(); // Say that they are missing a lower case letter;
            return false;
        }
        else if( ! foundNumber) {
            Toast.makeText(this,"missing a number", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if(! foundSpecial) {
            Toast.makeText(this,"missing a special symbol", Toast.LENGTH_SHORT).show();
            return false;
        }

        else

            return true; //only get here if they're all true
    }
    boolean isSpecialCharacter(char c) {
    //return true if c is one of: #$%^&*!@?
    //return false otherwise
        switch (c) {
            case '#':
            case '?':
            case '*':
            case '%':
            case'$':
            case '&':
            case '!':
            case '@':
            case '^':
                return true;
            default:
                return false;
        }
    }
}