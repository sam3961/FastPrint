package com.app.fastprint.utills;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.app.fastprint.ui.cart.responseModel.BuyNowListing;
import com.app.fastprint.ui.cart.responseModel.CartListing;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppControler {

    /*
     * A Singleton for managing your SharedPreferences.
     *
     * You should make sure to change the SETTINGS_NAME to what you want
     * and choose the operating made that suits your needs, the default is
     * MODE_PRIVATE.
     *
     * IMPORTANT: The class is not thread safe. It should work fine in most
     * circumstances since the write and read operations are fast. However
     * if you call edit for bulk updates and do not commit your changes
     * there is a possibility of data loss if a background thread has modified
     * preferences at the same time.
     *
     * Usage:
     *
     * int sampleInt = ${NAME}.getInstance(context).getInt(Key.SAMPLE_INT);
     * ${NAME}.getInstance(context).set(Key.SAMPLE_INT, sampleInt);
     *
     * If ${NAME}.getInstance(Context) has been called once, you can
     * simple use ${NAME}.getInstance() to save some precious line space.
     */

    private static final String SETTINGS_NAME = "fastprint";
    private static AppControler sSharedPrefs;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;

    /**
     * Class for keeping all the keys used for shared preferences in one place.
     */
    public static class Key {
        /* Recommended naming convention:
         * ints, floats, doubles, longs:
         * SAMPLE_NUM or SAMPLE_COUNT or SAMPLE_INT, SAMPLE_LONG etc.
         *
         * boolean: IS_SAMPLE, HAS_SAMPLE, CONTAINS_SAMPLE
         *
         * String: SAMPLE_KEY, SAMPLE_STR or just SAMPLE
         */

        public static final String DEVICE_TOKEN = "device_token";
        public static final String AUTH_TOKEN = "auth_token";
        public static final String USER_ID = "user_id";
        public static final String USER_NAME = "user_name";
        public static final String USER_EMAIL = "email";
        public static final String USER_PHONE = "phone_number";
        public static final String USER_FIRST_NAME = "first_name";
        public static final String USER_LAST_NAME = "last_name";
        public static final String USER_IMAGE = "user_image";
        public static final String USER_CURRENT_LATITUDE = "current_lattitude";
        public static final String USER_CURRENT_LONGITUDE = "current_longitude";
        public static final String CONSUMER_KEY = "consumer_key";
        public static final String SECRECT_KEY = "secret_key";
        public static final String ADDRESS = "address";
        public static final String CART_TOTAL = "cart_total";

    }

    private AppControler(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
    }


    public static AppControler getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new AppControler(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static AppControler getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }

        //Option 1:
        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");

        //Option 2:
        // Alternatively, you can create a new instance here
        // with something like this:
        // getInstance(MyCustomApplication.getAppContext());
    }

    public void put(String key, String val) {
        doEdit();
        mEditor.putString(key, val);
        doCommit();
    }

    public void put(String key, int val) {
        doEdit();
        mEditor.putInt(key, val);
        doCommit();
    }

    public void put(String key, boolean val) {
        doEdit();
        mEditor.putBoolean(key, val);
        doCommit();
    }

    public void put(String key, float val) {
        doEdit();
        mEditor.putFloat(key, val);
        doCommit();
    }

    /**
     * Convenience method for storing doubles.
     * <p>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The name of the preference to store.
     * @param val The new value for the preference.
     */
    public void put(String key, double val) {
        doEdit();
        mEditor.putString(key, String.valueOf(val));
        doCommit();
    }

    public void put(String key, long val) {
        doEdit();
        mEditor.putLong(key, val);
        doCommit();
    }

    public String getString(String key, String defaultValue) {
        return mPref.getString(key, defaultValue);
    }

    public String getString(String key) {
        return mPref.getString(key, "");
    }

    public int getInt(String key) {
        return mPref.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return mPref.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return mPref.getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        return mPref.getLong(key, defaultValue);
    }

    public float getFloat(String key) {
        return mPref.getFloat(key, 0);
    }

    public float getFloat(String key, float defaultValue) {
        return mPref.getFloat(key, defaultValue);
    }

    /**
     * Convenience method for retrieving doubles.
     * <p>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The name of the preference to fetch.
     */
    public double getDouble(String key) {
        return getDouble(key, 0);
    }

    /**
     * Convenience method for retrieving doubles.
     * <p>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The name of the preference to fetch.
     */
    public double getDouble(String key, double defaultValue) {
        try {
            return Double.valueOf(mPref.getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mPref.getBoolean(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return mPref.getBoolean(key, false);
    }

    /**
     * Remove keys from SharedPreferences.
     *
     * @param keys The name of the key(s) to be removed.
     */
    public void remove(String... keys) {
        doEdit();
        for (String key : keys) {
            mEditor.remove(key);
        }
        doCommit();
    }

    /**
     * Remove all keys from SharedPreferences.
     */
    public void clear() {
        doEdit();
        mEditor.clear();
        doCommit();
    }

    public void edit() {
        mBulkUpdate = true;
        mEditor = mPref.edit();
    }

    public void commit() {
        mBulkUpdate = false;
        mEditor.commit();
        mEditor = null;
    }

    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }

    public void addProductToCart(ArrayList<CartListing> list, String key) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        mPref.edit().putString(key, json).apply();
    }

    public void addProductToBuyNow(ArrayList<BuyNowListing> list, String key) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        mPref.edit().putString(key, json).apply();
    }

    public ArrayList<CartListing> getCartList(String key) {
        ArrayList<CartListing> cartListings = new ArrayList<>();
        Gson gson = new Gson();
        String json = mPref.getString(key, "");
        if (json.isEmpty()) {
            cartListings = new ArrayList<>();
        } else {
            Type type = new TypeToken<List<CartListing>>() {
            }.getType();
            cartListings = gson.fromJson(json, type);
        }
        if (cartListings==null)
            cartListings = new ArrayList<>();
        return cartListings;
    }
    public ArrayList<BuyNowListing> getBuyNowProduct(String key) {
        ArrayList<BuyNowListing> cartListings = new ArrayList<>();
        Gson gson = new Gson();
        String json = mPref.getString(key, "");
        if (json.isEmpty()) {
            cartListings = new ArrayList<>();
        } else {
            Type type = new TypeToken<List<BuyNowListing>>() {
            }.getType();
            cartListings = gson.fromJson(json, type);
        }
        return cartListings;
    }
    public static void dismissKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
