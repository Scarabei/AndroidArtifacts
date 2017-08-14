package android.support.v7.app;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.Menu;
import android.view.Window;
import android.view.Window.Callback;
import java.util.List;

@RequiresApi(24)
class AppCompatDelegateImplN extends AppCompatDelegateImplV23 {
   AppCompatDelegateImplN(Context context, Window window, AppCompatCallback callback) {
      super(context, window, callback);
   }

   Callback wrapWindowCallback(Callback callback) {
      return new AppCompatDelegateImplN.AppCompatWindowCallbackN(callback);
   }

   class AppCompatWindowCallbackN extends AppCompatDelegateImplV23.AppCompatWindowCallbackV23 {
      AppCompatWindowCallbackN(Callback callback) {
         super(callback);
      }

      public void onProvideKeyboardShortcuts(List data, Menu menu, int deviceId) {
         AppCompatDelegateImplV9.PanelFeatureState panel = AppCompatDelegateImplN.this.getPanelState(0, true);
         if (panel != null && panel.menu != null) {
            super.onProvideKeyboardShortcuts(data, panel.menu, deviceId);
         } else {
            super.onProvideKeyboardShortcuts(data, menu, deviceId);
         }

      }
   }
}
