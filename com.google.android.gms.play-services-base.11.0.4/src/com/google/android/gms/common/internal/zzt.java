package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.internal.zzbdt;

public abstract class zzt implements OnClickListener {
   public static zzt zza(Activity var0, Intent var1, int var2) {
      return new zzu(var1, var0, var2);
   }

   public static zzt zza(@NonNull Fragment var0, Intent var1, int var2) {
      return new zzv(var1, var0, var2);
   }

   public static zzt zza(@NonNull zzbdt var0, Intent var1, int var2) {
      return new zzw(var1, var0, 2);
   }

   public void onClick(DialogInterface var1, int var2) {
      try {
         this.zzrv();
         return;
      } catch (ActivityNotFoundException var7) {
         Log.e("DialogRedirect", "Failed to start resolution intent", var7);
      } finally {
         var1.dismiss();
      }

   }

   protected abstract void zzrv();
}
