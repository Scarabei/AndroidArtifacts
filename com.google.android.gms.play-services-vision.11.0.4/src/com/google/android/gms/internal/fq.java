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

public final class fq extends fb {
   private final fr zzbNV;

   public fq(Context var1, fr var2) {
      super(var1, "TextNativeHandle");
      this.zzbNV = var2;
      this.zzDR();
   }

   public final fk[] zza(Bitmap var1, fc var2, fm var3) {
      if (!this.isOperational()) {
         return new fk[0];
      } else {
         try {
            IObjectWrapper var4 = zzn.zzw(var1);
            return ((fg)this.zzDR()).zza(var4, var2, var3);
         } catch (RemoteException var5) {
            Log.e("TextNativeHandle", "Error calling native text recognizer", var5);
            return new fk[0];
         }
      }
   }

   protected final void zzDO() throws RemoteException {
      ((fg)this.zzDR()).zzDS();
   }

   // $FF: synthetic method
   protected final Object zza(DynamiteModule var1, Context var2) throws RemoteException, zzc {
      IBinder var8;
      IInterface var9;
      Object var6 = (var8 = var1.zzcV("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator")) == null ? null : ((var9 = var8.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator")) instanceof fi ? (fi)var9 : new fj(var8));
      IObjectWrapper var7 = zzn.zzw(var2);
      return ((fi)var6).zza(var7, this.zzbNV);
   }
}
