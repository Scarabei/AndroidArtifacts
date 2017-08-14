package com.google.android.gms.awareness.snapshot;

import com.google.android.gms.common.api.Result;
import java.util.List;

public interface PlacesResult extends Result {
   List getPlaceLikelihoods();
}
