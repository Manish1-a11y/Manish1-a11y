package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//register page
public class MainActivity2 extends AppCompatActivity {
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button bl,br;
    EditText name,inputemail,pass;
    ProgressDialog pd;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        br=(Button) findViewById(R.id.button3);
        bl=findViewById(R.id.button4);
        name=findViewById(R.id.Text2);
        inputemail=findViewById(R.id.EmailAddress);
        pass=findViewById(R.id.Password2);
        //noinspection deprecation
        pd=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s=new Intent(MainActivity2.this, MainActivity.class);
                startActivity(s);
            }
        });



        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });

    }
    private void PerformAuth() {
        String user = name.getText().toString();
        String email = inputemail.getText().toString();
        String Password = pass.getText().toString();

        if (!email.matches(emailPattern)) {
            inputemail.setError("Enter correct email");
        } else if (Password.isEmpty() || pass.length() < 3) {
            pass.setError("Enter correct password");

        } else if (user.isEmpty()) {
            name.setError("Enter User name");

        } else {
            pd.setMessage("wait for registration....");
            pd.setTitle("Registration Done.....");
            pd.setCanceledOnTouchOutside(false);
            pd.show();

            mAuth.createUserWithEmailAndPassword(email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        pd.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(MainActivity2.this, "Registration Succesfull", Toast.LENGTH_SHORT).show();
                    } else {
                        pd.dismiss();
                        Toast.makeText(MainActivity2.this, "Enter full details" + task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }
    }
        private void sendUserToNextActivity() {
            Intent i = new Intent(MainActivity2.this, MainActivity3.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }




}