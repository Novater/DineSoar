package cis350.upenn.edu.dinesoar;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jonathan on 4/4/16.
 */
public class FragmentDiningGroup extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dining_group, container, false);
        TextView findDiningText = (TextView) v.findViewById(R.id.create_dining_group_text);
        String fontPath = "fonts/PFAgoraSansPro-Reg.ttf";
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        findDiningText.setTypeface(tf);
        TextView findDiningDescription = (TextView) v.findViewById(R.id.description_dining_group);
        findDiningDescription.setTypeface(tf);

        return v;
    }
}
