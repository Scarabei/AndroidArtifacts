package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.app.Presentation;
import android.content.Context;
import android.view.Display;
import android.view.Window;

@TargetApi(19)
public class CastPresentation extends Presentation {
   public CastPresentation(Context var1, Display var2) {
      super(var1, var2);
      this.zznc();
   }

   public CastPresentation(Context var1, Display var2, int var3) {
      super(var1, var2, var3);
      this.zznc();
   }

   private final void zznc() {
      Window var1;
      if ((var1 = this.getWindow()) != null) {
         var1.setType(2030);
         var1.addFlags(268435456);
         var1.addFlags(16777216);
         var1.addFlags(1024);
      }

   }
}
