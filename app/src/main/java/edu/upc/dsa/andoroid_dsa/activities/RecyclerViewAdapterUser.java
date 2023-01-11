package edu.upc.dsa.andoroid_dsa.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.User;

public class RecyclerViewAdapterUser extends RecyclerView.Adapter<RecyclerViewAdapterUser.ViewHolder> {
    //private static final String URL_INT
    public List<User> users;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username, coins, experience;
        ImageView fotoUser, rankingPosition;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.usernameRnk);
            coins = (TextView) itemView.findViewById(R.id.coinsRnk);
            experience = (TextView) itemView.findViewById(R.id.experienceRnk);
            fotoUser = (ImageView) itemView.findViewById(R.id.imgUser);
            rankingPosition = (ImageView) itemView.findViewById(R.id.rankingPosition);
        }
    }

    public RecyclerViewAdapterUser(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_user, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.username.setText(users.get(position).getName());
        holder.coins.setText(Integer.toString(users.get(position).getCoins()));
        holder.experience.setText(Integer.toString(users.get(position).getExperience()));
        Picasso.get().load(users.get(position).getProfilePicture()).into(holder.fotoUser);
        switch (position) {
            case 0:
                holder.rankingPosition.setImageResource(R.drawable.first);
                break;
            case 1:
                holder.rankingPosition.setImageResource(R.drawable.second);
                break;
            case 2:
                holder.rankingPosition.setImageResource(R.drawable.third);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
