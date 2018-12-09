package com.clj.blesample

import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import com.clj.blesample.R.id.tab0
import com.clj.blesample.base.BaseActivity
import com.clj.blesample.ui.history.HistoryFragment
import com.clj.blesample.ui.mine.MineFragment
import com.clj.blesample.ui.time.TimeFragment
import com.clj.blesample.util.PermissionConstants
import com.clj.blesample.util.PermissionUtils
import com.clj.blesample.util.ToastUtils
import com.clj.blesample.widget.NativeTabButton
import com.liuniukeji.singemall.widget.CustomFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_bmain.*

class BMainActivity : BaseActivity() {
    private var mTabButtons: Array<NativeTabButton>? = null
    private var mFragments: Array<Fragment>? = null
    private var title = intArrayOf(R.string.main_tab0, R.string.main_tab1, R.string.main_tab2)
    private var checkImage = intArrayOf(R.mipmap.logo, R.mipmap.logo, R.mipmap.logo)
    private var unCheckImage = intArrayOf(R.mipmap.logo, R.mipmap.logo, R.mipmap.logo)
    private var adapter: CustomFragmentPagerAdapter? = null

    override fun loadViewLayout() {
        setContentView(R.layout.activity_bmain)
        initTab()
        initFragment()
        setFragmentShow(0)
    }

    override fun processLogic() {
        PermissionUtils
                .permission(PermissionConstants.STORAGE, PermissionConstants.PHONE, PermissionConstants.CAMERA)
                .rationale {
                    showToast("为保证正常使用,请授权本应用获取相关权限")
                }
                .callback(object : PermissionUtils.FullCallback {
                    override fun onGranted(permissionsGranted: MutableList<String>?) {
                    }

                    override fun onDenied(permissionsDeniedForever: MutableList<String>?, permissionsDenied: MutableList<String>?) {
                        showToast("为保证正常使用,请授权本应用获取相关权限")
                    }
                })
                .request()
    }

    fun initTab() {
        mTabButtons = arrayOf(tab0, tab1, tab2)
        for (i in mTabButtons!!.indices) {
            mTabButtons!![i].setTitle(getString(title[i]))
            mTabButtons!![i].setIndex(i)
            mTabButtons!![i].setSelectedImage(ContextCompat.getDrawable(context, checkImage[i]))
            mTabButtons!![i].setUnselectedImage(ContextCompat.getDrawable(context, unCheckImage[i]))
            mTabButtons!![i].setmSelectedColorRes(R.color.tabColor_selected)
            mTabButtons!![i].setmUnselectedColorRes(R.color.tabColor_unSelect)
        }
    }

    fun initFragment() {
        mFragments = arrayOf(TimeFragment(), HistoryFragment(), MineFragment())
        adapter = CustomFragmentPagerAdapter(mFragments, supportFragmentManager)
        sp_main.adapter = adapter
        sp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                for (item in mTabButtons!!) {
                    item.setSelectedButton(false)
                }
                mTabButtons!![position].setSelectedButton(true)

            }
        })
    }

    private var lastPage: Int = 0
    //登陆状态判断
    private fun checkShouldLogin(position: Int): Boolean {
        if (position == lastPage) {

        }
        /*  if (position == 3 && !MyApplication.hasToken()) {//个人中心
              setFragmentShow(lastPage)
              gotoActivity(LoginAndRegisterActivity::class.java)
              return true
          } else if (position == 2 && !MyApplication.hasToken()) {//微赚中心
              setFragmentShow(lastPage)
              gotoActivity(LoginAndRegisterActivity::class.java)
              return true
          } else if (position == 1 && !MyApplication.hasToken()) {//购物车
              setFragmentShow(lastPage)
              gotoActivity(LoginAndRegisterActivity::class.java)
              return true
          } else {
              lastPage = position
              return false
          }*/
        lastPage = position
        return false
    }

    fun setFragmentShow(index: Int) {
        if (checkShouldLogin(index)) {
            return
        }

        for (item in mTabButtons!!) {
            item.setSelectedButton(false)
        }
        mTabButtons!![index].setSelectedButton(true)

        sp_main.setCurrentItem(index, false)
    }

    private var lastTime: Long = 0

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTime < 3000) {
            super.onBackPressed()
        } else {
            lastTime = System.currentTimeMillis()
            ToastUtils.showShort("再按一次退出")
        }

    }
}
