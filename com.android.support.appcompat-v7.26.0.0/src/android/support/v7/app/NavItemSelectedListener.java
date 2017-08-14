package android.support.v7.app;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class NavItemSelectedListener implements OnItemSelectedListener {
   private final ActionBar.OnNavigationListener mListener;

   public NavItemSelectedListener(ActionBar.OnNavigationListener listener) {
      this.mListener = listener;
   }

   public void onItemSelected(AdapterView parent, View view, int position, long id) {
      if (this.mListener != null) {
         this.mListener.onNavigationItemSelected(position, id);
      }

   }

   public void onNothingSelected(AdapterView parent) {
   }
}
