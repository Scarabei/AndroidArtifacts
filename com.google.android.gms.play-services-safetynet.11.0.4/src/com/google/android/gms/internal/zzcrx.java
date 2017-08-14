package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafeBrowsingData;
import com.google.android.gms.safetynet.zza;
import com.google.android.gms.safetynet.zzd;
import com.google.android.gms.safetynet.zzf;

public abstract class zzcrx extends zzee implements zzcrw {
   public zzcrx() {
      this.attachInterface(this, "com.google.android.gms.safetynet.internal.ISafetyNetCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Status var5;
         boolean var6;
         switch(var1) {
         case 1:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            zza var11 = (zza)zzef.zza(var2, zza.CREATOR);
            this.zza(var5, var11);
            break;
         case 2:
            String var9 = var2.readString();
            this.zzeG(var9);
            break;
         case 3:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            SafeBrowsingData var10 = (SafeBrowsingData)zzef.zza(var2, SafeBrowsingData.CREATOR);
            this.zza(var5, var10);
            break;
         case 4:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            var6 = zzef.zza(var2);
            this.zza(var5, var6);
            break;
         case 5:
         case 9:
         default:
            return false;
         case 6:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            zzf var8 = (zzf)zzef.zza(var2, zzf.CREATOR);
            this.zza(var5, var8);
            break;
         case 7:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            var6 = zzef.zza(var2);
            this.zzb(var5, var6);
            break;
         case 8:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            zzd var7 = (zzd)zzef.zza(var2, zzd.CREATOR);
            this.zza(var5, var7);
            break;
         case 10:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            var6 = zzef.zza(var2);
            this.zzc(var5, var6);
            break;
         case 11:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            this.zzF(var5);
            break;
         case 12:
            var5 = (Status)zzef.zza(var2, Status.CREATOR);
            var6 = zzef.zza(var2);
            this.zzd(var5, var6);
         }

         return true;
      }
   }
}
