package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zzov extends NativeAd.Image {
   private final zzos zzID;
   private final Drawable mDrawable;
   private final Uri mUri;
   private final double zzHA;

   public zzov(zzos var1) {
      this.zzID = var1;
      Drawable var2 = null;

      try {
         IObjectWrapper var3;
         if ((var3 = this.zzID.zzeg()) != null) {
            var2 = (Drawable)zzn.zzE(var3);
         }
      } catch (RemoteException var9) {
         zzajc.zzb("Failed to get drawable.", var9);
      }

      this.mDrawable = var2;
      Uri var10 = null;

      try {
         var10 = this.zzID.getUri();
      } catch (RemoteException var8) {
         zzajc.zzb("Failed to get uri.", var8);
      }

      this.mUri = var10;
      double var4 = 1.0D;

      try {
         var4 = this.zzID.getScale();
      } catch (RemoteException var7) {
         zzajc.zzb("Failed to get scale.", var7);
      }

      this.zzHA = var4;
   }

   public final Drawable getDrawable() {
      return this.mDrawable;
   }

   public final Uri getUri() {
      return this.mUri;
   }

   public final double getScale() {
      return this.zzHA;
   }
}
