package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Collections;
import java.util.List;

public final class zzae extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzaf();
   private final List zzbjj;
   private final String zzbjk;
   private final Uri zzbjl;
   private final float zzbkx;
   private final int zzbky;

   zzae(List var1, String var2, Uri var3, float var4, int var5) {
      this.zzbjj = Collections.unmodifiableList(var1);
      this.zzbjk = var2;
      this.zzbjl = var3;
      this.zzbkx = var4;
      this.zzbky = var5;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzbjj, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbjk, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbjl, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbkx);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzbky);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
