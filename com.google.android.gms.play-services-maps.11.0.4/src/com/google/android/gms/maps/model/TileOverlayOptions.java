package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.maps.model.internal.zzaa;
import com.google.android.gms.maps.model.internal.zzz;

public final class TileOverlayOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzt();
   private zzz zzbnY;
   private TileProvider zzbnZ;
   private boolean zzbnl = true;
   private float zzbnk;
   private boolean zzboa = true;
   private float zzbnt = 0.0F;

   public TileOverlayOptions() {
   }

   TileOverlayOptions(IBinder var1, boolean var2, float var3, boolean var4, float var5) {
      this.zzbnY = zzaa.zzaj(var1);
      this.zzbnZ = this.zzbnY == null ? null : new zzr(this);
      this.zzbnl = var2;
      this.zzbnk = var3;
      this.zzboa = var4;
      this.zzbnt = var5;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbnY.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.isVisible());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getZIndex());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getFadeIn());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getTransparency());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final TileOverlayOptions tileProvider(TileProvider var1) {
      this.zzbnZ = var1;
      this.zzbnY = this.zzbnZ == null ? null : new zzs(this, var1);
      return this;
   }

   public final TileOverlayOptions zIndex(float var1) {
      this.zzbnk = var1;
      return this;
   }

   public final TileOverlayOptions visible(boolean var1) {
      this.zzbnl = var1;
      return this;
   }

   public final TileOverlayOptions fadeIn(boolean var1) {
      this.zzboa = var1;
      return this;
   }

   public final TileOverlayOptions transparency(float var1) {
      zzbo.zzb(var1 >= 0.0F && var1 <= 1.0F, "Transparency must be in the range [0..1]");
      this.zzbnt = var1;
      return this;
   }

   public final TileProvider getTileProvider() {
      return this.zzbnZ;
   }

   public final float getZIndex() {
      return this.zzbnk;
   }

   public final boolean isVisible() {
      return this.zzbnl;
   }

   public final boolean getFadeIn() {
      return this.zzboa;
   }

   public final float getTransparency() {
      return this.zzbnt;
   }

   // $FF: synthetic method
   static zzz zza(TileOverlayOptions var0) {
      return var0.zzbnY;
   }
}
