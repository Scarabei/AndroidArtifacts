package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.internal.zzbud;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BleDevice extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   private final int zzaku;
   private final String zzaTl;
   private final String mName;
   private final List zzaTm;
   private final List zzaTn;
   public static final Creator CREATOR = new zzd();

   BleDevice(int var1, String var2, String var3, List var4, List var5) {
      this.zzaku = var1;
      this.zzaTl = var2;
      this.mName = var3;
      this.zzaTm = Collections.unmodifiableList(var4);
      this.zzaTn = Collections.unmodifiableList(var5);
   }

   public String getAddress() {
      return this.zzaTl;
   }

   public String getName() {
      return this.mName;
   }

   public List getSupportedProfiles() {
      return this.zzaTm;
   }

   public List getDataTypes() {
      return this.zzaTn;
   }

   public boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof BleDevice) {
            BleDevice var3 = (BleDevice)var1;
            if (this.mName.equals(var3.mName) && this.zzaTl.equals(var3.zzaTl) && zzbud.zza(var3.zzaTm, this.zzaTm) && zzbud.zza(this.zzaTn, var3.zzaTn)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mName, this.zzaTl, this.zzaTm, this.zzaTn});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("name", this.mName).zzg("address", this.zzaTl).zzg("dataTypes", this.zzaTn).zzg("supportedProfiles", this.zzaTm).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getAddress(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 3, this.getSupportedProfiles(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getDataTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
