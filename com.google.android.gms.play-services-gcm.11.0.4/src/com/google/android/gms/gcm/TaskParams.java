package com.google.android.gms.gcm;

import android.os.Bundle;
import java.util.List;

public class TaskParams {
   private final String tag;
   private final Bundle extras;
   private final List zzbgo;

   public TaskParams(String var1) {
      this(var1, (Bundle)null, (List)null);
   }

   public TaskParams(String var1, Bundle var2) {
      this(var1, var2, (List)null);
   }

   public TaskParams(String var1, Bundle var2, List var3) {
      this.tag = var1;
      this.extras = var2;
      this.zzbgo = var3;
   }

   public String getTag() {
      return this.tag;
   }

   public Bundle getExtras() {
      return this.extras;
   }
}
