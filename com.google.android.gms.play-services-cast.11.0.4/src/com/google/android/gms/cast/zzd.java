package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import java.util.ArrayList;

public final class zzd implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new ApplicationMetadata[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      ArrayList var6 = null;
      ArrayList var7 = null;
      String var8 = null;
      Uri var9 = null;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var10, WebImage.CREATOR);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzC(var2, var10);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 7:
            var9 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, Uri.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new ApplicationMetadata(var4, var5, var6, var7, var8, var9);
   }
}
