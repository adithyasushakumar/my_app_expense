package ae.bluecast.my_app_expense.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ae.bluecast.my_app_expense.R;
import ae.bluecast.my_app_expense.activities.Login_Activity;

public class Register_Activity extends AppCompatActivity {

    Button register;
    String url;
    SharedPreferences preferences = null;
    String str_Name, str_Password, str_RePassword, str_Email, str_Mobile,
            random;

    EditText edt_Name, edt_Password, edt_RePassword, edt_Email, edt_Mobile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        register = (Button) findViewById(R.id.rg);
        edt_Name = (EditText) findViewById(R.id.qp);
        edt_Password = (EditText) findViewById(R.id.qa);
        edt_RePassword = (EditText) findViewById(R.id.qn);
        edt_Mobile = (EditText) findViewById(R.id.qs);
        edt_Email = (EditText) findViewById(R.id.qc);


        preferences = getSharedPreferences("LoginName", Context.MODE_PRIVATE);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_Name = edt_Name.getText().toString();
                str_Password = edt_Password.getText().toString();
                str_RePassword = edt_RePassword.getText().toString();
                str_Mobile = edt_Mobile.getText().toString();
                str_Email = edt_Email.getText().toString();

                if (str_Name.length() == 0 & str_Password.length() == 0
                        & str_RePassword.length() == 0 & str_Mobile.length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "All fields are mandatory to fill", Toast.LENGTH_LONG)
                            .show();
                } else if (str_Name.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter your Name",
                            Toast.LENGTH_LONG).show();
                } else if (str_Password.length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your Password", Toast.LENGTH_LONG).show();
                } else if (str_RePassword.length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "Please Re-enter your Password", Toast.LENGTH_LONG).show();
                } else if (str_Email.length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your Email Id", Toast.LENGTH_LONG).show();
                } else if (str_Password.contains(str_RePassword) != str_RePassword
                        .contains(str_Password)) {
                    Toast.makeText(getApplicationContext(),
                            "Confirm Password does not match", Toast.LENGTH_LONG)
                            .show();
                } else if (str_Mobile.length() == 0) {

                    Toast.makeText(getApplicationContext(),
                            "Please enter your mobile number", Toast.LENGTH_LONG)
                            .show();

                } else {

                    Intent sendtoLogin = new Intent(getApplicationContext(), Login_Activity.class);
                    Bundle bundl = new Bundle();
                    bundl.putString("Name", edt_Name.getText().toString());
                    bundl.putString("PassWord", edt_Password.getText().toString());

                    sendtoLogin.putExtra("RegDetails", bundl);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString("Name", edt_Name.getText().toString());
                    edit.putString("PassWord", edt_Password.getText().toString());
                    edit.commit();
                    startActivity(sendtoLogin);

                    Toast.makeText(getApplicationContext(),
                            "You have successfully registered", Toast.LENGTH_LONG).show();
                    startActivity(sendtoLogin);


                }
            }
        });
    }
}