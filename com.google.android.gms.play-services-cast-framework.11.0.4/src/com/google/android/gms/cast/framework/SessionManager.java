package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzayo;

public class SessionManager {
   private static final zzayo zzarK = new zzayo("SessionManager");
   private final zzw zzasw;

   public SessionManager(zzw var1) {
      this.zzasw = var1;
   }

   public Session getCurrentSession() {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         return (Session)com.google.android.gms.dynamic.zzn.zzE(this.zzasw.zzny());
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"getWrappedCurrentSession", zzw.class.getSimpleName()});
         return null;
      }
   }

   public CastSession getCurrentCastSession() {
      zzbo.zzcz("Must be called from the main thread.");
      Session var1;
      return (var1 = this.getCurrentSession()) != null && var1 instanceof CastSession ? (CastSession)var1 : null;
   }

   public void endCurrentSession(boolean var1) {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         this.zzasw.zzb(true, var1);
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"endCurrentSession", zzw.class.getSimpleName()});
      }
   }

   public void addSessionManagerListener(SessionManagerListener var1) throws NullPointerException {
      zzbo.zzcz("Must be called from the main thread.");
      this.addSessionManagerListener(var1, Session.class);
   }

   public void addSessionManagerListener(SessionManagerListener var1, Class var2) throws NullPointerException {
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      zzbo.zzcz("Must be called from the main thread.");

      try {
         this.zzasw.zza((zzy)(new zzaf(var1, var2)));
      } catch (RemoteException var4) {
         zzarK.zzb(var4, "Unable to call %s on %s.", new Object[]{"addSessionManagerListener", zzw.class.getSimpleName()});
      }
   }

   public void removeSessionManagerListener(SessionManagerListener var1) {
      zzbo.zzcz("Must be called from the main thread.");
      this.removeSessionManagerListener(var1, Session.class);
   }

   public void removeSessionManagerListener(SessionManagerListener var1, Class var2) {
      zzbo.zzu(var2);
      zzbo.zzcz("Must be called from the main thread.");
      if (var1 != null) {
         try {
            this.zzasw.zzb((zzy)(new zzaf(var1, var2)));
         } catch (RemoteException var4) {
            zzarK.zzb(var4, "Unable to call %s on %s.", new Object[]{"removeSessionManagerListener", zzw.class.getSimpleName()});
         }
      }
   }

   final int getCastState() {
      try {
         return this.zzasw.getCastState();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"addCastStateListener", zzw.class.getSimpleName()});
         return 1;
      }
   }

   final void addCastStateListener(CastStateListener var1) throws NullPointerException {
      zzbo.zzu(var1);

      try {
         this.zzasw.zza((zzo)(new zzd(var1)));
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"addCastStateListener", zzw.class.getSimpleName()});
      }
   }

   final void removeCastStateListener(CastStateListener var1) {
      if (var1 != null) {
         try {
            this.zzasw.zzb((zzo)(new zzd(var1)));
         } catch (RemoteException var3) {
            zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"removeCastStateListener", zzw.class.getSimpleName()});
         }
      }
   }

   public final IObjectWrapper zznp() {
      try {
         return this.zzasw.zznu();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"getWrappedThis", zzw.class.getSimpleName()});
         return null;
      }
   }
}
