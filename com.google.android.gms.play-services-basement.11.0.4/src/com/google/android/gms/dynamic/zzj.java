package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@SuppressLint({"NewApi"})
public final class zzj extends zzl {
   private Fragment zzaSB;

   public static zzj zza(Fragment var0) {
      return var0 != null ? new zzj(var0) : null;
   }

   private zzj(Fragment var1) {
      this.zzaSB = var1;
   }

   public final IObjectWrapper zzty() {
      return zzn.zzw(this.zzaSB.getActivity());
   }

   public final Bundle getArguments() {
      return this.zzaSB.getArguments();
   }

   public final int getId() {
      return this.zzaSB.getId();
   }

   public final zzk zztz() {
      return zza(this.zzaSB.getParentFragment());
   }

   public final IObjectWrapper zztA() {
      return zzn.zzw(this.zzaSB.getResources());
   }

   public final boolean getRetainInstance() {
      return this.zzaSB.getRetainInstance();
   }

   public final String getTag() {
      return this.zzaSB.getTag();
   }

   public final zzk zztB() {
      return zza(this.zzaSB.getTargetFragment());
   }

   public final int getTargetRequestCode() {
      return this.zzaSB.getTargetRequestCode();
   }

   public final boolean getUserVisibleHint() {
      return this.zzaSB.getUserVisibleHint();
   }

   public final IObjectWrapper getView() {
      return zzn.zzw(this.zzaSB.getView());
   }

   public final boolean isAdded() {
      return this.zzaSB.isAdded();
   }

   public final boolean isDetached() {
      return this.zzaSB.isDetached();
   }

   public final boolean isHidden() {
      return this.zzaSB.isHidden();
   }

   public final boolean isInLayout() {
      return this.zzaSB.isInLayout();
   }

   public final boolean isRemoving() {
      return this.zzaSB.isRemoving();
   }

   public final boolean isResumed() {
      return this.zzaSB.isResumed();
   }

   public final boolean isVisible() {
      return this.zzaSB.isVisible();
   }

   public final void zzC(IObjectWrapper var1) {
      View var2 = (View)zzn.zzE(var1);
      this.zzaSB.registerForContextMenu(var2);
   }

   public final void setHasOptionsMenu(boolean var1) {
      this.zzaSB.setHasOptionsMenu(var1);
   }

   public final void setMenuVisibility(boolean var1) {
      this.zzaSB.setMenuVisibility(var1);
   }

   public final void setRetainInstance(boolean var1) {
      this.zzaSB.setRetainInstance(var1);
   }

   public final void setUserVisibleHint(boolean var1) {
      this.zzaSB.setUserVisibleHint(var1);
   }

   public final void startActivity(Intent var1) {
      this.zzaSB.startActivity(var1);
   }

   public final void startActivityForResult(Intent var1, int var2) {
      this.zzaSB.startActivityForResult(var1, var2);
   }

   public final void zzD(IObjectWrapper var1) {
      View var2 = (View)zzn.zzE(var1);
      this.zzaSB.unregisterForContextMenu(var2);
   }
}
