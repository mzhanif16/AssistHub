package MyAssistHub.App.api;

import MyAssistHub.App.model.BaseRespone;
import MyAssistHub.App.model.login.Login;
import MyAssistHub.App.model.register.Register;
import MyAssistHub.App.model.user.UserResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> Loginresponse(
        @Field("username") String username,
        @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> Registerresponse(
            @Field("first_name") String firstname,
            @Field("last_name") String lastname,
            @Field("username") String username,
            @Field("phone_number") String phonenumber,
            @Field("email") String email,
            @Field("password") String password
    );

    //get profile
    @FormUrlEncoded
    @POST("profile.php")
    Call<BaseRespone<UserResponse>> ProfilResponse(
            @Field("userId") String userId
    );

    //update profile
    @FormUrlEncoded
    @POST("update.php")
    Call<BaseRespone> UpdateResponse(
            @Field("userId") String userId,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("gender") String gender,
            @Field("birth") String birth,
            @Field("address") String userAddress
    );

}
