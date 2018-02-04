package ae.bluecast.my_app_expense.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ae.bluecast.my_app_expense.R;

public class SplashActivity extends Activity {
    // Splash screen timer
    //private static int SPLASH_TIME_OUT = 1000;
    Button blogin,btsingin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        blogin=(Button)findViewById(R.id.btlog) ;
        btsingin=(Button)findViewById(R.id.btsin) ;

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent spi = new Intent(SplashActivity.this, Register_Activity.class);
                startActivity(spi);

                // close this activity
//                finish();
//            }
//        }, SPLASH_TIME_OUT);

            }
        });
        btsingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
                // This method will be executed once the timer is over
//                // Start your app main activity
                Intent ins = new Intent(SplashActivity.this, Login_Activity.class);
                startActivity(ins);

                // close this activity
//                finish();
//            }
//        }, SPLASH_TIME_OUT);


            }
        });
    }
}
