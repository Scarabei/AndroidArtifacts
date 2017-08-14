package com.google.android.gms.safetynet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;

public class SafeBrowsingData extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzh();
   private String zzbBI;
   private DataHolder zzbBJ;

   public SafeBrowsingData(String var1, DataHolder var2) {
      this.zzbBI = var1;
      this.zzbBJ = var2;
   }

   public SafeBrowsingData(String var1) {
      this(var1, (DataHolder)null);
   }

   public String getMetadata() {
      return this.zzbBI;
   }

   public DataHolder getBlacklistsDataHolder() {
      return this.zzbBJ;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getMetadata(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getBlacklistsDataHolder(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
