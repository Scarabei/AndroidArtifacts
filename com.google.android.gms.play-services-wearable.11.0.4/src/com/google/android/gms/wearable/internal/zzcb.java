package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class zzcb extends com.google.android.gms.common.internal.safeparcel.zza implements DataItem {
   public static final Creator CREATOR = new zzcc();
   private final Uri mUri;
   private final Map zzbSE;
   private byte[] zzbdY;

   zzcb(Uri var1, Bundle var2, byte[] var3) {
      this.mUri = var1;
      HashMap var4 = new HashMap();
      var2.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
      Iterator var5 = var2.keySet().iterator();

      while(var5.hasNext()) {
         String var6 = (String)var5.next();
         var4.put(var6, (DataItemAssetParcelable)var2.getParcelable(var6));
      }

      this.zzbSE = var4;
      this.zzbdY = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getUri(), var2, false);
      Bundle var7;
      (var7 = new Bundle()).setClassLoader(DataItemAssetParcelable.class.getClassLoader());
      Iterator var8 = this.zzbSE.entrySet().iterator();

      while(var8.hasNext()) {
         Entry var9 = (Entry)var8.next();
         var7.putParcelable((String)var9.getKey(), new DataItemAssetParcelable((DataItemAsset)var9.getValue()));
      }

      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, var7, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getData(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean isDataValid() {
      return true;
   }

   public final Uri getUri() {
      return this.mUri;
   }

   public final byte[] getData() {
      return this.zzbdY;
   }

   public final Map getAssets() {
      return this.zzbSE;
   }

   public final String toString() {
      boolean var2 = Log.isLoggable("DataItem", 3);
      zzcb var1 = this;
      StringBuilder var3;
      (var3 = new StringBuilder("DataItemParcelable[")).append("@");
      var3.append(Integer.toHexString(this.hashCode()));
      String var4 = String.valueOf(this.zzbdY == null ? "null" : this.zzbdY.length);
      var3.append((new StringBuilder(8 + String.valueOf(var4).length())).append(",dataSz=").append(var4).toString());
      int var8 = this.zzbSE.size();
      var3.append((new StringBuilder(23)).append(", numAssets=").append(var8).toString());
      var4 = String.valueOf(this.mUri);
      var3.append((new StringBuilder(6 + String.valueOf(var4).length())).append(", uri=").append(var4).toString());
      if (!var2) {
         var3.append("]");
         return var3.toString();
      } else {
         var3.append("]\n  assets: ");
         Iterator var5 = this.zzbSE.keySet().iterator();

         while(var5.hasNext()) {
            String var6 = (String)var5.next();
            String var7 = String.valueOf(var1.zzbSE.get(var6));
            var3.append((new StringBuilder(7 + String.valueOf(var6).length() + String.valueOf(var7).length())).append("\n    ").append(var6).append(": ").append(var7).toString());
         }

         var3.append("\n  ]");
         return var3.toString();
      }
   }

   // $FF: synthetic method
   public final DataItem setData(byte[] var1) {
      this.zzbdY = var1;
      return this;
   }
}
