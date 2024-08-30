package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button bl, br;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText inputEmail, pass;
    ProgressDialog pd;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    private Compiler EdgeToEdge;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable();
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bl = (Button) findViewById(R.id.button);
        br = (Button) findViewById(R.id.button2);
        inputEmail = findViewById(R.id.EmailAddress);
        pass = findViewById(R.id.Password);
        pd = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });


        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
//                Intent i = new Intent(MainActivity.this, MainActivity3.class);
//                startActivity(i);
            }


        });
    }

    private void performLogin() {

        String email = inputEmail.getText().toString();
        String Password = pass.getText().toString();

        if (!email.matches(emailPattern)) {
            inputEmail.setError("Enter correct email");
        } else if (Password.isEmpty() || pass.length() < 3) {
            pass.setError("Enter correct password");

        } else {
            pd.setMessage("wait for Login....");
            pd.setTitle("Login Done.....");
            pd.setCanceledOnTouchOutside(false);
            pd.show();

            mAuth.signInWithEmailAndPassword(email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        pd.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(MainActivity.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                    }else{
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, "Enter the data" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }
    private void sendUserToNextActivity() {
        Intent i=new Intent(MainActivity.this, MainActivity3.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

            }
}
