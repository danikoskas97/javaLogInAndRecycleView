package com.journaldev.recyclerviewcardview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnMoveToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnMoveToRegister = (Button) findViewById(R.id.registorButton);


    }

    public void gotToRegister(View view){
        Intent intent = new Intent(this,  signUp.class);
    }

    public void createUser() {
        EditText fullNameRegistor = findViewById(R.id.fullNameRegistor);
        EditText password = findViewById(R.id.passwordRegistor);
        mAuth.createUserWithEmailAndPassword(fullNameRegistor.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity2.this, "Authentication Successful.",
                                    Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(MainActivity2.this,
                                    "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                    }
                });
    }

}