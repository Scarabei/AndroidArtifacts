package com.google.android.gms.ads.internal.js;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzahz;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zzl {
   private final Object mLock;
   private final Context mContext;
   private final String zzLa;
   private final zzaje zztW;
   private zzahz zzLb;
   private zzahz zzLc;
   @Nullable
   private zzac zzLd;
   private int zzLe;

   public zzl(Context var1, zzaje var2, String var3) {
      this.mLock = new Object();
      this.zzLe = 1;
      this.zzLa = var3;
      this.mContext = var1.getApplicationContext();
      this.zztW = var2;
      this.zzLb = new zzx();
      this.zzLc = new zzx();
   }

   public zzl(Context var1, zzaje var2, String var3, zzahz var4, zzahz var5) {
      this(var1, var2, var3);
      this.zzLb = var4;
      this.zzLc = var5;
   }

   protected final zzac zza(@Nullable zzcu var1) {
      zzac var5 = new zzac(this.zzLc);
      zzbs.zzbz();
      zzagz.runOnUiThread(new zzm(this, var1, var5));
      var5.zza(new zzu(this, var5), new zzv(this, var5));
      return var5;
   }

   public final zzy zzb(@Nullable zzcu var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzLd != null && this.zzLd.getStatus() != -1) {
            if (this.zzLe == 0) {
               return this.zzLd.zzfa();
            } else if (this.zzLe == 1) {
               this.zzLe = 2;
               this.zza(var1);
               return this.zzLd.zzfa();
            } else {
               return this.zzLe == 2 ? this.zzLd.zzfa() : this.zzLd.zzfa();
            }
         } else {
            this.zzLe = 2;
            this.zzLd = this.zza(var1);
            return this.zzLd.zzfa();
         }
      }
   }

   // $FF: synthetic method
   static Context zza(zzl var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static zzaje zzb(zzl var0) {
      return var0.zztW;
   }

   // $FF: synthetic method
   static Object zzc(zzl var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static int zza(zzl var0, int var1) {
      return var0.zzLe = var1;
   }

   // $FF: synthetic method
   static zzahz zzd(zzl var0) {
      return var0.zzLb;
   }

   // $FF: synthetic method
   static zzac zza(zzl var0, zzac var1) {
      return var0.zzLd = var1;
   }

   // $FF: synthetic method
   static int zze(zzl var0) {
      return var0.zzLe;
   }

   // $FF: synthetic method
   static String zzf(zzl var0) {
      return var0.zzLa;
   }

   // $FF: synthetic method
   static zzac zzg(zzl var0) {
      return var0.zzLd;
   }
}
