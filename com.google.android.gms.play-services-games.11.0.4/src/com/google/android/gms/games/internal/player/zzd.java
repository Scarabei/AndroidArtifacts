package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;

public final class zzd extends com.google.android.gms.common.data.zzc implements zza {
   private final zze zzaYZ;

   public zzd(DataHolder var1, int var2, zze var3) {
      super(var1, var2);
      this.zzaYZ = var3;
   }

   public final String zzvf() {
      return this.getString(this.zzaYZ.zzbcs);
   }

   public final String zzvg() {
      return this.getString(this.zzaYZ.zzbct);
   }

   public final long zzvh() {
      return this.getLong(this.zzaYZ.zzbcu);
   }

   public final Uri zzvi() {
      return this.zzcw(this.zzaYZ.zzbcv);
   }

   public final Uri zzvj() {
      return this.zzcw(this.zzaYZ.zzbcw);
   }

   public final Uri zzvk() {
      return this.zzcw(this.zzaYZ.zzbcx);
   }

   public final int hashCode() {
      return zzb.zza(this);
   }

   public final boolean equals(Object var1) {
      return zzb.zza(this, var1);
   }

   public final String toString() {
      return zzb.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((zzb)((zza)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new zzb(this);
   }
}
