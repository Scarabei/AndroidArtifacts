package com.google.protobuf;

final class zzc {
   private static Class zzcrO = zzLq();

   private static Class zzLq() {
      try {
         return Class.forName("com.google.protobuf.ExtensionRegistry");
      } catch (ClassNotFoundException var0) {
         return null;
      }
   }

   public static zzd zzLr() {
      if (zzcrO != null) {
         try {
            String var0 = "getEmptyRegistry";
            return (zzd)zzcrO.getMethod(var0).invoke((Object)null);
         } catch (Exception var1) {
            ;
         }
      }

      return zzd.zzcrR;
   }
}
