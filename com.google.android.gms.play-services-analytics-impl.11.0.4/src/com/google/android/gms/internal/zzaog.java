package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.google.android.gms.analytics.zzl;

public final class zzaog extends zzamh {
   private SharedPreferences zzaix;
   private long zzaiy;
   private long zzaiz = -1L;
   private final zzaoi zzaiA;

   protected zzaog(zzamj var1) {
      super(var1);
      this.zzaiA = new zzaoi(this, "monitoring", ((Long)zzans.zzahT.get()).longValue(), (zzaoh)null);
   }

   protected final void zzjD() {
      this.zzaix = this.getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
   }

   public final long zzlU() {
      zzl.zzjC();
      this.zzkD();
      if (this.zzaiy == 0L) {
         long var1;
         if ((var1 = this.zzaix.getLong("first_run", 0L)) != 0L) {
            this.zzaiy = var1;
         } else {
            long var3 = this.zzkq().currentTimeMillis();
            Editor var5;
            (var5 = this.zzaix.edit()).putLong("first_run", var3);
            if (!var5.commit()) {
               this.zzbr("Failed to commit first run time");
            }

            this.zzaiy = var3;
         }
      }

      return this.zzaiy;
   }

   public final zzaoo zzlV() {
      return new zzaoo(this.zzkq(), this.zzlU());
   }

   public final long zzlW() {
      zzl.zzjC();
      this.zzkD();
      if (this.zzaiz == -1L) {
         this.zzaiz = this.zzaix.getLong("last_dispatch", 0L);
      }

      return this.zzaiz;
   }

   public final void zzlX() {
      zzl.zzjC();
      this.zzkD();
      long var1 = this.zzkq().currentTimeMillis();
      Editor var3;
      (var3 = this.zzaix.edit()).putLong("last_dispatch", var1);
      var3.apply();
      this.zzaiz = var1;
   }

   public final String zzlY() {
      zzl.zzjC();
      this.zzkD();
      String var1;
      return TextUtils.isEmpty(var1 = this.zzaix.getString("installation_campaign", (String)null)) ? null : var1;
   }

   public final void zzbz(String var1) {
      zzl.zzjC();
      this.zzkD();
      Editor var2 = this.zzaix.edit();
      if (TextUtils.isEmpty(var1)) {
         var2.remove("installation_campaign");
      } else {
         var2.putString("installation_campaign", var1);
      }

      if (!var2.commit()) {
         this.zzbr("Failed to commit campaign data");
      }

   }

   public final zzaoi zzlZ() {
      return this.zzaiA;
   }

   // $FF: synthetic method
   static SharedPreferences zza(zzaog var0) {
      return var0.zzaix;
   }
}
