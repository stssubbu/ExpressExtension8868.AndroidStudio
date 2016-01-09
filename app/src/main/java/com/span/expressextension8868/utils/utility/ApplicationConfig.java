package com.span.expressextension8868.utils.utility;

public class ApplicationConfig {

    //Staging URL
    //public static String BASE_URL = "http://ws990.efile4taxes.com/"; // - - -

    // Dev URL
    public static String BASE_URL = "http://ws990.fileafter.com/"; // - - -

    // Local URL
    //public static String BASE_URL="http://ws990local.fileafter.com/";

    //Common URL - LAN IP
    //public static String BASE_URL =  "http://192.168.1.33/";

    public static String COMMONSERVICE = "E990CommonService.svc/";

    public static String SERVICE_NAME = "E990Form8868Service.svc/";

    public static String REGISTER_MOB_USER = BASE_URL + COMMONSERVICE + "RegisterMobUser";

    public static String GET_USER_FOR_SIGNIN = BASE_URL + COMMONSERVICE + "GetUserForSignIn";

    public static String CHECKLATESTVERSION = BASE_URL + COMMONSERVICE + "CheckLatestVersion";

    public static String FORGOT_PASSWORD = BASE_URL + COMMONSERVICE + "ForgotPassword";

    public static String GET_CPA_PROFILE_BY_USERID = BASE_URL + COMMONSERVICE + "GetCPAProfileByUserId";

    public static String UPDATE_CPA_PROFILE = BASE_URL + COMMONSERVICE + "UpdateCPAProfile";

    public static String SENDERRORMAIL = BASE_URL + COMMONSERVICE + "SendErrorMail";

    public static String ASSIGN_STATIC_DATA = BASE_URL + COMMONSERVICE + "AssignStaticData";

    public static String DELETE_RETURN_BY_ID = BASE_URL + COMMONSERVICE + "DeleteReturnById";

    public static String GETTIMEZONEDETAILSBYSTATEID = BASE_URL + COMMONSERVICE + "GetTimeZoneDetailsByStateId";

    public static String GETPAYMENTDETAILSBYRETURNID = BASE_URL + COMMONSERVICE + "GetPaymentDetailsByReturnId";

    public static String SAVEPAYMENTDETAILS = BASE_URL + COMMONSERVICE + "SavePaymentDetails";

    public static String GETORDERSUMMARYBYRETURNID = BASE_URL + COMMONSERVICE + "GetOrderSummaryByReturnId";

    public static String PROCESSCREDITCARDANDTRANSMITRETURN = BASE_URL + COMMONSERVICE + "ProcessCreditCardAndTransmitReturn";

    public static String GETBUSINESSFROMLOOKUP = BASE_URL + COMMONSERVICE + "GetBusinessFromLookup";

    public static String UPDATEEXEMPTORGANIZATIONDETAILS = BASE_URL + COMMONSERVICE + "UpdateBusinessDetails";

    public static String GET_BUSINESS_FROM_LOOKUP = BASE_URL + COMMONSERVICE + "GetBusinessFromLookup";

    public static String GET_BUSINESS_LISY_BY_USERID = BASE_URL + COMMONSERVICE + "GetBusinessListByUserId";

    public static String GET_BUSINESS_LIST_DETAIL_BY_BID = BASE_URL + COMMONSERVICE + "GetBusinessDetailById";

    public static String GET_RETURN_LIST_BY_BID = BASE_URL + COMMONSERVICE + "GetReturnsListByBusinessId";

    public static String SAVETAXYEARDETAILS = BASE_URL + COMMONSERVICE + "SaveTaxYearDetails";

    public static String GETRETURNDETAILSBYRETURNID = BASE_URL + COMMONSERVICE + "GetReturnDetailsByReturnId";


    //Service Methods


    public static String GETEXEMPTORGANIZATIONLISTBYUSERID = BASE_URL + SERVICE_NAME + "GetExemptOrganizationListByUserId";


    public static String SAVEIRSPAYMENTDETAILS = BASE_URL + SERVICE_NAME + "SaveIRSPaymentDetails";

    public static String GETIRSPAYMENTDETAILBYID = BASE_URL + SERVICE_NAME + "GetIRSPaymentDetailById";

    public static String SENDFORM = BASE_URL + SERVICE_NAME + "SendForm";

    public static String SENDAPPROVAL = BASE_URL + SERVICE_NAME + "SendApprovalLetter";

    public static String SENDRECEIPT = BASE_URL + SERVICE_NAME + "SendReceipt";

    public static String VIEWFORM = BASE_URL + SERVICE_NAME + "ViewForm";

    public static String VIEWAPPROVAL = BASE_URL + SERVICE_NAME + "ViewApprovalLetter";

    public static String VIEWRECEIPT = BASE_URL + SERVICE_NAME + "ViewReceipt";


    public static String GET8868STATICDATA = BASE_URL + SERVICE_NAME + "Get8868StaticData";

    public static String GET8868DUEDATE = BASE_URL + SERVICE_NAME + "Get8868DueDate";

    public static String UPDATEFORM8868PARTOFGROUP = BASE_URL + SERVICE_NAME + "UpdateForm8868PartOfGroup";

    public static String SAVE8868EXTENSIONDETAILS = BASE_URL + SERVICE_NAME + "Save8868ExtensionDetails";

    public static String GET8868PARTOFGROUPCOUNTBYRETURNID = BASE_URL + SERVICE_NAME + "Get8868PartOfGroupCountByReturnId";

    public static String GET8868PARTOFGROUPDETAILSBYHCID = BASE_URL + SERVICE_NAME + "Get8868PartOfGroupDetailsByHCId";

    public static String GET8868PARTOFGROUPLISTBYRETURNID = BASE_URL + SERVICE_NAME + "Get8868PartOfGroupListByReturnId";


    public static String GETBOOKINCAREOFBYRETURNID = BASE_URL + SERVICE_NAME + "GetBookInCareOfByReturnId";

    public static String SAVE_BOOK_IN_CARE_OF = BASE_URL + SERVICE_NAME + "SaveBookInCareOf";

    public static String GET_SUMMARY_DETAILS = BASE_URL + SERVICE_NAME + "GetSummaryReturnDetailsByReturnId";

    public static String AUDIT_FORM_8868 = BASE_URL + SERVICE_NAME + "AuditForm8868";

    public static String GET_PAYMENT_DETAILS_BY_RETURN_ID = BASE_URL + COMMONSERVICE + "GetPaymentDetailsByReturnId";

    public static String SAVE_PAYMENT_DETAILS_BY_RETURN_ID = BASE_URL + COMMONSERVICE + "SavePaymentDetails";

    public static String GETRETURNSLISTBYBUSINESSID = BASE_URL + COMMONSERVICE
            + "GetReturnsListByBusinessId";

    public static String GETBUSINESSLISTBYUSERID = BASE_URL + COMMONSERVICE
            + "GetBusinessListByUserId";

    public static String DELETERETURNBYID = BASE_URL + COMMONSERVICE
            + "DeleteReturnById";


    public static String ALLOW_RETURN_BY_ID = BASE_URL + SERVICE_NAME
            + "AllowReturnById";

    public static String GET_ADDRESS_BY_ID = BASE_URL + COMMONSERVICE
            + "GetBusinessAddressDetailById";


}