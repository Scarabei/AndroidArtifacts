package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import java.util.List;

public abstract class zzvd extends zzee implements zzvc {
   public zzvd() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var5;
         boolean var9;
         String var10;
         switch(var1) {
         case 2:
            var10 = this.getHeadline();
            var3.writeNoException();
            var3.writeString(var10);
            break;
         case 3:
            List var13 = this.getImages();
            var3.writeNoException();
            var3.writeList(var13);
            break;
         case 4:
            var10 = this.getBody();
            var3.writeNoException();
            var3.writeString(var10);
            break;
         case 5:
            zzos var12 = this.zzeh();
            var3.writeNoException();
            zzef.zza(var3, var12);
            break;
         case 6:
            var10 = this.getCallToAction();
            var3.writeNoException();
            var3.writeString(var10);
            break;
         case 7:
            double var11 = this.getStarRating();
            var3.writeNoException();
            var3.writeDouble(var11);
            break;
         case 8:
            var10 = this.getStore();
            var3.writeNoException();
            var3.writeString(var10);
            break;
         case 9:
            var10 = this.getPrice();
            var3.writeNoException();
            var3.writeString(var10);
            break;
         case 10:
            this.recordImpression();
            var3.writeNoException();
            break;
         case 11:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zzl(var5);
            var3.writeNoException();
            break;
         case 12:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zzm(var5);
            var3.writeNoException();
            break;
         case 13:
            var9 = this.getOverrideImpressionRecording();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 14:
            var9 = this.getOverrideClickHandling();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 15:
            Bundle var8 = this.getExtras();
            var3.writeNoException();
            zzef.zzb(var3, var8);
            break;
         case 16:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zzn(var5);
            var3.writeNoException();
            break;
         case 17:
            zzks var7 = this.getVideoController();
            var3.writeNoException();
            zzef.zza(var3, var7);
            break;
         case 18:
            var5 = this.zzfw();
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
