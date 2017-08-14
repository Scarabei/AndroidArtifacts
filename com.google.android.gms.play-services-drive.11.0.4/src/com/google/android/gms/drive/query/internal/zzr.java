package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.query.Filter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzr extends zza {
   public static final Creator CREATOR = new zzs();
   private zzx zzaRd;
   private List zzaRs;
   private List zzaQX;

   zzr(zzx var1, List var2) {
      this.zzaRd = var1;
      this.zzaRs = var2;
   }

   public zzr(zzx var1, Iterable var2) {
      this.zzaRd = var1;
      this.zzaQX = new ArrayList();
      this.zzaRs = new ArrayList();
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         Filter var4 = (Filter)var3.next();
         this.zzaQX.add(var4);
         this.zzaRs.add(new FilterHolder(var4));
      }

   }

   public zzr(zzx var1, Filter var2, Filter... var3) {
      this.zzaRd = var1;
      this.zzaRs = new ArrayList(var3.length + 1);
      this.zzaRs.add(new FilterHolder(var2));
      this.zzaQX = new ArrayList(var3.length + 1);
      this.zzaQX.add(var2);
      Filter[] var4 = var3;
      int var5 = var3.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         Filter var7 = var4[var6];
         this.zzaRs.add(new FilterHolder(var7));
         this.zzaQX.add(var7);
      }

   }

   public final Object zza(zzj var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.zzaRs.iterator();

      while(var3.hasNext()) {
         FilterHolder var4 = (FilterHolder)var3.next();
         var2.add(var4.getFilter().zza(var1));
      }

      return var1.zza((zzx)this.zzaRd, (List)var2);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaRd, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzaRs, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
