package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public final class zzb extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final zzb zzaTj = new zzb("com.google.android.gms", (String)null, (String)null);
   private final int versionCode;
   private final String packageName;
   private final String version;
   private final String zzaTk;
   public static final Creator CREATOR = new zzc();

   public static zzb zzcX(String var0) {
      return "com.google.android.gms".equals(var0) ? zzaTj : new zzb(var0, (String)null, (String)null);
   }

   private zzb(String var1, String var2, String var3) {
      this(1, var1, "", (String)null);
   }

   zzb(int var1, String var2, String var3, String var4) {
      this.versionCode = var1;
      this.packageName = (String)zzbo.zzu(var2);
      this.version = "";
      this.zzaTk = var4;
   }

   public final String getPackageName() {
      return this.packageName;
   }

   public final boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof zzb) {
            zzb var3 = (zzb)var1;
            if (this.packageName.equals(var3.packageName) && zzbe.equal(this.version, var3.version) && zzbe.equal(this.zzaTk, var3.zzaTk)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.packageName, this.version, this.zzaTk});
   }

   public final String toString() {
      return String.format("Application{%s:%s:%s}", this.packageName, this.version, this.zzaTk);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.packageName, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.version, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaTk, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
