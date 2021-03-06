package com.gdin.analyse.info;
public class ApiException extends RuntimeException {

    public static final int LOGIN_FAIL = -1;
    public static final int PWD_ERROR = 1;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code){
        String message = "";
        switch (code) {
            case LOGIN_FAIL:
                message = "该用户不存在或密码错误";
                break;
            case PWD_ERROR:
                message = "密码错误";
                break;

            default:
                message = "未知错误";

        }
        return message;
    }
}

