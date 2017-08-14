package com.google.android.gms.cast.framework.media;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.gms.R.id;
import com.google.android.gms.R.layout;
import com.google.android.gms.R.string;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.List;

public final class zzal extends ArrayAdapter implements OnClickListener {
   private final Context mContext;
   private int zzauM = -1;

   public zzal(Context var1, List var2, int var3) {
      super(var1, layout.cast_tracks_chooser_dialog_row_layout, (List)(var2 != null ? var2 : new ArrayList()));
      this.mContext = var1;
      this.zzauM = var3;
   }

   public final View getView(int var1, View var2, ViewGroup var3) {
      zzan var4;
      if (var2 == null) {
         var2 = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(layout.cast_tracks_chooser_dialog_row_layout, var3, false);
         var4 = new zzan(this, (TextView)var2.findViewById(id.text), (RadioButton)var2.findViewById(id.radio), (zzam)null);
         var2.setTag(var4);
      } else {
         var4 = (zzan)var2.getTag();
      }

      if (var4 == null) {
         return null;
      } else {
         String var10000;
         label30: {
            var4.zzauO.setTag(var1);
            var4.zzauO.setChecked(this.zzauM == var1);
            var2.setOnClickListener(this);
            MediaTrack var7 = (MediaTrack)this.getItem(var1);
            String var8;
            if (TextUtils.isEmpty(var8 = var7.getName())) {
               if (var7.getSubtype() == 2) {
                  var10000 = this.mContext.getString(string.cast_tracks_chooser_dialog_closed_captions);
                  break label30;
               }

               String var9;
               if (!TextUtils.isEmpty(var7.getLanguage()) && !TextUtils.isEmpty(var9 = MediaUtils.getTrackLanguage(var7).getDisplayLanguage())) {
                  var10000 = var9;
                  break label30;
               }

               var8 = this.mContext.getString(string.cast_tracks_chooser_dialog_default_track_name, new Object[]{var1 + 1});
            }

            var10000 = var8;
         }

         String var5 = var10000;
         var4.zzauN.setText(var5);
         return var2;
      }
   }

   public final void onClick(View var1) {
      zzan var2 = (zzan)var1.getTag();
      this.zzauM = ((Integer)var2.zzauO.getTag()).intValue();
      this.notifyDataSetChanged();
   }

   public final MediaTrack zzod() {
      return this.zzauM >= 0 && this.zzauM < this.getCount() ? (MediaTrack)this.getItem(this.zzauM) : null;
   }
}
