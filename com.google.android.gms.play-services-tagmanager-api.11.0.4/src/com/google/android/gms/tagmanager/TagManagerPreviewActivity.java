package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class TagManagerPreviewActivity extends Activity {
   public void onCreate(Bundle var1) {
      Log.v("GoogleTagManager", "TagManagerPreviewActivity created.");
      super.onCreate(var1);
      if (this.getIntent().getData() == null) {
         Log.e("GoogleTagManager", "Activity intent has no data.");
      } else {
         zzbf.zza(this.getIntent(), this);
      }
   }
}
