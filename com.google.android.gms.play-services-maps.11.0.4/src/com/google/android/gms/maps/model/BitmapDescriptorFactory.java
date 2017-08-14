package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;

public final class BitmapDescriptorFactory {
   public static final float HUE_RED = 0.0F;
   public static final float HUE_ORANGE = 30.0F;
   public static final float HUE_YELLOW = 60.0F;
   public static final float HUE_GREEN = 120.0F;
   public static final float HUE_CYAN = 180.0F;
   public static final float HUE_AZURE = 210.0F;
   public static final float HUE_BLUE = 240.0F;
   public static final float HUE_VIOLET = 270.0F;
   public static final float HUE_MAGENTA = 300.0F;
   public static final float HUE_ROSE = 330.0F;
   private static com.google.android.gms.maps.model.internal.zza zzbnb;

   private static com.google.android.gms.maps.model.internal.zza zzwj() {
      return (com.google.android.gms.maps.model.internal.zza)zzbo.zzb(zzbnb, "IBitmapDescriptorFactory is not initialized");
   }

   public static void zza(com.google.android.gms.maps.model.internal.zza var0) {
      if (zzbnb == null) {
         zzbnb = (com.google.android.gms.maps.model.internal.zza)zzbo.zzu(var0);
      }
   }

   public static BitmapDescriptor fromResource(int var0) {
      try {
         return new BitmapDescriptor(zzwj().zzbo(var0));
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public static BitmapDescriptor fromAsset(String var0) {
      try {
         return new BitmapDescriptor(zzwj().zzdC(var0));
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public static BitmapDescriptor fromFile(String var0) {
      try {
         return new BitmapDescriptor(zzwj().zzdD(var0));
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public static BitmapDescriptor fromPath(String var0) {
      try {
         return new BitmapDescriptor(zzwj().zzdE(var0));
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public static BitmapDescriptor defaultMarker() {
      try {
         return new BitmapDescriptor(zzwj().zzwl());
      } catch (RemoteException var1) {
         throw new RuntimeRemoteException(var1);
      }
   }

   public static BitmapDescriptor defaultMarker(float var0) {
      try {
         return new BitmapDescriptor(zzwj().zze(var0));
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }

   public static BitmapDescriptor fromBitmap(Bitmap var0) {
      try {
         return new BitmapDescriptor(zzwj().zzd(var0));
      } catch (RemoteException var2) {
         throw new RuntimeRemoteException(var2);
      }
   }
}
