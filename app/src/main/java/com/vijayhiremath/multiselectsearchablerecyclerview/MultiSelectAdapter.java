package com.vijayhiremath.multiselectsearchablerecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vijay.hiremath on 06/10/16.
 */
public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectAdapter.ViewHolder> implements Filterable
{
    String TAG = MultiSelectAdapter.class.getSimpleName();

    private ArrayList<DummyModel> items;
    private static OptionClickListener mOptionClickListener;
    Context mContext;
    private List<DummyModel> orig;
    private ArrayList<DummyModel> selectedLabs = new ArrayList<>();

    public MultiSelectAdapter(ArrayList<DummyModel> mDataset, Context context)
    {
        this.items = mDataset;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_title;
        CheckBox tv_file_type;

        public ViewHolder(final View itemView)
        {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_name);
            tv_file_type = (CheckBox) itemView.findViewById(R.id.cb_checked);


        }
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                final FilterResults oReturn = new FilterResults();
                final List<DummyModel> results = new ArrayList<DummyModel>();

                if (orig == null) orig = items;

                if (constraint != null)
                {
                    if (orig != null & orig.size() > 0)
                    {
                        for (final DummyModel g : orig)
                        {
                            if (g.getName().toLowerCase().contains(constraint.toString()))
                                results.add(g);
                        }
                    }

                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                items = (ArrayList<DummyModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        ViewHolder dataObjectHolder = new ViewHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        String lab = items.get(position).getName();
        Integer id  = items.get(position).getId();
        holder.tv_title.setText(lab);

        holder.tv_file_type.setOnCheckedChangeListener(null);

        if (containsSelected(id))
        {
            holder.tv_file_type.setChecked(true);
        } else
        {
            holder.tv_file_type.setChecked(false);
        }

        holder.tv_file_type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if( mOptionClickListener != null )
                {
                    mOptionClickListener.onItemClick( position , isChecked);
                }
                else
                {
                    Log.e( TAG , "Listener not implemented in activity class :(" );
                }

            }
        });
    }

    private boolean containsSelected(Integer id)
    {
        for (int i = 0; i < selectedLabs.size(); i++)
        {
            if (id == selectedLabs.get(i).getId() )
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public interface OptionClickListener
    {
        void onItemClick(int positions, boolean isChecked);
    }

    public void setOnItemClickListener(OptionClickListener myClickListener)
    {
        this.mOptionClickListener = myClickListener;
    }

    public void addThisItem(int position)
    {
        selectedLabs.add(items.get(position));
    }

    public void removeThisItem(int position)
    {
        String lab = items.get(position).getName();
        for (int i = 0; i < selectedLabs.size(); i++)
        {
            if ( lab.equalsIgnoreCase(selectedLabs.get(i).getName() ) )
            {
                selectedLabs.remove(i);
                break;
            }
        }
    }

    public ArrayList<DummyModel> getSelectedLabs()
    {
        return selectedLabs;
    }
}
