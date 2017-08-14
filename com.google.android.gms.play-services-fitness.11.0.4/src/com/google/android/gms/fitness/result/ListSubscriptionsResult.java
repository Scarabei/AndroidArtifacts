package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListSubscriptionsResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   private final int zzaku;
   private final List zzaXA;
   private final Status mStatus;
   public static final Creator CREATOR = new zzg();

   ListSubscriptionsResult(int var1, List var2, Status var3) {
      this.zzaku = var1;
      this.zzaXA = var2;
      this.mStatus = var3;
   }

   private ListSubscriptionsResult(List var1, Status var2) {
      this.zzaku = 3;
      this.zzaXA = Collections.unmodifiableList(var1);
      this.mStatus = (Status)zzbo.zzb(var2, "status");
   }

   public static ListSubscriptionsResult zzD(Status var0) {
      return new ListSubscriptionsResult(Collections.emptyList(), var0);
   }

   public List getSubscriptions() {
      return this.zzaXA;
   }

   public List getSubscriptions(DataType var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.zzaXA.iterator();

      while(var3.hasNext()) {
         Subscription var4 = (Subscription)var3.next();
         if (var1.equals(var4.zztP())) {
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
         if (var1 instanceof ListSubscriptionsResult) {
            ListSubscriptionsResult var3 = (ListSubscriptionsResult)var1;
            if (this.mStatus.equals(var3.mStatus) && zzbe.equal(this.zzaXA, var3.zzaXA)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mStatus, this.zzaXA});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("status", this.mStatus).zzg("subscriptions", this.zzaXA).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getSubscriptions(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
