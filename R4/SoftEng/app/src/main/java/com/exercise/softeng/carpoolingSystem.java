package com.exercise.softeng;

import java.util.ArrayList;
import java.util.Arrays;

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




}
