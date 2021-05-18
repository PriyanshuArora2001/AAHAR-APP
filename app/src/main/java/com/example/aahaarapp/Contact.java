package com.example.aahaarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Contact extends AppCompatActivity {
    EditText name, email, message;
    Button submit;
    boolean isNameValid, isEmailValid, isMessageValid;
    TextInputLayout nameError, emailError, messageError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        message = (EditText) findViewById(R.id.message);
        submit = (Button) findViewById(R.id.submit);
        nameError = (TextInputLayout) findViewById(R.id.nameError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        messageError = (TextInputLayout) findViewById(R.id.messageError);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });
    }
        public void SetValidation() {
            // Check for a valid name.
            if (name.getText().toString().isEmpty()) {
                nameError.setError(getResources().getString(R.string.name_error));
                isNameValid = false;
            } else  {
                isNameValid = true;
                nameError.setErrorEnabled(false);
            }

            // Check for a valid email address.
            if (email.getText().toString().isEmpty()) {
                emailError.setError(getResources().getString(R.string.email_error));
                isEmailValid = false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                emailError.setError(getResources().getString(R.string.error_invalid_email));
                isEmailValid = false;
            } else  {
                isEmailValid = true;
                emailError.setErrorEnabled(false);
            }

            // Check for a valid phone number.
            if (message.getText().toString().isEmpty()) {
                messageError.setError(getResources().getString(R.string.phone_error));
                isMessageValid = false;
            } else  {
                isMessageValid = true;
                messageError.setErrorEnabled(false);
            }

            if (isNameValid && isEmailValid && isMessageValid ) {
                Toast.makeText(getApplicationContext(), "Successfully! We will shortly revert you back.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        }
    }
