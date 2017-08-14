package android.support.v7.app;

import android.app.UiModeManager;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.ActionMode;
import android.view.Window;
import android.view.Window.Callback;

@RequiresApi(23)
class AppCompatDelegateImplV23 extends AppCompatDelegateImplV14 {
   private final UiModeManager mUiModeManager;

   AppCompatDelegateImplV23(Context context, Window window, AppCompatCallback callback) {
      super(context, window, callback);
      this.mUiModeManager = (UiModeManager)context.getSystemService("uimode");
   }

   Callback wrapWindowCallback(Callback callback) {
      return new AppCompatDelegateImplV23.AppCompatWindowCallbackV23(callback);
   }

   int mapNightMode(int mode) {
      return mode == 0 && this.mUiModeManager.getNightMode() == 0 ? -1 : super.mapNightMode(mode);
   }

   class AppCompatWindowCallbackV23 extends AppCompatDelegateImplV14.AppCompatWindowCallbackV14 {
      AppCompatWindowCallbackV23(Callback callback) {
         super(callback);
      }

      public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback, int type) {
         if (AppCompatDelegateImplV23.this.isHandleNativeActionModesEnabled()) {
            switch(type) {
            case 0:
               return this.startAsSupportActionMode(callback);
            }
         }

         return super.onWindowStartingActionMode(callback, type);
      }

      public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback) {
         return null;
      }
   }
}
