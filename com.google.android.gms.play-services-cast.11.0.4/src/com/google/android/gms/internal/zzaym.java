package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

public abstract class zzaym extends zzee implements zzayl {
   public zzaym() {
      this.attachInterface(this, "com.google.android.gms.cast.internal.ICastDeviceControllerListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         long var6;
         String var10;
         int var11;
         String var13;
         boolean var15;
         switch(var1) {
         case 1:
            var11 = var2.readInt();
            this.zzae(var11);
            break;
         case 2:
            ApplicationMetadata var16 = (ApplicationMetadata)zzef.zza(var2, ApplicationMetadata.CREATOR);
            var13 = var2.readString();
            String var7 = var2.readString();
            var15 = zzef.zza(var2);
            this.zza(var16, var13, var7, var15);
            break;
         case 3:
            var11 = var2.readInt();
            this.zzZ(var11);
            break;
         case 4:
            var10 = var2.readString();
            double var14 = var2.readDouble();
            var15 = zzef.zza(var2);
            this.zza(var10, var14, var15);
            break;
         case 5:
            var10 = var2.readString();
            var13 = var2.readString();
            this.zzu(var10, var13);
            break;
         case 6:
            var10 = var2.readString();
            byte[] var12 = var2.createByteArray();
            this.zza(var10, var12);
            break;
         case 7:
            var11 = var2.readInt();
            this.zzag(var11);
            break;
         case 8:
            var11 = var2.readInt();
            this.zzaf(var11);
            break;
         case 9:
            var11 = var2.readInt();
            this.onApplicationDisconnected(var11);
            break;
         case 10:
            var10 = var2.readString();
            var6 = var2.readLong();
            int var8 = var2.readInt();
            this.zza(var10, var6, var8);
            break;
         case 11:
            var10 = var2.readString();
            var6 = var2.readLong();
            this.zzb(var10, var6);
            break;
         case 12:
            zzaxq var9 = (zzaxq)zzef.zza(var2, zzaxq.CREATOR);
            this.zzb(var9);
            break;
         case 13:
            zzayf var5 = (zzayf)zzef.zza(var2, zzayf.CREATOR);
            this.zzb(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
