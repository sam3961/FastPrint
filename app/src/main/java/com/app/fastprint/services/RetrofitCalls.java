package com.app.fastprint.services;

import android.os.Handler;
import android.os.Message;

import com.app.fastprint.ui.address.responseModel.BillingAddressResponseModel;
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
import com.app.fastprint.ui.others.responseModel.OtherResponseModel;
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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCalls {

    public APIInterface apiInterface;

    public RetrofitCalls() {
        apiInterface = APIClient.getRetrofitInstance().create(APIInterface.class);
    }

    // Home menu list api to show categories with banner
    public void menuListApi(final Handler mHandler) {

        final Message message = new Message();
        Call<MenuListResponseModel> call = apiInterface.menuList();
        call.enqueue(new Callback<MenuListResponseModel>() {
            @Override
            public void onResponse(Call<MenuListResponseModel> call,
                                   Response<MenuListResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.MENU_LIST_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.MENU_LIST_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<MenuListResponseModel> call, Throwable t) {
                message.what = apiInterface.MENU_LIST_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // about us api from menu click
    public void aboutUsApi(final Handler mHandler) {

        final Message message = new Message();
        Call<AboutUsResponseModel> call = apiInterface.aboutUs();
        call.enqueue(new Callback<AboutUsResponseModel>() {
            @Override
            public void onResponse(Call<AboutUsResponseModel> call,
                                   Response<AboutUsResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.ABOUT_US_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.ABOUT_US_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<AboutUsResponseModel> call, Throwable t) {
                message.what = apiInterface.ABOUT_US_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // services api from menu click
    public void servicesApi(final Handler mHandler) {

        final Message message = new Message();
        Call<ServicesResponseModel> call = apiInterface.services();
        call.enqueue(new Callback<ServicesResponseModel>() {
            @Override
            public void onResponse(Call<ServicesResponseModel> call,
                                   Response<ServicesResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SERVICES_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SERVICES_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ServicesResponseModel> call, Throwable t) {
                message.what = apiInterface.SERVICES_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // contact us api from menu click
    public void contactUsApi(final Handler mHandler) {

        final Message message = new Message();
        Call<ContactUsResponseModel> call = apiInterface.contactUs();
        call.enqueue(new Callback<ContactUsResponseModel>() {
            @Override
            public void onResponse(Call<ContactUsResponseModel> call,
                                   Response<ContactUsResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.CONTACTS_US_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.CONTACTS_US_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ContactUsResponseModel> call, Throwable t) {
                message.what = apiInterface.CONTACTS_US_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    // definationoflogo api from menu click
    public void definationofLogoApi(final Handler mHandler) {

        final Message message = new Message();
        Call<DefinationOfLogoResponseModel> call = apiInterface.definationofLogo();
        call.enqueue(new Callback<DefinationOfLogoResponseModel>() {
            @Override
            public void onResponse(Call<DefinationOfLogoResponseModel> call,
                                   Response<DefinationOfLogoResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.DEFINATION_OF_LOGO_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.DEFINATION_OF_LOGO_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefinationOfLogoResponseModel> call, Throwable t) {
                message.what = apiInterface.DEFINATION_OF_LOGO_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // findus api from menu click

    public void findusApi(final Handler mHandler) {

        final Message message = new Message();
        Call<FindUsResponseModel> call = apiInterface.findus();
        call.enqueue(new Callback<FindUsResponseModel>() {
            @Override
            public void onResponse(Call<FindUsResponseModel> call,
                                   Response<FindUsResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.FIND_US_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.FIND_US_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<FindUsResponseModel> call, Throwable t) {
                message.what = apiInterface.FIND_US_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }


    // enquiryfrom api from menu click

    public void enquiryformApi(final Handler mHandler) {

        final Message message = new Message();
        Call<FormsResponseModel> call = apiInterface.enquiryForms();
        call.enqueue(new Callback<FormsResponseModel>() {
            @Override
            public void onResponse(Call<FormsResponseModel> call,
                                   Response<FormsResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.FORM_LIST_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.FORM_LIST_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<FormsResponseModel> call, Throwable t) {
                message.what = apiInterface.FORM_LIST_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // socailmedia api from menu click

    public void socailMediaApi(final Handler mHandler) {

        final Message message = new Message();
        Call<SocialMediaResponseModel> call = apiInterface.socailMedia();
        call.enqueue(new Callback<SocialMediaResponseModel>() {
            @Override
            public void onResponse(Call<SocialMediaResponseModel> call,
                                   Response<SocialMediaResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SOCAIL_MEDIA_LIST_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SOCAIL_MEDIA_LIST_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<SocialMediaResponseModel> call, Throwable t) {
                message.what = apiInterface.SOCAIL_MEDIA_LIST_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // gallery api from menu click

    public void galleryApi(final Handler mHandler) {

        final Message message = new Message();
        Call<GalleryResponseModel> call = apiInterface.gallery();
        call.enqueue(new Callback<GalleryResponseModel>() {
            @Override
            public void onResponse(Call<GalleryResponseModel> call,
                                   Response<GalleryResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GALLERY_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GALLERY_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<GalleryResponseModel> call, Throwable t) {
                message.what = apiInterface.GALLERY_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // gallery api from menu click

    public void extrasApi(final Handler mHandler) {

        final Message message = new Message();
        Call<ExtrasResponseModel> call = apiInterface.extras();
        call.enqueue(new Callback<ExtrasResponseModel>() {
            @Override
            public void onResponse(Call<ExtrasResponseModel> call,
                                   Response<ExtrasResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.EXTRAS_LIST_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.EXTRAS_LIST_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ExtrasResponseModel> call, Throwable t) {
                message.what = apiInterface.EXTRAS_LIST_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }


    // send payment api from menu click

    public void sendpaymentApi(final Handler mHandler) {

        final Message message = new Message();
        Call<SendPaymentResponseModel> call = apiInterface.sendPayment();
        call.enqueue(new Callback<SendPaymentResponseModel>() {
            @Override
            public void onResponse(Call<SendPaymentResponseModel> call,
                                   Response<SendPaymentResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SEND_PAYMENT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SEND_PAYMENT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<SendPaymentResponseModel> call, Throwable t) {
                message.what = apiInterface.SEND_PAYMENT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }
    //  payment option api from menu click

    public void paymentOptionApi(final Handler mHandler) {

        final Message message = new Message();
        Call<PaymentResponseModel> call = apiInterface.paymentOption();
        call.enqueue(new Callback<PaymentResponseModel>() {
            @Override
            public void onResponse(Call<PaymentResponseModel> call,
                                   Response<PaymentResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.PAYMENT_OPTION_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.PAYMENT_OPTION_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<PaymentResponseModel> call, Throwable t) {
                message.what = apiInterface.PAYMENT_OPTION_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }
    // store listing api from menu click

    public void storeListingApi(final Handler mHandler) {

        final Message message = new Message();
        Call<StoreListingResponseModel> call = apiInterface.storeList();
        call.enqueue(new Callback<StoreListingResponseModel>() {
            @Override
            public void onResponse(Call<StoreListingResponseModel> call,
                                   Response<StoreListingResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.STORE_LISTING_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.STORE_LISTING_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<StoreListingResponseModel> call, Throwable t) {
                message.what = apiInterface.STORE_LISTING_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // gallery image api from menu click

    public void galleryImageApi(String photo_id, final Handler mHandler) {

        final Message message = new Message();
        Call<GalleryImageResponseModel> call = apiInterface.galleryImage(photo_id);
        call.enqueue(new Callback<GalleryImageResponseModel>() {
            @Override
            public void onResponse(Call<GalleryImageResponseModel> call,
                                   Response<GalleryImageResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GALLERY_IMAGE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GALLERY_IMAGE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<GalleryImageResponseModel> call, Throwable t) {
                message.what = apiInterface.GALLERY_IMAGE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // uploading file listing api from menu click

    public void uploadFileApi(final Handler mHandler) {

        final Message message = new Message();
        Call<UploadFileResponseModel> call = apiInterface.uploadFileList();
        call.enqueue(new Callback<UploadFileResponseModel>() {
            @Override
            public void onResponse(Call<UploadFileResponseModel> call,
                                   Response<UploadFileResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.UPLOAD_FILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.UPLOAD_FILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<UploadFileResponseModel> call, Throwable t) {
                message.what = apiInterface.UPLOAD_FILE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // store product api from menu click
    public void storeProductApi(String id, final Handler mHandler) {

        final Message message = new Message();
        Call<StoreProductResponseModel> call = apiInterface.storeProduct(id);
        call.enqueue(new Callback<StoreProductResponseModel>() {
            @Override
            public void onResponse(Call<StoreProductResponseModel> call,
                                   Response<StoreProductResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.STORE_PRODUTC_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.STORE_PRODUTC_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<StoreProductResponseModel> call, Throwable t) {
                message.what = apiInterface.STORE_PRODUTC_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    // commerical printing api from menu click
    public void commericalPrintingApi(String category_id, final Handler mHandler) {

        final Message message = new Message();
        Call<CommericalPrintingResponseModel> call = apiInterface.commericalPrinting(category_id);
        call.enqueue(new Callback<CommericalPrintingResponseModel>() {
            @Override
            public void onResponse(Call<CommericalPrintingResponseModel> call,
                                   Response<CommericalPrintingResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.COMMERICAL_PRINTING_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.COMMERICAL_PRINTING_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<CommericalPrintingResponseModel> call, Throwable t) {
                message.what = apiInterface.COMMERICAL_PRINTING_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    //  multipage printing api from menu click
    public void multipagePrintingApi(String id, final Handler mHandler) {

        final Message message = new Message();
        Call<MultiPagePrintingResponseModel> call = apiInterface.multipagePrinting(id);
        call.enqueue(new Callback<MultiPagePrintingResponseModel>() {
            @Override
            public void onResponse(Call<MultiPagePrintingResponseModel> call,
                                   Response<MultiPagePrintingResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.MULTIPAGE_PRINTING_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.MULTIPAGE_PRINTING_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<MultiPagePrintingResponseModel> call, Throwable t) {
                message.what = apiInterface.MULTIPAGE_PRINTING_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // commerical printing categories api from menu click
    public void commercialPrintingCategoriesApi(String id, final Handler mHandler) {

        final Message message = new Message();
        Call<CommercialPrintingCategoriesResponseModel> call = apiInterface.commercialPrintingCategories(id);
        call.enqueue(new Callback<CommercialPrintingCategoriesResponseModel>() {
            @Override
            public void onResponse(Call<CommercialPrintingCategoriesResponseModel> call,
                                   Response<CommercialPrintingCategoriesResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.PRINTING_CATEGORIES_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.PRINTING_CATEGORIES_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<CommercialPrintingCategoriesResponseModel> call, Throwable t) {
                message.what = apiInterface.PRINTING_CATEGORIES_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    // submit Commercial FromApi from on button click click
    public void submitCommercialFromApi(
            String category_id,
            String sub_category_id,
            String name,
            String company,
            String email,
            String phone,
            String job,
            String jobs,
            String quantity,
            String no_of_sheets,
            String pages_per_set,
            String no_of_set,
            String side,
            String orientation,
            String color_code,
            String number_of_color,
            String paper_type,
            String paper_gram,
            String size,
            String finished_size,
            String open_size,
            String finishing,
            String comment,
            MultipartBody.Part file_name,
            MultipartBody.Part signature_file,
            String current_address, final Handler mHandler) {

        RequestBody form_categories_id = RequestBody.create(MediaType.parse("text/plain"), category_id);
        RequestBody form_sub_category_id = RequestBody.create(MediaType.parse("text/plain"), sub_category_id);
        RequestBody user_name = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody user_company = RequestBody.create(MediaType.parse("text/plain"), company);
        RequestBody user_email = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody user_phone = RequestBody.create(MediaType.parse("text/plain"), phone);
        RequestBody user_job = RequestBody.create(MediaType.parse("text/plain"), job);
        RequestBody user_jobs = RequestBody.create(MediaType.parse("text/plain"), jobs);
        RequestBody user_quantity = RequestBody.create(MediaType.parse("text/plain"), quantity);
        RequestBody user_no_of_sheets = RequestBody.create(MediaType.parse("text/plain"), no_of_sheets);
        RequestBody user_pages_per_set = RequestBody.create(MediaType.parse("text/plain"), pages_per_set);
        RequestBody user_no_of_set = RequestBody.create(MediaType.parse("text/plain"), no_of_set);
        RequestBody user_side = RequestBody.create(MediaType.parse("text/plain"), side);
        RequestBody user_orientation = RequestBody.create(MediaType.parse("text/plain"), orientation);
        RequestBody user_color_code = RequestBody.create(MediaType.parse("text/plain"), color_code);
        RequestBody user_number_of_color = RequestBody.create(MediaType.parse("text/plain"), number_of_color);
        RequestBody user_paper_type = RequestBody.create(MediaType.parse("text/plain"), paper_type);
        RequestBody user_paper_gram = RequestBody.create(MediaType.parse("text/plain"), paper_gram);
        RequestBody user_size = RequestBody.create(MediaType.parse("text/plain"), size);
        RequestBody user_finished_size = RequestBody.create(MediaType.parse("text/plain"), finished_size);
        RequestBody user_open_size = RequestBody.create(MediaType.parse("text/plain"), open_size);
        RequestBody user_finishing = RequestBody.create(MediaType.parse("text/plain"), finishing);
        RequestBody user_comment = RequestBody.create(MediaType.parse("text/plain"), comment);
        RequestBody user_current_address = RequestBody.create(MediaType.parse("text/plain"), current_address);


        final Message message = new Message();
        Call<CommercialFromSubmitResponseModel> call = apiInterface.submitCommercialForm(form_categories_id, form_sub_category_id,
                user_name, user_company, user_email, user_phone, user_job, user_jobs, user_quantity, user_no_of_sheets, user_pages_per_set,
                user_no_of_set, user_side, user_orientation, user_color_code, user_number_of_color, user_paper_type, user_paper_gram, user_size,
                user_finished_size, user_open_size, user_finishing, user_comment, file_name, signature_file, user_current_address);
        call.enqueue(new Callback<CommercialFromSubmitResponseModel>() {
            @Override
            public void onResponse(Call<CommercialFromSubmitResponseModel> call,
                                   Response<CommercialFromSubmitResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.COMMERICAL_FORM_SUBMIT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.COMMERICAL_FORM_SUBMIT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<CommercialFromSubmitResponseModel> call, Throwable t) {
                message.what = apiInterface.COMMERICAL_FORM_SUBMIT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // submit multi form FromApi from on button click click
    public void submitMultiPageFromApi(String category_id, String name,
                                       String email, String company,
                                       String phone, String job, String quantity,
                                       String color_code, String number_of_color,
                                       String orientation, String size,
                                       String finished_size, String open_size,
                                       String slide, String finishing,
                                       String binding, String no_of_page,
                                       String cover_page_type, String inside_page_type,
                                       String cover_page_gram, String inside_page_gram,
                                       String current_address, String comment,
                                       RequestBody file_nameRequest,
                                       MultipartBody.Part file_name, MultipartBody.Part signature_file, final Handler mHandler) {

       RequestBody form_categories_id = RequestBody.create(MediaType.parse("text/plain"), category_id);
       // RequestBody form_categories_id = RequestBody.create(MediaType.parse("text/plain"), "2");
        RequestBody user_name = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody user_email = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody user_company = RequestBody.create(MediaType.parse("text/plain"), company);
        RequestBody user_phone = RequestBody.create(MediaType.parse("text/plain"), phone);
        RequestBody user_job = RequestBody.create(MediaType.parse("text/plain"), job);
        RequestBody user_quantity = RequestBody.create(MediaType.parse("text/plain"), quantity);
        RequestBody user_color_code = RequestBody.create(MediaType.parse("text/plain"), color_code);
        RequestBody user_number_of_color = RequestBody.create(MediaType.parse("text/plain"), number_of_color);
        RequestBody user_orientation = RequestBody.create(MediaType.parse("text/plain"), orientation);
        RequestBody user_size = RequestBody.create(MediaType.parse("text/plain"), size);
        RequestBody user_finished_size = RequestBody.create(MediaType.parse("text/plain"), finished_size);
        RequestBody user_open_size = RequestBody.create(MediaType.parse("text/plain"), open_size);
        RequestBody user_slide = RequestBody.create(MediaType.parse("text/plain"), slide);
        RequestBody user_finishing = RequestBody.create(MediaType.parse("text/plain"), finishing);
        RequestBody user_binding = RequestBody.create(MediaType.parse("text/plain"), binding);
        RequestBody user_no_of_page = RequestBody.create(MediaType.parse("text/plain"), no_of_page);
        RequestBody user_cover_page_type = RequestBody.create(MediaType.parse("text/plain"), cover_page_type);
        RequestBody user_inside_page_type = RequestBody.create(MediaType.parse("text/plain"), inside_page_type);
        RequestBody user_cover_page_gram = RequestBody.create(MediaType.parse("text/plain"), cover_page_gram);
        RequestBody user_inside_page_gram = RequestBody.create(MediaType.parse("text/plain"), inside_page_gram);
        RequestBody user_current_address = RequestBody.create(MediaType.parse("text/plain"), current_address);
        RequestBody user_comment = RequestBody.create(MediaType.parse("text/plain"), comment);

        final Message message = new Message();
        Call<MultiPageFormSubmitResponseModel> call = apiInterface.submitMultiPageForm(form_categories_id,
                user_name, user_email, user_company, user_phone, user_job, user_quantity, user_color_code,
                user_number_of_color, user_orientation, user_size, user_finished_size, user_open_size, user_slide, user_finishing, user_binding, user_no_of_page, user_cover_page_type,
                user_inside_page_type,
                user_cover_page_gram,
                user_inside_page_gram,
                user_current_address,
                user_comment,
                file_nameRequest,
                file_name,
                signature_file);
        call.enqueue(new Callback<MultiPageFormSubmitResponseModel>() {
            @Override
            public void onResponse(Call<MultiPageFormSubmitResponseModel> call,
                                   Response<MultiPageFormSubmitResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.MULTIPAGE_FORM_SUBMIT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.MULTIPAGE_FORM_SUBMIT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<MultiPageFormSubmitResponseModel> call, Throwable t) {
                message.what = apiInterface.MULTIPAGE_FORM_SUBMIT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // submit upload form file from on button click click
    public void submitUploadFileFromApi(String name, String email, String url_link, String phone, String order_number, MultipartBody.Part upload_file, MultipartBody.Part upload_signature, final Handler mHandler) {

        RequestBody username = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody emailname = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody companyname = RequestBody.create(MediaType.parse("text/plain"), url_link);
        RequestBody phonenumber = RequestBody.create(MediaType.parse("text/plain"), phone);
        RequestBody userordernumber = RequestBody.create(MediaType.parse("text/plain"), order_number);

        final Message message = new Message();
        Call<UploadFileSubmitResponseModel> call = apiInterface.submitUploadFile(
                username,
                emailname,
                companyname,
                phonenumber,
                userordernumber,
                upload_file,
                upload_signature);
        call.enqueue(new Callback<UploadFileSubmitResponseModel>() {
            @Override
            public void onResponse(Call<UploadFileSubmitResponseModel> call,
                                   Response<UploadFileSubmitResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SUBMIT_UPLOAD_FILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SUBMIT_UPLOAD_FILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<UploadFileSubmitResponseModel> call, Throwable t) {
                message.what = apiInterface.SUBMIT_UPLOAD_FILE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // product details api
    public void productDetailsApi(String id, final Handler mHandler) {

        final Message message = new Message();
        Call<ProductDetailsResponseModel> call = apiInterface.getProductDetails(id);
        call.enqueue(new Callback<ProductDetailsResponseModel>() {
            @Override
            public void onResponse(Call<ProductDetailsResponseModel> call,
                                   Response<ProductDetailsResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.PRODUCT_DETAIL_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.PRODUCT_DETAIL_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductDetailsResponseModel> call, Throwable t) {
                message.what = apiInterface.PRODUCT_DETAIL_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    // review api api
    public void reviewApi(String id, final Handler mHandler) {

        final Message message = new Message();
        Call<ReviewResponseModel> call = apiInterface.getReview(id);
        call.enqueue(new Callback<ReviewResponseModel>() {
            @Override
            public void onResponse(Call<ReviewResponseModel> call,
                                   Response<ReviewResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.REVIEW_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.REVIEW_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewResponseModel> call, Throwable t) {
                message.what = apiInterface.REVIEW_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    // get price api
    public void priceApi(String id, String quantity, String pages, String cover, final Handler mHandler) {

        final Message message = new Message();
        Call<VariationResponseModel> call = apiInterface.getPrice(id, quantity, pages, cover,"123");
        call.enqueue(new Callback<VariationResponseModel>() {
            @Override
            public void onResponse(Call<VariationResponseModel> call,
                                   Response<VariationResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GET_PRICE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GET_PRICE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<VariationResponseModel> call, Throwable t) {
                message.what = apiInterface.GET_PRICE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    // user signup
    public void signUpApi(String first_name, String last_name, String phone,
                          String email, String password, final Handler mHandler) {
        final Message message = new Message();
        Call<SignUpResponseModel> call = apiInterface.userSignUp(first_name, last_name, phone, email, password);
        call.enqueue(new Callback<SignUpResponseModel>() {
            @Override
            public void onResponse(Call<SignUpResponseModel> call,
                                   Response<SignUpResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SIGNUP_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SIGNUP_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponseModel> call, Throwable t) {
                message.what = apiInterface.SIGNUP_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    // user login
    public void loginApi(String email, String password, String devices_token,
                         String devices_type, String login_type, String current_latitude, String current_longitude, final Handler mHandler) {
        final Message message = new Message();
        Call<LoginResponseModel> call = apiInterface.userLogin(email, password, devices_token, devices_type, login_type, current_latitude, current_longitude);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call,
                                   Response<LoginResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.lOGIN_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.lOGIN_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                message.what = apiInterface.lOGIN_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    // about us api from menu click
    public void otherApi(final Handler mHandler) {

        final Message message = new Message();
        Call<OtherResponseModel> call = apiInterface.other();
        call.enqueue(new Callback<OtherResponseModel>() {
            @Override
            public void onResponse(Call<OtherResponseModel> call,
                                   Response<OtherResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.POLICY_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.POLICY_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<OtherResponseModel> call, Throwable t) {
                message.what = apiInterface.POLICY_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }


    // resetPassword
    public void resetPasswordApi(String email, final Handler mHandler) {
        final Message message = new Message();
        Call<ResetPasswordResponseModel> call = apiInterface.restPassword(email);
        call.enqueue(new Callback<ResetPasswordResponseModel>() {
            @Override
            public void onResponse(Call<ResetPasswordResponseModel> call,
                                   Response<ResetPasswordResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.FORGOT_PASSWORD_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.FORGOT_PASSWORD_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponseModel> call, Throwable t) {
                message.what = apiInterface.FORGOT_PASSWORD_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    // resetPassword
    public void logoutApi(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<LogoutResponseModel> call = apiInterface.logout(token);
        call.enqueue(new Callback<LogoutResponseModel>() {
            @Override
            public void onResponse(Call<LogoutResponseModel> call,
                                   Response<LogoutResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.LOGOUT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.LOGOUT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutResponseModel> call, Throwable t) {
                message.what = apiInterface.LOGOUT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    // resetPassword
    public void changePasswordApi(String token,String old_password,String new_password, final Handler mHandler) {
        final Message message = new Message();
        Call<ChangePasswordResponseModel> call = apiInterface.changePassword(token,old_password,new_password);
        call.enqueue(new Callback<ChangePasswordResponseModel>() {
            @Override
            public void onResponse(Call<ChangePasswordResponseModel> call,
                                   Response<ChangePasswordResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.CHANGE_PASSWORD_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.CHANGE_PASSWORD_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponseModel> call, Throwable t) {
                message.what = apiInterface.CHANGE_PASSWORD_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }


    // update profile
    public void updateProfileApi(String token,String first_name,String last_name, String email, String phone, RequestBody imageRequest,MultipartBody.Part imageToUpload, final Handler mHandler) {

        RequestBody usertoken = RequestBody.create(MediaType.parse("text/plain"), token);
        RequestBody userfirstname = RequestBody.create(MediaType.parse("text/plain"), first_name);
        RequestBody userlastname = RequestBody.create(MediaType.parse("text/plain"), last_name);
        RequestBody emailname = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody phonenumber = RequestBody.create(MediaType.parse("text/plain"), phone);

        final Message message = new Message();
        Call<UpdateProfileResponseModel> call = apiInterface.updateUserProfile(
                usertoken,
                userfirstname,
                userlastname,
                emailname,
                phonenumber,
                imageRequest,
                imageToUpload);
        call.enqueue(new Callback<UpdateProfileResponseModel>() {
            @Override
            public void onResponse(Call<UpdateProfileResponseModel> call,
                                   Response<UpdateProfileResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.UPDATE_PROFILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.UPDATE_PROFILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.UPDATE_PROFILE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    // add billing address
    public void addBillingAddressApi(String user_id,String first_name,String last_name,
                                       String address_1,
                                     String address_2, String city_name, String zip_code, String country_name,
                                     String state_name,String phone,
                                     final Handler mHandler) {
        final Message message = new Message();
        Call<BillingAddressResponseModel> call = apiInterface.addBillingAddress(user_id,
                first_name,last_name,address_1,
                address_2,city_name,zip_code,country_name,state_name,phone);
        call.enqueue(new Callback<BillingAddressResponseModel>() {
            @Override
            public void onResponse(Call<BillingAddressResponseModel> call,
                                   Response<BillingAddressResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.BILLING_ADDRESS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.BILLING_ADDRESS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<BillingAddressResponseModel> call, Throwable t) {
                message.what = apiInterface.BILLING_ADDRESS_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

 // add billing address
    public void getBillingAddressApi(String user_id,final Handler mHandler) {
        final Message message = new Message();
        Call<GetAddressResponseModel> call = apiInterface.getAddress(user_id);
        call.enqueue(new Callback<GetAddressResponseModel>() {
            @Override
            public void onResponse(Call<GetAddressResponseModel> call,
                                   Response<GetAddressResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GET_ADDRESS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GET_ADDRESS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAddressResponseModel> call, Throwable t) {
                message.what = apiInterface.GET_ADDRESS_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    // add billing address
    public void socialLoginApi(String email,String first_name,String last_name,
                               String image,String login_type,
                               String device_type,String current_lattitude,
                               String current_longitude,String device_token,final Handler mHandler) {
        final Message message = new Message();
        Call<SocialLoginResponseModel> call = apiInterface.socialLogin(email,first_name,last_name,image,
                login_type,device_type,current_lattitude,current_longitude,device_token);
        call.enqueue(new Callback<SocialLoginResponseModel>() {
            @Override
            public void onResponse(Call<SocialLoginResponseModel> call,
                                   Response<SocialLoginResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SOCIAL_LOGIN_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SOCIAL_LOGIN_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<SocialLoginResponseModel> call, Throwable t) {
                message.what = apiInterface.SOCIAL_LOGIN_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }
}

