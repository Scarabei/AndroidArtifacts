package com.google.protobuf;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

final class zzf implements PrivilegedExceptionAction {
   // $FF: synthetic method
   public final Object run() throws Exception {
      Class var1 = Unsafe.class;
      Field[] var2;
      int var3 = (var2 = Unsafe.class.getDeclaredFields()).length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Field var5;
         (var5 = var2[var4]).setAccessible(true);
         Object var6 = var5.get((Object)null);
         if (var1.isInstance(var6)) {
            return (Unsafe)var1.cast(var6);
         }
      }

      return null;
   }
}
