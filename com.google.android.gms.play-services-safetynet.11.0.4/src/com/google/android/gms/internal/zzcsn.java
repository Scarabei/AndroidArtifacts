package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;
import java.util.List;

public final class zzcsn extends zzz {
   private final Context mContext;

   public zzcsn(Context var1, Looper var2, zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5) {
      super(var1, var2, 45, var3, var4, var5);
      this.mContext = var1;
   }

   protected final String zzdb() {
      return "com.google.android.gms.safetynet.service.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.safetynet.internal.ISafetyNetService";
   }

   public final void zzb(zzcrw var1, byte[] var2, String var3) throws RemoteException {
      if (TextUtils.isEmpty(var3)) {
         var3 = this.zzeH("com.google.android.safetynet.ATTEST_API_KEY");
      }

      ((zzcry)this.zzrf()).zza(var1, var2, var3);
   }

   public final void zza(zzcrw var1, List var2, int var3, String var4, String var5) throws RemoteException {
      if (var5 == null) {
         var5 = this.zzeH("com.google.android.safetynet.API_KEY");
      }

      int[] var6 = new int[var2.size()];

      for(int var7 = 0; var7 < var2.size(); ++var7) {
         var6[var7] = ((Integer)var2.get(var7)).intValue();
      }

      ((zzcry)this.zzrf()).zza(var1, var5, var6, var3, var4);
   }

   private final String zzeH(String var1) {
      try {
         PackageManager var2;
         if ((var2 = this.mContext.getPackageManager()) == null) {
            return null;
         } else {
            ApplicationInfo var3;
            if ((var3 = var2.getApplicationInfo(this.mContext.getPackageName(), 128)) == null) {
               return null;
            } else {
               Bundle var4 = var3.metaData;
               return var3.metaData == null ? null : (String)var4.get(var1);
            }
         }
      } catch (NameNotFoundException var5) {
         return null;
      }
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.safetynet.internal.ISafetyNetService")) instanceof zzcry ? (zzcry)var3 : new zzcrz(var1));
      }
   }
}
