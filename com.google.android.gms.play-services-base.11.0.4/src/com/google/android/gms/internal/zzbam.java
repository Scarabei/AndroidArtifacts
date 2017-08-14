package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;

public abstract class zzbam {
   private int zzamr;

   public zzbam(int var1) {
      this.zzamr = var1;
   }

   public abstract void zza(zzbdd var1) throws DeadObjectException;

   public abstract void zzp(@NonNull Status var1);

   public abstract void zza(@NonNull zzbbt var1, boolean var2);

   private static Status zza(RemoteException var0) {
      StringBuilder var1 = new StringBuilder();
      if (VERSION.SDK_INT >= 15 && var0 instanceof TransactionTooLargeException) {
         var1.append("TransactionTooLargeException: ");
      }

      var1.append(var0.getLocalizedMessage());
      return new Status(8, var1.toString());
   }

   // $FF: synthetic method
   static Status zzb(RemoteException var0) {
      return zza(var0);
   }
}
