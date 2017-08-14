package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.awareness.fence.FenceQueryRequest;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class zzbja extends FenceQueryRequest {
   public static final Creator CREATOR = new zzbjc();
   private zzbjb zzaLb;

   zzbja(zzbjb var1) {
      this.zzaLb = var1;
   }

   public zzbja() {
      this(zzbjb.zza(1, (List)null));
   }

   public zzbja(Collection var1) {
      this(zzbjb.zza(2, new ArrayList(var1)));
   }

   public zzbja(String... var1) {
      this(zzbjb.zza(2, Arrays.asList(var1)));
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaLb, var2, false);
      zzd.zzI(var1, var5);
   }
}
