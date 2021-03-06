package com.kmema.android.graphqlproject.person;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kmema.android.graphqlproject.AllPeopleQuery;
import com.kmema.android.graphqlproject.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PersonDetailFragment} and makes a call to the
 * specified {@link PersonClickListner}.
 */
public class MyPersonRecyclerViewAdapter extends RecyclerView.Adapter<MyPersonRecyclerViewAdapter.ViewHolder> {

    private final List<AllPeopleQuery.person> mValues;
    private final Context context;
    private final PersonClickListner personClickListner;
    public MyPersonRecyclerViewAdapter(List<AllPeopleQuery.person> items, Context context, PersonFragment personFragment) {
        mValues = items;
        this.context = context;
        this.personClickListner = personFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mPersonName.setText(mValues.get(position).name());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personClickListner.personRecyclerViewClickListener(v, mValues.get(position));
                Toast.makeText(context, ""+ mValues.get(position).name(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mPersonName;
        AllPeopleQuery.person mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mPersonName = view.findViewById(R.id.textViewPersonGrid);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPersonName.getText() + "'";
        }
    }
}
