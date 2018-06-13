package com.example.natan.intentservicegithubapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.natan.intentservicegithubapi.model.Repository;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {

    private List<Repository> mGithubApiData;

    public UserAdapter() {

    }

    @NonNull
    @Override
    public UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int user_list_item = R.layout.user_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(user_list_item, parent, false);

        return new UserAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterViewHolder holder, int position) {
        holder.mNameTextView.setText(mGithubApiData.get(position).getName());
        holder.mAutorNameTextView.setText(mGithubApiData.get(position).getAutorName());
        holder.mDescriptionTextView.setText(mGithubApiData.get(position).getDescription());
        Picasso.get().load(mGithubApiData.get(position).getImageLink()).into(holder.mAvatarImageView);
    }

    @Override
    public int getItemCount() {
        if(mGithubApiData == null || mGithubApiData.isEmpty()) return 0;
        return mGithubApiData.size();
    }

    public class UserAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView mNameTextView;
        public final TextView mAutorNameTextView;
        public final TextView mDescriptionTextView;
        public final ImageView mAvatarImageView;

        public UserAdapterViewHolder(View itemView) {
            super(itemView);
            this.mNameTextView = (TextView) itemView.findViewById(R.id.tv_name);
            this.mAutorNameTextView = (TextView) itemView.findViewById(R.id.tv_autor_name);
            this.mDescriptionTextView = (TextView) itemView.findViewById(R.id.tv_description);
            this.mAvatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
        }

    }

    // Getters and Setters
    public void setmGithubApiData(List<Repository> githubApiData) {
        if(mGithubApiData != null) {
            if (!githubApiData.isEmpty() && !mGithubApiData.isEmpty()) this.mGithubApiData.addAll(githubApiData);
            else mGithubApiData = githubApiData;
        } else {
            this.mGithubApiData = githubApiData;
        }

        notifyDataSetChanged();
    }

    public List<Repository> getmGithubApiData() {
        return mGithubApiData;
    }
}
