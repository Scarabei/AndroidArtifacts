package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzbgo extends zza {
   public static final Creator CREATOR = new zzbgr();
   private int zzaku;
   private final HashMap zzaIR;
   private final ArrayList zzaIS;
   private final String zzaIT;

   zzbgo(int var1, ArrayList var2, String var3) {
      this.zzaku = var1;
      this.zzaIS = null;
      ArrayList var4 = var2;
      HashMap var5 = new HashMap();
      int var6 = var2.size();

      for(int var7 = 0; var7 < var6; ++var7) {
         zzbgp var8 = (zzbgp)var4.get(var7);
         var5.put(var8.className, var8.zzrS());
      }

      this.zzaIR = var5;
      this.zzaIT = (String)zzbo.zzu(var3);
      this.zzrQ();
   }

   private final void zzrQ() {
      Iterator var1 = this.zzaIR.keySet().iterator();

      while(var1.hasNext()) {
         String var2 = (String)var1.next();
         Map var3;
         Iterator var4 = (var3 = (Map)this.zzaIR.get(var2)).keySet().iterator();

         while(var4.hasNext()) {
            String var5 = (String)var4.next();
            ((zzbgj)var3.get(var5)).zza(this);
         }
      }

   }

   public final Map zzcJ(String var1) {
      return (Map)this.zzaIR.get(var1);
   }

   public final String zzrR() {
      return this.zzaIT;
   }

   public final String toString() {
      StringBuilder var1 = new StringBuilder();
      Iterator var2 = this.zzaIR.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         var1.append(var3).append(":\n");
         Map var4;
         Iterator var5 = (var4 = (Map)this.zzaIR.get(var3)).keySet().iterator();

         while(var5.hasNext()) {
            String var6 = (String)var5.next();
            var1.append("  ").append(var6).append(": ");
            var1.append(var4.get(var6));
         }
      }

      return var1.toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzbgo var6 = this;
      ArrayList var7 = new ArrayList();
      Iterator var8 = this.zzaIR.keySet().iterator();

      while(var8.hasNext()) {
         String var9 = (String)var8.next();
         zzbgp var10 = new zzbgp(var9, (Map)var6.zzaIR.get(var9));
         var7.add(var10);
      }

      zzd.zzc(var1, 2, var7, false);
      zzd.zza(var1, 3, this.zzaIT, false);
      zzd.zzI(var1, var5);
   }
}
