package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public class SignInAccount extends zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zze();
   private int versionCode;
   /** @deprecated */
   @Deprecated
   private String zzalO;
   private GoogleSignInAccount zzamh;
   /** @deprecated */
   @Deprecated
   private String zzafe;

   SignInAccount(int var1, String var2, GoogleSignInAccount var3, String var4) {
      this.versionCode = var1;
      this.zzamh = var3;
      this.zzalO = zzbo.zzh(var2, "8.3 and 8.4 SDKs require non-null email");
      this.zzafe = zzbo.zzh(var4, "8.3 and 8.4 SDKs require non-null userId");
   }

   public final GoogleSignInAccount zzmD() {
      return this.zzamh;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.versionCode);
      zzd.zza(var1, 4, this.zzalO, false);
      zzd.zza(var1, 7, this.zzamh, var2, false);
      zzd.zza(var1, 8, this.zzafe, false);
      zzd.zzI(var1, var5);
   }
}
