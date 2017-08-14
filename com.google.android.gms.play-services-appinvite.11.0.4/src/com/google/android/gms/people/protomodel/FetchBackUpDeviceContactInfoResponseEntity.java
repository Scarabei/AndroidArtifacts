package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FetchBackUpDeviceContactInfoResponseEntity extends com.google.android.gms.common.internal.safeparcel.zza implements FetchBackUpDeviceContactInfoResponse {
   public static final Creator CREATOR = new zzd();
   private int zzaku;
   private final List zzbzZ;
   private List zzbAa;

   FetchBackUpDeviceContactInfoResponseEntity(int var1, List var2) {
      this.zzbzZ = var2;
      this.zzaku = var1;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzAc(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public boolean isDataValid() {
      return true;
   }

   public final List zzAc() {
      if (this.zzbAa == null && this.zzbzZ != null) {
         this.zzbAa = new ArrayList(this.zzbzZ.size());
         Iterator var1 = this.zzbzZ.iterator();

         while(var1.hasNext()) {
            zza var2 = (zza)var1.next();
            this.zzbAa.add(var2);
         }
      }

      return this.zzbAa;
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof FetchBackUpDeviceContactInfoResponse)) {
         return false;
      } else if (this == var1) {
         return true;
      } else {
         FetchBackUpDeviceContactInfoResponse var2 = (FetchBackUpDeviceContactInfoResponse)var1;
         return zzbe.equal(this.zzAc(), var2.zzAc());
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzAc()});
   }
}
