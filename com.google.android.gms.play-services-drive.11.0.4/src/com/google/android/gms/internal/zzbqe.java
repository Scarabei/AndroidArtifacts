package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.query.internal.FilterHolder;

public final class zzbqe extends zza {
   public static final Creator CREATOR = new zzbqf();
   private String zzaoy;
   private String[] zzaMC;
   private DriveId zzaME;
   private FilterHolder zzaPq;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaoy, false);
      zzd.zza(var1, 3, this.zzaMC, false);
      zzd.zza(var1, 4, this.zzaME, var2, false);
      zzd.zza(var1, 5, this.zzaPq, var2, false);
      zzd.zzI(var1, var5);
   }

   public zzbqe(String var1, String[] var2, DriveId var3, FilterHolder var4) {
      this.zzaoy = var1;
      this.zzaMC = var2;
      this.zzaME = var3;
      this.zzaPq = var4;
   }
}
