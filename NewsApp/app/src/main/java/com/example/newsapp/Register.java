package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    Button btn2_signup;
    EditText user_name, pass_word, confirm_password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_name = findViewById(R.id.username);
        pass_word = findViewById(R.id.password1);
        confirm_password = findViewById(R.id.confirm_password);
        btn2_signup = findViewById(R.id.sign);
        mAuth = FirebaseAuth.getInstance();

        btn2_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user_name.getText().toString().trim();
                String password = pass_word.getText().toString().trim();
                String confirmPassword = confirm_password.getText().toString().trim();

                if (email.isEmpty()) {
                    user_name.setError("Email is empty");
                    user_name.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    user_name.setError("Enter a valid email address");
                    user_name.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    pass_word.setError("Password is empty");
                    pass_word.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    pass_word.setError("Password should be at least 6 characters long");
                    pass_word.requestFocus();
                    return;
                }

                if (!confirmPassword.equals(password)) {
                    confirm_password.setError("Passwords do not match");
                    confirm_password.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "You are successfully registered", Toast.LENGTH_SHORT).show();
                            // Add code for further actions after successful registration
                            Intent intent=new Intent(Register.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Register.this, "Registration failed. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
