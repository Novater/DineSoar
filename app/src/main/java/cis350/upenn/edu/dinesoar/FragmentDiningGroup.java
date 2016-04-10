package cis350.upenn.edu.dinesoar;

import android.app.Fragment;
import android.content.Intent;
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

    public static final int StartDiningGroupID = 4;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.fragment_dining_group, container, false);
        TextView findDiningText = (TextView) v.findViewById(R.id.create_dining_group_text);
        String fontPath = "fonts/PFAgoraSansPro-Reg.ttf";
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        findDiningText.setTypeface(tf);
        TextView findDiningDescription = (TextView) v.findViewById(R.id.description_dining_group);
        TextView findMoreRestaurantDescription = (TextView) v.findViewById(R.id.description_nearby_restaurants);
        TextView suggestedRestaurants = (TextView) v.findViewById(R.id.suggested_restaurants);
        suggestedRestaurants.setTypeface(tf);
        findMoreRestaurantDescription.setTypeface(tf);
        findDiningDescription.setTypeface(tf);
        setCreateNewGroup();
        return v;
    }

    public void setCreateNewGroup() {
        TextView createNew = (TextView) v.findViewById(R.id.start_now_button);
        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getView().getContext(), StartDiningGroupActivity.class);
                startActivityForResult(i, StartDiningGroupID);
            }
        });
    }
}
