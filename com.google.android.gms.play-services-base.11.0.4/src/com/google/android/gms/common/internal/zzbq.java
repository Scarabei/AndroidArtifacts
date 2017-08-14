package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public final class zzbq implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbp[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      Account var5 = null;
      int var6 = 0;
      GoogleSignInAccount var7 = null;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var8);
            break;
         case 2:
            var5 = (Account)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var8, Account.CREATOR);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var8);
            break;
         case 4:
            var7 = (GoogleSignInAccount)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var8, GoogleSignInAccount.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var8);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzbp(var4, var5, var6, var7);
   }
}
