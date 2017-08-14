package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.zzas;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzm extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzn();
   private final String zzaAl;
   private final zzg zzaAm;
   private final boolean zzaAn;

   zzm(String var1, IBinder var2, boolean var3) {
      this.zzaAl = var1;
      this.zzaAm = zzG(var2);
      this.zzaAn = var3;
   }

   zzm(String var1, zzg var2, boolean var3) {
      this.zzaAl = var1;
      this.zzaAm = var2;
      this.zzaAn = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, (String)this.zzaAl, false);
      IBinder var10002;
      if (this.zzaAm == null) {
         Log.w("GoogleCertificatesQuery", "certificate binder is null");
         var10002 = null;
      } else {
         var10002 = this.zzaAm.asBinder();
      }

      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, (IBinder)var10002, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaAn);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   private static zzg zzG(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         zzh var1 = null;

         IObjectWrapper var2;
         try {
            var2 = zzas.zzI(var0).zzoY();
         } catch (RemoteException var4) {
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", var4);
            return null;
         }

         byte[] var10000 = var2 == null ? null : (byte[])com.google.android.gms.dynamic.zzn.zzE(var2);
         byte[] var3 = var10000;
         if (var10000 != null) {
            var1 = new zzh(var3);
         } else {
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
         }

         return var1;
      }
   }
}
