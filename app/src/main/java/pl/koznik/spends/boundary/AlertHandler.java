package pl.koznik.spends.boundary;

import android.app.AlertDialog;
import android.content.Context;

public class AlertHandler {

    private Context context;

    public AlertHandler(Context context) {
        this.context = context;
    }

    public void showAlert(String title, String message) {
        AlertDialog.Builder messageBox = new AlertDialog.Builder(context);
        messageBox.setTitle(title);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }

}
