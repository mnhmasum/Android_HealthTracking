package com.example.androidbtcontrol.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbtcontrol.fragments.AirFlowFragment;
import com.example.androidbtcontrol.fragments.BPFragment;
import com.example.androidbtcontrol.fragments.BodyPositionFragment;
import com.example.androidbtcontrol.fragments.ECGFragment;
import com.example.androidbtcontrol.fragments.GLMeterFragment;
import com.example.androidbtcontrol.activities.MainActivity;
import com.example.androidbtcontrol.R;
import com.example.androidbtcontrol.fragments.SPO2Fragment;
import com.example.androidbtcontrol.fragments.TemperatureFragment;
import com.example.androidbtcontrol.datamodel.MenuData;

import java.util.ArrayList;

public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.ViewHolder> {
    private ArrayList<MenuData> mDataset = new ArrayList<>();
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public ImageView imageViewMenuIcon;
        public CardView cardView;

        public ViewHolder(View v, int viewType) {
            super(v);
            //v.setOnClickListener(this);
            textViewTitle = (TextView) v.findViewById(R.id.textViewTitle);
            imageViewMenuIcon = (ImageView) v.findViewById(R.id.textView2);
            cardView = (CardView) v.findViewById(R.id.cardView);
        }

        /*@Override
        public void onClick(View v) {
            Fragment fragment = new ECGFragment();
            onOpenFragment.openFragment(fragment);
        }*/
    }

    public HomeGridAdapter(ArrayList<MenuData> orderDetailsData, Context context) {
        this.mDataset = orderDetailsData;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_menu, parent, false);
        ViewHolder vh = new ViewHolder(v, viewType);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textViewTitle.setText(mDataset.get(position).getMenuTitle());
        holder.imageViewMenuIcon.setImageResource(mDataset.get(position).getImage());

        holder.imageViewMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(position);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFragment(position);
            }
        });

    }

    public void openFragment(int position) {
        Fragment fragment = null;
        if (position == 0) {
            ((MainActivity) context).openBackButton("Blood Pressure");
            fragment = new BPFragment();

        } else if (position == 1) {
            ((MainActivity) context).openBackButton("ECG");
            fragment = new ECGFragment();

        } else if (position == 2) {
            ((MainActivity) context).openBackButton("SPO");
            fragment = new SPO2Fragment();

        } else if (position == 3) {
            ((MainActivity) context).openBackButton("Airflow");
            fragment = new AirFlowFragment();

        } else if (position == 4) {
            ((MainActivity) context).openBackButton("Body Position");
            fragment = new BodyPositionFragment();

        } else if (position == 5) {
            ((MainActivity) context).openBackButton("Glow Meter");
            fragment = new GLMeterFragment();

        } else if (position == 6) {
            ((MainActivity) context).openBackButton("Temperature");
            fragment = new TemperatureFragment();

        } else {

        }

        onOpenFragment.openFragment(fragment);
    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public OnOpenFragment onOpenFragment;

    public void openFragment(OnOpenFragment onOpenFragment) {
        this.onOpenFragment = onOpenFragment;
    }

    public interface OnOpenFragment {
        void openFragment(Fragment fragment);
    }


}