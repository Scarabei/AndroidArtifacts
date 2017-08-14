package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.fb;
import com.google.android.gms.internal.fc;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.nio.ByteBuffer;

public final class zza extends fb {
   private final zzc zzbNz;

   public zza(Context var1, zzc var2) {
      super(var1, "FaceNativeHandle");
      this.zzbNz = var2;
      this.zzDR();
   }

   public final Face[] zzb(ByteBuffer var1, fc var2) {
      if (!this.isOperational()) {
         return new Face[0];
      } else {
         FaceParcel[] var3;
         try {
            IObjectWrapper var4 = zzn.zzw(var1);
            var3 = ((zze)this.zzDR()).zzc(var4, var2);
         } catch (RemoteException var7) {
            Log.e("FaceNativeHandle", "Could not call native face detector", var7);
            return new Face[0];
         }

         Face[] var8 = new Face[var3.length];

         for(int var5 = 0; var5 < var3.length; ++var5) {
            FaceParcel var6 = var3[var5];
            var8[var5] = new Face(var6.id, new PointF(var6.centerX, var6.centerY), var6.width, var6.height, var6.zzbNA, var6.zzbNB, zza(var6), var6.zzbND, var6.zzbNE, var6.zzbNF);
         }

         return var8;
      }
   }

   public final boolean zzbN(int var1) {
      if (!this.isOperational()) {
         return false;
      } else {
         try {
            return ((zze)this.zzDR()).zzbN(var1);
         } catch (RemoteException var3) {
            Log.e("FaceNativeHandle", "Could not call native face detector", var3);
            return false;
         }
      }
   }

   protected final void zzDO() throws RemoteException {
      ((zze)this.zzDR()).zzDP();
   }

   private static Landmark[] zza(FaceParcel var0) {
      LandmarkParcel[] var2 = var0.zzbNC;
      Landmark[] var1;
      if (var0.zzbNC == null) {
         var1 = new Landmark[0];
      } else {
         var1 = new Landmark[var2.length];

         for(int var3 = 0; var3 < var2.length; ++var3) {
            LandmarkParcel var4 = var2[var3];
            var1[var3] = new Landmark(new PointF(var4.x, var4.y), var4.type);
         }
      }

      return var1;
   }

   // $FF: synthetic method
   protected final Object zza(DynamiteModule var1, Context var2) throws RemoteException, com.google.android.gms.dynamite.DynamiteModule.zzc {
      IBinder var8;
      IInterface var9;
      Object var6 = (var8 = var1.zzcV("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator")) == null ? null : ((var9 = var8.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator")) instanceof zzg ? (zzg)var9 : new zzh(var8));
      IObjectWrapper var7 = zzn.zzw(var2);
      return ((zzg)var6).zza(var7, this.zzbNz);
   }
}
