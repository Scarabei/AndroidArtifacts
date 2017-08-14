package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.wearable.ChannelApi;

public final class zzai extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzaj();
   private zzak zzbSj;
   private int type;
   private int zzbSh;
   private int zzbSi;

   public zzai(zzak var1, int var2, int var3, int var4) {
      this.zzbSj = var1;
      this.type = var2;
      this.zzbSh = var3;
      this.zzbSi = var4;
   }

   public final void zza(ChannelApi.ChannelListener var1) {
      switch(this.type) {
      case 1:
         var1.onChannelOpened(this.zzbSj);
         return;
      case 2:
         var1.onChannelClosed(this.zzbSj, this.zzbSh, this.zzbSi);
         return;
      case 3:
         var1.onInputClosed(this.zzbSj, this.zzbSh, this.zzbSi);
         return;
      case 4:
         var1.onOutputClosed(this.zzbSj, this.zzbSh, this.zzbSi);
         return;
      default:
         int var2 = this.type;
         Log.w("ChannelEventParcelable", (new StringBuilder(25)).append("Unknown type: ").append(var2).toString());
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbSj, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.type);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbSh);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzbSi);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      String var1 = String.valueOf(this.zzbSj);
      int var5 = this.type;
      String var10000;
      switch(this.type) {
      case 1:
         var10000 = "CHANNEL_OPENED";
         break;
      case 2:
         var10000 = "CHANNEL_CLOSED";
         break;
      case 3:
         var10000 = "INPUT_CLOSED";
         break;
      case 4:
         var10000 = "OUTPUT_CLOSED";
         break;
      default:
         var10000 = Integer.toString(var5);
      }

      String var2 = String.valueOf(var10000);
      var5 = this.zzbSh;
      switch(this.zzbSh) {
      case 0:
         var10000 = "CLOSE_REASON_NORMAL";
         break;
      case 1:
         var10000 = "CLOSE_REASON_DISCONNECTED";
         break;
      case 2:
         var10000 = "CLOSE_REASON_REMOTE_CLOSE";
         break;
      case 3:
         var10000 = "CLOSE_REASON_LOCAL_CLOSE";
         break;
      default:
         var10000 = Integer.toString(var5);
      }

      String var3 = String.valueOf(var10000);
      int var4 = this.zzbSi;
      return (new StringBuilder(81 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length())).append("ChannelEventParcelable[, channel=").append(var1).append(", type=").append(var2).append(", closeReason=").append(var3).append(", appErrorCode=").append(var4).append("]").toString();
   }
}
