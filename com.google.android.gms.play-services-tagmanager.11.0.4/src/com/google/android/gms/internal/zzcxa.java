package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.tagmanager.zzce;
import com.google.android.gms.tagmanager.zzcn;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public final class zzcxa extends zzcvh {
   private final Map zzbJE;
   private final ExecutorService zzbHL;
   private final zzcun zzbJF;
   private final zzcn zzbHN;
   private final Context mContext;

   public zzcxa(Context var1, zzcn var2, zzce var3) {
      this(var1, var2, new zzcun(var1, var2, var3), zzcxe.zza.zzbx(var1));
   }

   private zzcxa(Context var1, zzcn var2, zzcun var3, ExecutorService var4) {
      this.zzbJE = new HashMap(1);
      zzbo.zzu(var2);
      this.zzbHN = var2;
      this.zzbJF = var3;
      this.zzbHL = var4;
      this.mContext = var1;
   }

   public final void zzn(String var1, @Nullable String var2, @Nullable String var3) throws RemoteException {
      this.zza(var1, var2, var3, (zzcvd)null);
   }

   public final void zza(String var1, @Nullable String var2, @Nullable String var3, @Nullable zzcvd var4) throws RemoteException {
      this.zzbHL.execute(new zzcxb(this, var1, var2, var3, var4));
   }

   public final void zzCr() throws RemoteException {
      this.zzbJE.clear();
   }

   public final void zza(String var1, Bundle var2, String var3, long var4, boolean var6) throws RemoteException {
      zzcut var7 = new zzcut(var1, var2, var3, new Date(var4), var6, this.zzbHN);
      this.zzbHL.execute(new zzcxc(this, var7));
   }

   public final void dispatch() {
      this.zzbHL.execute(new zzcxd(this));
   }

   // $FF: synthetic method
   static Map zza(zzcxa var0) {
      return var0.zzbJE;
   }

   // $FF: synthetic method
   static zzcun zzb(zzcxa var0) {
      return var0.zzbJF;
   }

   // $FF: synthetic method
   static Context zzc(zzcxa var0) {
      return var0.mContext;
   }
}
