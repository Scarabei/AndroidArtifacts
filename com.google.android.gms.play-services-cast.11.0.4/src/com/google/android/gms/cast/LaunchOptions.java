package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzaye;
import java.util.Arrays;
import java.util.Locale;

public class LaunchOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzab();
   private boolean zzapV;
   private String zzaeT;

   LaunchOptions(boolean var1, String var2) {
      this.zzapV = var1;
      this.zzaeT = var2;
   }

   public LaunchOptions() {
      this(false, zzaye.zzb(Locale.getDefault()));
   }

   public void setRelaunchIfRunning(boolean var1) {
      this.zzapV = var1;
   }

   public boolean getRelaunchIfRunning() {
      return this.zzapV;
   }

   public void setLanguage(String var1) {
      this.zzaeT = var1;
   }

   public String getLanguage() {
      return this.zzaeT;
   }

   public String toString() {
      return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", this.zzapV, this.zzaeT);
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getRelaunchIfRunning());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getLanguage(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof LaunchOptions)) {
         return false;
      } else {
         LaunchOptions var2 = (LaunchOptions)var1;
         return this.zzapV == var2.zzapV && zzaye.zza(this.zzaeT, var2.zzaeT);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzapV, this.zzaeT});
   }

   public static final class Builder {
      private LaunchOptions zzapW = new LaunchOptions();

      public final LaunchOptions.Builder setRelaunchIfRunning(boolean var1) {
         this.zzapW.setRelaunchIfRunning(var1);
         return this;
      }

      public final LaunchOptions.Builder setLocale(Locale var1) {
         this.zzapW.setLanguage(zzaye.zzb(var1));
         return this;
      }

      public final LaunchOptions build() {
         return this.zzapW;
      }
   }
}
