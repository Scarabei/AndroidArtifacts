package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public interface Continuation {
   Object then(@NonNull Task var1) throws Exception;
}
