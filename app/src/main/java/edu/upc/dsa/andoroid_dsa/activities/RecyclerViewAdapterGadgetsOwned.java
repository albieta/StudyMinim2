package edu.upc.dsa.andoroid_dsa.activities;

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

public class RecyclerViewAdapterGadgetsOwned extends RecyclerView.Adapter<RecyclerViewAdapterGadgetsOwned.ViewHolder>{
    //private static final String URL_INT
    public List<Gadget> gadgets;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id,cost,description;
        ImageView fotoGadget;
        ImageView deleteImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=(TextView) itemView.findViewById(R.id.usernameRnk);
            cost=(TextView) itemView.findViewById(R.id.coinsRnk);
            description=(TextView) itemView.findViewById(R.id.experienceRnk);
            fotoGadget=(ImageView) itemView.findViewById(R.id.imgUser);
            deleteImage=(ImageView) itemView.findViewById(R.id.delete);
        }
    }

    public RecyclerViewAdapterGadgetsOwned(List<Gadget> gadgets) {
        this.gadgets = gadgets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_gadget_owned,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(gadgets.get(position).getIdGadget());
        holder.description.setText(gadgets.get(position).getDescription());
        holder.cost.setText(Integer.toString(gadgets.get(position).getCost()));
        holder.deleteImage.setTag(gadgets.get(position).getIdGadget());
        Picasso.get().load(gadgets.get(position).getUnityShape()).into(holder.fotoGadget);
    }

    @Override
    public int getItemCount() {
        return gadgets.size();
    }

    public void deleteGadget(String id) {
        for(int i = 0; i < gadgets.size(); i++){
            if(gadgets.get(i).getIdGadget().equals(id)) {
                gadgets.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, gadgets.size());
            }
        }
    }
}
