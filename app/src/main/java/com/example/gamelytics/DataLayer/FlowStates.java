package com.example.gamelytics.DataLayer;

public interface FlowStates {

    // Return codes
    public static final double CURRENCY_DONT_EXIST = -1.0;
    public static final Object NOT_VALID_URL = null;
    public static final Object ERRONEOUS_JSON = null;
    public static final int SHOW_DISPLAYACTIVITY = 2;
    public static final int MODIFIED_CARD_OK = 0;
    public static final int MODIFIED_CARD_ERROR = 1;
    public static final int COULD_NOT_REMOVE_CARD = -1;
}
