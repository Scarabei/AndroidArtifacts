package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public class AccountChangeEventsResponse extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzc();
   private int mVersion;
   private List zztH;

   AccountChangeEventsResponse(int var1, List var2) {
      this.mVersion = var1;
      this.zztH = (List)zzbo.zzu(var2);
   }

   public AccountChangeEventsResponse(List var1) {
      this.mVersion = 1;
      this.zztH = (List)zzbo.zzu(var1);
   }

   public List getEvents() {
      return this.zztH;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.mVersion);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zztH, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
