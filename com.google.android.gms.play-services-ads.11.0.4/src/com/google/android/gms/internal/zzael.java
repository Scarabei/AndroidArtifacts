package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.safetynet.SafetyNet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

@zzzn
public final class zzael {
   private static final List zzXh = Arrays.asList(Integer.valueOf(4), Integer.valueOf(5));
   private Timer zzXi = new Timer();
   private GoogleApiClient zzXj;

   public final zzajm zza(Context var1, Set var2) {
      zzajg var3 = new zzajg();
      this.zzXj = (new Builder(var1)).addApi(SafetyNet.API).addConnectionCallbacks(new zzaen(this, var2, var3)).addOnConnectionFailedListener(new zzaem(this, var3)).build();
      this.zzXj.connect();
      return var3;
   }

   @VisibleForTesting
   final void zza(GoogleApiClient var1, Set var2, zzajg var3) {
      HashMap var4 = new HashMap();
      AtomicInteger var5 = new AtomicInteger(var2.size());
      Iterator var6 = var2.iterator();

      while(var6.hasNext()) {
         String var7 = (String)var6.next();
         zzcsa var10000 = (zzcsa)SafetyNet.SafetyNetApi;
         zzme var10 = zzmo.zzGc;
         var10000.zza(this.zzXj, zzXh, var7, (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var10)).setResultCallback(new zzaep(this, var4, var7, var5, var1, var3));
      }

   }

   // $FF: synthetic method
   static GoogleApiClient zza(zzael var0) {
      return var0.zzXj;
   }

   // $FF: synthetic method
   static Timer zzb(zzael var0) {
      return var0.zzXi;
   }
}
