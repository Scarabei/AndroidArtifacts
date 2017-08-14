package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.zzbo;

public class SupportErrorDialogFragment extends DialogFragment {
   private Dialog mDialog = null;
   private OnCancelListener zzazZ = null;

   public Dialog onCreateDialog(Bundle var1) {
      if (this.mDialog == null) {
         this.setShowsDialog(false);
      }

      return this.mDialog;
   }

   public void onCancel(DialogInterface var1) {
      if (this.zzazZ != null) {
         this.zzazZ.onCancel(var1);
      }

   }

   public static SupportErrorDialogFragment newInstance(Dialog var0) {
      return newInstance(var0, (OnCancelListener)null);
   }

   public static SupportErrorDialogFragment newInstance(Dialog var0, OnCancelListener var1) {
      SupportErrorDialogFragment var2 = new SupportErrorDialogFragment();
      Dialog var3;
      (var3 = (Dialog)zzbo.zzb(var0, "Cannot display null dialog")).setOnCancelListener((OnCancelListener)null);
      var3.setOnDismissListener((OnDismissListener)null);
      var2.mDialog = var3;
      if (var1 != null) {
         var2.zzazZ = var1;
      }

      return var2;
   }

   public void show(FragmentManager var1, String var2) {
      super.show(var1, var2);
   }
}
