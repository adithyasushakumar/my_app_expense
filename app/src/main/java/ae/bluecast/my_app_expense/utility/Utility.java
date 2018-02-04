package ae.bluecast.my_app_expense.utility;

import android.widget.EditText;

/**
 * Created by ASUS on 2/24/2017.
 */

public class Utility {

    public static boolean isBlankField(EditText etPersonData) {

        return etPersonData.getText().toString().trim().equals("");
    }

}

