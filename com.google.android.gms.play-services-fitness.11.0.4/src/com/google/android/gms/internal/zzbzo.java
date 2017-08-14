package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Collections;
import java.util.List;

public final class zzbzo extends zza {
   private final int zzaku;
   private final List zzaTn;
   public static final Creator CREATOR = new zzbzp();

   zzbzo(int var1, List var2) {
      this.zzaku = var1;
      this.zzaTn = var2;
   }

   public final List getDataTypes() {
      return Collections.unmodifiableList(this.zzaTn);
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("dataTypes", this.zzaTn).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, Collections.unmodifiableList(this.zzaTn), false);
      zzd.zzc(var1, 1000, this.zzaku);
      zzd.zzI(var1, var5);
   }
}
