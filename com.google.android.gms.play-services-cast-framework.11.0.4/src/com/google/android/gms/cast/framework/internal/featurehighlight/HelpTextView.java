package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.R.id;
import com.google.android.gms.internal.ik;

@Keep
public class HelpTextView extends LinearLayout implements zzi {
   private TextView zzatj;
   private TextView zzatk;

   @Keep
   public HelpTextView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      this.zzatj = (TextView)ik.zzu((TextView)this.findViewById(id.cast_featurehighlight_help_text_header_view));
      this.zzatk = (TextView)ik.zzu((TextView)this.findViewById(id.cast_featurehighlight_help_text_body_view));
   }

   @Keep
   public void setText(@Nullable CharSequence var1, @Nullable CharSequence var2) {
      zza(this.zzatj, var1);
      zza(this.zzatk, var2);
   }

   @Keep
   public View asView() {
      return this;
   }

   private static void zza(TextView var0, @Nullable CharSequence var1) {
      var0.setText(var1);
      var0.setVisibility(TextUtils.isEmpty(var1) ? 8 : 0);
   }
}
