package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;

public final class ew extends fb {
   private final eu zzbNh;

   public ew(Context var1, eu var2) {
      super(var1, "BarcodeNativeHandle");
      this.zzbNh = var2;
      this.zzDR();
   }

   public final Barcode[] zza(ByteBuffer var1, fc var2) {
      if (!this.isOperational()) {
         return new Barcode[0];
      } else {
         try {
            IObjectWrapper var3 = zzn.zzw(var1);
            return ((ex)this.zzDR()).zza(var3, var2);
         } catch (RemoteException var4) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", var4);
            return new Barcode[0];
         }
      }
   }

   public final Barcode[] zza(Bitmap var1, fc var2) {
      if (!this.isOperational()) {
         return new Barcode[0];
      } else {
         try {
            IObjectWrapper var3 = zzn.zzw(var1);
            return ((ex)this.zzDR()).zzb(var3, var2);
         } catch (RemoteException var4) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", var4);
            return new Barcode[0];
         }
      }
   }

   protected final void zzDO() throws RemoteException {
      if (this.isOperational()) {
         ((ex)this.zzDR()).zzDP();
      }

   }

   // $FF: synthetic method
   protected final Object zza(DynamiteModule var1, Context var2) throws RemoteException, zzc {
      IBinder var8;
      IInterface var9;
      Object var6 = (var8 = var1.zzcV("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator")) == null ? null : ((var9 = var8.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator")) instanceof ez ? (ez)var9 : new fa(var8));
      IObjectWrapper var7 = zzn.zzw(var2);
      return var6 == null ? null : ((ez)var6).zza(var7, this.zzbNh);
   }
}
