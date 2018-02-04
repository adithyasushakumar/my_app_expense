package ae.bluecast.my_app_expense.activities;

import android.content.Context;

import android.content.Intent;

import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ae.bluecast.my_app_expense.R;


public class Login_Activity extends AppCompatActivity {
    String str_UserName, str_Password, str_getID, str_getPass;
    EditText edt_UName, edt_Password;
    ImageView imageView;
    TextView txt_signup;
   // private TabLayout tabLayout;
   // private ViewPager viewPager;


    SharedPreferences preferences = null;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_UName = (EditText) findViewById(R.id.editText);
        edt_Password = (EditText) findViewById(R.id.editText2);
        txt_signup = (TextView)findViewById(R.id.signup);
        str_getID = edt_UName.getText().toString();
        str_getPass = edt_Password.getText().toString();

        Intent myIntent1 = getIntent();
        final Bundle b = myIntent1.getBundleExtra("RegDetails");



        preferences = getSharedPreferences("LoginName", Context.MODE_PRIVATE);

        String name = preferences.getString("Name", "");
        String pass = preferences.getString("Password", "");

        Toast.makeText(getApplicationContext(),name+pass+"zz",Toast.LENGTH_LONG).show();

        if (!name.isEmpty() && !pass.isEmpty()) {

            edt_UName.setText(name);
            edt_Password.setText(pass);

            str_getID=name;
            str_getPass=name;
        }


        Button bto = (Button) findViewById(R.id.oli);
        Button but = (Button) findViewById(R.id.oul);
        but.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {
                str_UserName = edt_UName.getText().toString();
                str_Password = edt_Password.getText().toString();

                if(b!=null)
                    if(!b.isEmpty() ) {
                        str_getID = b.getString("Name");
                        str_getPass = b.getString("PassWord");
                    }

                if (str_UserName.isEmpty() && str_Password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your login User Name and Password", Toast.LENGTH_LONG).show();
                }
//                else if (str_UserName.length() == 0) {
//                    Toast.makeText(getApplicationContext(), "Please enter your User Name", Toast.LENGTH_LONG).show();
//                }
//                else if (str_Password.length() == 0) {
//                    Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_LONG).show();
//                }

//
//                else if (!(str_getPass.matches(str_Password)))
//                {Toast.makeText(getApplicationContext(), "Either login/password is incorrect", Toast.LENGTH_LONG).show();
//                }

                else if ((str_getID.matches(str_UserName)) && (str_getPass.matches(str_Password))) {
                    Toast.makeText(getApplicationContext(), "You have successfuly login", Toast.LENGTH_LONG).show();


                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Name", str_getID);
                    editor.putString("PassWord", str_getPass);
                    editor.commit();
                    Intent myintent = new Intent(Login_Activity.this, Mainpage_Activity.class);

                    startActivity(myintent);


                } else {


                    Toast.makeText(getApplicationContext(), "Not Sucess", Toast.LENGTH_LONG).show();

                }


            }

        });

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(intent);


            }
        });

    }
}

