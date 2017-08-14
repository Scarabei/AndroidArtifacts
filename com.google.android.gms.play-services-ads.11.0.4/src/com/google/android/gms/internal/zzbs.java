package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public abstract class zzbs {
   private static final String TAG = zzbs.class.getSimpleName();

   protected abstract HashMap zzv();

   protected abstract void zzi(String var1);

   public String toString() {
      try {
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();
         ObjectOutputStream var3;
         (var3 = new ObjectOutputStream(var2)).writeObject(this.zzv());
         var3.close();
         String var1 = Base64.encodeToString(var2.toByteArray(), 0);
         return var1;
      } catch (IOException var4) {
         return null;
      }
   }

   protected static HashMap zzj(String var0) {
      try {
         if (!TextUtils.isEmpty(var0)) {
            ByteArrayInputStream var1 = new ByteArrayInputStream(Base64.decode(var0.getBytes(), 0));
            return (HashMap)(new ObjectInputStream(var1)).readObject();
         }
      } catch (ClassNotFoundException | IOException var2) {
         Log.d(TAG, "decode object failure");
      }

      return null;
   }
}
