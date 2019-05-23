package com.exercise.softeng;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by Vasilis on 5/22/2019.
 */

public class carpoolingSystem {

    /*Start of dummie data*/
    //TODO: ADD MORE DUMMIE DATA
    private static ArrayList<CreditCard>CCS=new ArrayList<CreditCard>(
            Arrays.asList(
                    new CreditCard("Bill", "Mormor", "1845698312593263",1234),
                    new CreditCard("Nick", "Pater", "6547299913451234", 4321)
            ));

    private static CreditCard[] bill_cc={CCS.get(0)};
    private static CreditCard[] nick_cc={CCS.get(1)};

    private static ArrayList<User>USERS=new ArrayList<User>(
            Arrays.asList(
                new User("ElpsyCongroo", "pass", "Bill", "Mormor", bill_cc),
                new User("NickP", "pass", "Nick", "pater", nick_cc)
            ));
    /*End of dummie data*/



    /*
    *   Returns
    *       - 0 if username not found in USERS
    *       - 1 if password is incorrect
    *       - 2 if username and password match
    * */
    public static int logUser(String username, String password){
        for(User user: USERS){
            if(user.getUsername().equals(username)){
                if(password.equals(user.getPassword()))return 2;
                else return 1;
            }
        }
        return 0;
    }


    //Using Stack overflow answer: https://stackoverflow.com/questions/3574644/how-can-i-find-the-latitude-and-longitude-from-address
    @Nullable
    public static LatLng getCoordsFromAddress(Context context, String address){
        Geocoder coder = new Geocoder(context);
        List<Address> addresses;
        try {
            addresses = coder.getFromLocationName(address,5);
            if (address==null) {
                return null;
            }
            Address location=addresses.get(0);
            return new LatLng(location.getLatitude(), location.getLongitude());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    //Using Stack overflow answer: https://stackoverflow.com/questions/9409195/how-to-get-complete-address-from-latitude-and-longitude
    @Nullable
    public static String getAddressFromCoords(Context context, LatLng coords){
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(coords.latitude, coords.longitude, 1);
            return addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
