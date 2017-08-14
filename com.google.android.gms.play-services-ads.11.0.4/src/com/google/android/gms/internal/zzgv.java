package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

@zzzn
public final class zzgv {
   private final Object zzyn = new Object();
   private zzgw zzyo = null;
   private boolean zzyp = false;

   public final void initialize(Context var1) {
      Object var2 = this.zzyn;
      synchronized(this.zzyn) {
         if (!this.zzyp) {
            zzme var6 = zzmo.zzDJ;
            if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue()) {
               return;
            }

            Application var3 = null;
            Context var4;
            if ((var4 = (var4 = var1.getApplicationContext()) == null ? var1 : var4) instanceof Application) {
               var3 = (Application)var4;
            }

            if (var3 == null) {
               zzafr.zzaT("Can not cast Context to Application");
               return;
            }

            if (this.zzyo == null) {
               this.zzyo = new zzgw();
            }

            this.zzyo.zza(var3, var1);
            this.zzyp = true;
         }

      }
   }

   public final void zza(zzgy var1) {
      Object var2 = this.zzyn;
      synchronized(this.zzyn) {
         zzme var4 = zzmo.zzDJ;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue()) {
            if (this.zzyo == null) {
               this.zzyo = new zzgw();
            }

            this.zzyo.zza(var1);
         }
      }
   }

   @Nullable
   public final Activity getActivity() {
      Object var1 = this.zzyn;
      synchronized(this.zzyn) {
         return this.zzyo != null ? this.zzyo.getActivity() : null;
      }
   }

   @Nullable
   public final Context getContext() {
      Object var1 = this.zzyn;
      synchronized(this.zzyn) {
         return this.zzyo != null ? this.zzyo.getContext() : null;
      }
   }
}
