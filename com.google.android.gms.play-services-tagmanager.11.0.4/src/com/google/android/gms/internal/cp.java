package com.google.android.gms.internal;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

final class cp implements ct {
   // $FF: synthetic field
   private Context zztF;

   cp(Context var1) {
      this.zztF = var1;
      super();
   }

   public final InputStream open(String var1) throws IOException {
      return this.zztF.getAssets().open(var1);
   }
}
