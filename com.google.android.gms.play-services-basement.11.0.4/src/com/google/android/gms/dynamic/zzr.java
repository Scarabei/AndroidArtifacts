package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public final class zzr extends zzl {
   private Fragment zzaSE;

   public static zzr zza(Fragment var0) {
      return var0 != null ? new zzr(var0) : null;
   }

   private zzr(Fragment var1) {
      this.zzaSE = var1;
   }

   public final IObjectWrapper zzty() {
      return zzn.zzw(this.zzaSE.getActivity());
   }

   public final Bundle getArguments() {
      return this.zzaSE.getArguments();
   }

   public final int getId() {
      return this.zzaSE.getId();
   }

   public final zzk zztz() {
      return zza(this.zzaSE.getParentFragment());
   }

   public final IObjectWrapper zztA() {
      return zzn.zzw(this.zzaSE.getResources());
   }

   public final boolean getRetainInstance() {
      return this.zzaSE.getRetainInstance();
   }

   public final String getTag() {
      return this.zzaSE.getTag();
   }

   public final zzk zztB() {
      return zza(this.zzaSE.getTargetFragment());
   }

   public final int getTargetRequestCode() {
      return this.zzaSE.getTargetRequestCode();
   }

   public final boolean getUserVisibleHint() {
      return this.zzaSE.getUserVisibleHint();
   }

   public final IObjectWrapper getView() {
      return zzn.zzw(this.zzaSE.getView());
   }

   public final boolean isAdded() {
      return this.zzaSE.isAdded();
   }

   public final boolean isDetached() {
      return this.zzaSE.isDetached();
   }

   public final boolean isHidden() {
      return this.zzaSE.isHidden();
   }

   public final boolean isInLayout() {
      return this.zzaSE.isInLayout();
   }

   public final boolean isRemoving() {
      return this.zzaSE.isRemoving();
   }

   public final boolean isResumed() {
      return this.zzaSE.isResumed();
   }

   public final boolean isVisible() {
      return this.zzaSE.isVisible();
   }

   public final void zzC(IObjectWrapper var1) {
      View var2 = (View)zzn.zzE(var1);
      this.zzaSE.registerForContextMenu(var2);
   }

   public final void setHasOptionsMenu(boolean var1) {
      this.zzaSE.setHasOptionsMenu(var1);
   }

   public final void setMenuVisibility(boolean var1) {
      this.zzaSE.setMenuVisibility(var1);
   }

   public final void setRetainInstance(boolean var1) {
      this.zzaSE.setRetainInstance(var1);
   }

   public final void setUserVisibleHint(boolean var1) {
      this.zzaSE.setUserVisibleHint(var1);
   }

   public final void startActivity(Intent var1) {
      this.zzaSE.startActivity(var1);
   }

   public final void startActivityForResult(Intent var1, int var2) {
      this.zzaSE.startActivityForResult(var1, var2);
   }

   public final void zzD(IObjectWrapper var1) {
      View var2 = (View)zzn.zzE(var1);
      this.zzaSE.unregisterForContextMenu(var2);
   }
}
