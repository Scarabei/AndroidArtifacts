package com.google.android.gms.dynamite;

import dalvik.system.PathClassLoader;

final class zzg extends PathClassLoader {
   zzg(String var1, ClassLoader var2) {
      super(var1, var2);
   }

   protected final Class loadClass(String var1, boolean var2) throws ClassNotFoundException {
      if (!var1.startsWith("java.") && !var1.startsWith("android.")) {
         try {
            return this.findClass(var1);
         } catch (ClassNotFoundException var3) {
            ;
         }
      }

      return super.loadClass(var1, var2);
   }
}
