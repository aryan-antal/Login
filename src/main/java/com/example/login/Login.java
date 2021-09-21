package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    TextView tv1;
    EditText et1,et2;
    Button b2;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        et1=findViewById(R.id.editTextTextPersonName);
        et2=findViewById(R.id.editTextTextPersonName1);
        tv1=findViewById(R.id.textview);
        b2=findViewById(R.id.button2);



        mAuthStateListener=new FirebaseAuth.AuthStateListener() {



            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser= mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser !=null)
                {
                    Toast.makeText(Login.this,"you are logged in",Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(Login.this,Home.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Login.this, "Please Signup", Toast.LENGTH_SHORT).show();
                }
            }
        };

        b2.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(Login.this,"Fields are Empty !",Toast.LENGTH_SHORT).show();
                }
                else if (! (username.isEmpty() && password.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful())
                            {
                                Toast.makeText(Login.this,"Login Error, Please try again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent inToHome= new Intent(Login.this,Home.class);
                                startActivity(inToHome);
                            }
                        }
                    });
                }

                else
                {
                    Toast.makeText(Login.this,"Error ocurred!",Toast.LENGTH_SHORT).show();
                }

            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignup= new Intent ( Login.this,MainActivity.class);
                startActivity(intSignup);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}