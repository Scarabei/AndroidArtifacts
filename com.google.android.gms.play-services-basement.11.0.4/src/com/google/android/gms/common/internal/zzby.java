package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.R.string;

public final class zzby {
   private final Resources zzaIw;
   private final String zzaIx;

   public zzby(Context var1) {
      zzbo.zzu(var1);
      this.zzaIw = var1.getResources();
      this.zzaIx = this.zzaIw.getResourcePackageName(string.common_google_play_services_unknown_issue);
   }

   public final String getString(String var1) {
      int var2;
      return (var2 = this.zzaIw.getIdentifier(var1, "string", this.zzaIx)) == 0 ? null : this.zzaIw.getString(var2);
   }
}
