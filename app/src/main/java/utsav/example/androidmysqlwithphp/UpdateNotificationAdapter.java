package utsav.example.androidmysqlwithphp;


import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Adaptor for recycler view
 * getting used - on teacher notification view/swipe delete page
 * @Autor - Utsav Sinha
 * @Autor - Shalabh Sinha
 */


public class UpdateNotificationAdapter extends RecyclerView.Adapter<UpdateNotificationAdapter.ViewHolder>{
    //private Pdf[] listdata;
    ArrayList<Pdf> data=new ArrayList<Pdf>();

    // RecyclerView recyclerView;
    public UpdateNotificationAdapter(Activity activity, int layoutResourceId, ArrayList<Pdf> data) {
        //this.listdata = listdata;
        this.data=data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_updatenotificatonrecyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Random rnd = new Random();
        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.itemView.setBackgroundColor(currentColor);

       // final Pdf myListData = listdata[position];
        final Pdf pdf = data.get(position);
       // holder.textView.setText(pdf.getName());
        //holder.texturl.setText(pdf.getUrl());
        holder.txtid.setText(pdf.getId());

        //changed
        holder.textView.setText(pdf.getFile_name());
        holder.texturl.setText(pdf.getFile_name_url());


        //holder.itemView.setTag(pdf.getId());
        holder.itemView.setTag(data.get(position).getId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        //return listdata.length;
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView texturl;
        private TextView txtid;
        public LinearLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            this.textView = (TextView) itemView.findViewById(R.id.textViewName);
            this.texturl = (TextView) itemView.findViewById(R.id.textViewUrl);
            this.txtid  = (TextView)  itemView.findViewById(R.id.button3);
            relativeLayout = (LinearLayout)itemView.findViewById(R.id.relativeLayout);

        }
    }

    public void removeItem(int position) {
        data.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }
}
