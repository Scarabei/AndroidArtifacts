package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public final class zzaer implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzaeq[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      boolean var6 = false;
      boolean var7 = false;
      ArrayList var8 = null;
      boolean var9 = false;
      boolean var10 = false;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzq(var2, var11);
            break;
         case 3:
            var5 = zzb.zzq(var2, var11);
            break;
         case 4:
            var6 = zzb.zzc(var2, var11);
            break;
         case 5:
            var7 = zzb.zzc(var2, var11);
            break;
         case 6:
            var8 = zzb.zzC(var2, var11);
            break;
         case 7:
            var9 = zzb.zzc(var2, var11);
            break;
         case 8:
            var10 = zzb.zzc(var2, var11);
            break;
         default:
            zzb.zzb(var2, var11);
         }
      }

      zzb.zzF(var2, var3);
      return new zzaeq(var4, var5, var6, var7, var8, var9, var10);
   }
}
