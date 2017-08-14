package android.support.v4.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.List;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
   public static final int INVALID_ID = Integer.MIN_VALUE;
   public static final int HOST_ID = -1;
   private static final String DEFAULT_CLASS_NAME = "android.view.View";
   private static final Rect INVALID_PARENT_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
   private final Rect mTempScreenRect = new Rect();
   private final Rect mTempParentRect = new Rect();
   private final Rect mTempVisibleRect = new Rect();
   private final int[] mTempGlobalRect = new int[2];
   private final AccessibilityManager mManager;
   private final View mHost;
   private ExploreByTouchHelper.MyNodeProvider mNodeProvider;
   private int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
   private int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
   private int mHoveredVirtualViewId = Integer.MIN_VALUE;
   private static final FocusStrategy.BoundsAdapter NODE_ADAPTER = new FocusStrategy.BoundsAdapter() {
      public void obtainBounds(AccessibilityNodeInfoCompat node, Rect outBounds) {
         node.getBoundsInParent(outBounds);
      }
   };
   private static final FocusStrategy.CollectionAdapter SPARSE_VALUES_ADAPTER = new FocusStrategy.CollectionAdapter() {
      public AccessibilityNodeInfoCompat get(SparseArrayCompat collection, int index) {
         return (AccessibilityNodeInfoCompat)collection.valueAt(index);
      }

      public int size(SparseArrayCompat collection) {
         return collection.size();
      }
   };

   public ExploreByTouchHelper(View host) {
      if (host == null) {
         throw new IllegalArgumentException("View may not be null");
      } else {
         this.mHost = host;
         Context context = host.getContext();
         this.mManager = (AccessibilityManager)context.getSystemService("accessibility");
         host.setFocusable(true);
         if (ViewCompat.getImportantForAccessibility(host) == 0) {
            ViewCompat.setImportantForAccessibility(host, 1);
         }

      }
   }

   public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
      if (this.mNodeProvider == null) {
         this.mNodeProvider = new ExploreByTouchHelper.MyNodeProvider();
      }

      return this.mNodeProvider;
   }

   public final boolean dispatchHoverEvent(@NonNull MotionEvent event) {
      if (this.mManager.isEnabled() && this.mManager.isTouchExplorationEnabled()) {
         switch(event.getAction()) {
         case 7:
         case 9:
            int virtualViewId = this.getVirtualViewAt(event.getX(), event.getY());
            this.updateHoveredVirtualView(virtualViewId);
            return virtualViewId != Integer.MIN_VALUE;
         case 8:
         default:
            return false;
         case 10:
            if (this.mAccessibilityFocusedVirtualViewId != Integer.MIN_VALUE) {
               this.updateHoveredVirtualView(Integer.MIN_VALUE);
               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public final boolean dispatchKeyEvent(@NonNull KeyEvent event) {
      boolean handled = false;
      int action = event.getAction();
      if (action != 1) {
         int keyCode = event.getKeyCode();
         switch(keyCode) {
         case 19:
         case 20:
         case 21:
         case 22:
            if (event.hasNoModifiers()) {
               int direction = keyToDirection(keyCode);
               int count = 1 + event.getRepeatCount();

               for(int i = 0; i < count && this.moveFocus(direction, (Rect)null); ++i) {
                  handled = true;
               }
            }
            break;
         case 23:
         case 66:
            if (event.hasNoModifiers() && event.getRepeatCount() == 0) {
               this.clickKeyboardFocusedVirtualView();
               handled = true;
            }
            break;
         case 61:
            if (event.hasNoModifiers()) {
               handled = this.moveFocus(2, (Rect)null);
            } else if (event.hasModifiers(1)) {
               handled = this.moveFocus(1, (Rect)null);
            }
         }
      }

      return handled;
   }

   public final void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
      if (this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
         this.clearKeyboardFocusForVirtualView(this.mKeyboardFocusedVirtualViewId);
      }

      if (gainFocus) {
         this.moveFocus(direction, previouslyFocusedRect);
      }

   }

   public final int getAccessibilityFocusedVirtualViewId() {
      return this.mAccessibilityFocusedVirtualViewId;
   }

   public final int getKeyboardFocusedVirtualViewId() {
      return this.mKeyboardFocusedVirtualViewId;
   }

   private static int keyToDirection(int keyCode) {
      switch(keyCode) {
      case 19:
         return 33;
      case 20:
      default:
         return 130;
      case 21:
         return 17;
      case 22:
         return 66;
      }
   }

   private void getBoundsInParent(int virtualViewId, Rect outBounds) {
      AccessibilityNodeInfoCompat node = this.obtainAccessibilityNodeInfo(virtualViewId);
      node.getBoundsInParent(outBounds);
   }

   private boolean moveFocus(int direction, @Nullable Rect previouslyFocusedRect) {
      SparseArrayCompat allNodes = this.getAllNodes();
      int focusedNodeId = this.mKeyboardFocusedVirtualViewId;
      AccessibilityNodeInfoCompat focusedNode = focusedNodeId == Integer.MIN_VALUE ? null : (AccessibilityNodeInfoCompat)allNodes.get(focusedNodeId);
      AccessibilityNodeInfoCompat nextFocusedNode;
      switch(direction) {
      case 1:
      case 2:
         boolean isLayoutRtl = ViewCompat.getLayoutDirection(this.mHost) == 1;
         nextFocusedNode = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInRelativeDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, focusedNode, direction, isLayoutRtl, false);
         break;
      case 17:
      case 33:
      case 66:
      case 130:
         Rect selectedRect = new Rect();
         if (this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
            this.getBoundsInParent(this.mKeyboardFocusedVirtualViewId, selectedRect);
         } else if (previouslyFocusedRect != null) {
            selectedRect.set(previouslyFocusedRect);
         } else {
            guessPreviouslyFocusedRect(this.mHost, direction, selectedRect);
         }

         nextFocusedNode = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInAbsoluteDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, focusedNode, selectedRect, direction);
         break;
      default:
         throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }

      int nextFocusedNodeId;
      if (nextFocusedNode == null) {
         nextFocusedNodeId = Integer.MIN_VALUE;
      } else {
         int index = allNodes.indexOfValue(nextFocusedNode);
         nextFocusedNodeId = allNodes.keyAt(index);
      }

      return this.requestKeyboardFocusForVirtualView(nextFocusedNodeId);
   }

   private SparseArrayCompat getAllNodes() {
      List virtualViewIds = new ArrayList();
      this.getVisibleVirtualViews(virtualViewIds);
      SparseArrayCompat allNodes = new SparseArrayCompat();

      for(int virtualViewId = 0; virtualViewId < virtualViewIds.size(); ++virtualViewId) {
         AccessibilityNodeInfoCompat virtualView = this.createNodeForChild(virtualViewId);
         allNodes.put(virtualViewId, virtualView);
      }

      return allNodes;
   }

   private static Rect guessPreviouslyFocusedRect(@NonNull View host, int direction, @NonNull Rect outBounds) {
      int w = host.getWidth();
      int h = host.getHeight();
      switch(direction) {
      case 17:
         outBounds.set(w, 0, w, h);
         break;
      case 33:
         outBounds.set(0, h, w, h);
         break;
      case 66:
         outBounds.set(-1, 0, -1, h);
         break;
      case 130:
         outBounds.set(0, -1, w, -1);
         break;
      default:
         throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }

      return outBounds;
   }

   private boolean clickKeyboardFocusedVirtualView() {
      return this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE && this.onPerformActionForVirtualView(this.mKeyboardFocusedVirtualViewId, 16, (Bundle)null);
   }

   public final boolean sendEventForVirtualView(int virtualViewId, int eventType) {
      if (virtualViewId != Integer.MIN_VALUE && this.mManager.isEnabled()) {
         ViewParent parent = this.mHost.getParent();
         if (parent == null) {
            return false;
         } else {
            AccessibilityEvent event = this.createEvent(virtualViewId, eventType);
            return ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, event);
         }
      } else {
         return false;
      }
   }

   public final void invalidateRoot() {
      this.invalidateVirtualView(-1, 1);
   }

   public final void invalidateVirtualView(int virtualViewId) {
      this.invalidateVirtualView(virtualViewId, 0);
   }

   public final void invalidateVirtualView(int virtualViewId, int changeTypes) {
      if (virtualViewId != Integer.MIN_VALUE && this.mManager.isEnabled()) {
         ViewParent parent = this.mHost.getParent();
         if (parent != null) {
            AccessibilityEvent event = this.createEvent(virtualViewId, 2048);
            AccessibilityEventCompat.setContentChangeTypes(event, changeTypes);
            ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, event);
         }
      }

   }

   /** @deprecated */
   @Deprecated
   public int getFocusedVirtualView() {
      return this.getAccessibilityFocusedVirtualViewId();
   }

   protected void onVirtualViewKeyboardFocusChanged(int virtualViewId, boolean hasFocus) {
   }

   private void updateHoveredVirtualView(int virtualViewId) {
      if (this.mHoveredVirtualViewId != virtualViewId) {
         int previousVirtualViewId = this.mHoveredVirtualViewId;
         this.mHoveredVirtualViewId = virtualViewId;
         this.sendEventForVirtualView(virtualViewId, 128);
         this.sendEventForVirtualView(previousVirtualViewId, 256);
      }
   }

   private AccessibilityEvent createEvent(int virtualViewId, int eventType) {
      switch(virtualViewId) {
      case -1:
         return this.createEventForHost(eventType);
      default:
         return this.createEventForChild(virtualViewId, eventType);
      }
   }

   private AccessibilityEvent createEventForHost(int eventType) {
      AccessibilityEvent event = AccessibilityEvent.obtain(eventType);
      this.mHost.onInitializeAccessibilityEvent(event);
      return event;
   }

   public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
      super.onInitializeAccessibilityEvent(host, event);
      this.onPopulateEventForHost(event);
   }

   private AccessibilityEvent createEventForChild(int virtualViewId, int eventType) {
      AccessibilityEvent event = AccessibilityEvent.obtain(eventType);
      AccessibilityNodeInfoCompat node = this.obtainAccessibilityNodeInfo(virtualViewId);
      event.getText().add(node.getText());
      event.setContentDescription(node.getContentDescription());
      event.setScrollable(node.isScrollable());
      event.setPassword(node.isPassword());
      event.setEnabled(node.isEnabled());
      event.setChecked(node.isChecked());
      this.onPopulateEventForVirtualView(virtualViewId, event);
      if (event.getText().isEmpty() && event.getContentDescription() == null) {
         throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
      } else {
         event.setClassName(node.getClassName());
         AccessibilityRecordCompat.setSource(event, this.mHost, virtualViewId);
         event.setPackageName(this.mHost.getContext().getPackageName());
         return event;
      }
   }

   @NonNull
   AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int virtualViewId) {
      return virtualViewId == -1 ? this.createNodeForHost() : this.createNodeForChild(virtualViewId);
   }

   @NonNull
   private AccessibilityNodeInfoCompat createNodeForHost() {
      AccessibilityNodeInfoCompat info = AccessibilityNodeInfoCompat.obtain(this.mHost);
      ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, info);
      ArrayList virtualViewIds = new ArrayList();
      this.getVisibleVirtualViews(virtualViewIds);
      int realNodeCount = info.getChildCount();
      if (realNodeCount > 0 && virtualViewIds.size() > 0) {
         throw new RuntimeException("Views cannot have both real and virtual children");
      } else {
         int i = 0;

         for(int count = virtualViewIds.size(); i < count; ++i) {
            info.addChild(this.mHost, ((Integer)virtualViewIds.get(i)).intValue());
         }

         return info;
      }
   }

   public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
      super.onInitializeAccessibilityNodeInfo(host, info);
      this.onPopulateNodeForHost(info);
   }

   @NonNull
   private AccessibilityNodeInfoCompat createNodeForChild(int virtualViewId) {
      AccessibilityNodeInfoCompat node = AccessibilityNodeInfoCompat.obtain();
      node.setEnabled(true);
      node.setFocusable(true);
      node.setClassName("android.view.View");
      node.setBoundsInParent(INVALID_PARENT_BOUNDS);
      node.setBoundsInScreen(INVALID_PARENT_BOUNDS);
      node.setParent(this.mHost);
      this.onPopulateNodeForVirtualView(virtualViewId, node);
      if (node.getText() == null && node.getContentDescription() == null) {
         throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
      } else {
         node.getBoundsInParent(this.mTempParentRect);
         if (this.mTempParentRect.equals(INVALID_PARENT_BOUNDS)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
         } else {
            int actions = node.getActions();
            if ((actions & 64) != 0) {
               throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            } else if ((actions & 128) != 0) {
               throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            } else {
               node.setPackageName(this.mHost.getContext().getPackageName());
               node.setSource(this.mHost, virtualViewId);
               if (this.mAccessibilityFocusedVirtualViewId == virtualViewId) {
                  node.setAccessibilityFocused(true);
                  node.addAction(128);
               } else {
                  node.setAccessibilityFocused(false);
                  node.addAction(64);
               }

               boolean isFocused = this.mKeyboardFocusedVirtualViewId == virtualViewId;
               if (isFocused) {
                  node.addAction(2);
               } else if (node.isFocusable()) {
                  node.addAction(1);
               }

               node.setFocused(isFocused);
               this.mHost.getLocationOnScreen(this.mTempGlobalRect);
               node.getBoundsInScreen(this.mTempScreenRect);
               if (this.mTempScreenRect.equals(INVALID_PARENT_BOUNDS)) {
                  node.getBoundsInParent(this.mTempScreenRect);
                  if (node.mParentVirtualDescendantId != -1) {
                     AccessibilityNodeInfoCompat parentNode = AccessibilityNodeInfoCompat.obtain();

                     for(int virtualDescendantId = node.mParentVirtualDescendantId; virtualDescendantId != -1; virtualDescendantId = parentNode.mParentVirtualDescendantId) {
                        parentNode.setParent(this.mHost, -1);
                        parentNode.setBoundsInParent(INVALID_PARENT_BOUNDS);
                        this.onPopulateNodeForVirtualView(virtualDescendantId, parentNode);
                        parentNode.getBoundsInParent(this.mTempParentRect);
                        this.mTempScreenRect.offset(this.mTempParentRect.left, this.mTempParentRect.top);
                     }

                     parentNode.recycle();
                  }

                  this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
               }

               if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
                  this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                  boolean intersects = this.mTempScreenRect.intersect(this.mTempVisibleRect);
                  if (intersects) {
                     node.setBoundsInScreen(this.mTempScreenRect);
                     if (this.isVisibleToUser(this.mTempScreenRect)) {
                        node.setVisibleToUser(true);
                     }
                  }
               }

               return node;
            }
         }
      }
   }

   boolean performAction(int virtualViewId, int action, Bundle arguments) {
      switch(virtualViewId) {
      case -1:
         return this.performActionForHost(action, arguments);
      default:
         return this.performActionForChild(virtualViewId, action, arguments);
      }
   }

   private boolean performActionForHost(int action, Bundle arguments) {
      return ViewCompat.performAccessibilityAction(this.mHost, action, arguments);
   }

   private boolean performActionForChild(int virtualViewId, int action, Bundle arguments) {
      switch(action) {
      case 1:
         return this.requestKeyboardFocusForVirtualView(virtualViewId);
      case 2:
         return this.clearKeyboardFocusForVirtualView(virtualViewId);
      case 64:
         return this.requestAccessibilityFocus(virtualViewId);
      case 128:
         return this.clearAccessibilityFocus(virtualViewId);
      default:
         return this.onPerformActionForVirtualView(virtualViewId, action, arguments);
      }
   }

   private boolean isVisibleToUser(Rect localRect) {
      if (localRect != null && !localRect.isEmpty()) {
         if (this.mHost.getWindowVisibility() != 0) {
            return false;
         } else {
            ViewParent viewParent;
            View view;
            for(viewParent = this.mHost.getParent(); viewParent instanceof View; viewParent = view.getParent()) {
               view = (View)viewParent;
               if (view.getAlpha() <= 0.0F || view.getVisibility() != 0) {
                  return false;
               }
            }

            return viewParent != null;
         }
      } else {
         return false;
      }
   }

   private boolean requestAccessibilityFocus(int virtualViewId) {
      if (this.mManager.isEnabled() && this.mManager.isTouchExplorationEnabled()) {
         if (this.mAccessibilityFocusedVirtualViewId != virtualViewId) {
            if (this.mAccessibilityFocusedVirtualViewId != Integer.MIN_VALUE) {
               this.clearAccessibilityFocus(this.mAccessibilityFocusedVirtualViewId);
            }

            this.mAccessibilityFocusedVirtualViewId = virtualViewId;
            this.mHost.invalidate();
            this.sendEventForVirtualView(virtualViewId, 32768);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean clearAccessibilityFocus(int virtualViewId) {
      if (this.mAccessibilityFocusedVirtualViewId == virtualViewId) {
         this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
         this.mHost.invalidate();
         this.sendEventForVirtualView(virtualViewId, 65536);
         return true;
      } else {
         return false;
      }
   }

   public final boolean requestKeyboardFocusForVirtualView(int virtualViewId) {
      if (!this.mHost.isFocused() && !this.mHost.requestFocus()) {
         return false;
      } else if (this.mKeyboardFocusedVirtualViewId == virtualViewId) {
         return false;
      } else {
         if (this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
            this.clearKeyboardFocusForVirtualView(this.mKeyboardFocusedVirtualViewId);
         }

         this.mKeyboardFocusedVirtualViewId = virtualViewId;
         this.onVirtualViewKeyboardFocusChanged(virtualViewId, true);
         this.sendEventForVirtualView(virtualViewId, 8);
         return true;
      }
   }

   public final boolean clearKeyboardFocusForVirtualView(int virtualViewId) {
      if (this.mKeyboardFocusedVirtualViewId != virtualViewId) {
         return false;
      } else {
         this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
         this.onVirtualViewKeyboardFocusChanged(virtualViewId, false);
         this.sendEventForVirtualView(virtualViewId, 8);
         return true;
      }
   }

   protected abstract int getVirtualViewAt(float var1, float var2);

   protected abstract void getVisibleVirtualViews(List var1);

   protected void onPopulateEventForVirtualView(int virtualViewId, AccessibilityEvent event) {
   }

   protected void onPopulateEventForHost(AccessibilityEvent event) {
   }

   protected abstract void onPopulateNodeForVirtualView(int var1, AccessibilityNodeInfoCompat var2);

   protected void onPopulateNodeForHost(AccessibilityNodeInfoCompat node) {
   }

   protected abstract boolean onPerformActionForVirtualView(int var1, int var2, Bundle var3);

   private class MyNodeProvider extends AccessibilityNodeProviderCompat {
      public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int virtualViewId) {
         AccessibilityNodeInfoCompat node = ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(virtualViewId);
         return AccessibilityNodeInfoCompat.obtain(node);
      }

      public boolean performAction(int virtualViewId, int action, Bundle arguments) {
         return ExploreByTouchHelper.this.performAction(virtualViewId, action, arguments);
      }

      public AccessibilityNodeInfoCompat findFocus(int focusType) {
         int focusedId = focusType == 2 ? ExploreByTouchHelper.this.mAccessibilityFocusedVirtualViewId : ExploreByTouchHelper.this.mKeyboardFocusedVirtualViewId;
         return focusedId == Integer.MIN_VALUE ? null : this.createAccessibilityNodeInfo(focusedId);
      }
   }
}
