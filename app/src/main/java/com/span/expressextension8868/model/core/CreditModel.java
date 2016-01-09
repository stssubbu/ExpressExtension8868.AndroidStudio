package com.span.expressextension8868.model.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by STS-099 on 12/10/2015.
 */
public class CreditModel implements Parcelable {

    String Address1, Address2, city, state, country, zip;

    String FirstName, LastName, CardType, CreditCardnumber, expirymonth, expiryyear, cvc, stateorprovince;
    String isforadd;

    public String getIsforadd() {
        return this.isforadd;
    }

    public void setIsforadd(String isforadd) {
        this.isforadd = isforadd;
    }

    public String getStateorprovince() {
        return stateorprovince;
    }

    public void setStateorprovince(String stateorprovince) {
        this.stateorprovince = stateorprovince;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getCreditCardnumber() {
        return CreditCardnumber;
    }

    public void setCreditCardnumber(String creditCardnumber) {
        CreditCardnumber = creditCardnumber;
    }

    public String getExpirymonth() {
        return expirymonth;
    }

    public void setExpirymonth(String expirymonth) {
        this.expirymonth = expirymonth;
    }

    public String getExpiryyear() {
        return expiryyear;
    }

    public void setExpiryyear(String expiryyear) {
        this.expiryyear = expiryyear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void checkinputs() {
        Log.i("Credit inputs", "FirstName " + this.FirstName);
        Log.i("Credit inputs", "LastName " + this.LastName);
        Log.i("Credit inputs", "CardType " + this.CardType);
        Log.i("Credit inputs", "CreditCardnumber " + this.CreditCardnumber);
        Log.i("Credit inputs", "expirymonth " + this.expirymonth);
        Log.i("Credit inputs", "expiryyear " + this.expiryyear);
        Log.i("Credit inputs", "cvc " + this.cvc);
        Log.i("Credit inputs", "stateorprovince " + this.stateorprovince);
        Log.i("Credit inputs", "Address1 " + this.Address1);
        Log.i("Credit inputs", "Address2 " + this.Address2);
        Log.i("Credit inputs", "city " + this.city);
        Log.i("Credit inputs", "state " + this.state);
        Log.i("Credit inputs", "country " + this.country);
        Log.i("Credit inputs", "zip " + this.zip);
        Log.i("Credit inputs", "ISFORADD " + this.isforadd);
    }

    public static final Parcelable.Creator<CreditModel> CREATOR = new Creator<CreditModel>() {
        public CreditModel createFromParcel(Parcel source) {
            CreditModel creditModel = new CreditModel();
            creditModel.FirstName = source.readString();
            creditModel.LastName = source.readString();
            creditModel.CardType = source.readString();
            creditModel.CreditCardnumber = source.readString();
            creditModel.expirymonth = source.readString();
            creditModel.expiryyear = source.readString();
            creditModel.cvc = source.readString();
            creditModel.stateorprovince = source.readString();
            creditModel.Address1 = source.readString();
            creditModel.Address2 = source.readString();
            creditModel.city = source.readString();
            creditModel.state = source.readString();
            creditModel.country = source.readString();
            creditModel.zip = source.readString();
            creditModel.isforadd = source.readString();
            return creditModel;
        }

        public CreditModel[] newArray(int size) {
            return new CreditModel[size];
        }
    };

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(FirstName);
        parcel.writeString(LastName);
        parcel.writeString(CardType);
        parcel.writeString(CreditCardnumber);
        parcel.writeString(expirymonth);
        parcel.writeString(expiryyear);
        parcel.writeString(cvc);
        parcel.writeString(stateorprovince);
        parcel.writeString(Address1);
        parcel.writeString(Address2);
        parcel.writeString(city);
        parcel.writeString(state);
        parcel.writeString(country);
        parcel.writeString(zip);
        parcel.writeString(isforadd);
    }

}