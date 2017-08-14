package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public final class zzb implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new AddPlaceRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      LatLng var5 = null;
      String var6 = null;
      ArrayList var7 = null;
      String var8 = null;
      Uri var9 = null;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 2:
            var5 = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, LatLng.CREATOR);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzB(var2, var10);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 6:
            var9 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, Uri.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new AddPlaceRequest(var4, var5, var6, var7, var8, var9);
   }
}
