package com.google.android.gms.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzbo;

public class ErrorDialogFragment extends DialogFragment {
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

   public static ErrorDialogFragment newInstance(Dialog var0) {
      return newInstance(var0, (OnCancelListener)null);
   }

   public static ErrorDialogFragment newInstance(Dialog var0, OnCancelListener var1) {
      ErrorDialogFragment var2 = new ErrorDialogFragment();
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
