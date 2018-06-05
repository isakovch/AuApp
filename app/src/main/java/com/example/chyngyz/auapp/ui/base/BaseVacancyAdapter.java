package com.example.chyngyz.auapp.ui.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.chyngyz.auapp.R;
import com.example.chyngyz.auapp.data.entity.Vacancy;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseVacancyAdapter extends ArrayAdapter<Vacancy> {

    public BaseVacancyAdapter(@NonNull Context context, ArrayList<Vacancy> vacancyList, ArrayList<String> favouriteList, ArrayList<String> viewedList) {
        super(context, 0, vacancyList);
    }

    protected abstract void onCheckBoxClicked(boolean isChecked, String vacancyId);

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vacancy_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Vacancy item = getItem(position);

        if (null != item) {
            holder.mHeader.setText(item.getHeader());
            holder.mProfession.setText(item.getProfession());

            holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheckBoxClicked(holder.mCheckBox.isChecked(), item.getPid());
                }
            });
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.profession)
        TextView mProfession;
        @BindView(R.id.header)
        TextView mHeader;
        @BindView(R.id.checkbox)
        CheckBox mCheckBox;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
