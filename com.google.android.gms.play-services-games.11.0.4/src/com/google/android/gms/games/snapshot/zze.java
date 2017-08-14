package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.zzbo;

public final class zze extends com.google.android.gms.games.internal.zzc implements SnapshotMetadataChange {
   public static final Creator CREATOR = new zzd();
   private final String zzafa;
   private final Long zzbeD;
   private final Uri zzbeC;
   private BitmapTeleporter zzbeE;
   private final Long zzbeA;

   zze() {
      this((String)null, (Long)null, (BitmapTeleporter)null, (Uri)null, (Long)null);
   }

   zze(String var1, Long var2, BitmapTeleporter var3, Uri var4, Long var5) {
      this.zzafa = var1;
      this.zzbeD = var2;
      this.zzbeE = var3;
      this.zzbeC = var4;
      this.zzbeA = var5;
      if (this.zzbeE != null) {
         zzbo.zza(this.zzbeC == null, "Cannot set both a URI and an image");
      } else {
         if (this.zzbeC != null) {
            zzbo.zza(this.zzbeE == null, "Cannot set both a URI and an image");
         }

      }
   }

   public final String getDescription() {
      return this.zzafa;
   }

   public final Long getPlayedTimeMillis() {
      return this.zzbeD;
   }

   public final Long getProgressValue() {
      return this.zzbeA;
   }

   public final BitmapTeleporter zzvv() {
      return this.zzbeE;
   }

   public final Bitmap getCoverImage() {
      return this.zzbeE == null ? null : this.zzbeE.zzqO();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getDescription(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getPlayedTimeMillis(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbeC, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbeE, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getProgressValue(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
