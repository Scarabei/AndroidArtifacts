package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class zzc extends com.google.android.gms.common.internal.safeparcel.zza implements zza {
   public static final Creator CREATOR = new zzb();
   private final String zzapa;
   private final List zzbzU;
   private final String zzbzV;
   private final Long zzbzW;
   private final Long zzbzX;
   private List zzbzY;

   public zzc(String var1, List var2, String var3, Long var4, Long var5) {
      this.zzapa = var1;
      this.zzbzU = var2;
      this.zzbzV = var3;
      this.zzbzW = var4;
      this.zzbzX = var5;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzapa, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzzY(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbzV, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbzW, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbzX, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean isDataValid() {
      return true;
   }

   public final String getDeviceId() {
      return this.zzapa;
   }

   public final List zzzY() {
      if (this.zzbzY == null && this.zzbzU != null) {
         this.zzbzY = new ArrayList(this.zzbzU.size());
         Iterator var1 = this.zzbzU.iterator();

         while(var1.hasNext()) {
            zze var2 = (zze)var1.next();
            this.zzbzY.add(var2);
         }
      }

      return this.zzbzY;
   }

   public final String zzzZ() {
      return this.zzbzV;
   }

   public final Long zzAa() {
      return this.zzbzW;
   }

   public final Long zzAb() {
      return this.zzbzX;
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zza)) {
         return false;
      } else if (this == var1) {
         return true;
      } else {
         zza var3 = (zza)var1;
         return zzbe.equal(this.getDeviceId(), var3.getDeviceId()) && zzbe.equal(this.zzzY(), var3.zzzY()) && zzbe.equal(this.zzzZ(), var3.zzzZ()) && zzbe.equal(this.zzAa(), var3.zzAa()) && zzbe.equal(this.zzAb(), var3.zzAb());
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.getDeviceId(), this.zzzY(), this.zzzZ(), this.zzAa(), this.zzAb()});
   }
}
