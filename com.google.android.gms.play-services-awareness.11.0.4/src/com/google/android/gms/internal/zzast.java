package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;
import java.util.List;

public final class zzast extends zza implements BeaconState {
   public static final Creator CREATOR = new zzasw();
   private final ArrayList zzanQ;

   public zzast(ArrayList var1) {
      this.zzanQ = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzanQ, false);
      zzd.zzI(var1, var5);
   }

   public final List getBeaconInfo() {
      return this.zzanQ;
   }

   public final String toString() {
      if (this.zzanQ != null && !this.zzanQ.isEmpty()) {
         StringBuilder var1;
         (var1 = new StringBuilder()).append("BeaconState: ");
         ArrayList var3;
         int var4 = (var3 = (ArrayList)this.zzanQ).size();
         int var5 = 0;

         while(var5 < var4) {
            Object var10000 = var3.get(var5);
            ++var5;
            BeaconState.BeaconInfo var2 = (BeaconState.BeaconInfo)var10000;
            var1.append(var2);
         }

         return var1.toString();
      } else {
         return "BeaconState: empty";
      }
   }
}
