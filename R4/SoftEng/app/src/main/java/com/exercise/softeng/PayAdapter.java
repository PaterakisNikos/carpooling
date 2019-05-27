package com.exercise.softeng;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vasilis on 5/25/2019.
 */

public class PayAdapter extends ArrayAdapter<Ride> {

    //private static int[] image_rezouces={R.drawable.ic_reqstate_accepted, R.drawable.ic_reqstate_declined, R.drawable.ic_reqstate_pending};

    private String passenger;
    private List<Object[]>passenger_payments;

    private RenderListener executor;

    public PayAdapter(@NonNull Context context, Ride[] rides, String passenger){
        super(context, R.layout.payride_row, rides);
        this.passenger=passenger;
        passenger_payments= carpoolingSystem.getPayments(this.passenger);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View customView=layoutInflater.inflate(R.layout.payride_row, parent, false);
        final Ride current=getItem(position);
        TextView paying, cost;
        paying=(TextView)customView.findViewById(R.id.lbl_paying_data);
        cost=(TextView)customView.findViewById(R.id.lbl_cost_data);
        int counter=1;
        for(Request req: current.getRequests()){
            if(req.getState()==Request.ACCEPTED_REQUEST)counter++;
        }
        final double ammount=(current.price/(double)counter);
        cost.setText(""+ammount);
        paying.setText(current.getDriver());
        Button pay, reject;
        pay=(Button)customView.findViewById(R.id.btn_acceptpay);
        reject=(Button)customView.findViewById(R.id.btn_rejectpay);
        ImageView pay_state=(ImageView)customView.findViewById(R.id.pay_state_imageview);
        pay_state.setImageResource(ImageResources.QUESTION_MARK);
        for (Object[] payment: passenger_payments){
            double pay_ammount=(double)payment[1];
            Ride assosiated= carpoolingSystem.getRide((int)payment[0]);
            if(assosiated.getDriver().equals(current.getDriver())&&assosiated.getStart().getTime().isEqual(current.getStart().getTime())){
                if(pay_ammount==0.0)
                    pay_state.setImageResource(ImageResources.CROSS);
                else
                    pay_state.setImageResource(ImageResources.CHECK);
                pay.setEnabled(false);
                reject.setEnabled(false);
                return customView;
            }
        }

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carpoolingSystem.PAY(passenger, current, ammount);
                executor.re_render();
            }
        });


        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carpoolingSystem.PAY(passenger, current, 0.0);
                executor.re_render();
            }
        });
        return customView;
    }


    public void attachRenderListener(Activity activity){
        executor=(RenderListener)activity;
    }


}
