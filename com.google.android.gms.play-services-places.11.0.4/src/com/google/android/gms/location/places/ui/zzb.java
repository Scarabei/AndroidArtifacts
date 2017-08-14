package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.util.TypedValue;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

public class zzb {
   protected final Intent mIntent;

   public zzb(String var1) {
      this.mIntent = new Intent(var1);
      this.mIntent.setPackage("com.google.android.gms");
   }

   protected Intent build(Activity var1) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
      Theme var2 = var1.getTheme();
      TypedValue var3 = new TypedValue();
      TypedValue var4 = new TypedValue();
      if (var2.resolveAttribute(16843827, var3, true) && !this.mIntent.hasExtra("primary_color")) {
         this.mIntent.putExtra("primary_color", var3.data);
      }

      if (var2.resolveAttribute(16843828, var4, true) && !this.mIntent.hasExtra("primary_color_dark")) {
         this.mIntent.putExtra("primary_color_dark", var4.data);
      }

      GoogleApiAvailability.getInstance();
      GoogleApiAvailability.zzas(var1);
      return this.mIntent;
   }
}
