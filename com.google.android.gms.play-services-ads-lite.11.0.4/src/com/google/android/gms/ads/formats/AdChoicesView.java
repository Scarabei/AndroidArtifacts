package com.google.android.gms.ads.formats;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class AdChoicesView extends RelativeLayout {
   public AdChoicesView(Context var1) {
      super(var1);
   }

   public AdChoicesView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public AdChoicesView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   @TargetApi(21)
   public AdChoicesView(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }
}
