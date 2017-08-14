package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;

@zzzn
public final class zzakr extends MutableContextWrapper {
   private Activity zzaaC;
   private Context mApplicationContext;
   private Context zzacs;

   public zzakr(Context var1) {
      super(var1);
      this.setBaseContext(var1);
   }

   public final void setBaseContext(Context var1) {
      this.mApplicationContext = var1.getApplicationContext();
      this.zzaaC = var1 instanceof Activity ? (Activity)var1 : null;
      this.zzacs = var1;
      super.setBaseContext(this.mApplicationContext);
   }

   public final void startActivity(Intent var1) {
      if (this.zzaaC != null) {
         this.zzaaC.startActivity(var1);
      } else {
         var1.setFlags(268435456);
         this.mApplicationContext.startActivity(var1);
      }
   }

   public final Activity zzis() {
      return this.zzaaC;
   }

   public final Object getSystemService(String var1) {
      return this.zzacs.getSystemService(var1);
   }

   public final Context zzit() {
      return this.zzacs;
   }
}
