package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd extends AbstractDataBuffer {
   private static final String[] zzaFz = new String[]{"data"};
   private final Creator zzaFA;

   public zzd(DataHolder var1, Creator var2) {
      super(var1);
      this.zzaFA = var2;
   }

   public static DataHolder.zza zzqQ() {
      return DataHolder.zza(zzaFz);
   }

   public static void zza(DataHolder.zza var0, SafeParcelable var1) {
      Parcel var2 = Parcel.obtain();
      var1.writeToParcel(var2, 0);
      ContentValues var3;
      (var3 = new ContentValues()).put("data", var2.marshall());
      var0.zza(var3);
      var2.recycle();
   }

   public SafeParcelable zzas(int var1) {
      byte[] var2 = this.zzaCX.zzg("data", var1, this.zzaCX.zzat(var1));
      Parcel var3;
      (var3 = Parcel.obtain()).unmarshall(var2, 0, var2.length);
      var3.setDataPosition(0);
      SafeParcelable var4 = (SafeParcelable)this.zzaFA.createFromParcel(var3);
      var3.recycle();
      return var4;
   }

   // $FF: synthetic method
   public Object get(int var1) {
      return this.zzas(var1);
   }
}
