package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.ads.internal.js.JavascriptEngineFactory;
import com.google.android.gms.ads.internal.js.zza;
import com.google.android.gms.ads.internal.js.zzah;
import com.google.android.gms.ads.internal.js.zzl;
import com.google.android.gms.ads.internal.js.zzx;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzzn
public final class zzyh {
   private static final long zzRj;
   private static final Object zzuF;
   private static boolean zzRk;
   private static zzl zzRl;
   private final Context mContext;
   private final zzaje zzuK;
   private final zzbb zzRm;
   private final zzcu zzIc;
   private final Object mLock;
   private JavascriptEngineFactory zzRn;
   private zzah zzRo;
   private zzajm zzRp;
   private boolean zzRq;
   private boolean zzRr;

   public zzyh(Context var1, zzafg var2, zzbb var3, zzcu var4) {
      this(var1, var3, var4, var2 != null && var2.zzUj != null ? var2.zzUj.zzvT : null);
   }

   public zzyh(Context var1, zzbb var2, zzcu var3, zzaje var4) {
      this.mLock = new Object();
      this.zzRq = false;
      this.zzRr = false;
      this.mContext = var1;
      this.zzRm = var2;
      this.zzIc = var3;
      this.zzuK = var4;
      zzme var5 = zzmo.zzFt;
      this.zzRq = ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).booleanValue();
   }

   public final void zzgs() {
      if (this.zzRq) {
         zzyh var1 = this;
         Object var2 = zzuF;
         synchronized(zzuF) {
            if (!zzRk) {
               Context var10002 = var1.mContext.getApplicationContext() != null ? var1.mContext.getApplicationContext() : var1.mContext;
               zzme var4 = zzmo.zzFq;
               zzRl = new zzl(var10002, var1.zzuK, (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4), new zzyk(var1), new zzx());
               zzRk = true;
            }

         }
      } else {
         this.zzRn = new JavascriptEngineFactory();
      }
   }

   public final void zzgt() throws zzakm {
      if (this.zzRq) {
         this.zzRo = new zzah(zzRl.zzb(this.zzIc));
      } else {
         zzme var2 = zzmo.zzFq;
         this.zzRp = this.zzRn.zza(this.mContext, this.zzuK, (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2), this.zzIc, this.zzRm.zzak());
      }
   }

   public final void zza(zzym var1) {
      if (this.zzRq) {
         zzah var5 = this.zzRo;
         if (this.zzRo == null) {
            zzafr.zzaT("SharedJavascriptEngine not initialized");
         } else {
            var5.zza(new zzyi(this, var1), new zzyj(this, var1));
         }
      } else {
         try {
            zza var2;
            if ((var2 = this.zzgu()) == null) {
               zzafr.zzaT("JavascriptEngine not initialized");
            } else {
               var1.zzd(var2);
            }
         } catch (InterruptedException var3) {
            Thread.currentThread().interrupt();
            zzafr.zzc("Exception occurred during execution", var3);
         } catch (ExecutionException | TimeoutException | CancellationException var4) {
            zzafr.zzc("Exception occurred during execution", var4);
         }
      }
   }

   public final void zzfd() {
      if (!this.zzRq) {
         try {
            zza var1;
            if ((var1 = this.zzgu()) != null) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               zzagz.runOnUiThread(new zzyl(this, var1));
            }

            return;
         } catch (ExecutionException | InterruptedException | TimeoutException | CancellationException var2) {
            zzafr.zzc("Exception occurred while destroying engine", var2);
         }
      }

   }

   @Nullable
   private final zza zzgu() throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
      if (this.zzRp == null) {
         return null;
      } else {
         zza var1 = (zza)this.zzRp.get(zzRj, TimeUnit.MILLISECONDS);
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            if (!this.zzRr) {
               var1.zza(this.zzRm, this.zzRm, this.zzRm, this.zzRm, false, (zzrm)null, (zzw)null, (zzwv)null);
               this.zzRr = true;
            }

            return var1;
         }
      }
   }

   // $FF: synthetic method
   static zzbb zza(zzyh var0) {
      return var0.zzRm;
   }

   static {
      zzRj = TimeUnit.SECONDS.toMillis(60L);
      zzuF = new Object();
      zzRk = false;
      zzRl = null;
   }
}
