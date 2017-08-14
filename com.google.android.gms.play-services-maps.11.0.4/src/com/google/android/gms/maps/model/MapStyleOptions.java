package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.io.IOException;
import java.io.InputStream;

public final class MapStyleOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   private static final String TAG = MapStyleOptions.class.getSimpleName();
   public static final Creator CREATOR = new zzg();
   private String zzbnC;

   public MapStyleOptions(String var1) {
      this.zzbnC = var1;
   }

   public static MapStyleOptions loadRawResourceStyle(Context var0, int var1) throws NotFoundException {
      InputStream var2 = var0.getResources().openRawResource(var1);

      String var4;
      try {
         byte[] var3 = com.google.android.gms.common.util.zzn.zza(var2, true);
         var4 = new String(var3, "UTF-8");
         return new MapStyleOptions(var4);
      } catch (IOException var5) {
         var4 = String.valueOf(var5);
         throw new NotFoundException((new StringBuilder(37 + String.valueOf(var4).length())).append("Failed to read resource ").append(var1).append(": ").append(var4).toString());
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbnC, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
