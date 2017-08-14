package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzn extends zza {
   public static final Creator CREATOR = new zzm();
   private int versionCode;
   private int zzamr;
   private Bundle mBundle;

   zzn(int var1, int var2, Bundle var3) {
      this.versionCode = var1;
      this.zzamr = var2;
      this.mBundle = var3;
   }

   public zzn(GoogleSignInOptionsExtension var1) {
      this(1, 1, var1.toBundle());
   }

   public final int getType() {
      return this.zzamr;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.versionCode);
      zzd.zzc(var1, 2, this.zzamr);
      zzd.zza(var1, 3, this.mBundle, false);
      zzd.zzI(var1, var5);
   }
}
