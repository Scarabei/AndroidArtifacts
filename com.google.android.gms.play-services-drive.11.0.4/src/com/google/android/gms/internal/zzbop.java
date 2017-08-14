package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.zza;

public abstract class zzbop extends zzee implements zzboo {
   public zzbop() {
      this.attachInterface(this, "com.google.android.gms.drive.internal.IDriveServiceCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            zzbpd var13 = (zzbpd)zzef.zza(var2, zzbpd.CREATOR);
            this.zza(var13);
            break;
         case 2:
            zzbpl var12 = (zzbpl)zzef.zza(var2, zzbpl.CREATOR);
            this.zza(var12);
            break;
         case 3:
            zzbpf var11 = (zzbpf)zzef.zza(var2, zzbpf.CREATOR);
            this.zza(var11);
            break;
         case 4:
            zzbpq var10 = (zzbpq)zzef.zza(var2, zzbpq.CREATOR);
            this.zza(var10);
            break;
         case 5:
            zzboz var9 = (zzboz)zzef.zza(var2, zzboz.CREATOR);
            this.zza(var9);
            break;
         case 6:
            Status var8 = (Status)zzef.zza(var2, Status.CREATOR);
            this.onError(var8);
            break;
         case 7:
            this.onSuccess();
            break;
         case 8:
            zzbpn var7 = (zzbpn)zzef.zza(var2, zzbpn.CREATOR);
            this.zza(var7);
            break;
         case 9:
            zzef.zza(var2, zzbpz.CREATOR);
            break;
         case 10:
         case 19:
         default:
            return false;
         case 11:
            zzef.zza(var2, zzbpp.CREATOR);
            zzbsx.zzL(var2.readStrongBinder());
            break;
         case 12:
            zzef.zza(var2, zzbpv.CREATOR);
            break;
         case 13:
            zzef.zza(var2, zzbps.CREATOR);
            break;
         case 14:
            zzbpb var6 = (zzbpb)zzef.zza(var2, zzbpb.CREATOR);
            this.zza(var6);
            break;
         case 15:
            boolean var5 = zzef.zza(var2);
            this.zzag(var5);
            break;
         case 16:
            zzef.zza(var2, zzbpj.CREATOR);
            break;
         case 17:
            zzef.zza(var2, zza.CREATOR);
            break;
         case 18:
            zzef.zza(var2, zzbox.CREATOR);
            break;
         case 20:
            zzef.zza(var2, zzbok.CREATOR);
            break;
         case 21:
            zzef.zza(var2, zzbqr.CREATOR);
            break;
         case 22:
            zzef.zza(var2, zzbpx.CREATOR);
         }

         var3.writeNoException();
         return true;
      }
   }
}
