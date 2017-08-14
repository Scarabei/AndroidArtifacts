package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.CompletionListener;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.zzb;
import com.google.android.gms.drive.events.zzd;
import com.google.android.gms.drive.events.zzi;
import com.google.android.gms.drive.events.zzl;
import com.google.android.gms.drive.events.zzn;

final class zzboe extends Handler {
   private final Context mContext;

   private zzboe(Looper var1, Context var2) {
      super(var1);
      this.mContext = var2;
   }

   public final void handleMessage(Message var1) {
      switch(var1.what) {
      case 1:
         Pair var2;
         zzi var3 = (zzi)(var2 = (Pair)var1.obj).first;
         DriveEvent var4;
         switch((var4 = (DriveEvent)var2.second).getType()) {
         case 1:
            ((ChangeListener)var3).onChange((ChangeEvent)var4);
            return;
         case 2:
            ((CompletionListener)var3).onCompletion((CompletionEvent)var4);
            return;
         case 3:
            zzl var7;
            DataHolder var8;
            if ((var8 = (var7 = (zzl)var4).zztb()) != null) {
               MetadataBuffer var9 = new MetadataBuffer(var8);
               new zzbof(var9);
            }

            if (var7.zztc()) {
               var7.zztd();
            }

            return;
         case 4:
            ((zzd)var3).zza((zzb)var4);
            return;
         case 5:
         case 6:
         case 7:
         default:
            String var6 = String.valueOf(var4);
            zzbng.zzy("EventCallback", (new StringBuilder(18 + String.valueOf(var6).length())).append("Unexpected event: ").append(var6).toString());
            return;
         case 8:
            zzbkp var5 = ((zzn)var4).zzte();
            new zzbkn(var5);
            return;
         }
      default:
         zzbng.zzm(this.mContext, "EventCallback", "Don't know how to handle this event");
      }
   }

   // $FF: synthetic method
   zzboe(Looper var1, Context var2, zzbod var3) {
      this(var1, var2);
   }
}
