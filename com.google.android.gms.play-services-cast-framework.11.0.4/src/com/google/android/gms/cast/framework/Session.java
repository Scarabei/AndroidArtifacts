package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzauj;
import com.google.android.gms.internal.zzayo;

public abstract class Session {
   private static final zzayo zzarK = new zzayo("Session");
   private final zzu zzast;
   private final Session.zza zzasu = new Session.zza((zzae)null);

   protected Session(Context var1, String var2, String var3) {
      this.zzast = zzauj.zza((Context)var1, (String)var2, (String)var3, (zzac)this.zzasu);
   }

   protected abstract void start(Bundle var1);

   protected abstract void resume(Bundle var1);

   protected abstract void end(boolean var1);

   public long getSessionRemainingTimeMs() {
      zzbo.zzcz("Must be called from the main thread.");
      return 0L;
   }

   public final String getCategory() {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         return this.zzast.getCategory();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"getCategory", zzu.class.getSimpleName()});
         return null;
      }
   }

   public final String getSessionId() {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         return this.zzast.getSessionId();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"getSessionId", zzu.class.getSimpleName()});
         return null;
      }
   }

   public boolean isConnected() {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         return this.zzast.isConnected();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"isConnected", zzu.class.getSimpleName()});
         return false;
      }
   }

   public boolean isConnecting() {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         return this.zzast.isConnecting();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"isConnecting", zzu.class.getSimpleName()});
         return false;
      }
   }

   public boolean isDisconnecting() {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         return this.zzast.isDisconnecting();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"isDisconnecting", zzu.class.getSimpleName()});
         return false;
      }
   }

   public boolean isDisconnected() {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         return this.zzast.isDisconnected();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"isDisconnected", zzu.class.getSimpleName()});
         return true;
      }
   }

   public boolean isResuming() {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         return this.zzast.isResuming();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"isResuming", zzu.class.getSimpleName()});
         return false;
      }
   }

   public boolean isSuspended() {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         return this.zzast.isSuspended();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"isSuspended", zzu.class.getSimpleName()});
         return false;
      }
   }

   protected final void notifySessionStarted(String var1) {
      try {
         this.zzast.notifySessionStarted(var1);
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"notifySessionStarted", zzu.class.getSimpleName()});
      }
   }

   protected final void notifyFailedToStartSession(int var1) {
      try {
         this.zzast.notifyFailedToStartSession(var1);
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"notifyFailedToStartSession", zzu.class.getSimpleName()});
      }
   }

   protected final void notifySessionEnded(int var1) {
      try {
         this.zzast.notifySessionEnded(var1);
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"notifySessionEnded", zzu.class.getSimpleName()});
      }
   }

   protected final void notifySessionResumed(boolean var1) {
      try {
         this.zzast.notifySessionResumed(var1);
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"notifySessionResumed", zzu.class.getSimpleName()});
      }
   }

   protected final void notifyFailedToResumeSession(int var1) {
      try {
         this.zzast.notifyFailedToResumeSession(var1);
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"notifyFailedToResumeSession", zzu.class.getSimpleName()});
      }
   }

   protected final void notifySessionSuspended(int var1) {
      try {
         this.zzast.notifySessionSuspended(var1);
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"notifySessionSuspended", zzu.class.getSimpleName()});
      }
   }

   public final IObjectWrapper zznw() {
      try {
         return this.zzast.zznw();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"getWrappedObject", zzu.class.getSimpleName()});
         return null;
      }
   }

   class zza extends zzad {
      // $FF: synthetic field
      private Session zzasv;

      private zza() {
         this.zzasv = Session.this;
         super();
      }

      public final IObjectWrapper zznz() {
         return com.google.android.gms.dynamic.zzn.zzw(this.zzasv);
      }

      public final void start(Bundle var1) {
         this.zzasv.start(var1);
      }

      public final void resume(Bundle var1) {
         this.zzasv.resume(var1);
      }

      public final void end(boolean var1) {
         this.zzasv.end(var1);
      }

      public final long getSessionRemainingTimeMs() {
         return this.zzasv.getSessionRemainingTimeMs();
      }

      // $FF: synthetic method
      zza(zzae var2) {
         this();
      }
   }
}
