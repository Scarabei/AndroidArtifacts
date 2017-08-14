package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;

final class zzfs implements zzfr {
   private Handler handler;
   // $FF: synthetic field
   final zzfo zzbGP;

   private zzfs(zzfo var1) {
      this.zzbGP = var1;
      this.handler = new Handler(zzfo.zza(this.zzbGP).getMainLooper(), new zzft(this));
   }

   public final void zzBY() {
      this.handler.removeMessages(1, zzfo.zzBX());
      this.handler.sendMessage(this.obtainMessage());
   }

   public final void cancel() {
      this.handler.removeMessages(1, zzfo.zzBX());
   }

   public final void zzs(long var1) {
      this.handler.removeMessages(1, zzfo.zzBX());
      this.handler.sendMessageDelayed(this.obtainMessage(), var1);
   }

   private final Message obtainMessage() {
      return this.handler.obtainMessage(1, zzfo.zzBX());
   }

   // $FF: synthetic method
   zzfs(zzfo var1, zzfp var2) {
      this(var1);
   }
}
