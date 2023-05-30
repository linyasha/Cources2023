package dev.lynko.cources2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import dev.lynko.cources2023.databinding.ActivityMainBinding
import dev.lynko.cources2023.fragments.FirstFragment
import dev.lynko.cources2023.fragments.SecondFragment
import dev.lynko.cources2023.fragments.ThrirdFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {

                }

                override fun onPageScrollStateChanged(state: Int) {

                }

            })

            tabLayout.setupWithViewPager(viewPager)
            viewPager.offscreenPageLimit

        }

    }

    inner class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> FirstFragment.newInstance()
                1 -> SecondFragment.newInstance()
                else -> ThrirdFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "First Fragment"
                1 -> "Second Fragment"
                else -> "Third Fragment"
        }

    }

}}