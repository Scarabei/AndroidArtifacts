package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@zzzn
public abstract class zzhf {
   @Nullable
   private static MessageDigest zzyW = null;
   protected Object mLock = new Object();

   abstract byte[] zzy(String var1);

   @Nullable
   protected final MessageDigest zzcW() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (zzyW != null) {
            return zzyW;
         } else {
            for(int var2 = 0; var2 < 2; ++var2) {
               try {
                  zzyW = MessageDigest.getInstance("MD5");
               } catch (NoSuchAlgorithmException var4) {
                  ;
               }
            }

            return zzyW;
         }
      }
   }
}
