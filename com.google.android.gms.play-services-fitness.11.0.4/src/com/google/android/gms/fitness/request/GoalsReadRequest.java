package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbuf;
import com.google.android.gms.internal.zzbwe;
import com.google.android.gms.internal.zzbwf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GoalsReadRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int versionCode;
   private final zzbwe zzaWN;
   private final List zzaWO;
   private final List zzaWP;
   private final List zzaUJ;
   public static final Creator CREATOR = new zzac();

   public List getDataTypes() {
      return this.zzaWO;
   }

   public List getObjectiveTypes() {
      return this.zzaWP.isEmpty() ? null : this.zzaWP;
   }

   public List getActivityNames() {
      if (this.zzaUJ.isEmpty()) {
         return null;
      } else {
         ArrayList var1 = new ArrayList();
         Iterator var2 = this.zzaUJ.iterator();

         while(var2.hasNext()) {
            int var3 = ((Integer)var2.next()).intValue();
            var1.add(com.google.android.gms.fitness.zza.getName(var3));
         }

         return var1;
      }
   }

   GoalsReadRequest(int var1, IBinder var2, List var3, List var4, List var5) {
      this.versionCode = var1;
      this.zzaWN = var2 == null ? null : zzbwf.zzS(var2);
      this.zzaWO = var3;
      this.zzaWP = var4;
      this.zzaUJ = var5;
   }

   private GoalsReadRequest(GoalsReadRequest.Builder var1) {
      this((zzbwe)null, var1.zzaWO, var1.zzaWP, var1.zzaUJ);
   }

   public GoalsReadRequest(GoalsReadRequest var1, zzbwe var2) {
      this(var2, var1.getDataTypes(), var1.zzaWP, var1.zzaUJ);
   }

   private GoalsReadRequest(zzbwe var1, List var2, List var3, List var4) {
      this(1, var1 == null ? null : var1.asBinder(), var2, var3, var4);
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof GoalsReadRequest) {
            GoalsReadRequest var3 = (GoalsReadRequest)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.zzaWO, var3.zzaWO) && com.google.android.gms.common.internal.zzbe.equal(this.zzaWP, var3.zzaWP) && com.google.android.gms.common.internal.zzbe.equal(this.zzaUJ, var3.zzaUJ)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaWO, this.zzaWP, this.getActivityNames()});
   }

   public String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("dataTypes", this.zzaWO).zzg("objectiveTypes", this.zzaWP).zzg("activities", this.getActivityNames()).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaWN.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzd(var1, 2, this.getDataTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzd(var1, 3, this.zzaWP, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzd(var1, 4, this.zzaUJ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   GoalsReadRequest(GoalsReadRequest.Builder var1, zzab var2) {
      this(var1);
   }

   public static class Builder {
      private final List zzaWO = new ArrayList();
      private final List zzaWP = new ArrayList();
      private final List zzaUJ = new ArrayList();

      public GoalsReadRequest.Builder addDataType(DataType var1) {
         zzbo.zzb(var1, "Attempting to use a null data type");
         if (!this.zzaWO.contains(var1)) {
            this.zzaWO.add(var1);
         }

         return this;
      }

      public GoalsReadRequest.Builder addActivity(String var1) {
         int var2;
         zzbo.zza((var2 = com.google.android.gms.fitness.zza.zzcW(var1)) != 4, "Attempting to add an unknown activity");
         zzbuf.zza(var2, this.zzaUJ);
         return this;
      }

      public GoalsReadRequest.Builder addObjectiveType(int var1) {
         zzbo.zza(var1 == 1 || var1 == 2 || var1 == 3, "Attempting to add an invalid objective type");
         if (!this.zzaWP.contains(var1)) {
            this.zzaWP.add(var1);
         }

         return this;
      }

      public GoalsReadRequest build() {
         zzbo.zza(!this.zzaWO.isEmpty(), "At least one data type should be specified.");
         return new GoalsReadRequest(this, (zzab)null);
      }
   }
}
