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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseVacancyAdapter<C> extends ArrayAdapter<Vacancy> {

    public BaseVacancyAdapter(@NonNull Context context,
                              List<Vacancy> vacancyList,
                              ArrayList<String> favouriteList,
                              ArrayList<String> viewedList,
                              C callback) {

        super(context, 0, vacancyList);
    }

    protected abstract void onCheckBoxClicked(boolean isChecked, Vacancy vacancy);

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

            holder.mCheckBox.setOnClickListener(v -> onCheckBoxClicked(holder.mCheckBox.isChecked(), item));
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
