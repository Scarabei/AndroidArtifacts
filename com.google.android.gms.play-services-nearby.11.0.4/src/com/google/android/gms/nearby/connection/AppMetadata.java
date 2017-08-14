package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import java.util.List;

/** @deprecated */
@Deprecated
public final class AppMetadata extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzc();
   private final List zzbwn;

   public AppMetadata(List var1) {
      this.zzbwn = (List)zzbo.zzb(var1, "Must specify application identifiers");
      int var10000 = var1.size();
      String var2 = "Application identifiers cannot be empty";
      if (var10000 == 0) {
         throw new IllegalArgumentException(String.valueOf(var2));
      }
   }

   public final List getAppIdentifiers() {
      return this.zzbwn;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getAppIdentifiers(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
