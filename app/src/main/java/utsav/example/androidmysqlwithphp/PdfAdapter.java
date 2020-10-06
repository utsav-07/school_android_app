package utsav.example.androidmysqlwithphp;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


/**
 * This is Adaptor class for list view
 * list view is using on notification view page - class(Notification_Upload.java)
 * @Autor - Utsav Sinha
 * @Autor - Shalabh Sinha
 */

public class PdfAdapter extends ArrayAdapter<Pdf>
{
    Activity activity;
    int layoutResourceId;
    ArrayList<Pdf> data=new ArrayList<Pdf>();
    Pdf pdf;

    public PdfAdapter(Activity activity, int layoutResourceId, ArrayList<Pdf> data) {
        super(activity, layoutResourceId, data);
        this.activity=activity;
        this.layoutResourceId=layoutResourceId;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View row=convertView;
        PdfHolder holder=null;
        if(row==null)
        {
            LayoutInflater inflater=LayoutInflater.from(activity);
            row=inflater.inflate(layoutResourceId,parent,false);
            holder=new PdfHolder();
            holder.textViewName= (TextView) row.findViewById(R.id.textViewName);
            holder.textViewUrl= (TextView) row.findViewById(R.id.textViewUrl);
            row.setTag(holder);

        }
        else
        {
            holder= (PdfHolder) row.getTag();
        }

        pdf = data.get(position);
        //holder.textViewName.setText(pdf.getName());
       // holder.textViewUrl.setText(pdf.getUrl());

        holder.textViewName.setText(pdf.getFile_name());
        holder.textViewUrl.setText(pdf.getFile_name_url());
        //below code for adding row's color randomly
        Random rnd = new Random();
        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        row.setBackgroundColor(currentColor);

        return row;
    }


    class PdfHolder
    {
        TextView textViewName,textViewUrl;
    }

}