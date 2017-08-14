package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;

public abstract class zzdl extends com.google.android.gms.internal.zzee implements zzdk {
   public zzdl() {
      this.attachInterface(this, "com.google.android.gms.wearable.internal.IWearableListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         zzeg var10;
         switch(var1) {
         case 1:
            DataHolder var12 = (DataHolder)com.google.android.gms.internal.zzef.zza(var2, DataHolder.CREATOR);
            this.zzS(var12);
            break;
         case 2:
            zzdx var11 = (zzdx)com.google.android.gms.internal.zzef.zza(var2, zzdx.CREATOR);
            this.zza(var11);
            break;
         case 3:
            var10 = (zzeg)com.google.android.gms.internal.zzef.zza(var2, zzeg.CREATOR);
            this.zza(var10);
            break;
         case 4:
            var10 = (zzeg)com.google.android.gms.internal.zzef.zza(var2, zzeg.CREATOR);
            this.zzb(var10);
            break;
         case 5:
            ArrayList var9 = var2.createTypedArrayList(zzeg.CREATOR);
            this.onConnectedNodes(var9);
            break;
         case 6:
            zzl var8 = (zzl)com.google.android.gms.internal.zzef.zza(var2, zzl.CREATOR);
            this.zza(var8);
            break;
         case 7:
            zzai var7 = (zzai)com.google.android.gms.internal.zzef.zza(var2, zzai.CREATOR);
            this.zza(var7);
            break;
         case 8:
            zzaa var6 = (zzaa)com.google.android.gms.internal.zzef.zza(var2, zzaa.CREATOR);
            this.zza(var6);
            break;
         case 9:
            zzi var5 = (zzi)com.google.android.gms.internal.zzef.zza(var2, zzi.CREATOR);
            this.zza(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
