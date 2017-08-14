package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;

public final class SignInConfiguration extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzw();
   private int versionCode;
   private final String zzamu;
   private GoogleSignInOptions zzamv;

   SignInConfiguration(int var1, String var2, GoogleSignInOptions var3) {
      this.versionCode = var1;
      this.zzamu = zzbo.zzcF(var2);
      this.zzamv = var3;
   }

   public SignInConfiguration(String var1, GoogleSignInOptions var2) {
      this(3, var1, var2);
   }

   public final GoogleSignInOptions zzmL() {
      return this.zzamv;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzamu, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzamv, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else {
         try {
            SignInConfiguration var2 = (SignInConfiguration)var1;
            if (this.zzamu.equals(var2.zzamu)) {
               if (this.zzamv == null) {
                  if (var2.zzamv == null) {
                     return true;
                  }
               } else if (this.zzamv.equals(var2.zzamv)) {
                  return true;
               }
            }

            return false;
         } catch (ClassCastException var3) {
            return false;
         }
      }
   }

   public final int hashCode() {
      return (new zzo()).zzo(this.zzamu).zzo(this.zzamv).zzmJ();
   }
}
