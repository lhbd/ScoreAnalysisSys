package com.gdin.analyse.present;

import com.gdin.analyse.entity.UserMessage;
import com.gdin.analyse.model.UserMessage.UserMessageModel;
import com.gdin.analyse.model.UserMessage.UserMessageModelImpl;
import com.gdin.analyse.view.UserMessageView;

import java.util.List;

public class UserMessagePresent {
    UserMessageView view ;
    UserMessageModel model;

    public UserMessagePresent(UserMessageView view) {
        this.view = view;
        model = new UserMessageModelImpl();
    }

    public List<UserMessage> init(){
        return model.init();
    }

}
