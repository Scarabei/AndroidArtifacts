package com.google.android.gms.internal;

public final class zzcaf {
   private static zzcaf zzaXH;
   private final zzcaa zzaXI = new zzcaa();
   private final zzcab zzaXJ = new zzcab();

   private static zzcaf zzua() {
      Class var0 = zzcaf.class;
      synchronized(zzcaf.class) {
         return zzaXH;
      }
   }

   public static zzcaa zzub() {
      return zzua().zzaXI;
   }

   public static zzcab zzuc() {
      return zzua().zzaXJ;
   }

   static {
      zzcaf var0 = new zzcaf();
      Class var1 = zzcaf.class;
      synchronized(zzcaf.class) {
         zzaXH = var0;
      }
   }
}
