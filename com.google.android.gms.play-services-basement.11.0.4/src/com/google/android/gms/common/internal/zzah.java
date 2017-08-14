package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class zzah implements ServiceConnection {
   private final Set zzaHT;
   private int mState;
   private boolean zzaHU;
   private IBinder zzaHj;
   private final zzaf zzaHV;
   private ComponentName zzaHO;
   // $FF: synthetic field
   private zzag zzaHW;

   public zzah(zzag var1, zzaf var2) {
      this.zzaHW = var1;
      super();
      this.zzaHV = var2;
      this.zzaHT = new HashSet();
      this.mState = 2;
   }

   public final void onServiceConnected(ComponentName var1, IBinder var2) {
      synchronized(zzag.zza(this.zzaHW)) {
         zzag.zzb(this.zzaHW).removeMessages(1, this.zzaHV);
         this.zzaHj = var2;
         this.zzaHO = var1;
         Iterator var4 = this.zzaHT.iterator();

         while(var4.hasNext()) {
            ((ServiceConnection)var4.next()).onServiceConnected(var1, var2);
         }

         this.mState = 1;
      }
   }

   public final void onServiceDisconnected(ComponentName var1) {
      synchronized(zzag.zza(this.zzaHW)) {
         zzag.zzb(this.zzaHW).removeMessages(1, this.zzaHV);
         this.zzaHj = null;
         this.zzaHO = var1;
         Iterator var3 = this.zzaHT.iterator();

         while(var3.hasNext()) {
            ((ServiceConnection)var3.next()).onServiceDisconnected(var1);
         }

         this.mState = 2;
      }
   }

   public final void zzcB(String var1) {
      this.mState = 3;
      zzag.zzd(this.zzaHW);
      this.zzaHU = com.google.android.gms.common.stats.zza.zza(zzag.zzc(this.zzaHW), var1, this.zzaHV.zzrB(), this, 129);
      if (this.zzaHU) {
         Message var2 = zzag.zzb(this.zzaHW).obtainMessage(1, this.zzaHV);
         zzag.zzb(this.zzaHW).sendMessageDelayed(var2, zzag.zze(this.zzaHW));
      } else {
         this.mState = 2;

         try {
            zzag.zzd(this.zzaHW);
            zzag.zzc(this.zzaHW).unbindService(this);
         } catch (IllegalArgumentException var5) {
            ;
         }
      }
   }

   public final void zzcC(String var1) {
      zzag.zzb(this.zzaHW).removeMessages(1, this.zzaHV);
      zzag.zzd(this.zzaHW);
      zzag.zzc(this.zzaHW).unbindService(this);
      this.zzaHU = false;
      this.mState = 2;
   }

   public final void zza(ServiceConnection var1, String var2) {
      zzag.zzd(this.zzaHW);
      zzag.zzc(this.zzaHW);
      this.zzaHV.zzrB();
      this.zzaHT.add(var1);
   }

   public final void zzb(ServiceConnection var1, String var2) {
      zzag.zzd(this.zzaHW);
      zzag.zzc(this.zzaHW);
      this.zzaHT.remove(var1);
   }

   public final boolean isBound() {
      return this.zzaHU;
   }

   public final int getState() {
      return this.mState;
   }

   public final boolean zza(ServiceConnection var1) {
      return this.zzaHT.contains(var1);
   }

   public final boolean zzrC() {
      return this.zzaHT.isEmpty();
   }

   public final IBinder getBinder() {
      return this.zzaHj;
   }

   public final ComponentName getComponentName() {
      return this.zzaHO;
   }
}
