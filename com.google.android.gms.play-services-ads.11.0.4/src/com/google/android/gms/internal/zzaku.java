package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzaku implements Runnable {
   // $FF: synthetic field
   private int zzacD;
   // $FF: synthetic field
   private int zzacE;
   // $FF: synthetic field
   private boolean zzacF;
   // $FF: synthetic field
   private boolean zzacG;
   // $FF: synthetic field
   private zzaks zzacC;

   zzaku(zzaks var1, int var2, int var3, boolean var4, boolean var5) {
      this.zzacC = var1;
      this.zzacD = var2;
      this.zzacE = var3;
      this.zzacF = var4;
      this.zzacG = var5;
      super();
   }

   public final void run() {
      synchronized(zzaks.zzc(this.zzacC)) {
         boolean var2 = this.zzacD != this.zzacE;
         boolean var3 = !zzaks.zzd(this.zzacC) && this.zzacE == 1;
         boolean var4 = var2 && this.zzacE == 1;
         boolean var5 = var2 && this.zzacE == 2;
         boolean var6 = var2 && this.zzacE == 3;
         boolean var7 = this.zzacF != this.zzacG;
         zzaks.zza(this.zzacC, zzaks.zzd(this.zzacC) || var3);
         if (zzaks.zze(this.zzacC) != null) {
            if (var3) {
               try {
                  zzaks.zze(this.zzacC).onVideoStart();
               } catch (RemoteException var14) {
                  zzafr.zzc("Unable to call onVideoStart()", var14);
               }
            }

            if (var4) {
               try {
                  zzaks.zze(this.zzacC).onVideoPlay();
               } catch (RemoteException var13) {
                  zzafr.zzc("Unable to call onVideoPlay()", var13);
               }
            }

            if (var5) {
               try {
                  zzaks.zze(this.zzacC).onVideoPause();
               } catch (RemoteException var12) {
                  zzafr.zzc("Unable to call onVideoPause()", var12);
               }
            }

            if (var6) {
               try {
                  zzaks.zze(this.zzacC).onVideoEnd();
               } catch (RemoteException var11) {
                  zzafr.zzc("Unable to call onVideoEnd()", var11);
               }
            }

            if (var7) {
               try {
                  zzaks.zze(this.zzacC).onVideoMute(this.zzacG);
               } catch (RemoteException var10) {
                  zzafr.zzc("Unable to call onVideoMute()", var10);
               }
            }

         }
      }
   }
}
