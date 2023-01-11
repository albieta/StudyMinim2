package edu.upc.dsa.andoroid_dsa;

import androidx.annotation.BinderThread;

import java.util.List;

import edu.upc.dsa.andoroid_dsa.models.Credentials;
import edu.upc.dsa.andoroid_dsa.models.EditableUserInformation;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.Purchase;
import edu.upc.dsa.andoroid_dsa.models.User;
import edu.upc.dsa.andoroid_dsa.models.UserId;
import edu.upc.dsa.andoroid_dsa.models.UserInformation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @POST("shop/user/register")
    Call<UserInformation> createUser(@Body UserInformation user);

    @POST("shop/user/login")
    Call<UserId> logIn(@Body Credentials credentials);

    @PUT("shop/user/update")
    Call<Void> updateUser(@Body EditableUserInformation user);

    @GET("shop/gadget/all")
    Call<List<Gadget>> getGadgets();

    @GET("shop/user/{idUser}")
    Call<User> getUser(@Path("idUser") String idUser);

    @PUT("shop/gadget/buy/{idGadget}/{idUser}")
    Call<Void> buyAGadget(@Path("idGadget") String idGadget,@Path("idUser") String idUser);

    @GET("shop/purchase/{idUser}")
    Call<List<Gadget>> purchasedGadgets(@Path("idUser") String idUser);

    @GET("shop/gadget/{idGadget}")
    Call<Gadget> getGadget(@Path("idUser") String idGadget);

    @PUT("shop/purchase/delete")
    Call<Void> deletePurchase(@Body Purchase purchase);

    @GET("shop/user/ranking")
    Call<List<User>> rankingUsers();
}
