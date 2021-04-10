package com.app.fastprint.services;

import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;
import com.app.fastprint.ui.cart.couponsResponse.CouponsResponse;
import com.app.fastprint.ui.category.aboutus.responseModel.AboutUsResponseModel;
import com.app.fastprint.ui.category.commericalPrinting.responseModel.CommercialFromSubmitResponseModel;
import com.app.fastprint.ui.category.commericalPrinting.responseModel.CommericalPrintingResponseModel;
import com.app.fastprint.ui.category.commericalPrintingList.responseModel.CommercialPrintingCategoriesResponseModel;
import com.app.fastprint.ui.category.contactus.responseModel.ContactUsResponseModel;
import com.app.fastprint.ui.category.enquiryforms.responseModel.FormsResponseModel;
import com.app.fastprint.ui.category.extras.responseModel.ExtrasResponseModel;
import com.app.fastprint.ui.category.gallery.responseModel.GalleryResponseModel;
import com.app.fastprint.ui.category.logo.responseModel.DefinationOfLogoResponseModel;
import com.app.fastprint.ui.category.multipagePrinting.responseModel.MultiPageFormSubmitResponseModel;
import com.app.fastprint.ui.category.multipagePrinting.responseModel.MultiPagePrintingResponseModel;
import com.app.fastprint.ui.category.services.responseModel.ServicesResponseModel;
import com.app.fastprint.ui.changepassword.responseModel.ChangePasswordResponseModel;
import com.app.fastprint.ui.editprofile.responseModel.UpdateProfileResponseModel;
import com.app.fastprint.ui.findus.responseModel.FindUsResponseModel;
import com.app.fastprint.ui.galleryImages.responseModel.GalleryImageResponseModel;
import com.app.fastprint.ui.login.loginResponse.LoginResponseModel;
import com.app.fastprint.ui.login.loginResponse.SocialLoginResponseModel;
import com.app.fastprint.ui.main.responseModel.MenuListResponseModel;
import com.app.fastprint.ui.myorders.ordersResonse.OrdersResponse;
import com.app.fastprint.ui.notification.notificationResponse.NotificationResponse;
import com.app.fastprint.ui.orderdetails.orderDetailResponse.OrderDetailResponse;
import com.app.fastprint.ui.others.responseModel.OtherResponseModel;
import com.app.fastprint.ui.payment.generateTokens.GenerateTokenResponse;
import com.app.fastprint.ui.payment.orderCreateRequest.OrderCreateRequest;
import com.app.fastprint.ui.payment.orderCreateResponse.OrderCreateResponse;
import com.app.fastprint.ui.payment.paymentResponseModel.GetAddressResponseModel;
import com.app.fastprint.ui.payment.paymentResponseModel.PaymentResponseModel;
import com.app.fastprint.ui.product.responseModel.StoreListingResponseModel;
import com.app.fastprint.ui.product.responseModel.StoreProductResponseModel;
import com.app.fastprint.ui.productDetails.responseModel.ProductDetailsResponseModel;
import com.app.fastprint.ui.productDetails.responseModel.variationResponse.VariationResponseModel;
import com.app.fastprint.ui.resetPassword.responseModel.ResetPasswordResponseModel;
import com.app.fastprint.ui.reviewListing.responseModel.ReviewResponseModel;
import com.app.fastprint.ui.sendPayment.sendPaymentResponseModel.SendPaymentResponseModel;
import com.app.fastprint.ui.settings.responseModel.LogoutResponseModel;
import com.app.fastprint.ui.signup.responseModel.SignUpResponseModel;
import com.app.fastprint.ui.socialmedia.responseModel.SocialMediaResponseModel;
import com.app.fastprint.ui.uploadFileForm.responseModel.UploadFileSubmitResponseModel;
import com.app.fastprint.ui.uploadfile.responnseModel.UploadFileResponseModel;
import com.google.gson.JsonObject;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    public static final int MENU_LIST_SUCCESS = 1;
    public static final int MENU_LIST_FAILED = 2;

    public static final int ABOUT_US_SUCCESS = 3;
    public static final int ABOUT_US_FAILED = 4;

    public static final int SERVICES_SUCCESS = 5;
    public static final int SERVICES_FAILED = 6;

    public static final int CONTACTS_US_SUCCESS = 7;
    public static final int CONTACTS_US_FAILED = 8;

    public static final int DEFINATION_OF_LOGO_SUCCESS = 9;
    public static final int DEFINATION_OF_LOGO_FAILED = 10;

    public static final int FIND_US_SUCCESS = 11;
    public static final int FIND_US_FAILED = 12;

    public static final int FORM_LIST_SUCCESS = 12;
    public static final int FORM_LIST_FAILED = 13;

    public static final int GALLERY_SUCCESS = 14;
    public static final int GALLERY_FAILED = 15;

    public static final int SOCAIL_MEDIA_LIST_SUCCESS = 16;
    public static final int SOCAIL_MEDIA_LIST_FAILED = 17;

    public static final int EXTRAS_LIST_SUCCESS = 18;
    public static final int EXTRAS_LIST_FAILED = 19;

    public static final int SEND_PAYMENT_SUCCESS = 20;
    public static final int SEND_PAYMENT_FAILED = 21;

    public static final int STORE_LISTING_SUCCESS = 22;
    public static final int STORE_LISTING_FAILED = 23;

    public static final int GALLERY_IMAGE_SUCCESS = 24;
    public static final int GALLERY_IMAGE_FAILED = 25;

    public static final int PAYMENT_OPTION_SUCCESS = 26;
    public static final int PAYMENT_OPTION_FAILED = 27;

    public static final int UPLOAD_FILE_SUCCESS = 28;
    public static final int UPLOAD_FILE_FAILED = 29;

    public static final int STORE_PRODUTC_SUCCESS = 30;
    public static final int STORE_PRODUTC_FAILED = 31;

    public static final int COMMERICAL_PRINTING_SUCCESS = 32;
    public static final int COMMERICAL_PRINTING_FAILED = 33;

    public static final int MULTIPAGE_PRINTING_SUCCESS = 34;
    public static final int MULTIPAGE_PRINTING_FAILED = 35;

    public static final int PRINTING_CATEGORIES_SUCCESS = 36;
    public static final int PRINTING_CATEGORIES_FAILED = 37;

    public static final int COMMERICAL_FORM_SUBMIT_SUCCESS = 38;
    public static final int COMMERICAL_FORM_SUBMIT_FAILED = 39;

    public static final int MULTIPAGE_FORM_SUBMIT_SUCCESS = 40;
    public static final int MULTIPAGE_FORM_SUBMIT_FAILED = 41;

    public static final int SUBMIT_UPLOAD_FILE_SUCCESS = 42;
    public static final int SUBMIT_UPLOAD_FILE_FAILED = 43;

    public static final int PRODUCT_DETAIL_SUCCESS = 44;
    public static final int PRODUCT_DETAIL_FAILED = 45;

    public static final int REVIEW_SUCCESS = 46;
    public static final int REVIEW_FAILED = 47;

    public static final int GET_PRICE_SUCCESS = 48;
    public static final int GET_PRICE_FAILED = 49;

    public static final int SIGNUP_SUCCESS = 50;
    public static final int SIGNUP_FAILED = 51;

    public static final int lOGIN_SUCCESS = 52;
    public static final int lOGIN_FAILED = 53;

    public static final int POLICY_SUCCESS = 54;
    public static final int POLICY_FAILED = 55;

    public static final int FORGOT_PASSWORD_SUCCESS = 56;
    public static final int FORGOT_PASSWORD_FAILED = 57;

    public static final int LOGOUT_SUCCESS = 58;
    public static final int LOGOUT_FAILED = 59;

    public static final int CHANGE_PASSWORD_SUCCESS = 60;
    public static final int CHANGE_PASSWORD_FAILED = 61;

    public static final int UPDATE_PROFILE_SUCCESS = 62;
    public static final int UPDATE_PROFILE_FAILED = 63;

    public static final int BILLING_ADDRESS_SUCCESS = 64;
    public static final int BILLING_ADDRESS_FAILED = 65;

    public static final int GET_ADDRESS_SUCCESS = 66;
    public static final int GET_ADDRESS_FAILED = 67;

    public static final int SOCIAL_LOGIN_SUCCESS = 68;
    public static final int SOCIAL_LOGIN_FAILED = 69;

    @POST("os/v1/menu")
    Call<MenuListResponseModel> menuList();

    @POST("os/v1/about_us")
    Call<AboutUsResponseModel> aboutUs();

    @POST("os/v1/services")
    Call<ServicesResponseModel> services();

    @POST("os/v1/contact_us")
    Call<ContactUsResponseModel> contactUs();

    @POST("os/v1/definition_of_logo")
    Call<DefinationOfLogoResponseModel> definationofLogo();

    @POST("os/v1/find_us")
    Call<FindUsResponseModel> findus();

    @POST("os/v1/enquiry")
    Call<FormsResponseModel> enquiryForms();

    @POST("os/v1/social_media")
    Call<SocialMediaResponseModel> socailMedia();

    @POST("os/v1/gallery_list")
    Call<GalleryResponseModel> gallery();

    @POST("os/v1/extras")
    Call<ExtrasResponseModel> extras();

    @POST("os/v1/sendPayment")
    Call<SendPaymentResponseModel> sendPayment();

    @POST("os/v1/sendPayment")
    Call<PaymentResponseModel> paymentOption();

    @POST("os/v1/store")
    Call<StoreListingResponseModel> storeList();

    @POST("os/v1/gallery")
    Call<GalleryImageResponseModel> galleryImage(@Query("photo_id") String photo_id);

    @POST("os/v1/storeProduct")
    Call<StoreProductResponseModel> storeProduct(@Query("id") String id);

    @POST("os/v1/uploadFile")
    Call<UploadFileResponseModel> uploadFileList();

    @POST("os/v1/commerical")
    Call<CommericalPrintingResponseModel> commericalPrinting(@Query("id") String id);

    @POST("os/v1/multi_page")
    Call<MultiPagePrintingResponseModel> multipagePrinting(@Query("id") String id);

    @POST("os/v1/commerical_category")
    Call<CommercialPrintingCategoriesResponseModel> commercialPrintingCategories(@Query("id") String id);

    @Multipart
    @POST("os/v1/commerical_submit")
    Call<CommercialFromSubmitResponseModel> submitCommercialForm(
            @Part("category_id") RequestBody categories_id,
            @Part("sub_category_id") RequestBody sub_category_id,
            @Part("name") RequestBody name,
            @Part("company") RequestBody company,
            @Part("email") RequestBody email,
            @Part("phone") RequestBody phone,
            @Part("job") RequestBody job,
            @Part("jobs") RequestBody jobs,
            @Part("quantity") RequestBody quantity,
            @Part("no_of_sheets") RequestBody no_of_sheets,
            @Part("pages_per_set") RequestBody pages_per_set,
            @Part("no_of_set") RequestBody no_of_set,
            @Part("side") RequestBody side,
            @Part("orientation") RequestBody orientation,
            @Part("color_code") RequestBody color_code,
            @Part("number_of_color") RequestBody number_of_color,
            @Part("paper_type") RequestBody paper_type,
            @Part("paper_gram") RequestBody paper_gram,
            @Part("size") RequestBody size,
            @Part("finished_size") RequestBody finished_size,
            @Part("open_size") RequestBody open_size,
            @Part("finishing") RequestBody finishing,
            @Part("comment") RequestBody comment,
            @Part MultipartBody.Part file_name,
            @Part MultipartBody.Part signature_file,
            @Part("current_address") RequestBody current_address);

    @Multipart
    @POST("os/v1/multi_page_submit")
    Call<MultiPageFormSubmitResponseModel> submitMultiPageForm(
            @Part("category_id") RequestBody category_id,
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("company") RequestBody company,
            @Part("phone") RequestBody phone,
            @Part("job") RequestBody job,
            @Part("quantity") RequestBody quantity,
            @Part("color_code") RequestBody color_code,
            @Part("number_of_color") RequestBody number_of_color,
            @Part("orientation") RequestBody orientation,
            @Part("size") RequestBody size,
            @Part("finished_size") RequestBody finished_size,
            @Part("open_size") RequestBody open_size,
            @Part("slide") RequestBody slide,
            @Part("finishing") RequestBody finishing,
            @Part("binding") RequestBody binding,
            @Part("no_of_page") RequestBody no_of_page,
            @Part("cover_page_type") RequestBody cover_page_type,
            @Part("inside_page_type") RequestBody inside_page_type,
            @Part("cover_page_gram") RequestBody cover_page_gram,
            @Part("inside_page_gram") RequestBody inside_page_gram,
            @Part("current_address") RequestBody current_address,
            @Part("comment") RequestBody comment,
            @Part("file_name") RequestBody file_nameRequest,
            @Part MultipartBody.Part file_name,
            @Part MultipartBody.Part signature_file);

    @Multipart
    @POST("os/v1/submit_upload_file")
    Call<UploadFileSubmitResponseModel> submitUploadFile(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("url_link") RequestBody url_link,
            @Part("phone") RequestBody phone,
            @Part("order_number") RequestBody order_number,
            @Part MultipartBody.Part upload_file,
            @Part MultipartBody.Part upload_signature);


    @POST("os/v1/product_detail")
    Call<ProductDetailsResponseModel> getProductDetails(@Query("id") String id);

    @POST("os/v1/product_review")
    Call<ReviewResponseModel> getReview(@Query("id") String id);

    @POST("os/v1/get_variation_details")
    Call<VariationResponseModel> getPrice(@Query("product_id") String id,
                                          @Query("quantity") String quantity,
                                          @Query("pages") String pages,
                                          @Query("cover") String cover,
                                          @Query("device_token") String device_token);
