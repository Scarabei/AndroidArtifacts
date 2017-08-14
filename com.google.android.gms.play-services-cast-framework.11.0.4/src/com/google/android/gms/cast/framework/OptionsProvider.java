package com.google.android.gms.cast.framework;

import android.content.Context;
import java.util.List;

public interface OptionsProvider {
   CastOptions getCastOptions(Context var1);

   List getAdditionalSessionProviders(Context var1);
}
