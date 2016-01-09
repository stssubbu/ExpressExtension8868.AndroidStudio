package com.span.expressextension8868.utils.utility;

/**
 * Created by STS-099 on 11/13/2015.
 */
public enum StatusEnum {

    None(0), InProgress(1), Transmitted(2), Accepted(3), Rejected(4);


    int Ordinal = 0;

    StatusEnum(int ord) {
        this.Ordinal = ord;
    }

    public static StatusEnum byOrdinal2ndWay(int ord) {
        return StatusEnum.values()[ord - 1]; // less safe
    }

    public static StatusEnum byOrdinal(int ord) {
        for (StatusEnum m : StatusEnum.values()) {
            if (m.Ordinal == ord) {
                return m;
            }
        }
        return null;
    }

    //  public static StatusEnum[] MONTHS_INDEXED = new StatusEnum[]{null, JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC};


}
