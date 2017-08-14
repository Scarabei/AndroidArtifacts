package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.js.zzai;
import com.google.android.gms.ads.internal.js.zzl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.Map.Entry;

@zzzn
public final class zzfh implements zzfp {
   private final Object mLock = new Object();
   private final WeakHashMap zzwK = new WeakHashMap();
   private final ArrayList zzwL = new ArrayList();
   private final Context mApplicationContext;
   private final zzaje zztW;
   private final zzl zzwM;

   public zzfh(Context var1, zzaje var2, zzl var3) {
      this.mApplicationContext = var1.getApplicationContext();
      this.zztW = var2;
      this.zzwM = var3;
   }

   public final void zza(zziv var1, zzaff var2) {
      this.zza(var1, var2, var2.zzPg.getView());
   }

   public final void zza(zziv var1, zzaff var2, View var3) {
      this.zza(var1, var2, (zzgs)(new zzfo(var3, var2)), (zzai)null);
   }

   public final void zza(zziv var1, zzaff var2, View var3, zzai var4) {
      this.zza(var1, var2, (zzgs)(new zzfo(var3, var2)), var4);
   }

   public final void zza(zziv var1, zzaff var2, zzgs var3, @Nullable zzai var4) {
      Object var5 = this.mLock;
      synchronized(this.mLock) {
         zzfi var6;
         if (this.zzf(var2)) {
            var6 = (zzfi)this.zzwK.get(var2);
         } else {
            (var6 = new zzfi(this.mApplicationContext, var1, var2, this.zztW, var3)).zza((zzfp)this);
            this.zzwK.put(var2, var6);
            this.zzwL.add(var6);
         }

         if (var4 != null) {
            var6.zza((zzgd)(new zzfq(var6, var4)));
         } else {
            var6.zza((zzgd)(new zzfu(var6, this.zzwM, this.mApplicationContext)));
         }

      }
   }

   private final boolean zzf(zzaff var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         zzfi var3;
         return (var3 = (zzfi)this.zzwK.get(var1)) != null && var3.zzcs();
      }
   }

   public final void zzg(zzaff var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         zzfi var3;
         if ((var3 = (zzfi)this.zzwK.get(var1)) != null) {
            var3.zzcq();
         }

      }
   }

   public final void zza(zzfi var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (!var1.zzcs()) {
            this.zzwL.remove(var1);
            Iterator var3 = this.zzwK.entrySet().iterator();

            while(var3.hasNext()) {
               if (((Entry)var3.next()).getValue() == var1) {
                  var3.remove();
               }
            }
         }

      }
   }

   public final void zzh(zzaff var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         zzfi var3;
         if ((var3 = (zzfi)this.zzwK.get(var1)) != null) {
            var3.stop();
         }

      }
   }

   public final void zzi(zzaff var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         zzfi var3;
         if ((var3 = (zzfi)this.zzwK.get(var1)) != null) {
            var3.pause();
         }

      }
   }

   public final void zzj(zzaff var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         zzfi var3;
         if ((var3 = (zzfi)this.zzwK.get(var1)) != null) {
            var3.resume();
         }

      }
   }
}
