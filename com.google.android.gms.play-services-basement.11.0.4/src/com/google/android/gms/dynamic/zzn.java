package com.google.android.gms.dynamic;

import android.os.IBinder;
import java.lang.reflect.Field;

public final class zzn extends IObjectWrapper.zza {
   private final Object mWrappedObject;

   private zzn(Object var1) {
      this.mWrappedObject = var1;
   }

   public static IObjectWrapper zzw(Object var0) {
      return new zzn(var0);
   }

   public static Object zzE(IObjectWrapper var0) {
      if (var0 instanceof zzn) {
         return ((zzn)var0).mWrappedObject;
      } else {
         IBinder var1;
         Field[] var2 = (var1 = var0.asBinder()).getClass().getDeclaredFields();
         Field var3 = null;
         int var4 = 0;
         Field[] var5 = var2;
         int var6 = var2.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            Field var8;
            if (!(var8 = var5[var7]).isSynthetic()) {
               var3 = var8;
               ++var4;
            }
         }

         if (var4 == 1) {
            if (!var3.isAccessible()) {
               var3.setAccessible(true);

               try {
                  return var3.get(var1);
               } catch (NullPointerException var9) {
                  throw new IllegalArgumentException("Binder object is null.", var9);
               } catch (IllegalAccessException var10) {
                  throw new IllegalArgumentException("Could not access the field in remoteBinder.", var10);
               }
            } else {
               throw new IllegalArgumentException("IObjectWrapper declared field not private!");
            }
         } else {
            int var11 = var2.length;
            throw new IllegalArgumentException((new StringBuilder(64)).append("Unexpected number of IObjectWrapper declared fields: ").append(var11).toString());
         }
      }
   }
}
