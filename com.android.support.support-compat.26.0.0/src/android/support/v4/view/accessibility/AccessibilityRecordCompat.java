package android.support.v4.view.accessibility;

import android.os.Parcelable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

public class AccessibilityRecordCompat {
   private static final AccessibilityRecordCompat.AccessibilityRecordCompatBaseImpl IMPL;
   private final AccessibilityRecord mRecord;

   /** @deprecated */
   @Deprecated
   public AccessibilityRecordCompat(Object record) {
      this.mRecord = (AccessibilityRecord)record;
   }

   /** @deprecated */
   @Deprecated
   public Object getImpl() {
      return this.mRecord;
   }

   /** @deprecated */
   @Deprecated
   public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat record) {
      return new AccessibilityRecordCompat(AccessibilityRecord.obtain(record.mRecord));
   }

   /** @deprecated */
   @Deprecated
   public static AccessibilityRecordCompat obtain() {
      return new AccessibilityRecordCompat(AccessibilityRecord.obtain());
   }

   /** @deprecated */
   @Deprecated
   public void setSource(View source) {
      this.mRecord.setSource(source);
   }

   /** @deprecated */
   @Deprecated
   public void setSource(View root, int virtualDescendantId) {
      setSource(this.mRecord, root, virtualDescendantId);
   }

   public static void setSource(@NonNull AccessibilityRecord record, View root, int virtualDescendantId) {
      IMPL.setSource(record, root, virtualDescendantId);
   }

   /** @deprecated */
   @Deprecated
   public AccessibilityNodeInfoCompat getSource() {
      return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mRecord.getSource());
   }

   /** @deprecated */
   @Deprecated
   public int getWindowId() {
      return this.mRecord.getWindowId();
   }

   /** @deprecated */
   @Deprecated
   public boolean isChecked() {
      return this.mRecord.isChecked();
   }

   /** @deprecated */
   @Deprecated
   public void setChecked(boolean isChecked) {
      this.mRecord.setChecked(isChecked);
   }

   /** @deprecated */
   @Deprecated
   public boolean isEnabled() {
      return this.mRecord.isEnabled();
   }

   /** @deprecated */
   @Deprecated
   public void setEnabled(boolean isEnabled) {
      this.mRecord.setEnabled(isEnabled);
   }

   /** @deprecated */
   @Deprecated
   public boolean isPassword() {
      return this.mRecord.isPassword();
   }

   /** @deprecated */
   @Deprecated
   public void setPassword(boolean isPassword) {
      this.mRecord.setPassword(isPassword);
   }

   /** @deprecated */
   @Deprecated
   public boolean isFullScreen() {
      return this.mRecord.isFullScreen();
   }

   /** @deprecated */
   @Deprecated
   public void setFullScreen(boolean isFullScreen) {
      this.mRecord.setFullScreen(isFullScreen);
   }

   /** @deprecated */
   @Deprecated
   public boolean isScrollable() {
      return this.mRecord.isScrollable();
   }

   /** @deprecated */
   @Deprecated
   public void setScrollable(boolean scrollable) {
      this.mRecord.setScrollable(scrollable);
   }

   /** @deprecated */
   @Deprecated
   public int getItemCount() {
      return this.mRecord.getItemCount();
   }

   /** @deprecated */
   @Deprecated
   public void setItemCount(int itemCount) {
      this.mRecord.setItemCount(itemCount);
   }

   /** @deprecated */
   @Deprecated
   public int getCurrentItemIndex() {
      return this.mRecord.getCurrentItemIndex();
   }

   /** @deprecated */
   @Deprecated
   public void setCurrentItemIndex(int currentItemIndex) {
      this.mRecord.setCurrentItemIndex(currentItemIndex);
   }

   /** @deprecated */
   @Deprecated
   public int getFromIndex() {
      return this.mRecord.getFromIndex();
   }

   /** @deprecated */
   @Deprecated
   public void setFromIndex(int fromIndex) {
      this.mRecord.setFromIndex(fromIndex);
   }

   /** @deprecated */
   @Deprecated
   public int getToIndex() {
      return this.mRecord.getToIndex();
   }

   /** @deprecated */
   @Deprecated
   public void setToIndex(int toIndex) {
      this.mRecord.setToIndex(toIndex);
   }

   /** @deprecated */
   @Deprecated
   public int getScrollX() {
      return this.mRecord.getScrollX();
   }

   /** @deprecated */
   @Deprecated
   public void setScrollX(int scrollX) {
      this.mRecord.setScrollX(scrollX);
   }

   /** @deprecated */
   @Deprecated
   public int getScrollY() {
      return this.mRecord.getScrollY();
   }

   /** @deprecated */
   @Deprecated
   public void setScrollY(int scrollY) {
      this.mRecord.setScrollY(scrollY);
   }

   /** @deprecated */
   @Deprecated
   public int getMaxScrollX() {
      return getMaxScrollX(this.mRecord);
   }

   public static int getMaxScrollX(AccessibilityRecord record) {
      return IMPL.getMaxScrollX(record);
   }

   /** @deprecated */
   @Deprecated
   public void setMaxScrollX(int maxScrollX) {
      setMaxScrollX(this.mRecord, maxScrollX);
   }

   public static void setMaxScrollX(AccessibilityRecord record, int maxScrollX) {
      IMPL.setMaxScrollX(record, maxScrollX);
   }

   /** @deprecated */
   @Deprecated
   public int getMaxScrollY() {
      return getMaxScrollY(this.mRecord);
   }

   public static int getMaxScrollY(AccessibilityRecord record) {
      return IMPL.getMaxScrollY(record);
   }

   /** @deprecated */
   @Deprecated
   public void setMaxScrollY(int maxScrollY) {
      setMaxScrollY(this.mRecord, maxScrollY);
   }

   public static void setMaxScrollY(AccessibilityRecord record, int maxScrollY) {
      IMPL.setMaxScrollY(record, maxScrollY);
   }

   /** @deprecated */
   @Deprecated
   public int getAddedCount() {
      return this.mRecord.getAddedCount();
   }

   /** @deprecated */
   @Deprecated
   public void setAddedCount(int addedCount) {
      this.mRecord.setAddedCount(addedCount);
   }

   /** @deprecated */
   @Deprecated
   public int getRemovedCount() {
      return this.mRecord.getRemovedCount();
   }

   /** @deprecated */
   @Deprecated
   public void setRemovedCount(int removedCount) {
      this.mRecord.setRemovedCount(removedCount);
   }

   /** @deprecated */
   @Deprecated
   public CharSequence getClassName() {
      return this.mRecord.getClassName();
   }

   /** @deprecated */
   @Deprecated
   public void setClassName(CharSequence className) {
      this.mRecord.setClassName(className);
   }

   /** @deprecated */
   @Deprecated
   public List getText() {
      return this.mRecord.getText();
   }

   /** @deprecated */
   @Deprecated
   public CharSequence getBeforeText() {
      return this.mRecord.getBeforeText();
   }

   /** @deprecated */
   @Deprecated
   public void setBeforeText(CharSequence beforeText) {
      this.mRecord.setBeforeText(beforeText);
   }

   /** @deprecated */
   @Deprecated
   public CharSequence getContentDescription() {
      return this.mRecord.getContentDescription();
   }

   /** @deprecated */
   @Deprecated
   public void setContentDescription(CharSequence contentDescription) {
      this.mRecord.setContentDescription(contentDescription);
   }

   /** @deprecated */
   @Deprecated
   public Parcelable getParcelableData() {
      return this.mRecord.getParcelableData();
   }

   /** @deprecated */
   @Deprecated
   public void setParcelableData(Parcelable parcelableData) {
      this.mRecord.setParcelableData(parcelableData);
   }

   /** @deprecated */
   @Deprecated
   public void recycle() {
      this.mRecord.recycle();
   }

   /** @deprecated */
   @Deprecated
   public int hashCode() {
      return this.mRecord == null ? 0 : this.mRecord.hashCode();
   }

   /** @deprecated */
   @Deprecated
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         AccessibilityRecordCompat other = (AccessibilityRecordCompat)obj;
         if (this.mRecord == null) {
            if (other.mRecord != null) {
               return false;
            }
         } else if (!this.mRecord.equals(other.mRecord)) {
            return false;
         }

         return true;
      }
   }

   static {
      if (VERSION.SDK_INT >= 16) {
         IMPL = new AccessibilityRecordCompat.AccessibilityRecordCompatApi16Impl();
      } else if (VERSION.SDK_INT >= 15) {
         IMPL = new AccessibilityRecordCompat.AccessibilityRecordCompatApi15Impl();
      } else {
         IMPL = new AccessibilityRecordCompat.AccessibilityRecordCompatBaseImpl();
      }

   }

   @RequiresApi(16)
   static class AccessibilityRecordCompatApi16Impl extends AccessibilityRecordCompat.AccessibilityRecordCompatApi15Impl {
      public void setSource(AccessibilityRecord record, View root, int virtualDescendantId) {
         record.setSource(root, virtualDescendantId);
      }
   }

   @RequiresApi(15)
   static class AccessibilityRecordCompatApi15Impl extends AccessibilityRecordCompat.AccessibilityRecordCompatBaseImpl {
      public int getMaxScrollX(AccessibilityRecord record) {
         return record.getMaxScrollX();
      }

      public int getMaxScrollY(AccessibilityRecord record) {
         return record.getMaxScrollY();
      }

      public void setMaxScrollX(AccessibilityRecord record, int maxScrollX) {
         record.setMaxScrollX(maxScrollX);
      }

      public void setMaxScrollY(AccessibilityRecord record, int maxScrollY) {
         record.setMaxScrollY(maxScrollY);
      }
   }

   static class AccessibilityRecordCompatBaseImpl {
      public int getMaxScrollX(AccessibilityRecord record) {
         return 0;
      }

      public int getMaxScrollY(AccessibilityRecord record) {
         return 0;
      }

      public void setMaxScrollX(AccessibilityRecord record, int maxScrollX) {
      }

      public void setMaxScrollY(AccessibilityRecord record, int maxScrollY) {
      }

      public void setSource(AccessibilityRecord record, View root, int virtualDescendantId) {
      }
   }
}
