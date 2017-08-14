package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.tagmanager.zzcn;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class zzcuf {
   private final Context mContext;
   private final String zzbDw;
   private final String zzbHH;
   private final String zzbHI;
   private final zzcvz zzbHJ;
   private final cd zzbHK;
   private final ExecutorService zzbHL;
   private final ScheduledExecutorService zzbHM;
   private final zzcn zzbHN;
   private final zze zzvw;
   private final zzcuo zzbHO;
   private zzcvu zzbHP;
   private volatile int mState = 1;
   private List zzbHQ = new ArrayList();
   private ScheduledFuture zzbGa = null;
   private boolean zzbHR = false;

   zzcuf(Context var1, String var2, @Nullable String var3, @Nullable String var4, zzcvz var5, cd var6, ExecutorService var7, ScheduledExecutorService var8, zzcn var9, zze var10, zzcuo var11) {
      this.mContext = var1;
      this.zzbDw = (String)zzbo.zzu(var2);
      this.zzbHJ = (zzcvz)zzbo.zzu(var5);
      this.zzbHK = (cd)zzbo.zzu(var6);
      this.zzbHL = (ExecutorService)zzbo.zzu(var7);
      this.zzbHM = (ScheduledExecutorService)zzbo.zzu(var8);
      this.zzbHN = (zzcn)zzbo.zzu(var9);
      this.zzvw = (zze)zzbo.zzu(var10);
      this.zzbHO = (zzcuo)zzbo.zzu(var11);
      this.zzbHH = var4;
      this.zzbHI = var3;
      zzcut var12 = new zzcut("gtm.load", new Bundle(), "gtm", new Date(), false, this.zzbHN);
      this.zzbHQ.add(var12);
      String var13 = this.zzbDw;
      zzcvk.v((new StringBuilder(35 + String.valueOf(var13).length())).append("Container ").append(var13).append("is scheduled for loading.").toString());
      this.zzbHL.execute(new zzcuj(this, (zzcug)null));
   }

   public final void zza(zzcut var1) {
      this.zzbHL.execute(new zzcuk(this, var1));
   }

   public final void dispatch() {
      this.zzbHL.execute(new zzcug(this));
   }

   private final void zzaj(long var1) {
      if (this.zzbGa != null) {
         this.zzbGa.cancel(false);
      }

      String var3 = this.zzbDw;
      zzcvk.v((new StringBuilder(45 + String.valueOf(var3).length())).append("Refresh container ").append(var3).append(" in ").append(var1).append("ms.").toString());
      this.zzbGa = this.zzbHM.schedule(new zzcuh(this), var1, TimeUnit.MILLISECONDS);
   }

   // $FF: synthetic method
   static int zza(zzcuf var0) {
      return var0.mState;
   }

   // $FF: synthetic method
   static zzcvu zzb(zzcuf var0) {
      return var0.zzbHP;
   }

   // $FF: synthetic method
   static ExecutorService zzc(zzcuf var0) {
      return var0.zzbHL;
   }

   // $FF: synthetic method
   static String zzd(zzcuf var0) {
      return var0.zzbDw;
   }

   // $FF: synthetic method
   static String zze(zzcuf var0) {
      return var0.zzbHI;
   }

   // $FF: synthetic method
   static String zzf(zzcuf var0) {
      return var0.zzbHH;
   }

   // $FF: synthetic method
   static zzcuo zzg(zzcuf var0) {
      return var0.zzbHO;
   }

   // $FF: synthetic method
   static cd zzh(zzcuf var0) {
      return var0.zzbHK;
   }

   // $FF: synthetic method
   static void zza(zzcuf var0, long var1) {
      var0.zzaj(var1);
   }

   // $FF: synthetic method
   static List zzi(zzcuf var0) {
      return var0.zzbHQ;
   }

   // $FF: synthetic method
   static zzcn zzj(zzcuf var0) {
      return var0.zzbHN;
   }

   // $FF: synthetic method
   static Context zzk(zzcuf var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static boolean zza(zzcuf var0, boolean var1) {
      return var0.zzbHR = var1;
   }

   // $FF: synthetic method
   static boolean zzl(zzcuf var0) {
      return var0.zzbHR;
   }

   // $FF: synthetic method
   static zzcvu zza(zzcuf var0, zzcvu var1) {
      return var0.zzbHP = var1;
   }

   // $FF: synthetic method
   static zzcvz zzm(zzcuf var0) {
      return var0.zzbHJ;
   }

   // $FF: synthetic method
   static int zza(zzcuf var0, int var1) {
      return var0.mState = var1;
   }

   // $FF: synthetic method
   static List zza(zzcuf var0, List var1) {
      return var0.zzbHQ = null;
   }

   // $FF: synthetic method
   static zze zzn(zzcuf var0) {
      return var0.zzvw;
   }
}
