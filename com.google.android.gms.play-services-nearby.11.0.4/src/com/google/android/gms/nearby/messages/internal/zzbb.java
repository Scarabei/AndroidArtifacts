package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.nearby.messages.MessageFilter;
import com.google.android.gms.nearby.messages.Strategy;

public final class zzbb implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new SubscribeRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      IBinder var5 = null;
      Strategy var6 = null;
      IBinder var7 = null;
      MessageFilter var8 = null;
      PendingIntent var9 = null;
      int var10 = 0;
      String var11 = null;
      String var12 = null;
      byte[] var13 = null;
      boolean var14 = false;
      IBinder var15 = null;
      boolean var16 = false;
      ClientAppContext var17 = null;
      boolean var18 = false;
      int var19 = 0;

      while(var2.dataPosition() < var3) {
         int var20;
         switch((var20 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var20);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var20);
            break;
         case 3:
            var6 = (Strategy)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var20, Strategy.CREATOR);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var20);
            break;
         case 5:
            var8 = (MessageFilter)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var20, MessageFilter.CREATOR);
            break;
         case 6:
            var9 = (PendingIntent)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var20, PendingIntent.CREATOR);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var20);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var20);
            break;
         case 9:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var20);
            break;
         case 10:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(var2, var20);
            break;
         case 11:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var20);
            break;
         case 12:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var20);
            break;
         case 13:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var20);
            break;
         case 14:
            var17 = (ClientAppContext)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var20, ClientAppContext.CREATOR);
            break;
         case 15:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var20);
            break;
         case 16:
            var19 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var20);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var20);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new SubscribeRequest(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19);
   }
}
