package com.zygotecnologia.zygotv.utils

import com.google.android.material.tabs.TabLayout

/**
 * @author allef.santos on 13/03/21
 */
interface OnTabSelected : TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab?)

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}
}