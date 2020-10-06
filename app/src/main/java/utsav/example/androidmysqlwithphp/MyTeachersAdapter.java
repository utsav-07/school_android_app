package utsav.example.androidmysqlwithphp;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyTeachersAdapter extends RecyclerView.Adapter<ImageViewHOlder1> {

    private Context context;
    private List<ModelImage_Teachers> imageList;

    public MyTeachersAdapter(Context context, List<ModelImage_Teachers> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHOlder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_tchrdescrip,parent,false);
        return new ImageViewHOlder1(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHOlder1 holder, int position) {

        holder.textViewHead.setText(imageList.get(position).getUsername());
        holder.textViewDescrp.setText(imageList.get(position).getEmail());//description
        Glide.with(context).load(imageList.get(position).getImageurl()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}

class ImageViewHOlder1 extends RecyclerView.ViewHolder{
    public TextView textViewHead;
    public TextView textViewDescrp;
    ImageView imageView;
    public ImageViewHOlder1(@NonNull View itemView) {
        super(itemView);

        textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
        textViewDescrp = (TextView) itemView.findViewById(R.id.textViewDescrp);
        imageView = itemView.findViewById(R.id.imageView);
    }
}