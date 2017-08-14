package com.google.android.gms.internal;

import android.os.ConditionVariable;
import android.os.Build.VERSION;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;

public class zzcn {
   private zzdb zzpJ;
   private static final ConditionVariable zzpK = new ConditionVariable();
   protected static volatile zzazn zzpL = null;
   protected volatile Boolean zzpM;
   private static volatile Random zzpN = null;

   public zzcn(zzdb var1) {
      this.zzpJ = var1;
      ExecutorService var3 = var1.zzC();
      var3.execute(new zzco(this));
   }

   public final void zza(int var1, int var2, long var3) throws IOException {
      try {
         zzpK.block();
         if (this.zzpM.booleanValue() && zzpL != null && this.zzpJ.zzqT) {
            zzat var5;
            (var5 = new zzat()).zzaH = this.zzpJ.zzqD.getPackageName();
            var5.zzaI = var3;
            zzazp var6;
            (var6 = zzpL.zze(adp.zzc(var5))).zzaj(var2);
            var6.zzai(var1);
            this.zzpJ.zzG();
            var6.zzoT();
         }

      } catch (Exception var7) {
         ;
      }
   }

   public static int zzy() {
      try {
         return VERSION.SDK_INT >= 21 ? ThreadLocalRandom.current().nextInt() : zzz().nextInt();
      } catch (RuntimeException var0) {
         return zzz().nextInt();
      }
   }

   private static Random zzz() {
      if (zzpN == null) {
         Class var0 = zzcn.class;
         synchronized(zzcn.class) {
            if (zzpN == null) {
               zzpN = new Random();
            }
         }
      }

      return zzpN;
   }

   // $FF: synthetic method
   static ConditionVariable zzA() {
      return zzpK;
   }

   // $FF: synthetic method
   static zzdb zza(zzcn var0) {
      return var0.zzpJ;
   }
}
