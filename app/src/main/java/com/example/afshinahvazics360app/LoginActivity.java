package com.example.afshinahvazics360app;

// LoginActivity.java
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonSubmit;
    private TextView textViewFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI components
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSubmit = findViewById(R.id.buttonSubmit);
//        textViewFeedback = findViewById(R.id.textViewFeedback);

        // Set click listener for the submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the login function here
                login();
            }
        });
    }

    private void login() {
        // Implement login logic here
        // Validate user input, check database, display feedback messages, and navigate to the main view
        // This is a placeholder and should be replaced with your actual implementation
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Example: Validate that both fields are not empty
        if (username.isEmpty() || password.isEmpty()) {
            showFeedback("Username and password are required");
        } else {
            // Example: Check the database for a matching account
            boolean isAccountValid = checkDatabase(username, password);

            if (isAccountValid) {
                // Example: Navigate to the main view
                // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                // startActivity(intent);
                // Finish the activity to prevent going back to the login screen
                // finish();
                showFeedback("Login successful");
            } else {
                showFeedback("Invalid username or password");
            }
        }
    }

    private void showFeedback(String message) {
        // Display feedback message and make it visible
        textViewFeedback.setText(message);
        textViewFeedback.setVisibility(View.VISIBLE);
    }

    // implement later
    private boolean checkDatabase(String username, String password) {
        return true;
    }
}
