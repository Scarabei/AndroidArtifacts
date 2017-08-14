package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SessionStopResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   private final int zzaku;
   private final Status mStatus;
   private final List zzaWs;
   public static final Creator CREATOR = new zzi();

   SessionStopResult(int var1, Status var2, List var3) {
      this.zzaku = var1;
      this.mStatus = var2;
      this.zzaWs = Collections.unmodifiableList(var3);
   }

   public SessionStopResult(Status var1, List var2) {
      this.zzaku = 3;
      this.mStatus = var1;
      this.zzaWs = Collections.unmodifiableList(var2);
   }

   public Status getStatus() {
      return this.mStatus;
   }

   public List getSessions() {
      return this.zzaWs;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof SessionStopResult) {
            SessionStopResult var3 = (SessionStopResult)var1;
            if (this.mStatus.equals(var3.mStatus) && zzbe.equal(this.zzaWs, var3.zzaWs)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mStatus, this.zzaWs});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("status", this.mStatus).zzg("sessions", this.zzaWs).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getSessions(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
