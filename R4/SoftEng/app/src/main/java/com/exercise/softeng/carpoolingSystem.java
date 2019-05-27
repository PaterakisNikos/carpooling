package com.exercise.softeng;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
                    new CreditCard("Bill", "Mormor", "4077526441872843",1234),//0
                    new CreditCard("Nick", "Pater", "9455387992929074", 4321),//1
                    new CreditCard("Mike", "Skord", "2532000974276655", 5432),//2
                    new CreditCard("Maria", "Mark", "0949959745067773", 2345),//3
                    new CreditCard("Manol", "Mormor", "2130423046167705", 1345),//4
                    new CreditCard("Manousos", "Manour", "2130423046167705", 5431)//5
            ));

    private static CreditCard[] bill_cc={CCS.get(0)};
    private static CreditCard[] nick_cc={CCS.get(1)};
    private static CreditCard[] mike_cc={CCS.get(2)};
    private static CreditCard[] maria_cc={CCS.get(3)};
    private static CreditCard[] manol_cc={CCS.get(4)};
    private static CreditCard[] manman_cc={CCS.get(5)};

    private static ArrayList<User>USERS=new ArrayList<User>(
            Arrays.asList(
                new User("ElpsyCongroo", "pass", "Bill", "Mormor", bill_cc),//0
                new User("NickP", "pass", "Nick", "pater", nick_cc),//1
                new User("Mike", "pass", "Mike", "Skord", mike_cc),//2
                new User("Maria", "pass", "Maria", "Mark", maria_cc),//3
                new User("Manol", "pass", "Manol", "Mormor", manol_cc),//4
                new User("manman", "pass", "Manousous", "Manour", manman_cc)//5
            ));

    private static ArrayList<Ride>RIDES=new ArrayList<Ride>(
            Arrays.asList(
                new Ride(new RideInfo(true, new LatLng(38.0626628, 23.8444135), LocalDateTime.of(2019, 6, 5, 12, 0)), new RideInfo(false, new LatLng(37.9940805,23.7302467), LocalDateTime.of(2019, 6, 5, 12, 40)), 5.0, USERS.get(1).getUsername(), 3),//0
                new Ride(new RideInfo(true, new LatLng(37.9940805,23.7302467), LocalDateTime.of(2019, 6, 5, 15, 0)), new RideInfo(false, new LatLng(38.0626628, 23.8444135), LocalDateTime.of(2019, 6, 5, 15, 40)), 5.0, USERS.get(1).getUsername(), 3),//1
                new Ride(new RideInfo(true, new LatLng(38.0626628, 23.8444135), LocalDateTime.of(2019, 5, 20, 12, 0)), new RideInfo(false, new LatLng(38.0277869, 23.7837358), LocalDateTime.of(2019, 6, 5, 12, 20)), 3.0, USERS.get(5).getUsername(), 2)//2
            ));





    private static ArrayList<Object[]>PAYMENTS=new ArrayList<Object[]>();

    /*End of dummie data*/

    public static Ride getRide(int index){return RIDES.get(index);}

    public static void init(){
        RIDES.get(2).add_request(new Request(new LatLng(38.0626628, 23.8444135), USERS.get(0).getUsername()));
        RIDES.get(2).getRequests()[0].setState(Request.ACCEPTED_REQUEST);
        //PAY(USERS.get(0).getUsername(), getRide(2), 1.5);
    }


    public static List<Object[]> getPayments(String passenger){
        List<Object[]> temp=new ArrayList<Object[]>();
        for (Object[] objects: PAYMENTS){
            Log.i(PayRideActivity.TAG, (String)objects[2]+"?="+passenger);
            if(((String)objects[2]).equals(passenger)) temp.add(objects);
        }
        return temp;
    }


    public static Ride[] availableForPay(String passenger){
        List<Ride>temp=new ArrayList<Ride>();
        for(Ride ride: RIDES){
            if(LocalDateTime.now().isAfter(ride.getEnd().getTime())){
                for(Request req: ride.getRequests()) {
                    if (req.getState() == Request.ACCEPTED_REQUEST && req.getPassenger().equals(passenger))
                        temp.add(ride);
                }
            }
        }
        Ride []data=new Ride[temp.size()];
        int i=0;
        for (Ride ride: temp){
            data[i]=ride;
            i++;
        }
        return data;
    }

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

    //TODO ADD CHECKS TO SEE IF RIDE IS FULL OF REQUESTS
    // ------NOT FULLY IMPLEMENTED YET------
    public static Ride[] getAvailiableRides(String passenger) {
        List<Ride> temp=new ArrayList<Ride>();
        for (Ride ride: RIDES){
            if(ride.getStart().getTime().isAfter(LocalDateTime.now())&&!ride.getDriver().equals(passenger)) temp.add(ride);
        }
        Ride[] data=new Ride[temp.size()];
        int i=0;
        for(Ride ride: temp){
            data[i]=ride;
            i++;
        }
        return data;
    }


    public static Ride[] getOwnedRides(String driver){
        List<Ride>temp=new ArrayList<Ride>();
        for(Ride ride: RIDES){
            if(ride.getStart().getTime().isAfter(LocalDateTime.now())&&ride.getDriver().equals(driver))
                temp.add(ride);
        }
        Ride[]data=new Ride[temp.size()];
        for (int i =0; i< data.length;i++)
            data[i]=temp.get(i);
        return data;
    }


    public static void PAY(String passenger, Ride ride, double cost){
        Object[] data=new Object[3];
        data[0]=indexOfRide(ride);
        data[1]=cost;
        data[2]=passenger;
        PAYMENTS.add(data);
    }

    private static int indexOfRide(Ride ride){
        int i=0;
        for(Ride r: RIDES){
            if(r.getStart().getTime().isEqual(ride.getStart().getTime())&&ride.getDriver().equals(r.getDriver())) return i;
            i++;
        }
        return -1;
    }

    public static int PAYMENTS_LENGTH(){return PAYMENTS.size();}


    public static boolean CREATE_REQUEST(String passenger, int ID, LatLng coords){
        for (User user: USERS){
            if(user.getUsername().equals(passenger)){
                Passenger pass=(Passenger)user;
                Request new_request=pass.create_request(coords, getRide(ID));
                if(new_request.creation_successfull()) {
                    getRide(ID).add_request(new_request);
                    return true;
                }
                return false;
            }
        }
        return false;
    }


    public static boolean CREATE_RIDE(String driver, LatLng start, LatLng dest, LocalDateTime start_t, LocalDateTime end_t, double price, int accepted_passengers){
        for(User user: USERS){
            if(user.getUsername()==driver){
                Driver driv=(Driver)user;
                Ride new_ride=driv.createRide(start, start_t, end_t, dest, price, accepted_passengers);
                if(new_ride.creation_successfull()){
                    RIDES.add(new_ride);
                    return true;
                }
                return false;
            }
        }
        return false;
    }


}
