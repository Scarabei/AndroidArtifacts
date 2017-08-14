package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.media.MediaRouteSelector;

public class MediaRouteChooserDialogFragment extends DialogFragment {
   private final String ARGUMENT_SELECTOR = "selector";
   private MediaRouteChooserDialog mDialog;
   private MediaRouteSelector mSelector;

   public MediaRouteChooserDialogFragment() {
      this.setCancelable(true);
   }

   public MediaRouteSelector getRouteSelector() {
      this.ensureRouteSelector();
      return this.mSelector;
   }

   private void ensureRouteSelector() {
      if (this.mSelector == null) {
         Bundle args = this.getArguments();
         if (args != null) {
            this.mSelector = MediaRouteSelector.fromBundle(args.getBundle("selector"));
         }

         if (this.mSelector == null) {
            this.mSelector = MediaRouteSelector.EMPTY;
         }
      }

   }

   public void setRouteSelector(MediaRouteSelector selector) {
      if (selector == null) {
         throw new IllegalArgumentException("selector must not be null");
      } else {
         this.ensureRouteSelector();
         if (!this.mSelector.equals(selector)) {
            this.mSelector = selector;
            Bundle args = this.getArguments();
            if (args == null) {
               args = new Bundle();
            }

            args.putBundle("selector", selector.asBundle());
            this.setArguments(args);
            MediaRouteChooserDialog dialog = (MediaRouteChooserDialog)this.getDialog();
            if (dialog != null) {
               dialog.setRouteSelector(selector);
            }
         }

      }
   }

   public MediaRouteChooserDialog onCreateChooserDialog(Context context, Bundle savedInstanceState) {
      return new MediaRouteChooserDialog(context);
   }

   public Dialog onCreateDialog(Bundle savedInstanceState) {
      this.mDialog = this.onCreateChooserDialog(this.getContext(), savedInstanceState);
      this.mDialog.setRouteSelector(this.getRouteSelector());
      return this.mDialog;
   }

   public void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      if (this.mDialog != null) {
         this.mDialog.updateLayout();
      }

   }
}
