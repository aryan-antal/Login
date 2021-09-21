package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.ClosedSubscriberGroupInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;
    TextView tv1;
    Button b1;


    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();


        et1=findViewById(R.id.editTextTextPersonName);
        et2=findViewById(R.id.editTextTextPersonName1);
        tv1=findViewById(R.id.textview);
        b1=findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username= et1.getText().toString();
                String password=et2.getText().toString();

                if (username.isEmpty())
                {
                    et1.setError("please enter EmainId");
                    et1.requestFocus();
                }
                else if (password.isEmpty())
                {
                    et2.setError("Please enter Password");
                    et2.requestFocus();
                }
                else if (username.isEmpty() && password.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Fields are Empty !",Toast.LENGTH_SHORT).show();
                }
                else if (! (username.isEmpty() && password.isEmpty()))
                {
                 mFirebaseAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {

                         if (!task.isSuccessful())
                         {
                             Toast.makeText(MainActivity.this,"Signup Unsuccessful, Please try again",Toast.LENGTH_SHORT).show();
                         }
                         else {
                             startActivity(new Intent(MainActivity.this,Home.class));
                         }
                     }
                 });
                }

                else
                {
                    Toast.makeText(MainActivity.this,"Error ocurred!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent ( MainActivity.this, Login.class);
                startActivity(i);
            }
        });
    }
}