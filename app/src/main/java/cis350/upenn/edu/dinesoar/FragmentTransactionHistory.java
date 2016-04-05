package cis350.upenn.edu.dinesoar;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jonathan on 4/4/16.
 */
public class FragmentTransactionHistory extends Fragment {

    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {

        return inflator.inflate(R.layout.fragment_transaction, container, false);
    }
}
