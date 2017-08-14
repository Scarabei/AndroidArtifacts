package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import com.google.android.gms.awareness.AwarenessOptions;
import com.google.android.gms.awareness.fence.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.internal.safeparcel.zze;
import com.google.android.gms.common.util.zzd;
import java.util.ArrayList;

public final class zzbka extends zzz {
   private static zzel zzaLu;
   private final Looper zzrM;
   private final zzbkb zzaLv;
   private zzes zzaLw;

   public zzbka(Context var1, Looper var2, zzq var3, AwarenessOptions var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      super(var1, var2, 47, var3, var5, var6);
      this.zzrM = var2;
      String var7 = var3.getAccount() == null ? "@@ContextManagerNullAccount@@" : var3.getAccount().name;
      this.zzaLv = var4 == null ? new zzbkb(var7, var1.getPackageName(), Process.myUid(), var1.getPackageName(), zzd.zzA(var1, var1.getPackageName()), 3, (String)null, (String)null, -1, Process.myPid()) : zzbkb.zza(var1, var7, var4);
   }

   public final boolean zzrg() {
      return false;
   }

   protected final String zzdb() {
      return "com.google.android.contextmanager.service.ContextManagerService.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.contextmanager.internal.IContextManagerService";
   }

   protected final Bundle zzmo() {
      Bundle var1;
      (var1 = new Bundle()).putByteArray("com.google.android.contextmanager.service.args", zze.zza(this.zzaLv));
      return var1;
   }

   public final void zza(zzbaz var1, zzbjj var2) throws RemoteException {
      this.zzre();
      if (this.zzaLw == null) {
         this.zzaLw = new zzes(this.zzrM, zzbit.zzaKT);
      }

      zzes var5 = this.zzaLw;
      ArrayList var7;
      int var8 = (var7 = (ArrayList)var2.zzaLj).size();
      int var9 = 0;

      while(var9 < var8) {
         Object var10000 = var7.get(var9);
         ++var9;
         zzbjt var6;
         if ((var6 = (zzbjt)var10000).zzaLp == null) {
            zza var10 = var6.zzaLq;
         }
      }

      ((zzbkj)this.zzrf()).zza(zzbkd.zza((zzbaz)var1, (zzbkg)null), this.zzaLv.packageName, this.zzaLv.zzaLx, this.zzaLv.moduleId, (zzbjj)var2);
   }

   public final void zza(zzbaz var1, zzaub var2) throws RemoteException {
      this.zzre();
      ((zzbkj)this.zzrf()).zza(zzbkd.zzd(var1), this.zzaLv.packageName, this.zzaLv.zzaLx, this.zzaLv.moduleId, (zzaub)var2);
   }

   public final void zza(zzbaz var1, zzbja var2) throws RemoteException {
      this.zzre();
      ((zzbkj)this.zzrf()).zza(zzbkd.zze(var1), this.zzaLv.packageName, this.zzaLv.zzaLx, this.zzaLv.moduleId, (zzbja)var2);
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.contextmanager.internal.IContextManagerService")) instanceof zzbkj ? (zzbkj)var3 : new zzbkk(var1));
      }
   }

   static {
      zzaLu = zzel.zzrI;
   }
}
