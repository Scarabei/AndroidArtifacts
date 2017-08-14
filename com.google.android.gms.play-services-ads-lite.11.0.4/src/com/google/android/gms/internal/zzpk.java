package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import java.util.List;

public abstract class zzpk extends zzee implements zzpj {
   public zzpk() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
   }

   public static zzpj zzk(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzpj)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd")) instanceof zzpj ? (zzpj)var1 : new zzpl(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var5;
         String var9;
         switch(var1) {
         case 1:
            var9 = var2.readString();
            String var10 = this.zzP(var9);
            var3.writeNoException();
            var3.writeString(var10);
            break;
         case 2:
            var9 = var2.readString();
            zzos var8 = this.zzQ(var9);
            var3.writeNoException();
            zzef.zza(var3, var8);
            break;
         case 3:
            List var11 = this.getAvailableAssetNames();
            var3.writeNoException();
            var3.writeStringList(var11);
            break;
         case 4:
            var9 = this.getCustomTemplateId();
            var3.writeNoException();
            var3.writeString(var9);
            break;
         case 5:
            var9 = var2.readString();
            this.performClick(var9);
            var3.writeNoException();
            break;
         case 6:
            this.recordImpression();
            var3.writeNoException();
            break;
         case 7:
            zzks var7 = this.getVideoController();
            var3.writeNoException();
            zzef.zza(var3, var7);
            break;
         case 8:
            this.destroy();
            var3.writeNoException();
            break;
         case 9:
            var5 = this.zzen();
            var3.writeNoException();
            zzef.zza(var3, var5);
            break;
         case 10:
            var5 = zza.zzM(var2.readStrongBinder());
            boolean var6 = this.zzj(var5);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 11:
            var5 = this.zzei();
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
