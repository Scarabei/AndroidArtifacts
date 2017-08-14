package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BleDevicesResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   private final int zzaku;
   private final List zzaXt;
   private final Status mStatus;
   public static final Creator CREATOR = new zza();

   BleDevicesResult(int var1, List var2, Status var3) {
      this.zzaku = var1;
      this.zzaXt = Collections.unmodifiableList(var2);
      this.mStatus = var3;
   }

   private BleDevicesResult(List var1, Status var2) {
      this.zzaku = 3;
      this.zzaXt = Collections.unmodifiableList(var1);
      this.mStatus = var2;
   }

   public static BleDevicesResult zzB(Status var0) {
      return new BleDevicesResult(Collections.emptyList(), var0);
   }

   public List getClaimedBleDevices() {
      return this.zzaXt;
   }

   public List getClaimedBleDevices(DataType var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.zzaXt.iterator();

      while(var3.hasNext()) {
         BleDevice var4;
         if ((var4 = (BleDevice)var3.next()).getDataTypes().contains(var1)) {
            var2.add(var4);
         }
      }

      return Collections.unmodifiableList(var2);
   }

   public Status getStatus() {
      return this.mStatus;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof BleDevicesResult) {
            BleDevicesResult var3 = (BleDevicesResult)var1;
            if (this.mStatus.equals(var3.mStatus) && zzbe.equal(this.zzaXt, var3.zzaXt)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mStatus, this.zzaXt});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("status", this.mStatus).zzg("bleDevices", this.zzaXt).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getClaimedBleDevices(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
