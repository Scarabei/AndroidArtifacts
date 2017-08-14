package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzdj extends com.google.android.gms.internal.zzee implements zzdi {
   public zzdj() {
      this.attachInterface(this, "com.google.android.gms.wearable.internal.IWearableCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         zzbf var16;
         switch(var1) {
         case 2:
            zzcu var29 = (zzcu)com.google.android.gms.internal.zzef.zza(var2, zzcu.CREATOR);
            this.zza(var29);
            break;
         case 3:
            zzem var28 = (zzem)com.google.android.gms.internal.zzef.zza(var2, zzem.CREATOR);
            this.zza(var28);
            break;
         case 4:
            zzda var27 = (zzda)com.google.android.gms.internal.zzef.zza(var2, zzda.CREATOR);
            this.zza(var27);
            break;
         case 5:
            DataHolder var26 = (DataHolder)com.google.android.gms.internal.zzef.zza(var2, DataHolder.CREATOR);
            this.zzT(var26);
            break;
         case 6:
            zzce var25 = (zzce)com.google.android.gms.internal.zzef.zza(var2, zzce.CREATOR);
            this.zza(var25);
            break;
         case 7:
            zzes var24 = (zzes)com.google.android.gms.internal.zzef.zza(var2, zzes.CREATOR);
            this.zza(var24);
            break;
         case 8:
            zzdc var23 = (zzdc)com.google.android.gms.internal.zzef.zza(var2, zzdc.CREATOR);
            this.zza(var23);
            break;
         case 9:
            zzde var22 = (zzde)com.google.android.gms.internal.zzef.zza(var2, zzde.CREATOR);
            this.zza(var22);
            break;
         case 10:
            zzcy var21 = (zzcy)com.google.android.gms.internal.zzef.zza(var2, zzcy.CREATOR);
            this.zza(var21);
            break;
         case 11:
            Status var20 = (Status)com.google.android.gms.internal.zzef.zza(var2, Status.CREATOR);
            this.zza(var20);
            break;
         case 12:
            zzew var19 = (zzew)com.google.android.gms.internal.zzef.zza(var2, zzew.CREATOR);
            this.zza(var19);
            break;
         case 13:
            zzcw var18 = (zzcw)com.google.android.gms.internal.zzef.zza(var2, zzcw.CREATOR);
            this.zza(var18);
            break;
         case 14:
            zzei var17 = (zzei)com.google.android.gms.internal.zzef.zza(var2, zzei.CREATOR);
            this.zza(var17);
            break;
         case 15:
            var16 = (zzbf)com.google.android.gms.internal.zzef.zza(var2, zzbf.CREATOR);
            this.zza(var16);
            break;
         case 16:
            var16 = (zzbf)com.google.android.gms.internal.zzef.zza(var2, zzbf.CREATOR);
            this.zzb(var16);
            break;
         case 17:
            zzck var15 = (zzck)com.google.android.gms.internal.zzef.zza(var2, zzck.CREATOR);
            this.zza(var15);
            break;
         case 18:
            zzcm var14 = (zzcm)com.google.android.gms.internal.zzef.zza(var2, zzcm.CREATOR);
            this.zza(var14);
            break;
         case 19:
            zzaz var13 = (zzaz)com.google.android.gms.internal.zzef.zza(var2, zzaz.CREATOR);
            this.zza(var13);
            break;
         case 20:
            zzbb var12 = (zzbb)com.google.android.gms.internal.zzef.zza(var2, zzbb.CREATOR);
            this.zza(var12);
            break;
         case 21:
         case 24:
         case 25:
         default:
            return false;
         case 22:
            zzci var11 = (zzci)com.google.android.gms.internal.zzef.zza(var2, zzci.CREATOR);
            this.zza(var11);
            break;
         case 23:
            zzcg var10 = (zzcg)com.google.android.gms.internal.zzef.zza(var2, zzcg.CREATOR);
            this.zza(var10);
            break;
         case 26:
            zzf var9 = (zzf)com.google.android.gms.internal.zzef.zza(var2, zzf.CREATOR);
            this.zza(var9);
            break;
         case 27:
            zzeq var8 = (zzeq)com.google.android.gms.internal.zzef.zza(var2, zzeq.CREATOR);
            this.zza(var8);
            break;
         case 28:
            zzcp var7 = (zzcp)com.google.android.gms.internal.zzef.zza(var2, zzcp.CREATOR);
            this.zza(var7);
            break;
         case 29:
            zzct var6 = (zzct)com.google.android.gms.internal.zzef.zza(var2, zzct.CREATOR);
            this.zza(var6);
            break;
         case 30:
            zzcr var5 = (zzcr)com.google.android.gms.internal.zzef.zza(var2, zzcr.CREATOR);
            this.zza(var5);
         }

         var3.writeNoException();
         return true;
      }
   }
}
