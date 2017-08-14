package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzy implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzx[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      String var7 = null;
      IBinder var8 = null;
      Scope[] var9 = null;
      Bundle var10 = null;
      Account var11 = null;
      com.google.android.gms.common.zzc[] var12 = null;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzg(var2, var13);
            break;
         case 2:
            var5 = zzb.zzg(var2, var13);
            break;
         case 3:
            var6 = zzb.zzg(var2, var13);
            break;
         case 4:
            var7 = zzb.zzq(var2, var13);
            break;
         case 5:
            var8 = zzb.zzr(var2, var13);
            break;
         case 6:
            var9 = (Scope[])zzb.zzb(var2, var13, Scope.CREATOR);
            break;
         case 7:
            var10 = zzb.zzs(var2, var13);
            break;
         case 8:
            var11 = (Account)zzb.zza(var2, var13, Account.CREATOR);
            break;
         case 9:
         default:
            zzb.zzb(var2, var13);
            break;
         case 10:
            var12 = (com.google.android.gms.common.zzc[])zzb.zzb(var2, var13, com.google.android.gms.common.zzc.CREATOR);
         }
      }

      zzb.zzF(var2, var3);
      return new zzx(var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }
}
