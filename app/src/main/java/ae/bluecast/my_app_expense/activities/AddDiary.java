package ae.bluecast.my_app_expense.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ae.bluecast.my_app_expense.R;
import ae.bluecast.my_app_expense.models.AddDiaryModel;

public class AddDiary extends AppCompatActivity {
    private TextView tvPersonDetailId,tvPersonDetailDate,tvPersonDetailTitle,tvPersonDetails;
    private AddDiaryModel adddiaryModel=new AddDiaryModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        getAllWidgets();
        getDataFromPreviousActivity();
        setDataInWidgets();
    }

    private void getAllWidgets()
    {
        tvPersonDetailId= (TextView) findViewById(R.id.tvPersonDetailID);
        tvPersonDetailDate= (TextView) findViewById(R.id.tvPersonDetailDate);
        tvPersonDetailTitle= (TextView) findViewById(R.id.tvPersonDetailTitle);
        tvPersonDetails= (TextView) findViewById(R.id.tvPersonDetails);
    }

    private void getDataFromPreviousActivity()
    {
        int personID = getIntent().getIntExtra("PersonID", -1);
        adddiaryModel=Diary.getInstance().searchPerson(personID);
    }

    private void setDataInWidgets()
    {
        tvPersonDetailId.setText(getString(R.string.person_id,String.valueOf(adddiaryModel.getId())));
        tvPersonDetailDate.setText(getString(R.string.person_Date,adddiaryModel.getDate()));
        tvPersonDetailTitle.setText(getString(R.string.person_Ttile,adddiaryModel.getTitle()));
        tvPersonDetails.setText(getString(R.string.person_Details,adddiaryModel.getDetails()));

    }
}
