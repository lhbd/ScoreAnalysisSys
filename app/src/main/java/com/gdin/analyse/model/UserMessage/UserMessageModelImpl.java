package com.gdin.analyse.model.UserMessage;

import com.gdin.analyse.entity.UserMessage;

import java.util.ArrayList;
import java.util.List;

public class UserMessageModelImpl implements UserMessageModel {
    @Override
    public List<UserMessage> init() {
        List<UserMessage> data = new ArrayList<>();
        data.add(new UserMessage("学校","广师附中"));
        data.add(new UserMessage("年级","初二"));
        data.add(new UserMessage("班级","一班"));
        data.add(new UserMessage("姓名","咯欧锦"));
        data.add(new UserMessage("用户名","001"));
        return data;
    }
}
