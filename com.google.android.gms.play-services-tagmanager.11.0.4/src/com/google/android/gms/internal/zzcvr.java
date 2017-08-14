package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;

final class zzcvr implements OnClickListener {
   // $FF: synthetic field
   private zzcvq zzbIC;

   zzcvr(zzcvq var1) {
      this.zzbIC = var1;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      String var3 = zzcvq.zza(this.zzbIC).getPackageName();
      String var10000;
      String var10001;
      String var10002;
      Intent var4;
      if ((var4 = zzcvq.zza(this.zzbIC).getPackageManager().getLaunchIntentForPackage(var3)) != null) {
         var10001 = String.valueOf(var3);
         if (var10001.length() != 0) {
            var10000 = "Invoke the launch activity for package name: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Invoke the launch activity for package name: ");
         }

         zzcvk.zzaS(var10000);
         zzcvq.zza(this.zzbIC).startActivity(var4);
      } else {
         var10001 = String.valueOf(var3);
         if (var10001.length() != 0) {
            var10000 = "No launch activity found for package name: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("No launch activity found for package name: ");
         }

         zzcvk.zzaT(var10000);
      }
   }
}
