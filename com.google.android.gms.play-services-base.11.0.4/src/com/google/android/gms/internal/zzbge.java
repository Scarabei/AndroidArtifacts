package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class zzbge extends zza implements zzbgk {
   public static final Creator CREATOR = new zzbgg();
   private int zzaku;
   private final HashMap zzaIC;
   private final SparseArray zzaID;
   private final ArrayList zzaIE;

   zzbge(int var1, ArrayList var2) {
      this.zzaku = var1;
      this.zzaIC = new HashMap();
      this.zzaID = new SparseArray();
      this.zzaIE = null;
      this.zzd(var2);
   }

   public zzbge() {
      this.zzaku = 1;
      this.zzaIC = new HashMap();
      this.zzaID = new SparseArray();
      this.zzaIE = null;
   }

   private final void zzd(ArrayList var1) {
      ArrayList var3;
      int var4 = (var3 = (ArrayList)var1).size();
      int var5 = 0;

      while(var5 < var4) {
         Object var10000 = var3.get(var5);
         ++var5;
         zzbgf var2 = (zzbgf)var10000;
         this.zzi(var2.zzaIF, var2.zzaIG);
      }

   }

   public final zzbge zzi(String var1, int var2) {
      this.zzaIC.put(var1, var2);
      this.zzaID.put(var2, var1);
      return this;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzbge var6 = this;
      ArrayList var7 = new ArrayList();
      Iterator var8 = this.zzaIC.keySet().iterator();

      while(var8.hasNext()) {
         String var9 = (String)var8.next();
         var7.add(new zzbgf(var9, ((Integer)var6.zzaIC.get(var9)).intValue()));
      }

      zzd.zzc(var1, 2, var7, false);
      zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   public final Object convertBack(Object var1) {
      Integer var3 = (Integer)var1;
      String var4;
      return (var4 = (String)this.zzaID.get(var3.intValue())) == null && this.zzaIC.containsKey("gms_unknown") ? "gms_unknown" : var4;
   }
}
