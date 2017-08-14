package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzbw;
import com.google.android.gms.maps.internal.zzbx;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapView extends FrameLayout {
   private final MapView.zzb zzbmu;

   public MapView(Context var1) {
      super(var1);
      this.zzbmu = new MapView.zzb(this, var1, (GoogleMapOptions)null);
      this.setClickable(true);
   }

   public MapView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.zzbmu = new MapView.zzb(this, var1, GoogleMapOptions.createFromAttributes(var1, var2));
      this.setClickable(true);
   }

   public MapView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.zzbmu = new MapView.zzb(this, var1, GoogleMapOptions.createFromAttributes(var1, var2));
      this.setClickable(true);
   }

   public MapView(Context var1, GoogleMapOptions var2) {
      super(var1);
      this.zzbmu = new MapView.zzb(this, var1, var2);
      this.setClickable(true);
   }

   public final void onCreate(Bundle var1) {
      this.zzbmu.onCreate(var1);
      if (this.zzbmu.zztx() == null) {
         com.google.android.gms.dynamic.zza.zzb(this);
      }

   }

   public final void onResume() {
      this.zzbmu.onResume();
   }

   public final void onPause() {
      this.zzbmu.onPause();
   }

   public final void onStart() {
      this.zzbmu.onStart();
   }

   public final void onStop() {
      this.zzbmu.onStop();
   }

   public final void onDestroy() {
      this.zzbmu.onDestroy();
   }

   public final void onLowMemory() {
      this.zzbmu.onLowMemory();
   }

   public final void onSaveInstanceState(Bundle var1) {
      this.zzbmu.onSaveInstanceState(var1);
   }

   public void getMapAsync(OnMapReadyCallback var1) {
      zzbo.zzcz("getMapAsync() must be called on the main thread");
      this.zzbmu.getMapAsync(var1);
   }

   public final void onEnterAmbient(Bundle var1) {
      zzbo.zzcz("onEnterAmbient() must be called on the main thread");
      MapView.zzb var2 = this.zzbmu;
      if (this.zzbmu.zztx() != null) {
         ((MapView.zza)var2.zztx()).onEnterAmbient(var1);
      }

   }

   public final void onExitAmbient() {
      zzbo.zzcz("onExitAmbient() must be called on the main thread");
      MapView.zzb var1 = this.zzbmu;
      if (this.zzbmu.zztx() != null) {
         ((MapView.zza)var1.zztx()).onExitAmbient();
      }

   }

   static class zzb extends com.google.android.gms.dynamic.zza {
      private final ViewGroup zzbmy;
      private final Context zzbmz;
      private com.google.android.gms.dynamic.zzo zzbms;
      private final GoogleMapOptions zzbmA;
      private final List zzbmt = new ArrayList();

      zzb(ViewGroup var1, Context var2, GoogleMapOptions var3) {
         this.zzbmy = var1;
         this.zzbmz = var2;
         this.zzbmA = var3;
      }

      protected final void zza(com.google.android.gms.dynamic.zzo var1) {
         this.zzbms = var1;
         MapView.zzb var2 = this;
         if (this.zzbms != null && this.zztx() == null) {
            try {
               MapsInitializer.initialize(var2.zzbmz);
               IMapViewDelegate var3;
               if ((var3 = zzbx.zzbh(var2.zzbmz).zza(com.google.android.gms.dynamic.zzn.zzw(var2.zzbmz), var2.zzbmA)) == null) {
                  return;
               }

               var2.zzbms.zza(new MapView.zza(var2.zzbmy, var3));
               Iterator var4 = var2.zzbmt.iterator();

               while(var4.hasNext()) {
                  OnMapReadyCallback var5 = (OnMapReadyCallback)var4.next();
                  ((MapView.zza)var2.zztx()).getMapAsync(var5);
               }

               var2.zzbmt.clear();
               return;
            } catch (RemoteException var6) {
               throw new RuntimeRemoteException(var6);
            } catch (GooglePlayServicesNotAvailableException var7) {
               ;
            }
         }

      }

      public final void getMapAsync(OnMapReadyCallback var1) {
         if (this.zztx() != null) {
            ((MapView.zza)this.zztx()).getMapAsync(var1);
         } else {
            this.zzbmt.add(var1);
         }
      }
   }

   static class zza implements MapLifecycleDelegate {
      private final ViewGroup zzbmv;
      private final IMapViewDelegate zzbmw;
      private View zzbmx;

      public zza(ViewGroup var1, IMapViewDelegate var2) {
         this.zzbmw = (IMapViewDelegate)zzbo.zzu(var2);
         this.zzbmv = (ViewGroup)zzbo.zzu(var1);
      }

      public final void onInflate(Activity var1, Bundle var2, Bundle var3) {
         throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
      }

      public final void onCreate(Bundle var1) {
         try {
            Bundle var2 = new Bundle();
            zzbw.zzd(var1, var2);
            this.zzbmw.onCreate(var2);
            zzbw.zzd(var2, var1);
            this.zzbmx = (View)com.google.android.gms.dynamic.zzn.zzE(this.zzbmw.getView());
            this.zzbmv.removeAllViews();
            this.zzbmv.addView(this.zzbmx);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }

      public final View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
         throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
      }

      public final void onStart() {
         try {
            this.zzbmw.onStart();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onResume() {
         try {
            this.zzbmw.onResume();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onPause() {
         try {
            this.zzbmw.onPause();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onStop() {
         try {
            this.zzbmw.onStop();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onDestroyView() {
         throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
      }

      public final void onDestroy() {
         try {
            this.zzbmw.onDestroy();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onLowMemory() {
         try {
            this.zzbmw.onLowMemory();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onSaveInstanceState(Bundle var1) {
         try {
            Bundle var2 = new Bundle();
            zzbw.zzd(var1, var2);
            this.zzbmw.onSaveInstanceState(var2);
            zzbw.zzd(var2, var1);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }

      public final void getMapAsync(OnMapReadyCallback var1) {
         try {
            this.zzbmw.getMapAsync(new zzab(this, var1));
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }

      public final void onEnterAmbient(Bundle var1) {
         try {
            Bundle var2 = new Bundle();
            zzbw.zzd(var1, var2);
            this.zzbmw.onEnterAmbient(var2);
            zzbw.zzd(var2, var1);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }

      public final void onExitAmbient() {
         try {
            this.zzbmw.onExitAmbient();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }
   }
}
