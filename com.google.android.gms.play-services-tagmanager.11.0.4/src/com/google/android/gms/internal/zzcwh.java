package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Message;

final class zzcwh implements zzcwg {
   private Handler handler;
   // $FF: synthetic field
   final zzcwd zzbJa;

   private zzcwh(zzcwd var1) {
      this.zzbJa = var1;
      this.handler = new Handler(zzcwd.zza(this.zzbJa).getMainLooper(), new zzcwi(this));
   }

   public final void zzBY() {
      this.handler.removeMessages(1, zzcwd.zzBX());
      this.handler.sendMessage(this.obtainMessage());
   }

   public final void cancel() {
      this.handler.removeMessages(1, zzcwd.zzBX());
   }

   public final void zzs(long var1) {
      this.handler.removeMessages(1, zzcwd.zzBX());
      this.handler.sendMessageDelayed(this.obtainMessage(), var1);
   }

   private final Message obtainMessage() {
      return this.handler.obtainMessage(1, zzcwd.zzBX());
   }

   // $FF: synthetic method
   zzcwh(zzcwd var1, zzcwe var2) {
      this(var1);
   }
}
