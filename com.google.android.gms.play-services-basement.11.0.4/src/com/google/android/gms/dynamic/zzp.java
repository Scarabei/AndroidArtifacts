package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzbo;

public abstract class zzp {
   private final String zzaSC;
   private Object zzaSD;

   protected zzp(String var1) {
      this.zzaSC = var1;
   }

   protected final Object zzaS(Context var1) throws zzq {
      if (this.zzaSD == null) {
         zzbo.zzu(var1);
         Context var2;
         if ((var2 = com.google.android.gms.common.zzo.getRemoteContext(var1)) == null) {
            throw new zzq("Could not get remote context.");
         }

         ClassLoader var3 = var2.getClassLoader();

         try {
            IBinder var5 = (IBinder)var3.loadClass(this.zzaSC).newInstance();
            this.zzaSD = this.zzb(var5);
         } catch (ClassNotFoundException var6) {
            throw new zzq("Could not load creator class.", var6);
         } catch (InstantiationException var7) {
            throw new zzq("Could not instantiate creator.", var7);
         } catch (IllegalAccessException var8) {
            throw new zzq("Could not access creator.", var8);
         }
      }

      return this.zzaSD;
   }

   protected abstract Object zzb(IBinder var1);
}
