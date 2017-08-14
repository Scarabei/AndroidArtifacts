package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;

public final class zzd implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new GoogleSignInOptions[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      ArrayList var5 = null;
      Account var6 = null;
      boolean var7 = false;
      boolean var8 = false;
      boolean var9 = false;
      String var10 = null;
      String var11 = null;
      ArrayList var12 = null;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var13);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13, Scope.CREATOR);
            break;
         case 3:
            var6 = (Account)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, Account.CREATOR);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 9:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13, zzn.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var13);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new GoogleSignInOptions(var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }
}
