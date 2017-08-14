package com.google.android.gms.ads.internal.js;

import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzahz;
import com.google.android.gms.internal.zzajr;
import com.google.android.gms.internal.zzajt;

public final class zzac extends zzajt {
   private final Object mLock = new Object();
   private zzahz zzLc;
   private boolean zzLu;
   private int zzLv;

   public zzac(zzahz var1) {
      this.zzLc = var1;
      this.zzLu = false;
      this.zzLv = 0;
   }

   public final zzy zzfa() {
      zzy var1 = new zzy(this);
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zza(new zzad(this, var1), new zzae(this, var1));
         zzbo.zzae(this.zzLv >= 0);
         ++this.zzLv;
         return var1;
      }
   }

   protected final void zzfb() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzbo.zzae(this.zzLv > 0);
         zzafr.v("Releasing 1 reference for JS Engine");
         --this.zzLv;
         this.zzfd();
      }
   }

   public final void zzfc() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzbo.zzae(this.zzLv >= 0);
         zzafr.v("Releasing root reference. JS Engine will be destroyed once other references are released.");
         this.zzLu = true;
         this.zzfd();
      }
   }

   private final void zzfd() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzbo.zzae(this.zzLv >= 0);
         if (this.zzLu && this.zzLv == 0) {
            zzafr.v("No reference is left (including root). Cleaning up engine.");
            this.zza(new zzaf(this), new zzajr());
         } else {
            zzafr.v("There are still references to the engine. Not destroying.");
         }

      }
   }

   // $FF: synthetic method
   static zzahz zza(zzac var0) {
      return var0.zzLc;
   }
}
