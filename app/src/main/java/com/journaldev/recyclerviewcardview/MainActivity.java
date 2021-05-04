package com.journaldev.recyclerviewcardview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/*
Hence a RecyclerView is more customisable when compared to ListView and gives greater control to the users.
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DataModel> data;
    private CustomAdapter adapter;
    static View.OnClickListener myOnClickListener;
    private FirebaseAuth mAuth;
    private Button btnToLogIn;

// ...
// Initialize Firebase Auth


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToLogIn = (Button) findViewById(R.id.btnToLogIn);


        recycleView = findViewById(R.id.my_recicle_view);
        recycleView.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<DataModel>();

        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new DataModel(MyData.nameArray[i], MyData.versionArray[i], MyData.id_[i], MyData.drawableArray[i]));
        }
        adapter = new CustomAdapter(data);
        recycleView.setAdapter(adapter);


        mAuth = FirebaseAuth.getInstance();





    }




    public void logIn() {
        EditText email = findViewById(R.id.usernameLogin);
        EditText password = findViewById(R.id.usernameLogin);
        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Authentication Successfully.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void openActivity2(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
    }
}