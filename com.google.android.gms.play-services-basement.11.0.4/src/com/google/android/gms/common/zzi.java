package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzi extends zzg {
   private WeakReference zzaAi;
   private static final WeakReference zzaAj = new WeakReference((Object)null);

   zzi(byte[] var1) {
      super(var1);
      this.zzaAi = zzaAj;
   }

   final byte[] getBytes() {
      synchronized(this) {
         byte[] var2;
         if ((var2 = (byte[])this.zzaAi.get()) == null) {
            var2 = this.zzpa();
            this.zzaAi = new WeakReference(var2);
         }

         return var2;
      }
   }

   protected abstract byte[] zzpa();
}
