package com.exercise.softeng;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Vasilis on 5/26/2019.
 */

public class RequestAdapter extends ArrayAdapter<Request> {

    private Ride ride;

    RenderListener executor;


    public RequestAdapter(Context context, Request[] requests,  Ride ride){
        super(context, R.layout.mng_req_row, requests);
        this.ride=ride;
    }

    public void attachListener(Activity activity){executor=(RenderListener)activity;}

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View custom_view=inflater.inflate(R.layout.mng_req_row, parent,false);
        TextView label, lbl_emark;
        label=(TextView)custom_view.findViewById(R.id.lbl_passenger_data);
        lbl_emark=(TextView)custom_view.findViewById(R.id.lbl_embark_data);
        Request req=getItem(position);
        lbl_emark.setText(humanReadableAddress(req.position));
        label.setText(req.getPassenger());
        Button accept_req, decline_req;
        accept_req=(Button)custom_view.findViewById(R.id.btn_acceptreq);
        decline_req=(Button)custom_view.findViewById(R.id.btn_rejectreq);
        ImageView state=(ImageView)custom_view.findViewById(R.id.req_state_imageview);
        if(req.getState()==Request.ACCEPTED_REQUEST||req.getState()==Request.DECLINED_REQUEST){
            if(req.getState()==Request.ACCEPTED_REQUEST) state.setImageResource(ImageResources.CHECK);
            else state.setImageResource(ImageResources.CROSS);
            accept_req.setEnabled(false);
            decline_req.setEnabled(false);
            return custom_view;
        }
        accept_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ride.proccesRequest(position, true);
                executor.re_render();
            }
        });
        decline_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ride.proccesRequest(position, false);
                executor.re_render();
            }
        });
        return custom_view;
    }


    //Don't know if it works well for addresses outside of GREECE
    private  String humanReadableAddress(LatLng coords){
        String address= carpoolingSystem.getAddressFromCoords(getContext(),coords);
        String data=address.substring(0, address.indexOf(','));
        data+=address.substring(address.indexOf(','), address.indexOf(' ', address.indexOf(',')+2));
        return data;
    }

}
