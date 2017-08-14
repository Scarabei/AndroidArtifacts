package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;

public class UserMetadata extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzu();
   private String zzaML;
   private String zzalP;
   private String zzaMM;
   private boolean zzaMN;
   private String zzaMO;

   public UserMetadata(String var1, String var2, String var3, boolean var4, String var5) {
      this.zzaML = var1;
      this.zzalP = var2;
      this.zzaMM = var3;
      this.zzaMN = var4;
      this.zzaMO = var5;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaML, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzalP, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaMM, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzaMN);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaMO, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public String toString() {
      return String.format("Permission ID: '%s', Display Name: '%s', Picture URL: '%s', Authenticated User: %b, Email: '%s'", this.zzaML, this.zzalP, this.zzaMM, this.zzaMN, this.zzaMO);
   }
}
