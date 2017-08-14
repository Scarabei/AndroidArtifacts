package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbt extends zza {
   public static final Creator CREATOR = new zzbu();
   private int zzaku;
   private final int zzaIs;
   private final int zzaIt;
   /** @deprecated */
   @Deprecated
   private final Scope[] zzaIu;

   zzbt(int var1, int var2, int var3, Scope[] var4) {
      this.zzaku = var1;
      this.zzaIs = var2;
      this.zzaIt = var3;
      this.zzaIu = var4;
   }

   public zzbt(int var1, int var2, Scope[] var3) {
      this(1, var1, var2, (Scope[])null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zzc(var1, 2, this.zzaIs);
      zzd.zzc(var1, 3, this.zzaIt);
      zzd.zza(var1, 4, this.zzaIu, var2, false);
      zzd.zzI(var1, var5);
   }
}
