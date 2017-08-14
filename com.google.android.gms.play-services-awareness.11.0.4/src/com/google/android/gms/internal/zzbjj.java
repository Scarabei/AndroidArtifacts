package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;

public final class zzbjj extends zza implements FenceUpdateRequest {
   public static final Creator CREATOR = new zzbjk();
   public final ArrayList zzaLj;

   public zzbjj(ArrayList var1) {
      this.zzaLj = var1;
   }

   /** @deprecated */
   @Deprecated
   public zzbjj() {
      this(new ArrayList());
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzaLj, false);
      zzd.zzI(var1, var5);
   }
}
