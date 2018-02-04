package ae.bluecast.my_app_expense.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ae.bluecast.my_app_expense.R;
import ae.bluecast.my_app_expense.activities.Diary;
import ae.bluecast.my_app_expense.models.AddDiaryModel;

/**
 * Created by ASUS on 6/18/2017.
 */

public class AddDiaryAdapter extends BaseAdapter {

    private ArrayList<AddDiaryModel> AddDiaryArrayList;
    private Context context;
    private LayoutInflater inflater;

    public AddDiaryAdapter(Context context, ArrayList<AddDiaryModel> personDetailsArrayList) {
        this.context = context;
        this.AddDiaryArrayList = personDetailsArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return AddDiaryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return AddDiaryArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Holder holder;
        if (v == null) {
            v = inflater.inflate(R.layout.inflate_item, null);
            holder = new Holder();
            holder.tvDate = (TextView) v.findViewById(R.id.tvPersonDate);
            holder.ivEditPesonDetail=(ImageView)v.findViewById(R.id.ivEditPesonDetail);
            holder.ivDeletePerson=(ImageView)v.findViewById(R.id.ivDeletePerson);
            v.setTag(holder);
        } else {
            holder = (Holder) v.getTag();
        }

        holder.tvDate.setText(AddDiaryArrayList.get(position).getDate());
        holder.ivEditPesonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDiaryModel dataToEditModel= Diary.getInstance().searchPerson(AddDiaryArrayList.get(position).getId());
              Diary.getInstance().addOrUpdateDiaryDetailsDialog(dataToEditModel,position);
            }
        });
        holder.ivDeletePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowConfirmDialog(context,AddDiaryArrayList.get(position).getId(), position);
            }
        });
        return v;
    }

    class Holder {
        TextView tvDate;
        ImageView ivDeletePerson, ivEditPesonDetail;
    }

    public static void ShowConfirmDialog(Context context,final int personId,final int position)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder
                .setMessage("Are you sure you want to delete this record?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Diary.getInstance().deletePerson(personId,position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
