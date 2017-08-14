package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;

final class zzaha implements zznm {
   // $FF: synthetic field
   private List zzZw;
   // $FF: synthetic field
   private zznl zzZx;
   // $FF: synthetic field
   private Context zztF;

   zzaha(zzagz var1, List var2, zznl var3, Context var4) {
      this.zzZw = var2;
      this.zzZx = var3;
      this.zztF = var4;
      super();
   }

   public final void zzea() {
      Iterator var1 = this.zzZw.iterator();

      while(var1.hasNext()) {
         String var2 = (String)var1.next();
         String var10001 = String.valueOf(var2);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Pinging url: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Pinging url: ");
         }

         zzafr.zzaS(var10000);
         Uri var3 = Uri.parse(var2);
         this.zzZx.mayLaunchUrl(var3, (Bundle)null, (List)null);
      }

      this.zzZx.zzc((Activity)this.zztF);
   }

   public final void zzeb() {
   }
}
