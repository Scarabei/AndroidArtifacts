package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.view.KeyEvent;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzauj;
import com.google.android.gms.internal.zzauk;
import com.google.android.gms.internal.zzauo;
import com.google.android.gms.internal.zzavb;
import com.google.android.gms.internal.zzayo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class CastContext {
   private static final zzayo zzarK = new zzayo("CastContext");
   public static final String OPTIONS_PROVIDER_CLASS_NAME_KEY = "com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME";
   private static CastContext zzarL;
   private final Context zzarM;
   private final zzk zzarN;
   private final SessionManager zzarO;
   private final zze zzarP;
   private final CastOptions zzarQ;
   private zzavb zzarR;

   public static CastContext getSharedInstance(@NonNull Context var0) throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      if (zzarL == null) {
         OptionsProvider var1 = zzak(var0.getApplicationContext());
         zzarL = new CastContext(var0, var1.getCastOptions(var0.getApplicationContext()), var1.getAdditionalSessionProviders(var0.getApplicationContext()));
      }

      return zzarL;
   }

   private static OptionsProvider zzak(Context var0) throws IllegalStateException {
      try {
         String var2;
         if ((var2 = var0.getPackageManager().getApplicationInfo(var0.getPackageName(), 128).metaData.getString("com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME")) == null) {
            throw new IllegalStateException("The fully qualified name of the implementation of OptionsProvider must be provided as a metadata in the AndroidManifest.xml with key com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME.");
         } else {
            return (OptionsProvider)Class.forName(var2).newInstance();
         }
      } catch (ClassNotFoundException | NullPointerException | InstantiationException | IllegalAccessException | NameNotFoundException var3) {
         throw new IllegalStateException("Failed to initialize CastContext.", var3);
      }
   }

   private CastContext(Context var1, CastOptions var2, List var3) {
      this.zzarM = var1.getApplicationContext();
      this.zzarQ = var2;
      this.zzarR = new zzavb(MediaRouter.getInstance(this.zzarM));
      HashMap var4 = new HashMap();
      zzauk var5 = new zzauk(this.zzarM, var2, this.zzarR);
      var4.put(var5.getCategory(), var5.zznH());
      if (var3 != null) {
         Iterator var6 = var3.iterator();

         while(var6.hasNext()) {
            SessionProvider var7;
            zzbo.zzb(var7 = (SessionProvider)var6.next(), "Additional SessionProvider must not be null.");
            String var8 = zzbo.zzh(var7.getCategory(), "Category for SessionProvider must not be null or empty string.");
            zzbo.zzb(!var4.containsKey(var8), String.format("SessionProvider for category %s already added", var8));
            var4.put(var8, var7.zznH());
         }
      }

      this.zzarN = zzauj.zza((Context)this.zzarM, (CastOptions)var2, (zzauo)this.zzarR, (Map)var4);
      zzq var11 = null;

      try {
         var11 = this.zzarN.zznt();
      } catch (RemoteException var10) {
         zzarK.zzb(var10, "Unable to call %s on %s.", new Object[]{"getDiscoveryManagerImpl", zzk.class.getSimpleName()});
      }

      this.zzarP = var11 == null ? null : new zze(var11);
      zzw var12 = null;

      try {
         var12 = this.zzarN.zzns();
      } catch (RemoteException var9) {
         zzarK.zzb(var9, "Unable to call %s on %s.", new Object[]{"getSessionManagerImpl", zzk.class.getSimpleName()});
      }

      this.zzarO = var12 == null ? null : new SessionManager(var12);
   }

   public final CastOptions getCastOptions() throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzarQ;
   }

   public final SessionManager getSessionManager() throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzarO;
   }

   public final MediaRouteSelector getMergedSelector() throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         return MediaRouteSelector.fromBundle(this.zzarN.zznr());
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"getMergedSelectorAsBundle", zzk.class.getSimpleName()});
         return null;
      }
   }

   public final int getCastState() {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzarO.getCastState();
   }

   public final boolean isAppVisible() throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");

      try {
         return this.zzarN.isAppVisible();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"isApplicationVisible", zzk.class.getSimpleName()});
         return false;
      }
   }

   public final void addAppVisibilityListener(AppVisibilityListener var1) throws IllegalStateException, NullPointerException {
      zzbo.zzcz("Must be called from the main thread.");
      zzbo.zzu(var1);

      try {
         this.zzarN.zza(new zza(var1));
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"addVisibilityChangeListener", zzk.class.getSimpleName()});
      }
   }

   public final void removeAppVisibilityListener(AppVisibilityListener var1) throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      if (var1 != null) {
         try {
            this.zzarN.zzb(new zza(var1));
         } catch (RemoteException var3) {
            zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"addVisibilityChangeListener", zzk.class.getSimpleName()});
         }
      }
   }

   public final void addCastStateListener(CastStateListener var1) throws IllegalStateException, NullPointerException {
      zzbo.zzcz("Must be called from the main thread.");
      zzbo.zzu(var1);
      this.zzarO.addCastStateListener(var1);
   }

   public final void removeCastStateListener(CastStateListener var1) throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      if (var1 != null) {
         this.zzarO.removeCastStateListener(var1);
      }
   }

   public final zze zzno() {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzarP;
   }

   /** @deprecated */
   @Deprecated
   public final void registerLifecycleCallbacksBeforeIceCreamSandwich(@NonNull FragmentActivity var1, Bundle var2) {
   }

   public final boolean onDispatchVolumeKeyEventBeforeJellyBean(KeyEvent var1) {
      zzbo.zzcz("Must be called from the main thread.");
      if (com.google.android.gms.common.util.zzq.zzrZ()) {
         return false;
      } else {
         CastSession var2;
         if ((var2 = this.zzarO.getCurrentCastSession()) != null && var2.isConnected()) {
            double var3 = this.getCastOptions().getVolumeDeltaBeforeIceCreamSandwich();
            boolean var5 = var1.getAction() == 0;
            switch(var1.getKeyCode()) {
            case 24:
               zza(var2, var3, var5);
               return true;
            case 25:
               zza(var2, -var3, var5);
               return true;
            default:
               return false;
            }
         } else {
            return false;
         }
      }
   }

   private static boolean zza(CastSession var0, double var1, boolean var3) {
      if (var3) {
         try {
            double var4;
            if ((var4 = var0.getVolume() + var1) > 1.0D) {
               var4 = 1.0D;
            }

            var0.setVolume(var4);
         } catch (IllegalStateException | IOException var6) {
            zzarK.zzc("Unable to call CastSession.setVolume(double).", new Object[]{var6});
         }
      }

      return true;
   }

   public final IObjectWrapper zznp() {
      try {
         return this.zzarN.zznu();
      } catch (RemoteException var2) {
         zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"getWrappedThis", zzk.class.getSimpleName()});
         return null;
      }
   }
}
