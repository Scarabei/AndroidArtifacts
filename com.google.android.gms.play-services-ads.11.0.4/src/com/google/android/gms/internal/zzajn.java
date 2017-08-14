package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzzn
final class zzajn {
   private final Object zzabc = new Object();
   private final List zzabd = new ArrayList();
   private final List zzabe = new ArrayList();
   private boolean zzabf = false;

   public final void zzc(Runnable var1) {
      Object var2 = this.zzabc;
      synchronized(this.zzabc) {
         if (this.zzabf) {
            zzagt.zza(var1);
         } else {
            this.zzabd.add(var1);
         }

      }
   }

   public final void zzd(Runnable var1) {
      Object var2 = this.zzabc;
      synchronized(this.zzabc) {
         if (this.zzabf) {
            zze(var1);
         } else {
            this.zzabe.add(var1);
         }

      }
   }

   public final void zzin() {
      Object var1 = this.zzabc;
      synchronized(this.zzabc) {
         if (!this.zzabf) {
            Iterator var2 = this.zzabd.iterator();

            while(var2.hasNext()) {
               zzagt.zza((Runnable)var2.next());
            }

            var2 = this.zzabe.iterator();

            while(var2.hasNext()) {
               zze((Runnable)var2.next());
            }

            this.zzabd.clear();
            this.zzabe.clear();
            this.zzabf = true;
         }
      }
   }

   private static void zze(Runnable var0) {
      zzaiy.zzaaH.post(var0);
   }
}
