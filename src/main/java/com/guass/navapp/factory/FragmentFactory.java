package com.guass.navapp.factory;

import android.support.v4.app.Fragment;

import com.guass.navapp.fragment.ApplicationFragment;
import com.guass.navapp.fragment.CategrayFragment;
import com.guass.navapp.fragment.GameFragment;
import com.guass.navapp.fragment.HomeFragment;
import com.guass.navapp.fragment.RankFragment;
import com.guass.navapp.fragment.SubjectFragment;

/**
 * Created by guass on 2016/3/14.
 */
public class FragmentFactory {
    public static Fragment creatFragment(int id)
    {
        Fragment fragment = null;
        switch (id)
        {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new ApplicationFragment();
                break;
            case 2:
                fragment = new GameFragment();
                break;
            case 3:
                fragment = new SubjectFragment();
                break;
            case 4:
                fragment = new CategrayFragment();
                break;
            case 5:
                fragment = new RankFragment();
                break;
            default:
                break;
        }
        return  fragment;
    }
}
