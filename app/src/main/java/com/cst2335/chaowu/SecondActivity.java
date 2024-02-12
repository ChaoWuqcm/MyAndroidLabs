package com.cst2335.chaowu;

import android.content.Context;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class SecondActivity extends AppCompatActivity {
    SharedPreferences prefs;
    EditText phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView email = findViewById(R.id.textView3);
        ImageView profileImage = findViewById(R.id.imageView);
        Button callbutton = findViewById(R.id.button2);
        Button changeButton = findViewById(R.id.button3);
        prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        phoneNumber = findViewById(R.id.editTextPhone);
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        email.setText(emailAddress);

        callbutton.setOnClickListener(btn->{
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:"+phoneNumber.getText().toString()));
            startActivity(call);
        });
        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult() ,
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");

                            FileOutputStream fOut = null;
                            try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);

                                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                                fOut.flush();
                                fOut.close();
                            }
                            catch (FileNotFoundException e)
                            { e.printStackTrace();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            profileImage.setImageBitmap(thumbnail);
                        }
                    }
                });

        File file = new File( getFilesDir(), "Picture.png");

        if(file.exists())
        {
            Bitmap theImage = BitmapFactory.decodeFile(file.getAbsolutePath());
            profileImage.setImageBitmap(theImage);
        }
        //
        changeButton.setOnClickListener(btn->{
            Intent carmeraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            cameraResult.launch(carmeraIntent);
        });
        String savedPhoneNumber = prefs.getString("PhoneNumber", "");
        phoneNumber.setText(savedPhoneNumber);

    }
    @Override
    protected void onPause() {
        super.onPause();
        // Save the phone number entered in the EditText to SharedPreferences
        String phone = phoneNumber.getText().toString();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("PhoneNumber", phone);
        editor.apply();
    }

}
