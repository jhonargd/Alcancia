package com.example.util;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.alcancia.R;

import java.io.File;

public class Util {
    public static boolean checkPermission(Context context) {
        int PERMISSION_ALL = 1;
        boolean estado = false;
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!hasPermissions(context, PERMISSIONS)) {
            ActivityCompat.requestPermissions((Activity) context, PERMISSIONS, PERMISSION_ALL);
            estado = false;
        }else{
            estado = true;
        }

        return estado;
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

                    return false;
                }
            }
        }
        return true;
    }
    public static File dirApp() {
        File SDCardRoot = Environment.getExternalStorageDirectory();
        File dirApp = new File(SDCardRoot.getPath() + "/" + Const.NAMEDIRAPP);
        if (!dirApp.isDirectory()) dirApp.mkdirs();
        return dirApp;
    }
    public static void mostrarAlertDialog(Context context, String mensaje) {
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                dialog.cancel();
            }
        });
        alertDialog = builder.create();
        alertDialog.setMessage(mensaje);
        alertDialog.show();
        alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }
}
