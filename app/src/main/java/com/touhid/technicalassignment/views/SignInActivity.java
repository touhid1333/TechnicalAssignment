package com.touhid.technicalassignment.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.touhid.technicalassignment.R;
import com.touhid.technicalassignment.models.UserModel;
import com.touhid.technicalassignment.viewmodel.UserViewModel;

public class SignInActivity extends AppCompatActivity {

    private TextView logoTextTV, welcomeTextTV, signUpTextTV, activityNameTextTV;
    private TextInputEditText userEmailTIE, userPasswordTIE;
    private MaterialCheckBox rememberCheckbox;
    private MaterialButton signInBTN;

    private UserViewModel userViewModel;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //initialize views
        initialize();
    }

    private void initialize() {
        logoTextTV = findViewById(R.id.logo_text);
        welcomeTextTV = findViewById(R.id.welcome_text_tv);
        activityNameTextTV = findViewById(R.id.activity_name_tv);
        signUpTextTV = findViewById(R.id.sign_up_text);

        userEmailTIE = findViewById(R.id.email_tie);
        userPasswordTIE = findViewById(R.id.password_tie);

        rememberCheckbox = findViewById(R.id.remember_checkbox);

        signInBTN = findViewById(R.id.sign_in_btn);

        //shared prefference
        sharedPreferences = getApplicationContext().getSharedPreferences("preff", 0);
        String savedUserMail = sharedPreferences.getString("mail", null);
        String savedUserPass = sharedPreferences.getString("pass", null);
        if (savedUserMail != null && savedUserPass != null){
            userEmailTIE.setText(savedUserMail);
            userPasswordTIE.setText(savedUserPass);
            welcomeTextTV.setVisibility(View.VISIBLE);
            rememberCheckbox.setChecked(true);
        }

        //set logo text and activity name text
        String logoText = "<font color=#02A73A>O</font>" + "<font color=#000000>rder</font>" + "<font color= #FF3346> A</font>" + "<font color=#000000>utomation</font>";
        if (Build.VERSION.SDK_INT >= 24) {
            logoTextTV.setText(Html.fromHtml(logoText, Html.FROM_HTML_MODE_LEGACY)); // for 24 API  and more
        } else {
            logoTextTV.setText(Html.fromHtml(logoText)); // or for older API
        }

        String activityNameText = "<font color= #000000>Sign </font>" + "<font color = #02A73A>In</font>";
        if (Build.VERSION.SDK_INT >= 24){
            activityNameTextTV.setText(Html.fromHtml(activityNameText, Html.FROM_HTML_MODE_LEGACY)); // for 24 API and above
        } else {
            activityNameTextTV.setText(Html.fromHtml(activityNameText)); //for older version
        }

        //back to sign in page
        signUpTextTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });


        //check box work


        //view model initialize
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        //button work
        signInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userEmail = String.valueOf(userEmailTIE.getText());
                String userPassword = String.valueOf(userPasswordTIE.getText());

                if(validate(userEmail, userPassword)){
                    userViewModel.getUser(userEmail, userPassword).observe(SignInActivity.this, new Observer<UserModel>() {
                        @Override
                        public void onChanged(UserModel userModel) {
                            if (userModel != null){
                                if (userEmail.equals(userModel.getEmail())){
                                    //Log.d("Data.......", userModel.getName());
                                    Toast.makeText(SignInActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    if (rememberCheckbox.isChecked()){
                                        editor.putString("mail", userEmail);
                                        editor.putString("pass", userPassword);
                                        editor.apply();
                                    } else {
                                        editor.clear();
                                        editor.apply();
                                    }
                                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                }
                            } else {
                                Toast.makeText(SignInActivity.this, "Email/Password mismatch.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean validate(String userEmail, String userPassword){
        boolean valid = true;

        if (userEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            valid = false;
            userEmailTIE.setError("Error!");
        }

        if (userPassword.isEmpty()){
            valid = false;
            userPasswordTIE.setError("Error!");
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent exitIntent = new Intent(Intent.ACTION_MAIN);
        exitIntent.addCategory(Intent.CATEGORY_HOME);
        exitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exitIntent);
    }
}