package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbo;

public final class zzaml {
   private final Context mApplicationContext;
   private final Context zzagb;

   public zzaml(Context var1) {
      zzbo.zzu(var1);
      Context var2;
      zzbo.zzb(var2 = var1.getApplicationContext(), "Application context can't be null");
      this.mApplicationContext = var2;
      this.zzagb = var2;
   }

   public final Context getApplicationContext() {
      return this.mApplicationContext;
   }

   public final Context zzkE() {
      return this.zzagb;
   }
}
