package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ComponentName;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.instantapps.ActivityCompat;

public final class zzcbk implements ActivityCompat {
   private final Activity zzbhp;

   public zzcbk(Activity var1) {
      this.zzbhp = var1;
   }

   public final String getCallingPackage() {
      ComponentName var1;
      return (var1 = this.getCallingActivity()) != null ? var1.getPackageName() : null;
   }

   public final ComponentName getCallingActivity() {
      ComponentName var1;
      zzcbp var2;
      if ((var1 = this.zzbhp.getCallingActivity()) != null && var1.getPackageName() != null && var1.getPackageName().equals("com.google.android.instantapps.supervisor") && (var2 = zzcbp.zzbf(this.zzbhp)) != null) {
         try {
            ComponentName var3;
            if ((var3 = var2.zzdu(var1.getClassName())) != null) {
               return var3;
            }
         } catch (RemoteException var4) {
            Log.e("IAActivityCompat", "Error getting calling activity", var4);
         }
      }

      return var1;
   }
}
