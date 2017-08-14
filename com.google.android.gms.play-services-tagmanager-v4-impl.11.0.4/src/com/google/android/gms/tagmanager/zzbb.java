package com.google.android.gms.tagmanager;

import android.content.Context;

public final class zzbb implements zzby {
   private static zzbb zzbEy;
   private static final Object zzbDk = new Object();
   private zzek zzbDM;
   private zzbz zzbEz;

   private zzbb(Context var1) {
      this(zzca.zzbs(var1), new zzfm());
   }

   private zzbb(zzbz var1, zzek var2) {
      this.zzbEz = var1;
      this.zzbDM = var2;
   }

   public static zzby zzbm(Context var0) {
      Object var1 = zzbDk;
      synchronized(zzbDk) {
         if (zzbEy == null) {
            zzbEy = new zzbb(var0);
         }

         return zzbEy;
      }
   }

   public final boolean zzfh(String var1) {
      if (!this.zzbDM.zzlL()) {
         zzdj.zzaT("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
         return false;
      } else {
         this.zzbEz.zzfm(var1);
         return true;
      }
   }
}
