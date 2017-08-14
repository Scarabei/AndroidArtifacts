package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public class ConnectionConfiguration extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzg();
   private final String mName;
   private final String zzaTl;
   private final int zzamr;
   private final int zzaMJ;
   private final boolean zzbRb;
   private volatile boolean zzair;
   private volatile String zzbRc;
   private boolean zzbRd;
   private String zzbRe;

   ConnectionConfiguration(String var1, String var2, int var3, int var4, boolean var5, boolean var6, String var7, boolean var8, String var9) {
      this.mName = var1;
      this.zzaTl = var2;
      this.zzamr = var3;
      this.zzaMJ = var4;
      this.zzbRb = var5;
      this.zzair = var6;
      this.zzbRc = var7;
      this.zzbRd = var8;
      this.zzbRe = var9;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.mName, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaTl, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzamr);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzaMJ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbRb);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzair);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbRc, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbRd);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.zzbRe, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public String toString() {
      StringBuilder var1;
      StringBuilder var10000 = var1 = new StringBuilder("ConnectionConfiguration[ ");
      String var10002 = String.valueOf(this.mName);
      String var10001;
      String var10003;
      if (var10002.length() != 0) {
         var10001 = "mName=".concat(var10002);
      } else {
         var10003 = new String;
         var10001 = var10003;
         var10003.<init>("mName=");
      }

      var10000.append(var10001);
      var10002 = String.valueOf(this.zzaTl);
      if (var10002.length() != 0) {
         var10001 = ", mAddress=".concat(var10002);
      } else {
         var10003 = new String;
         var10001 = var10003;
         var10003.<init>(", mAddress=");
      }

      var1.append(var10001);
      int var2 = this.zzamr;
      var1.append((new StringBuilder(19)).append(", mType=").append(var2).toString());
      var2 = this.zzaMJ;
      var1.append((new StringBuilder(19)).append(", mRole=").append(var2).toString());
      boolean var3 = this.zzbRb;
      var1.append((new StringBuilder(16)).append(", mEnabled=").append(var3).toString());
      var3 = this.zzair;
      var1.append((new StringBuilder(20)).append(", mIsConnected=").append(var3).toString());
      var10002 = String.valueOf(this.zzbRc);
      if (var10002.length() != 0) {
         var10001 = ", mPeerNodeId=".concat(var10002);
      } else {
         var10003 = new String;
         var10001 = var10003;
         var10003.<init>(", mPeerNodeId=");
      }

      var1.append(var10001);
      var3 = this.zzbRd;
      var1.append((new StringBuilder(21)).append(", mBtlePriority=").append(var3).toString());
      var10002 = String.valueOf(this.zzbRe);
      if (var10002.length() != 0) {
         var10001 = ", mNodeId=".concat(var10002);
      } else {
         var10003 = new String;
         var10001 = var10003;
         var10003.<init>(", mNodeId=");
      }

      var1.append(var10001);
      var1.append("]");
      return var1.toString();
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof ConnectionConfiguration)) {
         return false;
      } else {
         ConnectionConfiguration var2 = (ConnectionConfiguration)var1;
         return zzbe.equal(this.mName, var2.mName) && zzbe.equal(this.zzaTl, var2.zzaTl) && zzbe.equal(this.zzamr, var2.zzamr) && zzbe.equal(this.zzaMJ, var2.zzaMJ) && zzbe.equal(this.zzbRb, var2.zzbRb) && zzbe.equal(this.zzbRd, var2.zzbRd);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mName, this.zzaTl, this.zzamr, this.zzaMJ, this.zzbRb, this.zzbRd});
   }
}
