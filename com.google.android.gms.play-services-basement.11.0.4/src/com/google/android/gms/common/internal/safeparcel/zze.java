package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;

public final class zze {
   public static byte[] zza(SafeParcelable var0) {
      Parcel var1 = Parcel.obtain();
      var0.writeToParcel(var1, 0);
      byte[] var2 = var1.marshall();
      var1.recycle();
      return var2;
   }

   public static SafeParcelable zza(byte[] var0, Creator var1) {
      zzbo.zzu(var1);
      Parcel var2;
      (var2 = Parcel.obtain()).unmarshall(var0, 0, var0.length);
      var2.setDataPosition(0);
      SafeParcelable var3 = (SafeParcelable)var1.createFromParcel(var2);
      var2.recycle();
      return var3;
   }

   public static void zza(SafeParcelable var0, Intent var1, String var2) {
      var1.putExtra(var2, zza(var0));
   }

   public static SafeParcelable zza(Intent var0, String var1, Creator var2) {
      byte[] var3;
      return (var3 = var0.getByteArrayExtra(var1)) == null ? null : zza(var3, var2);
   }
}
