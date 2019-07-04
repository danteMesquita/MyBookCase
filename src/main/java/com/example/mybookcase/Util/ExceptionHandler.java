package com.example.mybookcase.Util;

import android.widget.Toast;

public class ExceptionHandler {
    public String exceptionMessage;

    public ExceptionHandler(Exception ex) {
        this.exceptionMessage = ex.getMessage();
    }
}
