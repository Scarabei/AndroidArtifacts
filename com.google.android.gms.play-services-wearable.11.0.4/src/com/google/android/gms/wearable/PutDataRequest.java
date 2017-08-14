package com.google.android.gms.wearable;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.wearable.internal.DataItemAssetParcelable;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class PutDataRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final String WEAR_URI_SCHEME = "wear";
   public static final Creator CREATOR = new zzh();
   private static final long zzbRh;
   private static final Random zzbRi;
   private final Uri mUri;
   private final Bundle zzbRj;
   private byte[] zzbdY;
   private long zzbRk;

   PutDataRequest(Uri var1, Bundle var2, byte[] var3, long var4) {
      this.mUri = var1;
      this.zzbRj = var2;
      this.zzbRj.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
      this.zzbdY = var3;
      this.zzbRk = var4;
   }

   private PutDataRequest(Uri var1) {
      this(var1, new Bundle(), (byte[])null, zzbRh);
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbRj, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getData(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbRk);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public static PutDataRequest createFromDataItem(DataItem var0) {
      PutDataRequest var1 = zzt(var0.getUri());
      Iterator var2 = var0.getAssets().entrySet().iterator();

      while(var2.hasNext()) {
         Entry var3;
         if (((DataItemAsset)(var3 = (Entry)var2.next()).getValue()).getId() == null) {
            IllegalStateException var10000 = new IllegalStateException;
            String var10003 = String.valueOf((String)var3.getKey());
            String var10002;
            if (var10003.length() != 0) {
               var10002 = "Cannot create an asset for a put request without a digest: ".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Cannot create an asset for a put request without a digest: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }

         var1.putAsset((String)var3.getKey(), Asset.createFromRef(((DataItemAsset)var3.getValue()).getId()));
      }

      var1.setData(var0.getData());
      return var1;
   }

   public static PutDataRequest zzt(Uri var0) {
      return new PutDataRequest(var0);
   }

   public static PutDataRequest createWithAutoAppendedId(String var0) {
      StringBuilder var1 = new StringBuilder(var0);
      if (!var0.endsWith("/")) {
         var1.append("/");
      }

      var1.append("PN").append(zzbRi.nextLong());
      Uri var2 = zzgj(var1.toString());
      return new PutDataRequest(var2);
   }

   public static PutDataRequest create(String var0) {
      return zzt(zzgj(var0));
   }

   private static Uri zzgj(String var0) {
      if (TextUtils.isEmpty(var0)) {
         throw new IllegalArgumentException("An empty path was supplied.");
      } else if (!var0.startsWith("/")) {
         throw new IllegalArgumentException("A path must start with a single / .");
      } else if (var0.startsWith("//")) {
         throw new IllegalArgumentException("A path must start with a single / .");
      } else {
         return (new Builder()).scheme("wear").path(var0).build();
      }
   }

   public Uri getUri() {
      return this.mUri;
   }

   public byte[] getData() {
      return this.zzbdY;
   }

   public PutDataRequest setData(byte[] var1) {
      this.zzbdY = var1;
      return this;
   }

   public Map getAssets() {
      HashMap var1 = new HashMap();
      Iterator var2 = this.zzbRj.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         var1.put(var3, (Asset)this.zzbRj.getParcelable(var3));
      }

      return Collections.unmodifiableMap(var1);
   }

   public Asset getAsset(String var1) {
      return (Asset)this.zzbRj.getParcelable(var1);
   }

   public boolean hasAsset(String var1) {
      return this.zzbRj.containsKey(var1);
   }

   public PutDataRequest putAsset(String var1, Asset var2) {
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      this.zzbRj.putParcelable(var1, var2);
      return this;
   }

   public PutDataRequest removeAsset(String var1) {
      this.zzbRj.remove(var1);
      return this;
   }

   public boolean isUrgent() {
      return this.zzbRk == 0L;
   }

   public PutDataRequest setUrgent() {
      this.zzbRk = 0L;
      return this;
   }

   public String toString() {
      return this.toString(Log.isLoggable("DataMap", 3));
   }

   public String toString(boolean var1) {
      StringBuilder var2;
      StringBuilder var10000 = var2 = new StringBuilder("PutDataRequest[");
      String var3 = String.valueOf(this.zzbdY == null ? "null" : this.zzbdY.length);
      var10000.append((new StringBuilder(7 + String.valueOf(var3).length())).append("dataSz=").append(var3).toString());
      int var7 = this.zzbRj.size();
      var2.append((new StringBuilder(23)).append(", numAssets=").append(var7).toString());
      var3 = String.valueOf(this.mUri);
      var2.append((new StringBuilder(6 + String.valueOf(var3).length())).append(", uri=").append(var3).toString());
      long var8 = this.zzbRk;
      var2.append((new StringBuilder(35)).append(", syncDeadline=").append(var8).toString());
      if (!var1) {
         var2.append("]");
         return var2.toString();
      } else {
         var2.append("]\n  assets: ");
         Iterator var4 = this.zzbRj.keySet().iterator();

         while(var4.hasNext()) {
            String var5 = (String)var4.next();
            String var6 = String.valueOf(this.zzbRj.getParcelable(var5));
            var2.append((new StringBuilder(7 + String.valueOf(var5).length() + String.valueOf(var6).length())).append("\n    ").append(var5).append(": ").append(var6).toString());
         }

         var2.append("\n  ]");
         return var2.toString();
      }
   }

   static {
      zzbRh = TimeUnit.MINUTES.toMillis(30L);
      zzbRi = new SecureRandom();
   }
}
