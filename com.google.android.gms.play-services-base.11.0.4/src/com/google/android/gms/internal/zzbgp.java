package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzbgp extends zza {
   public static final Creator CREATOR = new zzbgs();
   private int versionCode;
   final String className;
   private ArrayList zzaIU;

   zzbgp(int var1, String var2, ArrayList var3) {
      this.versionCode = var1;
      this.className = var2;
      this.zzaIU = var3;
   }

   zzbgp(String var1, Map var2) {
      this.versionCode = 1;
      this.className = var1;
      Map var3 = var2;
      ArrayList var10001;
      if (var2 == null) {
         var10001 = null;
      } else {
         ArrayList var4 = new ArrayList();
         Iterator var5 = var2.keySet().iterator();

         while(var5.hasNext()) {
            String var6 = (String)var5.next();
            zzbgq var7 = new zzbgq(var6, (zzbgj)var3.get(var6));
            var4.add(var7);
         }

         var10001 = var4;
      }

      this.zzaIU = var10001;
   }

   final HashMap zzrS() {
      HashMap var1 = new HashMap();
      int var2 = this.zzaIU.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         zzbgq var4 = (zzbgq)this.zzaIU.get(var3);
         var1.put(var4.key, var4.zzaIV);
      }

      return var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.versionCode);
      zzd.zza(var1, 2, this.className, false);
      zzd.zzc(var1, 3, this.zzaIU, false);
      zzd.zzI(var1, var5);
   }
}
