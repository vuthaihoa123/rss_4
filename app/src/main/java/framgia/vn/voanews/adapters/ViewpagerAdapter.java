package framgia.vn.voanews.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toannguyen201194 on 23/05/2016.
 */
public class ViewpagerAdapter extends FragmentPagerAdapter {
	private final List<Fragment> mFrgList = new ArrayList<>();
	private final List<String> mFrgStrList = new ArrayList<>();

	public ViewpagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return mFrgList.get(position);
	}

	@Override
	public int getCount() {
		return mFrgList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mFrgStrList.get(position);
	}

	public void addFragment(Fragment fragment, String title) {
		mFrgList.add(fragment);
		mFrgStrList.add(title);
	}
}
