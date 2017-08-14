package com.google.android.gms.internal;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

final class zzahx implements Runnable {
   // $FF: synthetic field
   final Context zztF;
   // $FF: synthetic field
   private String zzZO;
   // $FF: synthetic field
   private boolean zzZP;
   // $FF: synthetic field
   private boolean zzZQ;

   zzahx(zzahw var1, Context var2, String var3, boolean var4, boolean var5) {
      this.zztF = var2;
      this.zzZO = var3;
      this.zzZP = var4;
      this.zzZQ = var5;
      super();
   }

   public final void run() {
      Builder var1;
      (var1 = new Builder(this.zztF)).setMessage(this.zzZO);
      if (this.zzZP) {
         var1.setTitle("Error");
      } else {
         var1.setTitle("Info");
      }

      if (this.zzZQ) {
         var1.setNeutralButton("Dismiss", (OnClickListener)null);
      } else {
         var1.setPositiveButton("Learn More", new zzahy(this));
         var1.setNegativeButton("Dismiss", (OnClickListener)null);
      }

      var1.create().show();
   }
}
