package com.google.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzd {
   private static volatile boolean zzcrP = false;
   private static final Class zzcrQ = zzLs();
   static final zzd zzcrR = new zzd(true);
   private final Map zzcrS;

   private static Class zzLs() {
      try {
         return Class.forName("com.google.protobuf.Extension");
      } catch (ClassNotFoundException var0) {
         return null;
      }
   }

   zzd() {
      this.zzcrS = new HashMap();
   }

   zzd(boolean var1) {
      this.zzcrS = Collections.emptyMap();
   }
}
