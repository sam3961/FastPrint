package com.app.fastprint.ui.settings.presenter;

import com.app.fastprint.ui.settings.SettingActivity;
import com.app.fastprint.ui.settings.interfaces.IMSetting;
import com.app.fastprint.ui.settings.interfaces.IPSetting;
import com.app.fastprint.ui.settings.interfaces.ISetting;
import com.app.fastprint.ui.settings.responseModel.LogoutResponseModel;
import com.app.fastprint.ui.settings.viewModel.MSetting;

public
class PSetting  implements IPSetting {
    ISetting iSetting;
    IMSetting imSetting;
    public PSetting(SettingActivity settingActivity) {
        this.iSetting=settingActivity;
    }

    @Override
    public void logout(String token) {
        imSetting=new MSetting(this);
        imSetting.logoutRestCalls(token);
    }

    @Override
    public void successResponseFromModel(LogoutResponseModel logoutResponseModel) {
        iSetting.successResponseFromPresenter(logoutResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iSetting.errorResponseFromPresenter(message);
    }
}
