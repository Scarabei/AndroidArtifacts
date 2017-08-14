package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzasu extends zza implements BeaconState.BeaconInfo {
   public static final Creator CREATOR = new zzass();
   private final String zzanR;
   private final String zzVB;
   private final byte[] zzanS;

   public zzasu(String var1, String var2, byte[] var3) {
      this.zzanR = zzbo.zzcF(var1);
      this.zzVB = zzbo.zzcF(var2);
      this.zzanS = var3;
   }

   public final String getNamespace() {
      return this.zzanR;
   }

   public final String getType() {
      return this.zzVB;
   }

   public final byte[] getContent() {
      return this.zzanS;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.getNamespace(), false);
      zzd.zza(var1, 3, this.getType(), false);
      zzd.zza(var1, 4, this.getContent(), false);
      zzd.zzI(var1, var5);
   }

   public final String toString() {
      String var1 = this.zzanS == null ? "<null>" : new String(this.zzanS);
      String var2 = this.zzanR;
      String var3 = this.zzVB;
      return (new StringBuilder(6 + String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var1).length())).append("(").append(var2).append(", ").append(var3).append(", ").append(var1).append(")").toString();
   }
}
