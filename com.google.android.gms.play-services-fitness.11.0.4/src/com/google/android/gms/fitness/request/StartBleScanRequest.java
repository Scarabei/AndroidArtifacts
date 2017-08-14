package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;
import java.util.Collections;
import java.util.List;

public class StartBleScanRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final List zzaTn;
   private final zzad zzaXp;
   private final int zzaXq;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzbf();

   StartBleScanRequest(int var1, List var2, IBinder var3, int var4, IBinder var5) {
      this.zzaku = var1;
      this.zzaTn = var2;
      IInterface var7;
      this.zzaXp = (zzad)(var3 == null ? null : ((var7 = var3.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback")) instanceof zzad ? (zzad)var7 : new zzaf(var3)));
      this.zzaXq = var4;
      this.zzaWo = zzbxh.zzW(var5);
   }

   private StartBleScanRequest(StartBleScanRequest.Builder var1) {
      this(com.google.android.gms.common.util.zzb.zza(var1.zzaWJ), var1.zzaXp, var1.zzaXq, (zzbxg)null);
   }

   public StartBleScanRequest(StartBleScanRequest var1, zzbxg var2) {
      this(var1.zzaTn, var1.zzaXp, var1.zzaXq, var2);
   }

   private StartBleScanRequest(List var1, zzad var2, int var3, zzbxg var4) {
      this.zzaku = 4;
      this.zzaTn = var1;
      this.zzaXp = var2;
      this.zzaXq = var3;
      this.zzaWo = var4;
   }

   public List getDataTypes() {
      return Collections.unmodifiableList(this.zzaTn);
   }

   public int getTimeoutSecs() {
      return this.zzaXq;
   }

   public String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("dataTypes", this.zzaTn).zzg("timeoutSecs", this.zzaXq).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getDataTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaXp.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getTimeoutSecs());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   StartBleScanRequest(StartBleScanRequest.Builder var1, zzbe var2) {
      this(var1);
   }

   public static class Builder {
      private DataType[] zzaWJ = new DataType[0];
      private zzad zzaXp;
      private int zzaXq = 10;

      public StartBleScanRequest.Builder setDataTypes(DataType... var1) {
         this.zzaWJ = var1;
         return this;
      }

      public StartBleScanRequest.Builder setBleScanCallback(BleScanCallback var1) {
         this.zzaXp = zzc.zztT().zza(var1);
         return this;
      }

      public StartBleScanRequest.Builder setTimeoutSecs(int var1) {
         zzbo.zzb(var1 > 0, "Stop time must be greater than zero");
         zzbo.zzb(var1 <= 60, "Stop time must be less than 1 minute");
         this.zzaXq = var1;
         return this;
      }

      public StartBleScanRequest build() {
         zzbo.zza(this.zzaXp != null, "Must set BleScanCallback");
         return new StartBleScanRequest(this, (zzbe)null);
      }
   }
}
