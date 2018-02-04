package ae.bluecast.my_app_expense.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import ae.bluecast.my_app_expense.R;

public class Mainpage_Activity extends AppCompatActivity {

    TextView bt,bt1,bt2,bt4,bt6,bt7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

       bt=(TextView )findViewById(R.id.txt);
        //bt1=(TextView )findViewById(R.id.pop);
        bt2=(TextView )findViewById(R.id.pic);
        bt4=(TextView )findViewById(R.id.pyp);
        bt6=(TextView )findViewById(R.id.tdo);
      //  bt7=(TextView )findViewById(R.id.sample);

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Mainpage_Activity.this,Calandar_Activity.class);
                startActivity(intent);


            }
        });
//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Mainpage_Activity.this,ExpenseDateActivity.class);
//                startActivity(intent1);
//            }
//        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Mainpage_Activity.this,Calculator_Activity.class);
                startActivity(intent2);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent3 =new Intent(Mainpage_Activity.this,Diary.class);
                startActivity(intent3);
            }

        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5=new Intent(Mainpage_Activity.this,TodoList.class);
                startActivity(intent5);
            }
        });
//        bt7.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent7=new Intent(Mainpage_Activity.this, ExpenseDateActivity.class);
//                startActivity(intent7);
//
//
//            }
//        });
    }
}

