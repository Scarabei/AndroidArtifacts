package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.tagmanager.zzcn;
import java.util.Date;
import java.util.Map;

public final class zzcut implements zze {
   private final Bundle zzbIc;
   private final String zzbId;
   private final Date zzbIe;
   private final String zzbIf;
   private Map zzbIg;
   private boolean zzbIh;
   private final zzcn zzbHN;

   public zzcut(@Nullable String var1, @Nullable Bundle var2, String var3, Date var4, boolean var5, zzcn var6) {
      this.zzbId = var1;
      this.zzbIc = var2 == null ? new Bundle() : var2;
      this.zzbIe = var4;
      this.zzbIf = var3;
      this.zzbIh = var5;
      this.zzbHN = var6;
   }

   public final String zzCk() {
      return this.zzbId;
   }

   public final Bundle zzCl() {
      return this.zzbIc;
   }

   public final String zzCm() {
      return this.zzbIf;
   }

   public final long currentTimeMillis() {
      return this.zzbIe.getTime();
   }

   public final long elapsedRealtime() {
      return SystemClock.elapsedRealtime();
   }

   public final long nanoTime() {
      return System.nanoTime();
   }

   @WorkerThread
   public final Map zzBh() {
      if (this.zzbIg == null) {
         try {
            Map var1 = this.zzbHN.zzBh();
            this.zzbIg = var1;
         } catch (RemoteException var2) {
            String var10001 = String.valueOf(var2.getMessage());
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Error calling measurement proxy:".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Error calling measurement proxy:");
            }

            zzcvk.e(var10000);
         }
      }

      return this.zzbIg;
   }

   public final boolean zzCn() {
      return this.zzbIh;
   }

   public final void zzat(boolean var1) {
      this.zzbIh = false;
   }
}
