package cis350.upenn.edu.dinesoar;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jonathan on 4/4/16.
 */
public class FragmentProfile extends Fragment {

    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        return inflator.inflate(R.layout.fragment_profile, container, false);
    }


}