//    @POST("product_price")
//    Call<GetPriceResponseModel> getPrice(@Query("id") String id,
//                                         @Query("quantity") String quantity,
//                                         @Query("pages") String pages,
//                                         @Query("cover") String cover);

    @POST("os/v1/sign_up")
    Call<SignUpResponseModel> userSignUp(@Query("first_name") String first_name,
                                         @Query("last_name") String last_name,
                                         @Query("phone") String phone,
                                         @Query("email") String email,
                                         @Query("password") String password);

    @POST("os/v1/login")
    Call<LoginResponseModel> userLogin(@Query("email") String email,
                                       @Query("password") String password,
                                       @Query("device_token") String devices_token,
                                       @Query("device_type") String devices_type,
                                       @Query("login_type") String login_type,
                                       @Query("current_latitude") String current_latitude,
                                       @Query("current_longitude") String current_longitude);

    @POST("os/v1/social_login")
    Call<SocialLoginResponseModel> socialLogin(@Query("email") String email,
                                               @Query("first_name") String first_name,
                                               @Query("last_name") String last_name,
                                               @Query("image") String image,
                                               @Query("login_type") String login_type,
                                               @Query("device_type") String device_type,
                                               @Query("current_latitude") String current_lattitude,
                                               @Query("current_longitude") String current_longitude,
                                               @Query("device_token") String device_token);


    @POST("os/v1/policy")
    Call<OtherResponseModel> other();

    @POST("os/v1/forgot_password")
    Call<ResetPasswordResponseModel> restPassword(@Query("email") String email);

    @POST("os/v1/logout")
    Call<LogoutResponseModel> logout(@Query("token") String token);

    @POST("os/v1/change_password")
    Call<ChangePasswordResponseModel> changePassword(@Query("user_id") String token,
                                                     @Query("old_password") String old_password,
                                                     @Query("new_password") String new_password);

    @Multipart
    @POST("os/v1/update_user")
    Call<UpdateProfileResponseModel> updateUserProfile(
            @Part("token") RequestBody token,
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody last_name,
            @Part("email") RequestBody email,
            @Part("phone") RequestBody phone,
            @Part("image") RequestBody imageRequest,
            @Part MultipartBody.Part image);


    @POST("os/v1/update_address")
    Call<BillingAddressResponseModel> addBillingAddress(@Query("user_id") String user_id,
                                                        @Query("first_name") String first_name,
                                                        @Query("last_name") String last_name,
                                                        @Query("address_1") String address_1,
                                                        @Query("address_2") String address_2,
                                                        @Query("city_name") String city_name,
                                                        @Query("zip_code") String zip_code,
                                                        @Query("country_name") String country_name,
                                                        @Query("state_name") String state_name,
                                                        @Query("phone") String phone);

    @POST("os/v1/get_address")
    Call<GetAddressResponseModel> getAddress(@Query("user_id") String user_id);


    @Multipart
    @POST("wc/v3/orders")
    Call<OrderCreateResponse> createOrder(@Query("consumer_key") String consumer_key,
                                          @Query("consumer_secret") String consumer_secret,
                                          @Query("amr_slug") String amr_slug,
                                          @PartMap HashMap<String, RequestBody> mParms,
                                          @Part MultipartBody.Part imageToUpload);

   
    @GET("wc/v3/orders")
    Call<OrdersResponse> listOrders(@Query("consumer_key") String consumer_key,
                                    @Query("consumer_secret") String consumer_secret,
                                    @Query("customer") String customer,
                                    @Query("amr_slug") String amr_slug);

    @GET("wc/v3/orders/{orderid}")
    Call<OrderDetailResponse> orderDetail(@Path("orderid") String orderid,
                                          @Query("consumer_key") String consumer_key,
                                          @Query("consumer_secret") String consumer_secret,
                                          @Query("amr_slug") String amr_slug);


    @POST("os/v1/woo_keys?token=SBWoiw9UE9qx4NVLSHC9")
    Call<GenerateTokenResponse> generateToken();

    @POST("os/v1/save_device_token")
    Call<JsonObject> saveDeviceToken(@Query("device_token") String device_token,
                                     @Query("device_type") String device_type);

    @POST("os/v1/get_notifications")
    Call<NotificationResponse> getNotifications();

    @GET("wc/v3/coupons")
    Call<CouponsResponse> getCouponListing(@Query("consumer_key") String consumer_key,
                                           @Query("consumer_secret") String consumer_secret,
                                           @Query("amr_slug") String amr_slug);


}
