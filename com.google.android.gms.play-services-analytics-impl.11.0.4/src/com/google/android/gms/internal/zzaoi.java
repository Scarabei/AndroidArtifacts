package com.google.android.gms.internal;

import android.content.SharedPreferences.Editor;
import android.util.Pair;
import com.google.android.gms.common.internal.zzbo;
import java.util.UUID;

public final class zzaoi {
   private final String mName;
   private final long zzaiB;
   // $FF: synthetic field
   private zzaog zzaiC;

   private zzaoi(zzaog var1, String var2, long var3) {
      this.zzaiC = var1;
      super();
      zzbo.zzcF(var2);
      zzbo.zzaf(var3 > 0L);
      this.mName = var2;
      this.zzaiB = var3;
   }

   private final void zzma() {
      long var1 = this.zzaiC.zzkq().currentTimeMillis();
      Editor var3;
      (var3 = zzaog.zza(this.zzaiC).edit()).remove(this.zzme());
      var3.remove(this.zzmf());
      var3.putLong(this.zzmd(), var1);
      var3.commit();
   }

   public final void zzbA(String var1) {
      if (this.zzmc() == 0L) {
         this.zzma();
      }

      if (var1 == null) {
         var1 = "";
      }

      synchronized(this) {
         long var3;
         if ((var3 = zzaog.zza(this.zzaiC).getLong(this.zzme(), 0L)) <= 0L) {
            Editor var5;
            (var5 = zzaog.zza(this.zzaiC).edit()).putString(this.zzmf(), var1);
            var5.putLong(this.zzme(), 1L);
            var5.apply();
         } else {
            boolean var6 = (UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) < Long.MAX_VALUE / (var3 + 1L);
            Editor var7 = zzaog.zza(this.zzaiC).edit();
            if (var6) {
               var7.putString(this.zzmf(), var1);
            }

            var7.putLong(this.zzme(), var3 + 1L);
            var7.apply();
         }
      }
   }

   public final Pair zzmb() {
      long var1;
      long var7;
      if ((var1 = (var7 = this.zzmc()) == 0L ? 0L : Math.abs(var7 - this.zzaiC.zzkq().currentTimeMillis())) < this.zzaiB) {
         return null;
      } else if (var1 > this.zzaiB << 1) {
         this.zzma();
         return null;
      } else {
         String var3 = zzaog.zza(this.zzaiC).getString(this.zzmf(), (String)null);
         long var4 = zzaog.zza(this.zzaiC).getLong(this.zzme(), 0L);
         this.zzma();
         return var3 != null && var4 > 0L ? new Pair(var3, var4) : null;
      }
   }

   private final long zzmc() {
      return zzaog.zza(this.zzaiC).getLong(this.zzmd(), 0L);
   }

   private final String zzmd() {
      return String.valueOf(this.mName).concat(":start");
   }

   private final String zzme() {
      return String.valueOf(this.mName).concat(":count");
   }

   private final String zzmf() {
      return String.valueOf(this.mName).concat(":value");
   }

   // $FF: synthetic method
   zzaoi(zzaog var1, String var2, long var3, zzaoh var5) {
      this(var1, var2, var3);
   }
}
