package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzare;

public final class IdToken extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzh();
   private int zzaku;
   @NonNull
   private final String zzakU;
   @NonNull
   private final String zzaln;

   IdToken(int var1, @NonNull String var2, @NonNull String var3) {
      zzare.zzbN(var2);
      zzbo.zzb(!TextUtils.isEmpty(var3), "id token string cannot be null or empty");
      this.zzaku = var1;
      this.zzakU = var2;
      this.zzaln = var3;
   }

   public IdToken(@NonNull String var1, @NonNull String var2) {
      this(1, var1, var2);
   }

   @NonNull
   public final String getAccountType() {
      return this.zzakU;
   }

   @NonNull
   public final String getIdToken() {
      return this.zzaln;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getAccountType(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getIdToken(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
