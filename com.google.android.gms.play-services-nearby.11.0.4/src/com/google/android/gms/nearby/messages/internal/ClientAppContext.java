package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public final class ClientAppContext extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzd();
   private int versionCode;
   private String zzbyW;
   @Nullable
   private String zzbyX;
   private boolean zzbyf;
   private int zzbyY;
   private String zzbyh;

   @Nullable
   static final ClientAppContext zza(@Nullable ClientAppContext var0, @Nullable String var1, @Nullable String var2, boolean var3) {
      if (var0 != null) {
         return var0;
      } else {
         return var1 == null && var2 == null ? null : new ClientAppContext(var1, var2, var3, (String)null, 0);
      }
   }

   ClientAppContext(int var1, String var2, @Nullable String var3, boolean var4, int var5, @Nullable String var6) {
      this.versionCode = var1;
      this.zzbyW = (String)zzbo.zzu(var2);
      if (var3 != null && !var3.isEmpty() && !var3.startsWith("0p:")) {
         Log.w("NearbyMessages", String.format("ClientAppContext: 0P identifier(%s) without 0P prefix(%s)", var3, "0p:"));
         String var10000 = String.valueOf("0p:");
         String var10001 = String.valueOf(var3);
         if (var10001.length() != 0) {
            var10000 = var10000.concat(var10001);
         } else {
            String var10002 = new String;
            var10001 = var10000;
            var10000 = var10002;
            var10002.<init>(var10001);
         }

         var3 = var10000;
      }

      this.zzbyX = var3;
      this.zzbyf = var4;
      this.zzbyY = var5;
      this.zzbyh = var6;
   }

   public ClientAppContext(String var1, @Nullable String var2, boolean var3, @Nullable String var4, int var5) {
      this(1, var1, var2, var3, var5, var4);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbyW, this.zzbyX, this.zzbyf, this.zzbyh, this.zzbyY});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ClientAppContext)) {
         return false;
      } else {
         ClientAppContext var2 = (ClientAppContext)var1;
         return zzU(this.zzbyW, var2.zzbyW) && zzU(this.zzbyX, var2.zzbyX) && this.zzbyf == var2.zzbyf && zzU(this.zzbyh, var2.zzbyh) && this.zzbyY == var2.zzbyY;
      }
   }

   private static boolean zzU(String var0, String var1) {
      return TextUtils.isEmpty(var0) ? TextUtils.isEmpty(var1) : var0.equals(var1);
   }

   public final String toString() {
      return String.format("{realClientPackageName: %s, zeroPartyIdentifier: %s, useRealClientApiKey: %b, apiKey: %s, callingContext: %d}", this.zzbyW, this.zzbyX, this.zzbyf, this.zzbyh, this.zzbyY);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbyW, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbyX, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbyf);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzbyY);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbyh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
