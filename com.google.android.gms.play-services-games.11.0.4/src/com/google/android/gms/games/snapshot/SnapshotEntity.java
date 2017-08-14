package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class SnapshotEntity extends com.google.android.gms.games.internal.zzc implements Snapshot {
   public static final Creator CREATOR = new zzc();
   private final SnapshotMetadataEntity zzbex;
   private final zza zzbey;

   public SnapshotEntity(SnapshotMetadata var1, zza var2) {
      this.zzbex = new SnapshotMetadataEntity(var1);
      this.zzbey = var2;
   }

   public final SnapshotMetadata getMetadata() {
      return this.zzbex;
   }

   public final SnapshotContents getSnapshotContents() {
      return this.zzbey.isClosed() ? null : this.zzbey;
   }

   public final Snapshot freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.getMetadata(), this.getSnapshotContents()});
   }

   public final boolean equals(Object var1) {
      if (var1 instanceof Snapshot) {
         if (this == var1) {
            return true;
         }

         Snapshot var4;
         if (zzbe.equal((var4 = (Snapshot)var1).getMetadata(), this.getMetadata()) && zzbe.equal(var4.getSnapshotContents(), this.getSnapshotContents())) {
            return true;
         }
      }

      return false;
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("Metadata", this.getMetadata()).zzg("HasContents", this.getSnapshotContents() != null).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getMetadata(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getSnapshotContents(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
