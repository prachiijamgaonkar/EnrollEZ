package com.example.admissionexamform.classes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.admissionexamform.R;

public class AppConstant {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showErrorDialog(final Activity context, String errorTitle, String errorMessage) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dlg_error);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        TextView title = (TextView) dialog.findViewById(R.id.dlgTypeTitle);
        TextView message = (TextView) dialog.findViewById(R.id.dlgTypeMsg);
        TextView ok = (TextView) dialog.findViewById(R.id.dlgBTypeBtnOk);
        title.setText(errorTitle);
        message.setText(errorMessage);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void showErrorDialog_fragment(final Context context, String errorTitle, String errorMessage) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dlg_error);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        TextView title = (TextView) dialog.findViewById(R.id.dlgTypeTitle);
        TextView message = (TextView) dialog.findViewById(R.id.dlgTypeMsg);
        TextView ok = (TextView) dialog.findViewById(R.id.dlgBTypeBtnOk);
        title.setText(errorTitle);
        message.setText(errorMessage);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
