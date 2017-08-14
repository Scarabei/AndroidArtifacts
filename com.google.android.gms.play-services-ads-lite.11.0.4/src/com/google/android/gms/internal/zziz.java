package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.widget.FrameLayout;

@zzzn
public class zziz {
   private zzkh zzAz;
   private final Object mLock = new Object();
   private final zziq zzAA;
   private final zzip zzAB;
   private final zzli zzAC;
   private final zzqc zzAD;
   private final zzadh zzAE;
   private final zzww zzAF;

   public zziz(zziq var1, zzip var2, zzli var3, zzqc var4, zzadh var5, zzww var6) {
      this.zzAA = var1;
      this.zzAB = var2;
      this.zzAC = var3;
      this.zzAD = var4;
      this.zzAE = var5;
      this.zzAF = var6;
   }

   @Nullable
   private static zzkh zzdm() {
      try {
         Object var0;
         if (!((var0 = zziz.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance()) instanceof IBinder)) {
            zzajc.zzaT("ClientApi class is not an instance of IBinder");
            return null;
         } else {
            return zzki.asInterface((IBinder)var0);
         }
      } catch (Exception var1) {
         zzajc.zzc("Failed to instantiate ClientApi class.", var1);
         return null;
      }
   }

   @Nullable
   private final zzkh zzdn() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzAz == null) {
            this.zzAz = zzdm();
         }

         return this.zzAz;
      }
   }

   @VisibleForTesting
   static Object zza(Context var0, boolean var1, zziz.zza var2) {
      if (!var1) {
         zzji.zzds();
         if (!zzaiy.zzX(var0)) {
            zzajc.zzaC("Google Play Services is not available");
            var1 = true;
         }
      }

      zzji.zzds();
      int var10000 = zzaiy.zzQ(var0);
      zzji.zzds();
      if (var10000 > zzaiy.zzP(var0)) {
         var1 = true;
      }

      Object var3;
      if (var1) {
         if ((var3 = var2.zzdp()) == null) {
            var3 = var2.zzdq();
         }
      } else if ((var3 = var2.zzdq()) == null) {
         var3 = var2.zzdp();
      }

      return var3;
   }

   private static void zzb(Context var0, String var1) {
      Bundle var2;
      (var2 = new Bundle()).putString("action", "no_ads_fallback");
      var2.putString("flow", var1);
      zzji.zzds().zza(var0, (String)null, "gmob-apps", var2, true);
   }

   public final zzju zzb(Context var1, String var2, zzuq var3) {
      return (zzju)zza(var1, false, new zzjd(this, var1, var2, var3));
   }

   public final zzow zza(Context var1, FrameLayout var2, FrameLayout var3) {
      return (zzow)zza(var1, false, new zzjf(this, var2, var3, var1));
   }

   @Nullable
   public final zzwx zzb(Activity var1) {
      String var3 = "com.google.android.gms.ads.internal.overlay.useClientJar";
      boolean var10000;
      Intent var4;
      if (!(var4 = var1.getIntent()).hasExtra(var3)) {
         zzajc.e("useClientJar flag not found in activity intent extras.");
         var10000 = false;
      } else {
         var10000 = var4.getBooleanExtra(var3, false);
      }

      boolean var2 = var10000;
      return (zzwx)zza(var1, var2, new zzjh(this, var1));
   }

   // $FF: synthetic method
   static zziq zzb(zziz var0) {
      return var0.zzAA;
   }

   // $FF: synthetic method
   static void zza(zziz var0, Context var1, String var2) {
      zzb(var1, var2);
   }

   // $FF: synthetic method
   static zzip zzc(zziz var0) {
      return var0.zzAB;
   }

   // $FF: synthetic method
   static zzli zzd(zziz var0) {
      return var0.zzAC;
   }

   // $FF: synthetic method
   static zzqc zze(zziz var0) {
      return var0.zzAD;
   }

   // $FF: synthetic method
   static zzadh zzf(zziz var0) {
      return var0.zzAE;
   }

   // $FF: synthetic method
   static zzww zzg(zziz var0) {
      return var0.zzAF;
   }

   @VisibleForTesting
   abstract class zza {
      // $FF: synthetic field
      private zziz zzAI;

      zza() {
         this.zzAI = zziz.this;
         super();
      }

      @Nullable
      protected abstract Object zza(zzkh var1) throws RemoteException;

      @Nullable
      protected abstract Object zzdo() throws RemoteException;

      @Nullable
      protected final Object zzdp() {
         zzkh var1;
         if ((var1 = this.zzAI.zzdn()) == null) {
            zzajc.zzaT("ClientApi class cannot be loaded.");
            return null;
         } else {
            try {
               return this.zza(var1);
            } catch (RemoteException var3) {
               zzajc.zzc("Cannot invoke local loader using ClientApi class", var3);
               return null;
            }
         }
      }

      @Nullable
      protected final Object zzdq() {
         try {
            return this.zzdo();
         } catch (RemoteException var2) {
            zzajc.zzc("Cannot invoke remote loader", var2);
            return null;
         }
      }
   }
}
