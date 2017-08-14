package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;
import java.util.List;

public class TokenData extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzj();
   private int zzaku;
   private final String zzakv;
   private final Long zzakw;
   private final boolean zzakx;
   private final boolean zzaky;
   private final List zzakz;

   @Nullable
   public static TokenData zzd(Bundle var0, String var1) {
      var0.setClassLoader(TokenData.class.getClassLoader());
      Bundle var2;
      if ((var2 = var0.getBundle(var1)) == null) {
         return null;
      } else {
         var2.setClassLoader(TokenData.class.getClassLoader());
         return (TokenData)var2.getParcelable("TokenData");
      }
   }

   TokenData(int var1, String var2, Long var3, boolean var4, boolean var5, List var6) {
      this.zzaku = var1;
      this.zzakv = zzbo.zzcF(var2);
      this.zzakw = var3;
      this.zzakx = var4;
      this.zzaky = var5;
      this.zzakz = var6;
   }

   public final String getToken() {
      return this.zzakv;
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof TokenData)) {
         return false;
      } else {
         TokenData var2 = (TokenData)var1;
         return TextUtils.equals(this.zzakv, var2.zzakv) && zzbe.equal(this.zzakw, var2.zzakw) && this.zzakx == var2.zzakx && this.zzaky == var2.zzaky && zzbe.equal(this.zzakz, var2.zzakz);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzakv, this.zzakw, this.zzakx, this.zzaky, this.zzakz});
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzakv, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzakw, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzakx);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzaky);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 6, this.zzakz, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
