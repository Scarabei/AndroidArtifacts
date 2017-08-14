package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;

public abstract class zzbsx extends zzee implements zzbsw {
   public static zzbsw zzL(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbsw)((var1 = var0.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService")) instanceof zzbsw ? (zzbsw)var1 : new zzbsy(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         String var6;
         String var7;
         IBinder var10;
         IInterface var11;
         int var12;
         Object var13;
         Object var16;
         int var17;
         Object var18;
         DataHolder var19;
         zzbsa var20;
         Object var21;
         int var23;
         switch(var1) {
         case 1:
            var5 = var2.readString();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IStringCallback")) instanceof zzbsz ? (zzbsz)var11 : new zzbta(var10));
            this.zza(var5, (zzbsz)var16);
            var3.writeNoException();
            break;
         case 2:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IBooleanCallback")) instanceof zzbsc ? (zzbsc)var11 : new zzbsd(var10));
            this.zza((zzbsc)var13);
            var3.writeNoException();
            break;
         case 3:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ISuccessCallback")) instanceof zzbtb ? (zzbtb)var11 : new zzbtc(var10));
            this.zza((zzbtb)var13);
            var3.writeNoException();
            break;
         case 4:
            var5 = var2.readString();
            var6 = var2.readString();
            var18 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDataHolderCallback")) instanceof zzbsi ? (zzbsi)var11 : new zzbsj(var10));
            this.zza(var5, var6, (zzbsi)var18);
            var3.writeNoException();
            break;
         case 5:
            var5 = var2.readString();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IIntCallback")) instanceof zzbsu ? (zzbsu)var11 : new zzbsv(var10));
            this.zza(var5, (zzbsu)var16);
            var3.writeNoException();
            break;
         case 6:
            var5 = var2.readString();
            DataHolder var26 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            var18 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zza(var5, var26, (zzbsq)var18);
            var3.writeNoException();
            break;
         case 7:
            var5 = var2.readString();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zza(var5, (zzbsq)var16);
            var3.writeNoException();
            break;
         case 8:
            var5 = var2.readString();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IIntCallback")) instanceof zzbsu ? (zzbsu)var11 : new zzbsv(var10));
            this.zzb(var5, (zzbsu)var16);
            var3.writeNoException();
            break;
         case 9:
            var5 = var2.readString();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IStringCallback")) instanceof zzbsz ? (zzbsz)var11 : new zzbta(var10));
            this.zzb(var5, (zzbsz)var16);
            var3.writeNoException();
            break;
         case 10:
            var5 = var2.readString();
            var17 = var2.readInt();
            var7 = var2.readString();
            var21 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zza(var5, var17, var7, (zzbsq)var21);
            var3.writeNoException();
            break;
         case 11:
            var5 = var2.readString();
            var17 = var2.readInt();
            var23 = var2.readInt();
            var21 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zza(var5, var17, var23, (zzbsq)var21);
            var3.writeNoException();
            break;
         case 12:
            var5 = var2.readString();
            var6 = var2.readString();
            var18 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zza(var5, var6, (zzbsq)var18);
            var3.writeNoException();
            break;
         case 13:
            var5 = var2.readString();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDataHolderCallback")) instanceof zzbsi ? (zzbsi)var11 : new zzbsj(var10));
            this.zzb(var5, (zzbsi)var16);
            var3.writeNoException();
            break;
         case 14:
            var5 = var2.readString();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IIntCallback")) instanceof zzbsu ? (zzbsu)var11 : new zzbsv(var10));
            this.zzc(var5, (zzbsu)var16);
            var3.writeNoException();
            break;
         case 15:
            var5 = var2.readString();
            var17 = var2.readInt();
            var19 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            var21 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zza(var5, var17, var19, (zzbsq)var21);
            var3.writeNoException();
            break;
         case 16:
            var5 = var2.readString();
            var17 = var2.readInt();
            var19 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            var21 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDataHolderEventCallback")) instanceof zzbsk ? (zzbsk)var11 : new zzbsl(var10));
            this.zza(var5, var17, var19, (zzbsk)var21);
            var3.writeNoException();
            break;
         case 17:
            var5 = var2.readString();
            var17 = var2.readInt();
            var23 = var2.readInt();
            var21 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDataHolderEventCallback")) instanceof zzbsk ? (zzbsk)var11 : new zzbsl(var10));
            this.zza(var5, var17, var23, (zzbsk)var21);
            var3.writeNoException();
            break;
         case 18:
            zzbry var25 = (zzbry)zzef.zza(var2, zzbry.CREATOR);
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ISuccessCallback")) instanceof zzbtb ? (zzbtb)var11 : new zzbtc(var10));
            this.zza(var25, (zzbtb)var16);
            var3.writeNoException();
            break;
         case 19:
            var20 = (zzbsa)zzef.zza(var2, zzbsa.CREATOR);
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ISuccessCallback")) instanceof zzbtb ? (zzbtb)var11 : new zzbtc(var10));
            this.zza(var20, (zzbtb)var16);
            var3.writeNoException();
            break;
         case 20:
            var5 = var2.readString();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDataHolderCallback")) instanceof zzbsi ? (zzbsi)var11 : new zzbsj(var10));
            this.zza(var5, (zzbsi)var16);
            var3.writeNoException();
            break;
         case 21:
            var5 = var2.readString();
            var6 = var2.readString();
            var18 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDataHolderEventCallback")) instanceof zzbsk ? (zzbsk)var11 : new zzbsl(var10));
            this.zza(var5, var6, (zzbsk)var18);
            var3.writeNoException();
            break;
         case 22:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zza((zzbsq)var13);
            var3.writeNoException();
            break;
         case 23:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zzb((zzbsq)var13);
            var3.writeNoException();
            break;
         case 24:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IBooleanCallback")) instanceof zzbsc ? (zzbsc)var11 : new zzbsd(var10));
            this.zzd((zzbsc)var13);
            var3.writeNoException();
            break;
         case 25:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IBooleanCallback")) instanceof zzbsc ? (zzbsc)var11 : new zzbsd(var10));
            this.zze((zzbsc)var13);
            var3.writeNoException();
            break;
         case 26:
            zzbtf var24 = (zzbtf)zzef.zza(var2, zzbtf.CREATOR);
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IStringCallback")) instanceof zzbsz ? (zzbsz)var11 : new zzbta(var10));
            this.zza(var24, (zzbsz)var16);
            var3.writeNoException();
            break;
         case 27:
            var5 = var2.readString();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IIndexReferenceCallback")) instanceof zzbss ? (zzbss)var11 : new zzbst(var10));
            this.zza(var5, (zzbss)var16);
            var3.writeNoException();
            break;
         case 28:
            var5 = var2.readString();
            var17 = var2.readInt();
            var18 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ISuccessCallback")) instanceof zzbtb ? (zzbtb)var11 : new zzbtc(var10));
            this.zza(var5, var17, (zzbtb)var18);
            var3.writeNoException();
            break;
         case 29:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IIntCallback")) instanceof zzbsu ? (zzbsu)var11 : new zzbsv(var10));
            this.zzb((zzbsu)var13);
            var3.writeNoException();
            break;
         case 30:
            var12 = var2.readInt();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zza(var12, (zzbsq)var16);
            var3.writeNoException();
            break;
         case 31:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorsCallback")) instanceof zzbsg ? (zzbsg)var11 : new zzbsh(var10));
            this.zza((zzbsg)var13);
            var3.writeNoException();
            break;
         case 32:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorEventCallback")) instanceof zzbse ? (zzbse)var11 : new zzbsf(var10));
            this.zza((zzbse)var13);
            var3.writeNoException();
            break;
         case 33:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IBooleanCallback")) instanceof zzbsc ? (zzbsc)var11 : new zzbsd(var10));
            this.zzb((zzbsc)var13);
            var3.writeNoException();
            break;
         case 34:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IErrorCallback")) instanceof zzbso ? (zzbso)var11 : new zzbsp(var10));
            this.zza((zzbso)var13);
            var3.writeNoException();
            break;
         case 35:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ISuccessCallback")) instanceof zzbtb ? (zzbtb)var11 : new zzbtc(var10));
            this.zzb((zzbtb)var13);
            var3.writeNoException();
            break;
         case 36:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback")) instanceof zzbsm ? (zzbsm)var11 : new zzbsn(var10));
            this.zza((zzbsm)var13);
            var3.writeNoException();
            break;
         case 37:
            var5 = var2.readString();
            var17 = var2.readInt();
            var7 = var2.readString();
            int var22 = var2.readInt();
            Object var9 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zza(var5, var17, var7, var22, (zzbsq)var9);
            var3.writeNoException();
            break;
         case 38:
            var5 = var2.readString();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ISuccessCallback")) instanceof zzbtb ? (zzbtb)var11 : new zzbtc(var10));
            this.zza(var5, (zzbtb)var16);
            var3.writeNoException();
            break;
         case 39:
            var5 = var2.readString();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ISuccessCallback")) instanceof zzbtb ? (zzbtb)var11 : new zzbtc(var10));
            this.zzb(var5, (zzbtb)var16);
            var3.writeNoException();
            break;
         case 40:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IIntCallback")) instanceof zzbsu ? (zzbsu)var11 : new zzbsv(var10));
            this.zza((zzbsu)var13);
            var3.writeNoException();
            break;
         case 41:
            var20 = (zzbsa)zzef.zza(var2, zzbsa.CREATOR);
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zza(var20, (zzbsq)var16);
            var3.writeNoException();
            break;
         case 42:
            var5 = var2.readString();
            var6 = var2.readString();
            var18 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDataHolderCallback")) instanceof zzbsi ? (zzbsi)var11 : new zzbsj(var10));
            this.zzb(var5, var6, (zzbsi)var18);
            var3.writeNoException();
            break;
         case 43:
            var5 = var2.readString();
            var6 = var2.readString();
            var19 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            var21 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IEventCallback")) instanceof zzbsq ? (zzbsq)var11 : new zzbsr(var10));
            this.zza(var5, var6, var19, (zzbsq)var21);
            var3.writeNoException();
            break;
         case 44:
            this.zztw();
            var3.writeNoException();
            break;
         case 45:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IBooleanCallback")) instanceof zzbsc ? (zzbsc)var11 : new zzbsd(var10));
            this.zzc((zzbsc)var13);
            var3.writeNoException();
            break;
         case 46:
            var5 = var2.readString();
            var17 = var2.readInt();
            var18 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IStringCallback")) instanceof zzbsz ? (zzbsz)var11 : new zzbta(var10));
            this.zza(var5, var17, (zzbsz)var18);
            var3.writeNoException();
            break;
         case 47:
            boolean var15 = zzef.zza(var2);
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ISuccessCallback")) instanceof zzbtb ? (zzbtb)var11 : new zzbtc(var10));
            this.zza(var15, (zzbtb)var16);
            var3.writeNoException();
            break;
         case 48:
            DriveId var14 = (DriveId)zzef.zza(var2, DriveId.CREATOR);
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ISuccessCallback")) instanceof zzbtb ? (zzbtb)var11 : new zzbtc(var10));
            this.zza(var14, (zzbtb)var16);
            var3.writeNoException();
            break;
         case 49:
            var13 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ISuccessCallback")) instanceof zzbtb ? (zzbtb)var11 : new zzbtc(var10));
            this.zzc((zzbtb)var13);
            var3.writeNoException();
            break;
         case 50:
            var12 = var2.readInt();
            var16 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ISuccessCallback")) instanceof zzbtb ? (zzbtb)var11 : new zzbtc(var10));
            this.zza(var12, (zzbtb)var16);
            var3.writeNoException();
            break;
         case 51:
            var5 = var2.readString();
            var6 = var2.readString();
            var7 = var2.readString();
            zzbtn var8 = this.zzd(var5, var6, var7);
            var3.writeNoException();
            zzef.zzb(var3, var8);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
