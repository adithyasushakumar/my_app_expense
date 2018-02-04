package ae.bluecast.my_app_expense.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

import ae.bluecast.my_app_expense.Adapter.AddDiaryAdapter;
import ae.bluecast.my_app_expense.R;
import ae.bluecast.my_app_expense.models.AddDiaryModel;
import ae.bluecast.my_app_expense.utility.Utility;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class Diary extends AppCompatActivity implements View.OnClickListener {
    private static int id = 1;
    private FloatingActionButton fabAddPerson;
    private Realm myRealm;
    private ListView lvPersonDairyList;
    private static ArrayList<AddDiaryModel> DairyDetailsArrayList = new ArrayList<>();
    private AddDiaryAdapter AddDiaryAdapter;
    private static Diary instance;
    private AlertDialog.Builder subDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).deleteRealmIfMigrationNeeded().build();

        myRealm = Realm.getInstance(config);
        instance = this;

        getAllWidgets();
        bindWidgetsWithEvents();
        setPersonDetailsAdapter();
        getAllUsers();
    }

    public static Diary getInstance() {
        return instance;
    }

    private void getAllWidgets() {
        fabAddPerson = (FloatingActionButton) findViewById(R.id.fabAddPerson);
        lvPersonDairyList = (ListView) findViewById(R.id.lvPersonDairyList);
    }

    private void bindWidgetsWithEvents() {
        fabAddPerson.setOnClickListener(this);
        lvPersonDairyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Diary.this,AddDiary.class);
                intent.putExtra("PersonID", DairyDetailsArrayList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void setPersonDetailsAdapter() {
        AddDiaryAdapter = new AddDiaryAdapter(Diary.this, DairyDetailsArrayList);
        lvPersonDairyList.setAdapter(AddDiaryAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabAddPerson:
                addOrUpdateDiaryDetailsDialog(null,-1);
                break;
        }
    }

    public void addOrUpdateDiaryDetailsDialog(final AddDiaryModel model,final int position) {

        //subdialog
        subDialog =  new AlertDialog.Builder(Diary.this)
                .setMessage("Please enter all the details!!!")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dlg2, int which) {
                        dlg2.cancel();
                    }
                });

        //maindialog
        LayoutInflater li = LayoutInflater.from(Diary.this);
        View promptsView = li.inflate(R.layout.prompt_dialoglist, null);
        AlertDialog.Builder mainDialog = new AlertDialog.Builder(Diary.this);
        mainDialog.setView(promptsView);

        final EditText etDate = (EditText) promptsView.findViewById(R.id.etDate);
        final EditText etTitle = (EditText) promptsView.findViewById(R.id.etTitle);
        final EditText etAddPersonDetails = (EditText) promptsView.findViewById(R.id.etAddPersonDetails);
        etDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(Diary.this, new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        String myFormat = "dd/MMM/yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

                        etDate.setText(sdf.format(mcurrentDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });

        if (model != null) {
            etDate.setText(model.getDate());
            etTitle.setText(model.getTitle());
            etAddPersonDetails.setText(model.getDetails());

        }

        mainDialog.setCancelable(false)
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        final AlertDialog dialog = mainDialog.create();
        dialog.show();

        Button b = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Utility.isBlankField(etDate) && !Utility.isBlankField(etTitle) && !Utility.isBlankField(etAddPersonDetails)) {
                    AddDiaryModel AddDiaryModel = new AddDiaryModel();
                    AddDiaryModel.setDate(etDate.getText().toString());
                    AddDiaryModel.setTitle(etTitle.getText().toString());
                    AddDiaryModel.setDetails(etAddPersonDetails.getText().toString());

                    if (model == null)
                        addDataToRealm(AddDiaryModel);
                    else
                        updateDiaryDetails(AddDiaryModel, position, model.getId());

                    dialog.cancel();
                } else {
                    subDialog.show();
                }
            }
        });
    }

    private void addDataToRealm(AddDiaryModel model) {
        myRealm.beginTransaction();

        AddDiaryModel AddDiaryModel = myRealm.createObject(AddDiaryModel.class);
        AddDiaryModel.setId(id);
        AddDiaryModel.setDate(model.getDate());
        AddDiaryModel.setTitle(model.getTitle());
        AddDiaryModel.setDetails(model.getDetails());

        DairyDetailsArrayList.add(AddDiaryModel);

        myRealm.commitTransaction();
        AddDiaryAdapter.notifyDataSetChanged();
        id++;
    }

    public void deletePerson(int personId, int position) {
        RealmResults<AddDiaryModel> results = myRealm.where(AddDiaryModel.class).equalTo("id", personId).findAll();

        myRealm.beginTransaction();
        results.remove(0);
        myRealm.commitTransaction();

        DairyDetailsArrayList.remove(position);
       AddDiaryAdapter.notifyDataSetChanged();
    }

    public AddDiaryModel searchPerson(int personId) {
        RealmResults<AddDiaryModel> results = myRealm.where(AddDiaryModel.class).equalTo("id", personId).findAll();

        myRealm.beginTransaction();
        myRealm.commitTransaction();

        return results.get(0);
    }

    public void updateDiaryDetails(AddDiaryModel model,int position,int personID) {
        AddDiaryModel editPersonDetails = myRealm.where(AddDiaryModel.class).equalTo("id", personID).findFirst();
        myRealm.beginTransaction();
        editPersonDetails.setDate(model.getDate());
        editPersonDetails.setTitle(model.getTitle());
        editPersonDetails.setDetails(model.getDetails());

        myRealm.commitTransaction();
        DairyDetailsArrayList.set(position, editPersonDetails);
        AddDiaryAdapter.notifyDataSetChanged();
    }

    private void getAllUsers() {
        RealmResults<AddDiaryModel> results = myRealm.where(AddDiaryModel.class).findAll();

        myRealm.beginTransaction();

        for (int i = 0; i < results.size(); i++) {
            DairyDetailsArrayList.add(results.get(i));
        }

        if(results.size()>0)
            id = myRealm.where(AddDiaryModel.class).max("id").intValue() + 1;
        myRealm.commitTransaction();
        AddDiaryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DairyDetailsArrayList.clear();
        myRealm.close();
    }

}
