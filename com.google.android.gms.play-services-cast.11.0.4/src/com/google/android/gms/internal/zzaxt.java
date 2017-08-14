package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;

public abstract class zzaxt extends zzaxw {
   private Handler mHandler;
   protected final zze zzvw;
   private long zzaxG;
   private Runnable zzaxH;
   protected boolean zzaxI;

   public zzaxt(String var1, String var2, String var3) {
      this(var1, zzi.zzrY(), var2, (String)null);
   }

   private zzaxt(String var1, zze var2, String var3, String var4) {
      this(var1, var2, var3, (String)null, 1000L);
   }

   public zzaxt(String var1, zze var2, String var3, String var4, long var5) {
      super(var1, var3, var4);
      this.mHandler = new Handler(Looper.getMainLooper());
      this.zzvw = var2;
      this.zzaxH = new zzaxv(this, (zzaxu)null);
      this.zzaxG = 1000L;
      this.zzZ(false);
   }

   public void zzoz() {
      this.zzZ(false);
   }

   protected final void zzZ(boolean var1) {
      if (this.zzaxI != var1) {
         this.zzaxI = var1;
         if (var1) {
            this.mHandler.postDelayed(this.zzaxH, this.zzaxG);
            return;
         }

         this.mHandler.removeCallbacks(this.zzaxH);
      }

   }

   protected abstract boolean zzz(long var1);
}
