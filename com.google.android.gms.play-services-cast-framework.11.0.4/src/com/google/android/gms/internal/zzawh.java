package com.google.android.gms.internal;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import java.io.IOException;

final class zzawh implements OnClickListener {
   // $FF: synthetic field
   private zzawf zzavF;

   zzawh(zzawf var1) {
      this.zzavF = var1;
      super();
   }

   public final void onClick(View var1) {
      CastSession var2;
      if ((var2 = CastContext.getSharedInstance(zzawf.zzb(this.zzavF)).getSessionManager().getCurrentCastSession()) != null && var2.isConnected()) {
         try {
            if (var2.isMute()) {
               var2.setMute(false);
               zzawf.zza(this.zzavF, true);
               return;
            }

            var2.setMute(true);
            zzawf.zza(this.zzavF, false);
            return;
         } catch (IllegalArgumentException | IOException var4) {
            zzawf.zzon().zzc("Unable to call CastSession.setMute(boolean).", new Object[]{var4});
         }
      }

   }
}
