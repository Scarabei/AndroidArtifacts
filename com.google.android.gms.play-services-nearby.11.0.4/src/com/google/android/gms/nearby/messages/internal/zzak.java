package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzcpj;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.Messages;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import java.util.Collections;
import java.util.Iterator;

public final class zzak implements Messages {
   public static final zzak zzbzi = new zzak();
   public static final com.google.android.gms.common.api.Api.zzf zzajR = new com.google.android.gms.common.api.Api.zzf();
   public static final com.google.android.gms.common.api.Api.zza zzajS = new zzal();

   public final PendingResult publish(GoogleApiClient var1, Message var2, PublishOptions var3) {
      zzbo.zzu(var2);
      zzbo.zzu(var3);
      zzbdw var4 = var3.getCallback() == null ? null : var1.zzp(var3.getCallback());
      return var1.zze(new zzan(this, var1, var2, var4, var3));
   }

   public final PendingResult publish(GoogleApiClient var1, Message var2) {
      return this.publish(var1, var2, PublishOptions.DEFAULT);
   }

   public final PendingResult unpublish(GoogleApiClient var1, Message var2) {
      zzbo.zzu(var2);
      return var1.zze(new zzao(this, var1, var2));
   }

   public final PendingResult subscribe(GoogleApiClient var1, MessageListener var2, SubscribeOptions var3) {
      zzbo.zzu(var2);
      zzbo.zzu(var3);
      zzbo.zzb(var3.getStrategy().zzzU() == 0, "Strategy.setBackgroundScanMode() is only supported by background subscribe (the version which takes a PendingIntent).");
      zzbdw var4 = zzh(var1).zza(var1, var2);
      zzbdw var5 = var3.getCallback() == null ? null : var1.zzp(var3.getCallback());
      return var1.zze(new zzap(this, var1, var4, var5, var3));
   }

   public final PendingResult subscribe(GoogleApiClient var1, MessageListener var2) {
      return this.subscribe(var1, var2, SubscribeOptions.DEFAULT);
   }

   public final PendingResult subscribe(GoogleApiClient var1, PendingIntent var2, SubscribeOptions var3) {
      zzbo.zzu(var2);
      zzbo.zzu(var3);
      zzbdw var4 = var3.getCallback() == null ? null : var1.zzp(var3.getCallback());
      return var1.zze(new zzaq(this, var1, var2, var4, var3));
   }

   public final PendingResult subscribe(GoogleApiClient var1, PendingIntent var2) {
      return this.subscribe(var1, var2, SubscribeOptions.DEFAULT);
   }

   public final PendingResult unsubscribe(GoogleApiClient var1, MessageListener var2) {
      zzbo.zzu(var2);
      zzbdw var3 = zzh(var1).zza(var2);
      return var1.zze(new zzar(this, var1, var3));
   }

   public final PendingResult unsubscribe(GoogleApiClient var1, PendingIntent var2) {
      zzbo.zzu(var2);
      return var1.zze(new zzas(this, var1, var2));
   }

   public final void handleIntent(Intent var1, MessageListener var2) {
      String var3 = "com.google.android.gms.nearby.messages.UPDATES";
      Bundle var4;
      zza((Iterable)((var4 = var1.getBundleExtra("com.google.android.gms.nearby.messages.UPDATES")) == null ? Collections.emptyList() : var4.getParcelableArrayList(var3)), var2);
   }

   public static void zza(Iterable var0, MessageListener var1) {
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         Update var3;
         if ((var3 = (Update)var2.next()).zzbt(1)) {
            var1.onFound(var3.zzbzd);
         }

         if (var3.zzbt(2)) {
            var1.onLost(var3.zzbzd);
         }

         if (var3.zzbt(4)) {
            var1.onDistanceChanged(var3.zzbzd, var3.zzbzB);
         }

         if (var3.zzbt(8)) {
            var1.onBleSignalChanged(var3.zzbzd, var3.zzbzC);
         }

         if (var3.zzbt(16)) {
            Message var10000 = var3.zzbzd;
            zzcpj var4 = var3.zzbzD;
         }
      }

   }

   public final PendingResult getPermissionStatus(GoogleApiClient var1) {
      return var1.zze(new zzat(this, var1));
   }

   public final PendingResult registerStatusCallback(GoogleApiClient var1, StatusCallback var2) {
      zzbo.zzu(var2);
      zzbdw var3 = zzh(var1).zza(var1, var2);
      return var1.zze(new zzau(this, var1, var3));
   }

   public final PendingResult unregisterStatusCallback(GoogleApiClient var1, StatusCallback var2) {
      zzbo.zzu(var2);
      zzbdw var3 = zzh(var1).zza(var2);
      return var1.zze(new zzam(this, var1, var3));
   }

   private static zzah zzh(GoogleApiClient var0) {
      return (zzah)var0.zza(zzajR);
   }
}
