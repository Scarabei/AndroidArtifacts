package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.internal.zzbdb;

public class GoogleApiActivity extends Activity implements OnCancelListener {
   private int zzaAR = 0;

   public static PendingIntent zza(Context var0, PendingIntent var1, int var2) {
      return PendingIntent.getActivity(var0, 0, zza(var0, var1, var2, true), 134217728);
   }

   public static Intent zza(Context var0, PendingIntent var1, int var2, boolean var3) {
      Intent var4;
      (var4 = new Intent(var0, GoogleApiActivity.class)).putExtra("pending_intent", var1);
      var4.putExtra("failing_client_id", var2);
      var4.putExtra("notify_manager", var3);
      return var4;
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      if (var1 != null) {
         this.zzaAR = var1.getInt("resolution");
      }

      if (this.zzaAR != 1) {
         GoogleApiActivity var2 = this;
         Bundle var3;
         if ((var3 = this.getIntent().getExtras()) == null) {
            Log.e("GoogleApiActivity", "Activity started without extras");
            this.finish();
            return;
         }

         PendingIntent var4 = (PendingIntent)var3.get("pending_intent");
         Integer var5 = (Integer)var3.get("error_code");
         if (var4 == null && var5 == null) {
            Log.e("GoogleApiActivity", "Activity started without resolution");
            this.finish();
            return;
         }

         if (var4 != null) {
            try {
               var2.startIntentSenderForResult(var4.getIntentSender(), 1, (Intent)null, 0, 0, 0);
               var2.zzaAR = 1;
               return;
            } catch (SendIntentException var7) {
               Log.e("GoogleApiActivity", "Failed to launch pendingIntent", var7);
               this.finish();
               return;
            }
         }

         GoogleApiAvailability.getInstance().showErrorDialogFragment(this, var5.intValue(), 2, this);
         this.zzaAR = 1;
      }

   }

   protected void onActivityResult(int var1, int var2, Intent var3) {
      super.onActivityResult(var1, var2, var3);
      if (var1 == 1) {
         boolean var4 = this.getIntent().getBooleanExtra("notify_manager", true);
         this.zzaAR = 0;
         this.setResult(var2, var3);
         if (var4) {
            zzbdb var6 = zzbdb.zzay(this);
            switch(var2) {
            case -1:
               var6.zzps();
               break;
            case 0:
               var6.zza(new ConnectionResult(13, (PendingIntent)null), this.getIntent().getIntExtra("failing_client_id", -1));
            }
         }
      } else if (var1 == 2) {
         this.zzaAR = 0;
         this.setResult(var2, var3);
      }

      this.finish();
   }

   protected void onSaveInstanceState(Bundle var1) {
      var1.putInt("resolution", this.zzaAR);
      super.onSaveInstanceState(var1);
   }

   public void onCancel(DialogInterface var1) {
      this.zzaAR = 0;
      this.setResult(0);
      this.finish();
   }
}
