package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzaet;
import com.google.android.gms.internal.zzafg;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzakm;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zznc;
import com.google.android.gms.internal.zzxx;

final class zzl implements Runnable {
   // $FF: synthetic field
   final zzafg zzsW;
   // $FF: synthetic field
   final zzaet zztc;
   // $FF: synthetic field
   private zznb zztd;
   // $FF: synthetic field
   final zzi zztb;

   zzl(zzi var1, zzafg var2, zzaet var3, zznb var4) {
      this.zztb = var1;
      this.zzsW = var2;
      this.zztc = var3;
      this.zztd = var4;
      super();
   }

   public final void run() {
      if (this.zzsW.zzXY.zzTx && this.zztb.zzsP.zzwo != null) {
         String var1 = null;
         if (this.zzsW.zzXY.zzPi != null) {
            zzbs.zzbz();
            var1 = zzagz.zzaI(this.zzsW.zzXY.zzPi);
         }

         zznc var2 = new zznc(this.zztb, var1, this.zzsW.zzXY.body);
         this.zztb.zzsP.zzwt = 1;

         try {
            this.zztb.zzsN = false;
            this.zztb.zzsP.zzwo.zza(var2);
            return;
         } catch (RemoteException var5) {
            zzafr.zzc("Could not call the onCustomRenderedAdLoadedListener.", var5);
            this.zztb.zzsN = true;
         }
      }

      zzw var6 = new zzw(this.zztc);

      zzaka var7;
      try {
         var7 = this.zztb.zza(this.zzsW, var6, this.zztc);
      } catch (zzakm var4) {
         zzafr.zzb("Could not obtain webview.", var4);
         zzagz.zzZr.post(new zzm(this));
         return;
      }

      var7.setOnTouchListener(new zzn(this, var6));
      var7.setOnClickListener(new zzo(this, var6));
      this.zztb.zzsP.zzwt = 0;
      zzbt var10000 = this.zztb.zzsP;
      zzbs.zzby();
      var10000.zzvW = zzxx.zza(this.zztb.zzsP.zzqD, this.zztb, this.zzsW, this.zztb.zzsP.zzvS, var7, this.zztb.zzsX, this.zztb, this.zztd);
   }
}
