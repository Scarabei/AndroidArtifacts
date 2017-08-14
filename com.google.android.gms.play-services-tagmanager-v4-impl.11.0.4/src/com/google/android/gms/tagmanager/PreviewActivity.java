package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class PreviewActivity extends Activity {
   public void onCreate(Bundle var1) {
      String var10000;
      String var10001;
      String var10002;
      try {
         super.onCreate(var1);
         zzdj.zzaS("Preview activity");
         Uri var2 = this.getIntent().getData();
         if (!TagManager.getInstance(this).zzr(var2)) {
            String var4 = String.valueOf(var2);
            String var3;
            zzdj.zzaT(var3 = (new StringBuilder(73 + String.valueOf(var4).length())).append("Cannot preview the app with the uri: ").append(var4).append(". Launching current version instead.").toString());
            String var8 = "Continue";
            String var6 = "Preview failure";
            AlertDialog var9;
            (var9 = (new Builder(this)).create()).setTitle(var6);
            var9.setMessage(var3);
            var9.setButton(-1, var8, new zzeh(this));
            var9.show();
         }

         Intent var11;
         if ((var11 = this.getPackageManager().getLaunchIntentForPackage(this.getPackageName())) != null) {
            var10001 = String.valueOf(this.getPackageName());
            if (var10001.length() != 0) {
               var10000 = "Invoke the launch activity for package name: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Invoke the launch activity for package name: ");
            }

            zzdj.zzaS(var10000);
            this.startActivity(var11);
         } else {
            var10001 = String.valueOf(this.getPackageName());
            if (var10001.length() != 0) {
               var10000 = "No launch activity found for package name: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("No launch activity found for package name: ");
            }

            zzdj.zzaS(var10000);
         }
      } catch (Exception var10) {
         var10001 = String.valueOf(var10.getMessage());
         if (var10001.length() != 0) {
            var10000 = "Calling preview threw an exception: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Calling preview threw an exception: ");
         }

         zzdj.e(var10000);
      }
   }
}
