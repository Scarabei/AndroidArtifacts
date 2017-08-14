package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.zzae;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SessionReadResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   private final int zzaku;
   private final List zzaWs;
   private final List zzaXB;
   private final Status mStatus;
   public static final Creator CREATOR = new zzh();

   SessionReadResult(int var1, List var2, List var3, Status var4) {
      this.zzaku = var1;
      this.zzaWs = var2;
      this.zzaXB = Collections.unmodifiableList(var3);
      this.mStatus = var4;
   }

   private SessionReadResult(List var1, List var2, Status var3) {
      this.zzaku = 3;
      this.zzaWs = var1;
      this.zzaXB = Collections.unmodifiableList(var2);
      this.mStatus = var3;
   }

   public static SessionReadResult zzE(Status var0) {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      return new SessionReadResult(var1, var2, var0);
   }

   public List getSessions() {
      return this.zzaWs;
   }

   public List getDataSet(Session var1, DataType var2) {
      zzbo.zzb(this.zzaWs.contains(var1), "Attempting to read data for session %s which was not returned", new Object[]{var1});
      ArrayList var3 = new ArrayList();
      Iterator var4 = this.zzaXB.iterator();

      while(var4.hasNext()) {
         zzae var5 = (zzae)var4.next();
         if (zzbe.equal(var1, var5.getSession()) && var2.equals(var5.getDataSet().getDataType())) {
            var3.add(var5.getDataSet());
         }
      }

      return var3;
   }

   public List getDataSet(Session var1) {
      zzbo.zzb(this.zzaWs.contains(var1), "Attempting to read data for session %s which was not returned", new Object[]{var1});
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.zzaXB.iterator();

      while(var3.hasNext()) {
         zzae var4 = (zzae)var3.next();
         if (zzbe.equal(var1, var4.getSession())) {
            var2.add(var4.getDataSet());
         }
      }

      return var2;
   }

   public Status getStatus() {
      return this.mStatus;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof SessionReadResult) {
            SessionReadResult var3 = (SessionReadResult)var1;
            if (this.mStatus.equals(var3.mStatus) && zzbe.equal(this.zzaWs, var3.zzaWs) && zzbe.equal(this.zzaXB, var3.zzaXB)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mStatus, this.zzaWs, this.zzaXB});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("status", this.mStatus).zzg("sessions", this.zzaWs).zzg("sessionDataSets", this.zzaXB).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getSessions(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzaXB, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
