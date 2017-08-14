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
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzbw;
import com.google.android.gms.maps.internal.zzbx;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
   private final StreetViewPanoramaView.zzb zzbmS;

   public StreetViewPanoramaView(Context var1) {
      super(var1);
      this.zzbmS = new StreetViewPanoramaView.zzb(this, var1, (StreetViewPanoramaOptions)null);
   }

   public StreetViewPanoramaView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.zzbmS = new StreetViewPanoramaView.zzb(this, var1, (StreetViewPanoramaOptions)null);
   }

   public StreetViewPanoramaView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.zzbmS = new StreetViewPanoramaView.zzb(this, var1, (StreetViewPanoramaOptions)null);
   }

   public StreetViewPanoramaView(Context var1, StreetViewPanoramaOptions var2) {
      super(var1);
      this.zzbmS = new StreetViewPanoramaView.zzb(this, var1, var2);
   }

   public final void onCreate(Bundle var1) {
      this.zzbmS.onCreate(var1);
      if (this.zzbmS.zztx() == null) {
         com.google.android.gms.dynamic.zza.zzb(this);
      }

   }

   public final void onResume() {
      this.zzbmS.onResume();
   }

   public final void onPause() {
      this.zzbmS.onPause();
   }

   public final void onDestroy() {
      this.zzbmS.onDestroy();
   }

   public final void onLowMemory() {
      this.zzbmS.onLowMemory();
   }

   public final void onSaveInstanceState(Bundle var1) {
      this.zzbmS.onSaveInstanceState(var1);
   }

   public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback var1) {
      zzbo.zzcz("getStreetViewPanoramaAsync() must be called on the main thread");
      this.zzbmS.getStreetViewPanoramaAsync(var1);
   }

   static class zzb extends com.google.android.gms.dynamic.zza {
      private final ViewGroup zzbmy;
      private final Context zzbmz;
      private com.google.android.gms.dynamic.zzo zzbms;
      private final StreetViewPanoramaOptions zzbmV;
      private final List zzbmK = new ArrayList();

      zzb(ViewGroup var1, Context var2, StreetViewPanoramaOptions var3) {
         this.zzbmy = var1;
         this.zzbmz = var2;
         this.zzbmV = var3;
      }

      protected final void zza(com.google.android.gms.dynamic.zzo var1) {
         this.zzbms = var1;
         StreetViewPanoramaView.zzb var2 = this;
         if (this.zzbms != null && this.zztx() == null) {
            try {
               IStreetViewPanoramaViewDelegate var3 = zzbx.zzbh(var2.zzbmz).zza(com.google.android.gms.dynamic.zzn.zzw(var2.zzbmz), var2.zzbmV);
               var2.zzbms.zza(new StreetViewPanoramaView.zza(var2.zzbmy, var3));
               Iterator var4 = var2.zzbmK.iterator();

               while(var4.hasNext()) {
                  OnStreetViewPanoramaReadyCallback var5 = (OnStreetViewPanoramaReadyCallback)var4.next();
                  ((StreetViewPanoramaView.zza)var2.zztx()).getStreetViewPanoramaAsync(var5);
               }

               var2.zzbmK.clear();
               return;
            } catch (RemoteException var6) {
               throw new RuntimeRemoteException(var6);
            } catch (GooglePlayServicesNotAvailableException var7) {
               ;
            }
         }

      }

      public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback var1) {
         if (this.zztx() != null) {
            ((StreetViewPanoramaView.zza)this.zztx()).getStreetViewPanoramaAsync(var1);
         } else {
            this.zzbmK.add(var1);
         }
      }
   }

   static class zza implements StreetViewLifecycleDelegate {
      private final ViewGroup zzbmv;
      private final IStreetViewPanoramaViewDelegate zzbmT;
      private View zzbmU;

      public zza(ViewGroup var1, IStreetViewPanoramaViewDelegate var2) {
         this.zzbmT = (IStreetViewPanoramaViewDelegate)zzbo.zzu(var2);
         this.zzbmv = (ViewGroup)zzbo.zzu(var1);
      }

      public final void onInflate(Activity var1, Bundle var2, Bundle var3) {
         throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
      }

      public final void onCreate(Bundle var1) {
         try {
            Bundle var2 = new Bundle();
            zzbw.zzd(var1, var2);
            this.zzbmT.onCreate(var2);
            zzbw.zzd(var2, var1);
            this.zzbmU = (View)com.google.android.gms.dynamic.zzn.zzE(this.zzbmT.getView());
            this.zzbmv.removeAllViews();
            this.zzbmv.addView(this.zzbmU);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }

      public final View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
         throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
      }

      public final void onStart() {
      }

      public final void onResume() {
         try {
            this.zzbmT.onResume();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onPause() {
         try {
            this.zzbmT.onPause();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onStop() {
      }

      public final void onDestroyView() {
         throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
      }

      public final void onDestroy() {
         try {
            this.zzbmT.onDestroy();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onLowMemory() {
         try {
            this.zzbmT.onLowMemory();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onSaveInstanceState(Bundle var1) {
         try {
            Bundle var2 = new Bundle();
            zzbw.zzd(var1, var2);
            this.zzbmT.onSaveInstanceState(var2);
            zzbw.zzd(var2, var1);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }

      public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback var1) {
         try {
            this.zzbmT.getStreetViewPanoramaAsync(new zzai(this, var1));
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }
   }
}
