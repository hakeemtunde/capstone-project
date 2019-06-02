package com.corebyte.mob.kiipa.ui;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.util.Log;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.services.StockExpirationScheduler;

public class SettingsActivity extends AppCompatPreferenceActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //R.id.settings_container



        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new GeneralPreferenceFragment()).commit();
    }

    public static class GeneralPreferenceFragment extends PreferenceFragment {

        //Context context = getActivity().getApplicationContext();

        private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener =
                new Preference.OnPreferenceChangeListener() {

                    @Override
                    public boolean onPreferenceChange(Preference preference, Object object) {
                        String stringValue = object.toString();

                        preference.setSummary(stringValue);

                        return true;
                    }
                };

        private static void bindPreferenceSummaryToValue(Preference preference) {

            preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                    PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(), "")
            );

        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);

            bindPreferenceSummaryToValue(findPreference(
                    getString(R.string.key_customer_credit_limit)));

            bindPreferenceSummaryToValue(findPreference(
                    getString(R.string.key_currency_symbol)
            ));

            final SwitchPreference notificationOnExpirePref = (SwitchPreference) findPreference(
                    getString(R.string.key_notify_on_expire_stock));

            notificationOnExpirePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {

                    boolean enabled = (boolean) o;
                    StockExpirationScheduler.stockExpirationJobSheduler(
                            getActivity().getApplicationContext(), enabled);
                    return true;
                }
            });

            SwitchPreference alertNotificationOnLowStrockPref = (SwitchPreference)findPreference(
                    getString(R.string.key_alert_on_low_stock));

            alertNotificationOnLowStrockPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {

                    boolean enabled = (boolean) o;
                    StockExpirationScheduler.setUpAlarmServiceOnStockLow(
                            getActivity().getApplicationContext(), enabled
                    );

                    return true;
                }
            });

        }
    }


}
