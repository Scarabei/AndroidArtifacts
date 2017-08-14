package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;

public final class zzb implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new GoogleSignInAccount[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      String var5 = null;
      String var6 = null;
      String var7 = null;
      String var8 = null;
      Uri var9 = null;
      String var10 = null;
      long var11 = 0L;
      String var13 = null;
      ArrayList var14 = null;
      String var15 = null;
      String var16 = null;

      while(var2.dataPosition() < var3) {
         int var17;
         switch((var17 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var17);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var17);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var17);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var17);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var17);
            break;
         case 6:
            var9 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var17, Uri.CREATOR);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var17);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var17);
            break;
         case 9:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var17);
            break;
         case 10:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var17, Scope.CREATOR);
            break;
         case 11:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var17);
            break;
         case 12:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var17);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var17);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new GoogleSignInAccount(var4, var5, var6, var7, var8, var9, var10, var11, var13, var14, var15, var16);
   }
}
