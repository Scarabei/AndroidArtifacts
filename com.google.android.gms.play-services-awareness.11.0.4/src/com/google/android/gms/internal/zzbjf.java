package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.awareness.fence.FenceState;
import com.google.android.gms.awareness.fence.FenceStateMap;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.common.internal.safeparcel.zze;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public final class zzbjf extends zza implements FenceStateMap {
   public static final Creator CREATOR = new zzbjg();
   private Map zzaLh = new HashMap();

   zzbjf(Bundle var1) {
      if (var1 != null) {
         Iterator var2 = var1.keySet().iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            this.zzaLh.put(var3, (zzbjd)zze.zza(var1.getByteArray(var3), zzbjd.CREATOR));
         }
      }

   }

   public final Set getFenceKeys() {
      return this.zzaLh.keySet();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      Bundle var10002;
      if (this.zzaLh == null) {
         var10002 = null;
      } else {
         Bundle var7 = new Bundle();
         Iterator var8 = this.zzaLh.entrySet().iterator();

         while(var8.hasNext()) {
            Entry var9 = (Entry)var8.next();
            var7.putByteArray((String)var9.getKey(), zze.zza((zzbjd)var9.getValue()));
         }

         var10002 = var7;
      }

      zzd.zza(var1, 2, var10002, false);
      zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   public final FenceState getFenceState(String var1) {
      return this.zzaLh.containsKey(var1) ? (zzbjd)this.zzaLh.get(var1) : null;
   }
}
