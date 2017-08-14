package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class SessionProvider {
   private final Context mApplicationContext;
   private final String mCategory;
   private final SessionProvider.zza zzasz = new SessionProvider.zza((zzag)null);

   protected SessionProvider(Context var1, String var2) {
      this.mApplicationContext = ((Context)zzbo.zzu(var1)).getApplicationContext();
      this.mCategory = zzbo.zzcF(var2);
   }

   public final Context getContext() {
      return this.mApplicationContext;
   }

   public final String getCategory() {
      return this.mCategory;
   }

   public abstract Session createSession(String var1);

   public abstract boolean isSessionRecoverable();

   public final IBinder zznH() {
      return this.zzasz;
   }

   class zza extends zzab {
      // $FF: synthetic field
      private SessionProvider zzasA;

      private zza() {
         this.zzasA = SessionProvider.this;
         super();
      }

      public final IObjectWrapper zzcd(String var1) {
         Session var2;
         return (var2 = this.zzasA.createSession(var1)) == null ? null : var2.zznw();
      }

      public final boolean isSessionRecoverable() {
         return this.zzasA.isSessionRecoverable();
      }

      public final String getCategory() {
         return this.zzasA.getCategory();
      }

      // $FF: synthetic method
      zza(zzag var2) {
         this();
      }
   }
}
