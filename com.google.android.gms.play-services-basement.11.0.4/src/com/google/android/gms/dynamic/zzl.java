package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzl extends zzee implements zzk {
   public zzl() {
      this.attachInterface(this, "com.google.android.gms.dynamic.IFragmentWrapper");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Object var5;
         IBinder var7;
         IInterface var8;
         Intent var9;
         boolean var10;
         IObjectWrapper var11;
         int var12;
         zzk var13;
         switch(var1) {
         case 2:
            var11 = this.zzty();
            var3.writeNoException();
            zzef.zza(var3, (IInterface)var11);
            break;
         case 3:
            Bundle var15 = this.getArguments();
            var3.writeNoException();
            zzef.zzb(var3, var15);
            break;
         case 4:
            var12 = this.getId();
            var3.writeNoException();
            var3.writeInt(var12);
            break;
         case 5:
            var13 = this.zztz();
            var3.writeNoException();
            zzef.zza(var3, (IInterface)var13);
            break;
         case 6:
            var11 = this.zztA();
            var3.writeNoException();
            zzef.zza(var3, (IInterface)var11);
            break;
         case 7:
            var10 = this.getRetainInstance();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 8:
            String var14 = this.getTag();
            var3.writeNoException();
            var3.writeString(var14);
            break;
         case 9:
            var13 = this.zztB();
            var3.writeNoException();
            zzef.zza(var3, (IInterface)var13);
            break;
         case 10:
            var12 = this.getTargetRequestCode();
            var3.writeNoException();
            var3.writeInt(var12);
            break;
         case 11:
            var10 = this.getUserVisibleHint();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 12:
            var11 = this.getView();
            var3.writeNoException();
            zzef.zza(var3, (IInterface)var11);
            break;
         case 13:
            var10 = this.isAdded();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 14:
            var10 = this.isDetached();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 15:
            var10 = this.isHidden();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 16:
            var10 = this.isInLayout();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 17:
            var10 = this.isRemoving();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 18:
            var10 = this.isResumed();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 19:
            var10 = this.isVisible();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 20:
            var5 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper")) instanceof IObjectWrapper ? (IObjectWrapper)var8 : new zzm(var7));
            this.zzC((IObjectWrapper)var5);
            var3.writeNoException();
            break;
         case 21:
            var10 = zzef.zza(var2);
            this.setHasOptionsMenu(var10);
            var3.writeNoException();
            break;
         case 22:
            var10 = zzef.zza(var2);
            this.setMenuVisibility(var10);
            var3.writeNoException();
            break;
         case 23:
            var10 = zzef.zza(var2);
            this.setRetainInstance(var10);
            var3.writeNoException();
            break;
         case 24:
            var10 = zzef.zza(var2);
            this.setUserVisibleHint(var10);
            var3.writeNoException();
            break;
         case 25:
            var9 = (Intent)zzef.zza(var2, Intent.CREATOR);
            this.startActivity(var9);
            var3.writeNoException();
            break;
         case 26:
            var9 = (Intent)zzef.zza(var2, Intent.CREATOR);
            int var6 = var2.readInt();
            this.startActivityForResult(var9, var6);
            var3.writeNoException();
            break;
         case 27:
            var5 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper")) instanceof IObjectWrapper ? (IObjectWrapper)var8 : new zzm(var7));
            this.zzD((IObjectWrapper)var5);
            var3.writeNoException();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
