package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.zze;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

@zzzn
public final class zzex extends zzp {
   private static final zzex zzsm = new zzex();

   public static zzey zzb(String var0, Context var1, boolean var2) {
      Object var3;
      if (zze.zzoW().isGooglePlayServicesAvailable(var1) != 0 || (var3 = zzsm.zzc(var0, var1, false)) == null) {
         var3 = new zzew(var0, var1, false);
      }

      return (zzey)var3;
   }

   private zzex() {
      super("com.google.android.gms.ads.adshield.AdShieldCreatorImpl");
   }

   private final zzey zzc(String var1, Context var2, boolean var3) {
      IObjectWrapper var4 = zzn.zzw(var2);

      try {
         IBinder var5;
         if (var3) {
            var5 = ((zzfb)this.zzaS(var2)).zza(var1, var4);
         } else {
            var5 = ((zzfb)this.zzaS(var2)).zzb(var1, var4);
         }

         if (var5 == null) {
            return null;
         } else {
            IInterface var7;
            return (zzey)((var7 = var5.queryLocalInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient")) instanceof zzey ? (zzey)var7 : new zzfa(var5));
         }
      } catch (zzq | RemoteException var8) {
         return null;
      }
   }

   // $FF: synthetic method
   protected final Object zzb(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (var3 = var1.queryLocalInterface("com.google.android.gms.ads.adshield.internal.IAdShieldCreator")) instanceof zzfb ? (zzfb)var3 : new zzfc(var1);
      }
   }
}
