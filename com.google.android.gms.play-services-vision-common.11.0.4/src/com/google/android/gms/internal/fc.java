package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.vision.Frame;

public final class fc extends zza {
   public static final Creator CREATOR = new fd();
   public int width;
   public int height;
   private int id;
   private long zzbiv;
   public int rotation;

   public fc() {
   }

   public fc(int var1, int var2, int var3, long var4, int var6) {
      this.width = var1;
      this.height = var2;
      this.id = var3;
      this.zzbiv = var4;
      this.rotation = var6;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.width);
      zzd.zzc(var1, 3, this.height);
      zzd.zzc(var1, 4, this.id);
      zzd.zza(var1, 5, this.zzbiv);
      zzd.zzc(var1, 6, this.rotation);
      zzd.zzI(var1, var5);
   }

   public static fc zzc(Frame var0) {
      fc var1;
      (var1 = new fc()).width = var0.getMetadata().getWidth();
      var1.height = var0.getMetadata().getHeight();
      var1.rotation = var0.getMetadata().getRotation();
      var1.id = var0.getMetadata().getId();
      var1.zzbiv = var0.getMetadata().getTimestampMillis();
      return var1;
   }
}
