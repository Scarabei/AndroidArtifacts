package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zze implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new SignInAccount[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      String var5 = "";
      GoogleSignInAccount var6 = null;
      String var7 = "";

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzg(var2, var8);
            break;
         case 2:
         case 3:
         case 5:
         case 6:
         default:
            zzb.zzb(var2, var8);
            break;
         case 4:
            var5 = zzb.zzq(var2, var8);
            break;
         case 7:
            var6 = (GoogleSignInAccount)zzb.zza(var2, var8, GoogleSignInAccount.CREATOR);
            break;
         case 8:
            var7 = zzb.zzq(var2, var8);
         }
      }

      zzb.zzF(var2, var3);
      return new SignInAccount(var4, var5, var6, var7);
   }
}
