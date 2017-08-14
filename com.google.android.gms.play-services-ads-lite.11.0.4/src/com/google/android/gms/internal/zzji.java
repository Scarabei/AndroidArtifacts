package com.google.android.gms.internal;

@zzzn
public final class zzji {
   private static final Object zzuF = new Object();
   private static zzji zzAL;
   private final zzaiy zzAM = new zzaiy();
   private final zziz zzAN = new zziz(new zziq(), new zzip(), new zzli(), new zzqc(), new zzadh(), new zzww());

   private static zzji zzdr() {
      Object var0 = zzuF;
      synchronized(zzuF) {
         return zzAL;
      }
   }

   public static zzaiy zzds() {
      return zzdr().zzAM;
   }

   public static zziz zzdt() {
      return zzdr().zzAN;
   }

   static {
      zzji var0 = new zzji();
      Object var1 = zzuF;
      synchronized(zzuF) {
         zzAL = var0;
      }
   }
}
