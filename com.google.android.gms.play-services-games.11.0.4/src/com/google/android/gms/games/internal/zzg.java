package com.google.android.gms.games.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzg extends zzee implements zzf {
   public zzg() {
      this.attachInterface(this, "com.google.android.gms.games.internal.IGamesCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         int var5;
         Bundle var6;
         DataHolder var10;
         String var13;
         String[] var17;
         String var18;
         switch(var1) {
         case 5001:
            var5 = var2.readInt();
            var13 = var2.readString();
            this.zzg(var5, var13);
            break;
         case 5002:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzf(var10);
            break;
         case 5003:
            var5 = var2.readInt();
            var13 = var2.readString();
            this.zzh(var5, var13);
            break;
         case 5004:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzh(var10);
            break;
         case 5005:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            DataHolder var19 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zza(var10, var19);
            break;
         case 5006:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzi(var10);
            break;
         case 5007:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzj(var10);
            break;
         case 5008:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzk(var10);
            break;
         case 5009:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzl(var10);
            break;
         case 5010:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 5011:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 5016:
            this.zzuq();
            break;
         case 5017:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzm(var10);
            break;
         case 5018:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzu(var10);
            break;
         case 5019:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzv(var10);
            break;
         case 5020:
            var5 = var2.readInt();
            var13 = var2.readString();
            this.onLeftRoom(var5, var13);
            break;
         case 5021:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzw(var10);
            break;
         case 5022:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzx(var10);
            break;
         case 5023:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzy(var10);
            break;
         case 5024:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzz(var10);
            break;
         case 5025:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzA(var10);
            break;
         case 5026:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            var17 = var2.createStringArray();
            this.zza(var10, var17);
            break;
         case 5027:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            var17 = var2.createStringArray();
            this.zzb(var10, var17);
            break;
         case 5028:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            var17 = var2.createStringArray();
            this.zzc(var10, var17);
            break;
         case 5029:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            var17 = var2.createStringArray();
            this.zzd(var10, var17);
            break;
         case 5030:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            var17 = var2.createStringArray();
            this.zze(var10, var17);
            break;
         case 5031:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            var17 = var2.createStringArray();
            this.zzf(var10, var17);
            break;
         case 5032:
            RealTimeMessage var20 = (RealTimeMessage)zzef.zza(var2, RealTimeMessage.CREATOR);
            this.onRealTimeMessageReceived(var20);
            break;
         case 5033:
            var5 = var2.readInt();
            int var16 = var2.readInt();
            String var14 = var2.readString();
            this.zzb(var5, var16, var14);
            break;
         case 5034:
            var2.readInt();
            var2.readString();
            zzef.zza(var2);
            break;
         case 5035:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 5036:
            var2.readInt();
            break;
         case 5037:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzn(var10);
            break;
         case 5038:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 5039:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 5040:
            var2.readInt();
            break;
         case 6001:
            var18 = var2.readString();
            this.onP2PConnected(var18);
            break;
         case 6002:
            var18 = var2.readString();
            this.onP2PDisconnected(var18);
            break;
         case 8001:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzB(var10);
            break;
         case 8002:
            var5 = var2.readInt();
            var6 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zzb(var5, var6);
            break;
         case 8003:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzp(var10);
            break;
         case 8004:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzq(var10);
            break;
         case 8005:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzr(var10);
            break;
         case 8006:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzs(var10);
            break;
         case 8007:
            var5 = var2.readInt();
            var13 = var2.readString();
            this.zzi(var5, var13);
            break;
         case 8008:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzt(var10);
            break;
         case 8009:
            var18 = var2.readString();
            this.onTurnBasedMatchRemoved(var18);
            break;
         case 8010:
            var18 = var2.readString();
            this.onInvitationRemoved(var18);
            break;
         case 9001:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 10001:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzo(var10);
            break;
         case 10002:
            var18 = var2.readString();
            this.onRequestRemoved(var18);
            break;
         case 10003:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzC(var10);
            break;
         case 10004:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 10005:
            var5 = var2.readInt();
            var6 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zzc(var5, var6);
            break;
         case 10006:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 11001:
            var2.readInt();
            zzef.zza(var2, Bundle.CREATOR);
            break;
         case 12001:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzD(var10);
            break;
         case 12003:
            var2.readInt();
            zzef.zza(var2, Bundle.CREATOR);
            break;
         case 12004:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            com.google.android.gms.drive.zzc var15 = (com.google.android.gms.drive.zzc)zzef.zza(var2, com.google.android.gms.drive.zzc.CREATOR);
            this.zza(var10, var15);
            break;
         case 12005:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzE(var10);
            break;
         case 12006:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzF(var10);
            break;
         case 12007:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzG(var10);
            break;
         case 12008:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzI(var10);
            break;
         case 12011:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzg(var10);
            break;
         case 12012:
            var5 = var2.readInt();
            var13 = var2.readString();
            this.zzj(var5, var13);
            break;
         case 12013:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 12014:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzH(var10);
            break;
         case 12015:
            var2.readInt();
            zzef.zza(var2, Bundle.CREATOR);
            break;
         case 12016:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 12017:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            var13 = var2.readString();
            com.google.android.gms.drive.zzc var7 = (com.google.android.gms.drive.zzc)zzef.zza(var2, com.google.android.gms.drive.zzc.CREATOR);
            com.google.android.gms.drive.zzc var8 = (com.google.android.gms.drive.zzc)zzef.zza(var2, com.google.android.gms.drive.zzc.CREATOR);
            com.google.android.gms.drive.zzc var9 = (com.google.android.gms.drive.zzc)zzef.zza(var2, com.google.android.gms.drive.zzc.CREATOR);
            this.zza(var10, var13, var7, var8, var9);
            break;
         case 13001:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 13002:
            var2.readInt();
            break;
         case 14001:
            var2.createTypedArray(DataHolder.CREATOR);
            break;
         case 15001:
            var10 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzJ(var10);
            break;
         case 17001:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 17002:
            var2.readInt();
            break;
         case 19001:
            var5 = var2.readInt();
            VideoCapabilities var12 = (VideoCapabilities)zzef.zza(var2, VideoCapabilities.CREATOR);
            this.zza(var5, var12);
            break;
         case 19002:
            var5 = var2.readInt();
            boolean var11 = zzef.zza(var2);
            this.zzh(var5, var11);
            break;
         case 19003:
            var2.readInt();
            zzef.zza(var2);
            zzef.zza(var2);
            break;
         case 19004:
            var2.readInt();
            break;
         case 19006:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 19007:
            var2.readInt();
            zzef.zza(var2, Bundle.CREATOR);
            break;
         case 19008:
            var2.readInt();
            break;
         case 19009:
            var2.readInt();
            break;
         case 19010:
            var2.readInt();
            break;
         case 20001:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 20002:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 20003:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 20004:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 20005:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 20006:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 20007:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 20008:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 20009:
            zzef.zza(var2, DataHolder.CREATOR);
            break;
         case 20010:
            var2.readInt();
            var2.readString();
            var2.readString();
            break;
         case 20011:
            var2.readInt();
            var2.readString();
            break;
         case 20012:
            zzef.zza(var2, Status.CREATOR);
            break;
         case 20013:
            zzef.zza(var2, Status.CREATOR);
            break;
         case 20014:
            zzef.zza(var2, Status.CREATOR);
            break;
         case 20015:
            zzef.zza(var2, Status.CREATOR);
            break;
         case 20016:
            var2.readInt();
            break;
         case 20017:
            var2.readInt();
            zzef.zza(var2, Uri.CREATOR);
            break;
         case 20018:
            var2.readInt();
            break;
         case 20019:
            var5 = var2.readInt();
            this.onCaptureOverlayStateChanged(var5);
            break;
         case 20020:
            var5 = var2.readInt();
            var6 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zzd(var5, var6);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
