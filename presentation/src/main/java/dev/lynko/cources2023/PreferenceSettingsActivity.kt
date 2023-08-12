package dev.lynko.cources2023

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.preference.*
import com.pawegio.kandroid.defaultSharedPreferences
import org.koin.androidx.viewmodel.ext.android.viewModel

class PreferenceSettingsActivity: AppCompatActivity(),
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, PreferenceSettingsFragment())
                .commit()
        }
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat,
        pref: Preference
    ): Boolean {
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            pref.fragment ?: ""
        )
        fragment.arguments = args
        fragment.setTargetFragment(caller, 0)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
        return true
    }

    class PreferenceSettingsFragment : PreferenceFragmentCompat() {

        private var changed = false
        private var domains = mutableListOf<String>()

        private lateinit var crearPreference: Preference
        private lateinit var cloudMessagingSwitchPref: SwitchPreferenceCompat
        private lateinit var overallSoundSwitchPref: SwitchPreferenceCompat
        private lateinit var themePref: ListPreference


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            changed = savedInstanceState?.getBoolean("KEY_CHANGED") == true
            if (changed) {
                requireActivity().setResult(Activity.RESULT_OK)
            }
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
        }

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preference_settings, rootKey)
            themePref = findPreference(Prefs.KEY_THEME)!!
            themePref.setOnPreferenceChangeListener { _, value ->
                //TODO(Do smth)
                Log.d("PREFS", "Send new theme to anatics ${value}")
                true
            }
//            cloudMessagingSwitchPref = findPreference("cloud_messaging")!!
//            cloudMessagingSwitchPref.setOnPreferenceChangeListener { _, value ->
//                val isEnabled = value as Boolean
//                //TODO(Do smth)
//                true
//            }
//

            crearPreference = findPreference<Preference>("clear_storage")!!

            crearPreference.setOnPreferenceClickListener {
//                Toas
                true
            }



        }
//
//        private fun setResult() {
//            if (!changed) {
//                changed = true
//                requireActivity().setResult(Activity.RESULT_OK)
//            }
//        }
//
//        companion object {
//            private const val REQUEST_DELETE_STORAGE = 0
//            private const val REQUEST_DELETE_PIN = 1
//            private const val REQUEST_DISABLE_CLOUD_MESSAGING = 2
//
//
//        }
    }
}