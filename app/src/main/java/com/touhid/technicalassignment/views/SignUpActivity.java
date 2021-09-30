package com.touhid.technicalassignment.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.touhid.technicalassignment.R;
import com.touhid.technicalassignment.models.UserModel;
import com.touhid.technicalassignment.viewmodel.UserViewModel;

public class SignUpActivity extends AppCompatActivity {

    private TextView logoTextTV, activityNameTV, signInTextTV;
    private TextInputEditText userNameTIE, userPasswordTIE, userConfirmPassTIE, userEmailTIE;
    private MaterialButton signUpBTN;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initialize the views
        initialize();
    }


    private void initialize() {
        logoTextTV = findViewById(R.id.logo_text);
        activityNameTV = findViewById(R.id.activity_name_tv);
        signInTextTV = findViewById(R.id.sign_in_text);

        userNameTIE = findViewById(R.id.name_tie);
        userEmailTIE = findViewById(R.id.email_tie);
        userPasswordTIE = findViewById(R.id.password_tie);
        userConfirmPassTIE = findViewById(R.id.retype_password_tie);

        signUpBTN = findViewById(R.id.sign_up_btn);

        //set logo text and activity name text
        String logoText = "<font color=#02A73A>O</font>" + "<font color=#000000>rder</font>" + "<font color= #FF3346> A</font>" + "<font color=#000000>utomation</font>";
        if (Build.VERSION.SDK_INT >= 24) {
            logoTextTV.setText(Html.fromHtml(logoText, Html.FROM_HTML_MODE_LEGACY)); // for 24 API  and more
        } else {
            logoTextTV.setText(Html.fromHtml(logoText)); // or for older API
        }

        String activityNameText = "<font color= #000000>Sign </font>" + "<font color = #02A73A>Up</font>";
        if (Build.VERSION.SDK_INT >= 24){
            activityNameTV.setText(Html.fromHtml(activityNameText, Html.FROM_HTML_MODE_LEGACY)); // for 24 API and above
        } else {
            activityNameTV.setText(Html.fromHtml(activityNameText)); //for older version
        }

        //back to sign in page
        signInTextTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });


        //view model initialize
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        //button work
        signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = String.valueOf(userNameTIE.getText());
                String userEmail = String.valueOf(userEmailTIE.getText());
                String userPassword = String.valueOf(userPasswordTIE.getText());
                String userConfirmPass = String.valueOf(userConfirmPassTIE.getText());

                if (validate(userName, userPassword, userEmail, userConfirmPass)){
                    UserModel userModel = new UserModel(userName,userEmail, userPassword);
                    userViewModel.insert(userModel);
                    Toast.makeText(SignUpActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                }
            }
        });

    }

    private boolean validate(String userName, String userPassword, String userEmail, String userConfirmPass){
        boolean valid = true;



        if (userName.isEmpty()){
            valid = false;
            userNameTIE.setError("Error!");
        }

        if (userEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            valid = false;
            userEmailTIE.setError("Error!");
        }

        if (userPassword.isEmpty()){
            valid = false;
            userPasswordTIE.setError("Error!");
        }

        if (userConfirmPass.isEmpty() || !userConfirmPass.equals(userPassword)){
            valid = false;
            userConfirmPassTIE.setError("Error!");
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
    }
}