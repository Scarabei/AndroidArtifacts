package com.google.android.gms.internal;

public class zzbez {
   private static final Object zzuF = new Object();
   private static zzbff zzaFo = null;
   private static int zzaFp = 0;
   private static String READ_PERMISSION = "com.google.android.providers.gsf.permission.READ_GSERVICES";
   private String zzBN;
   private Object zzBO;
   private Object zzaFq = null;

   protected zzbez(String var1, Object var2) {
      this.zzBN = var1;
      this.zzBO = var2;
   }

   public static zzbez zzg(String var0, boolean var1) {
      return new zzbfa(var0, var1);
   }

   public static zzbez zza(String var0, Long var1) {
      return new zzbfb(var0, var1);
   }

   public static zzbez zza(String var0, Integer var1) {
      return new zzbfc(var0, var1);
   }

   public static zzbez zza(String var0, Float var1) {
      return new zzbfd(var0, var1);
   }

   public static zzbez zzv(String var0, String var1) {
      return new zzbfe(var0, var1);
   }
}
