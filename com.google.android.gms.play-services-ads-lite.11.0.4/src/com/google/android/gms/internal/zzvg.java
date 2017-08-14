package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import java.util.List;

public abstract class zzvg extends zzee implements zzvf {
   public zzvg() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var6;
         boolean var8;
         String var9;
         switch(var1) {
         case 2:
            var9 = this.getHeadline();
            var3.writeNoException();
            var3.writeString(var9);
            break;
         case 3:
            List var11 = this.getImages();
            var3.writeNoException();
            var3.writeList(var11);
            break;
         case 4:
            var9 = this.getBody();
            var3.writeNoException();
            var3.writeString(var9);
            break;
         case 5:
            zzos var10 = this.zzem();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 6:
            var9 = this.getCallToAction();
            var3.writeNoException();
            var3.writeString(var9);
            break;
         case 7:
            var9 = this.getAdvertiser();
            var3.writeNoException();
            var3.writeString(var9);
            break;
         case 8:
            this.recordImpression();
            var3.writeNoException();
            break;
         case 9:
            var6 = zza.zzM(var2.readStrongBinder());
            this.zzl(var6);
            var3.writeNoException();
            break;
         case 10:
            var6 = zza.zzM(var2.readStrongBinder());
            this.zzm(var6);
            var3.writeNoException();
            break;
         case 11:
            var8 = this.getOverrideImpressionRecording();
            var3.writeNoException();
            zzef.zza(var3, var8);
            break;
         case 12:
            var8 = this.getOverrideClickHandling();
            var3.writeNoException();
            zzef.zza(var3, var8);
            break;
         case 13:
            Bundle var7 = this.getExtras();
            var3.writeNoException();
            zzef.zzb(var3, var7);
            break;
         case 14:
            var6 = zza.zzM(var2.readStrongBinder());
            this.zzn(var6);
            var3.writeNoException();
            break;
         case 15:
            var6 = this.zzfw();
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 16:
            zzks var5 = this.getVideoController();
            var3.writeNoException();
            zzef.zza(var3, var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
