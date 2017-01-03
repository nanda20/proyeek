package com.example.devintachr.proyeek;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by owner on 12/4/2016.
 */
public class AdapterHandler extends RecyclerView.Adapter<AdapterHandler.ViewHolder>{

    private Context context;
    List<DataDosen> item;

    public AdapterHandler(Context context, List<DataDosen> item) {
        this.context= context;
        this.item = item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtNip.setText(item.get(position).getNip_karyawan());
        holder.txtNama.setText(item.get(position).getNama());
        holder.txtJam.setText(item.get(position).getJam());
//            holder.txtRute.setText(item.get(position).getRoute());
//            holder.txtHarga.setText("Rp. "+String.valueOf(item.get(position).getHarga()));
//
//            if(item.get(position).getPromo()==1) {
//                holder.promo.setImageResource(R.drawable.promo);
//            }

//            switch (pesawat) {
//                case "Batik":
//                    holder.icon.setImageResource(R.drawable.batik);
//                    break;
//                case "Sriwijaya":
//                    holder.icon.setImageResource(R.drawable.sriwijaya);
//                    break;
//                case "Lion":
//                    holder.icon.setImageResource(R.drawable.lion);
//                    break;
//                case "Qatar":
//                    holder.icon.setImageResource(R.drawable.qatar);
//                    break;
//
//            }

//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent= new Intent(context, Detail.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("id",item.get(position).getId());
//                    intent.putExtra("pesawat",item.get(position).getPesawat());
//                    intent.putExtra("route",item.get(position).getRoute());
//                    intent.putExtra("harga",item.get(position).getHarga());
//                    intent.putExtra("promo",item.get(position).getPromo());
//                    intent.putExtra("lama",item.get(position).getLama_penerbangan());
//                    intent.putExtra("transit",item.get(position).getTransit());
//                    context.startActivity(intent);
//                }
//            });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNip,txtNama,txtJam;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNip=(TextView) itemView.findViewById(R.id.nip);
            txtNama=(TextView) itemView.findViewById(R.id.nama);
            txtJam=(TextView)itemView.findViewById(R.id.waktu);

        }
    }
}

