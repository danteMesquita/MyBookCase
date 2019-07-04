package com.example.mybookcase.Util;

import com.example.mybookcase.Models.UsuarioModel;

public class CurrenteUserHelper {
    private static UsuarioModel currenteUser;
    private static CurrenteUserHelper instance;

    private CurrenteUserHelper(){}

    public static CurrenteUserHelper getInstance() {
        if(instance == null || instance.equals(null)) {
            instance = new CurrenteUserHelper();
        }
        return instance;
    }

    public static void setCurrentUser(UsuarioModel userModel) {
        currenteUser = userModel;
    }

    public static int getCurrentUserId() {
        return currenteUser._id;
    }

    public static void Destroy() {
        currenteUser = null;
        instance = null;
    }
}
