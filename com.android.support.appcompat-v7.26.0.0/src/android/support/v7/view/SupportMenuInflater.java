package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.support.v7.widget.DrawableUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SupportMenuInflater extends MenuInflater {
   static final String LOG_TAG = "SupportMenuInflater";
   private static final String XML_MENU = "menu";
   private static final String XML_GROUP = "group";
   private static final String XML_ITEM = "item";
   static final int NO_ID = 0;
   static final Class[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class};
   static final Class[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE;
   final Object[] mActionViewConstructorArguments;
   final Object[] mActionProviderConstructorArguments;
   Context mContext;
   private Object mRealOwner;

   public SupportMenuInflater(Context context) {
      super(context);
      this.mContext = context;
      this.mActionViewConstructorArguments = new Object[]{context};
      this.mActionProviderConstructorArguments = this.mActionViewConstructorArguments;
   }

   public void inflate(int menuRes, Menu menu) {
      if (!(menu instanceof SupportMenu)) {
         super.inflate(menuRes, menu);
      } else {
         XmlResourceParser parser = null;

         try {
            parser = this.mContext.getResources().getLayout(menuRes);
            AttributeSet attrs = Xml.asAttributeSet(parser);
            this.parseMenu(parser, attrs, menu);
         } catch (XmlPullParserException var9) {
            throw new InflateException("Error inflating menu XML", var9);
         } catch (IOException var10) {
            throw new InflateException("Error inflating menu XML", var10);
         } finally {
            if (parser != null) {
               parser.close();
            }

         }

      }
   }

   private void parseMenu(XmlPullParser parser, AttributeSet attrs, Menu menu) throws XmlPullParserException, IOException {
      SupportMenuInflater.MenuState menuState = new SupportMenuInflater.MenuState(menu);
      int eventType = parser.getEventType();
      boolean lookingForEndOfUnknownTag = false;
      String unknownTagName = null;

      String tagName;
      do {
         if (eventType == 2) {
            tagName = parser.getName();
            if (!tagName.equals("menu")) {
               throw new RuntimeException("Expecting menu, got " + tagName);
            }

            eventType = parser.next();
            break;
         }

         eventType = parser.next();
      } while(eventType != 1);

      for(boolean reachedEndOfMenu = false; !reachedEndOfMenu; eventType = parser.next()) {
         switch(eventType) {
         case 1:
            throw new RuntimeException("Unexpected end of document");
         case 2:
            if (!lookingForEndOfUnknownTag) {
               tagName = parser.getName();
               if (tagName.equals("group")) {
                  menuState.readGroup(attrs);
               } else if (tagName.equals("item")) {
                  menuState.readItem(attrs);
               } else if (tagName.equals("menu")) {
                  SubMenu subMenu = menuState.addSubMenuItem();
                  this.parseMenu(parser, attrs, subMenu);
               } else {
                  lookingForEndOfUnknownTag = true;
                  unknownTagName = tagName;
               }
            }
            break;
         case 3:
            tagName = parser.getName();
            if (lookingForEndOfUnknownTag && tagName.equals(unknownTagName)) {
               lookingForEndOfUnknownTag = false;
               unknownTagName = null;
            } else if (tagName.equals("group")) {
               menuState.resetGroup();
            } else if (tagName.equals("item")) {
               if (!menuState.hasAddedItem()) {
                  if (menuState.itemActionProvider != null && menuState.itemActionProvider.hasSubMenu()) {
                     menuState.addSubMenuItem();
                  } else {
                     menuState.addItem();
                  }
               }
            } else if (tagName.equals("menu")) {
               reachedEndOfMenu = true;
            }
         }
      }

   }

   Object getRealOwner() {
      if (this.mRealOwner == null) {
         this.mRealOwner = this.findRealOwner(this.mContext);
      }

      return this.mRealOwner;
   }

   private Object findRealOwner(Object owner) {
      if (owner instanceof Activity) {
         return owner;
      } else {
         return owner instanceof ContextWrapper ? this.findRealOwner(((ContextWrapper)owner).getBaseContext()) : owner;
      }
   }

   static {
      ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
   }

   private class MenuState {
      private Menu menu;
      private int groupId;
      private int groupCategory;
      private int groupOrder;
      private int groupCheckable;
      private boolean groupVisible;
      private boolean groupEnabled;
      private boolean itemAdded;
      private int itemId;
      private int itemCategoryOrder;
      private CharSequence itemTitle;
      private CharSequence itemTitleCondensed;
      private int itemIconResId;
      private char itemAlphabeticShortcut;
      private int itemAlphabeticModifiers;
      private char itemNumericShortcut;
      private int itemNumericModifiers;
      private int itemCheckable;
      private boolean itemChecked;
      private boolean itemVisible;
      private boolean itemEnabled;
      private int itemShowAsAction;
      private int itemActionViewLayout;
      private String itemActionViewClassName;
      private String itemActionProviderClassName;
      private String itemListenerMethodName;
      ActionProvider itemActionProvider;
      private CharSequence itemContentDescription;
      private CharSequence itemTooltipText;
      private ColorStateList itemIconTintList = null;
      private Mode itemIconTintMode = null;
      private static final int defaultGroupId = 0;
      private static final int defaultItemId = 0;
      private static final int defaultItemCategory = 0;
      private static final int defaultItemOrder = 0;
      private static final int defaultItemCheckable = 0;
      private static final boolean defaultItemChecked = false;
      private static final boolean defaultItemVisible = true;
      private static final boolean defaultItemEnabled = true;

      public MenuState(Menu menu) {
         this.menu = menu;
         this.resetGroup();
      }

      public void resetGroup() {
         this.groupId = 0;
         this.groupCategory = 0;
         this.groupOrder = 0;
         this.groupCheckable = 0;
         this.groupVisible = true;
         this.groupEnabled = true;
      }

      public void readGroup(AttributeSet attrs) {
         TypedArray a = SupportMenuInflater.this.mContext.obtainStyledAttributes(attrs, styleable.MenuGroup);
         this.groupId = a.getResourceId(styleable.MenuGroup_android_id, 0);
         this.groupCategory = a.getInt(styleable.MenuGroup_android_menuCategory, 0);
         this.groupOrder = a.getInt(styleable.MenuGroup_android_orderInCategory, 0);
         this.groupCheckable = a.getInt(styleable.MenuGroup_android_checkableBehavior, 0);
         this.groupVisible = a.getBoolean(styleable.MenuGroup_android_visible, true);
         this.groupEnabled = a.getBoolean(styleable.MenuGroup_android_enabled, true);
         a.recycle();
      }

      public void readItem(AttributeSet attrs) {
         TypedArray a = SupportMenuInflater.this.mContext.obtainStyledAttributes(attrs, styleable.MenuItem);
         this.itemId = a.getResourceId(styleable.MenuItem_android_id, 0);
         int category = a.getInt(styleable.MenuItem_android_menuCategory, this.groupCategory);
         int order = a.getInt(styleable.MenuItem_android_orderInCategory, this.groupOrder);
         this.itemCategoryOrder = category & -65536 | order & '\uffff';
         this.itemTitle = a.getText(styleable.MenuItem_android_title);
         this.itemTitleCondensed = a.getText(styleable.MenuItem_android_titleCondensed);
         this.itemIconResId = a.getResourceId(styleable.MenuItem_android_icon, 0);
         this.itemAlphabeticShortcut = this.getShortcut(a.getString(styleable.MenuItem_android_alphabeticShortcut));
         this.itemAlphabeticModifiers = a.getInt(styleable.MenuItem_alphabeticModifiers, 4096);
         this.itemNumericShortcut = this.getShortcut(a.getString(styleable.MenuItem_android_numericShortcut));
         this.itemNumericModifiers = a.getInt(styleable.MenuItem_numericModifiers, 4096);
         if (a.hasValue(styleable.MenuItem_android_checkable)) {
            this.itemCheckable = a.getBoolean(styleable.MenuItem_android_checkable, false) ? 1 : 0;
         } else {
            this.itemCheckable = this.groupCheckable;
         }

         this.itemChecked = a.getBoolean(styleable.MenuItem_android_checked, false);
         this.itemVisible = a.getBoolean(styleable.MenuItem_android_visible, this.groupVisible);
         this.itemEnabled = a.getBoolean(styleable.MenuItem_android_enabled, this.groupEnabled);
         this.itemShowAsAction = a.getInt(styleable.MenuItem_showAsAction, -1);
         this.itemListenerMethodName = a.getString(styleable.MenuItem_android_onClick);
         this.itemActionViewLayout = a.getResourceId(styleable.MenuItem_actionLayout, 0);
         this.itemActionViewClassName = a.getString(styleable.MenuItem_actionViewClass);
         this.itemActionProviderClassName = a.getString(styleable.MenuItem_actionProviderClass);
         boolean hasActionProvider = this.itemActionProviderClassName != null;
         if (hasActionProvider && this.itemActionViewLayout == 0 && this.itemActionViewClassName == null) {
            this.itemActionProvider = (ActionProvider)this.newInstance(this.itemActionProviderClassName, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionProviderConstructorArguments);
         } else {
            if (hasActionProvider) {
               Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
            }

            this.itemActionProvider = null;
         }

         this.itemContentDescription = a.getText(styleable.MenuItem_contentDescription);
         this.itemTooltipText = a.getText(styleable.MenuItem_tooltipText);
         if (a.hasValue(styleable.MenuItem_iconTintMode)) {
            this.itemIconTintMode = DrawableUtils.parseTintMode(a.getInt(styleable.MenuItem_iconTintMode, -1), this.itemIconTintMode);
         } else {
            this.itemIconTintMode = null;
         }

         if (a.hasValue(styleable.MenuItem_iconTint)) {
            this.itemIconTintList = a.getColorStateList(styleable.MenuItem_iconTint);
         } else {
            this.itemIconTintList = null;
         }

         a.recycle();
         this.itemAdded = false;
      }

      private char getShortcut(String shortcutString) {
         return shortcutString == null ? '\u0000' : shortcutString.charAt(0);
      }

      private void setItem(MenuItem item) {
         item.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled).setCheckable(this.itemCheckable >= 1).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId);
         if (this.itemShowAsAction >= 0) {
            item.setShowAsAction(this.itemShowAsAction);
         }

         if (this.itemListenerMethodName != null) {
            if (SupportMenuInflater.this.mContext.isRestricted()) {
               throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }

            item.setOnMenuItemClickListener(new SupportMenuInflater.InflatedOnMenuItemClickListener(SupportMenuInflater.this.getRealOwner(), this.itemListenerMethodName));
         }

         MenuItemImpl var10000;
         if (item instanceof MenuItemImpl) {
            var10000 = (MenuItemImpl)item;
         } else {
            var10000 = null;
         }

         if (this.itemCheckable >= 2) {
            if (item instanceof MenuItemImpl) {
               ((MenuItemImpl)item).setExclusiveCheckable(true);
            } else if (item instanceof MenuItemWrapperICS) {
               ((MenuItemWrapperICS)item).setExclusiveCheckable(true);
            }
         }

         boolean actionViewSpecified = false;
         if (this.itemActionViewClassName != null) {
            View actionView = (View)this.newInstance(this.itemActionViewClassName, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionViewConstructorArguments);
            item.setActionView(actionView);
            actionViewSpecified = true;
         }

         if (this.itemActionViewLayout > 0) {
            if (!actionViewSpecified) {
               item.setActionView(this.itemActionViewLayout);
               actionViewSpecified = true;
            } else {
               Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            }
         }

         if (this.itemActionProvider != null) {
            MenuItemCompat.setActionProvider(item, this.itemActionProvider);
         }

         MenuItemCompat.setContentDescription(item, this.itemContentDescription);
         MenuItemCompat.setTooltipText(item, this.itemTooltipText);
         MenuItemCompat.setAlphabeticShortcut(item, this.itemAlphabeticShortcut, this.itemAlphabeticModifiers);
         MenuItemCompat.setNumericShortcut(item, this.itemNumericShortcut, this.itemNumericModifiers);
         if (this.itemIconTintMode != null) {
            MenuItemCompat.setIconTintMode(item, this.itemIconTintMode);
         }

         if (this.itemIconTintList != null) {
            MenuItemCompat.setIconTintList(item, this.itemIconTintList);
         }

      }

      public void addItem() {
         this.itemAdded = true;
         this.setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
      }

      public SubMenu addSubMenuItem() {
         this.itemAdded = true;
         SubMenu subMenu = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
         this.setItem(subMenu.getItem());
         return subMenu;
      }

      public boolean hasAddedItem() {
         return this.itemAdded;
      }

      private Object newInstance(String className, Class[] constructorSignature, Object[] arguments) {
         try {
            Class clazz = SupportMenuInflater.this.mContext.getClassLoader().loadClass(className);
            Constructor constructor = clazz.getConstructor(constructorSignature);
            constructor.setAccessible(true);
            return constructor.newInstance(arguments);
         } catch (Exception var6) {
            Log.w("SupportMenuInflater", "Cannot instantiate class: " + className, var6);
            return null;
         }
      }
   }

   private static class InflatedOnMenuItemClickListener implements OnMenuItemClickListener {
      private static final Class[] PARAM_TYPES = new Class[]{MenuItem.class};
      private Object mRealOwner;
      private Method mMethod;

      public InflatedOnMenuItemClickListener(Object realOwner, String methodName) {
         this.mRealOwner = realOwner;
         Class c = realOwner.getClass();

         try {
            this.mMethod = c.getMethod(methodName, PARAM_TYPES);
         } catch (Exception var6) {
            InflateException ex = new InflateException("Couldn't resolve menu item onClick handler " + methodName + " in class " + c.getName());
            ex.initCause(var6);
            throw ex;
         }
      }

      public boolean onMenuItemClick(MenuItem item) {
         try {
            if (this.mMethod.getReturnType() == Boolean.TYPE) {
               return ((Boolean)this.mMethod.invoke(this.mRealOwner, item)).booleanValue();
            } else {
               this.mMethod.invoke(this.mRealOwner, item);
               return true;
            }
         } catch (Exception var3) {
            throw new RuntimeException(var3);
         }
      }
   }
}
