package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;

public final class zza extends zzam {
   private int zzaGG;

   public static Account zza(zzal var0) {
      Account var1 = null;
      if (var0 != null) {
         long var2 = Binder.clearCallingIdentity();

         try {
            var1 = var0.getAccount();
         } catch (RemoteException var7) {
            Log.w("AccountAccessor", "Remote account accessor probably died");
         } finally {
            Binder.restoreCallingIdentity(var2);
         }
      }

      return var1;
   }

   public final Account getAccount() {
      int var1;
      if ((var1 = Binder.getCallingUid()) == this.zzaGG) {
         return null;
      } else if (com.google.android.gms.common.zzo.zzf((Context)null, var1)) {
         this.zzaGG = var1;
         return null;
      } else {
         throw new SecurityException("Caller is not GooglePlayServices");
      }
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         return !(var1 instanceof zza) ? false : null.equals((Object)null);
      }
   }
}
