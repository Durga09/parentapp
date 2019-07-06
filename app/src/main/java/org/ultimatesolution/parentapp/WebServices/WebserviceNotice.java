package org.ultimatesolution.parentapp.WebServices;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public  class WebserviceNotice {

    //Namespace of the Webservice - It is http://tempuri.org for .NET webservice
    private static String NAMESPACE = "http://tempuri.org/";
    //Webservice URL - It is asmx file location hosted in the server in case of .Net
    //Change the IP address to your machine IP address
    private static String URL = "http://schools.ct600.com/WebServices/ManagePosts.asmx";
    //SOAP Action URI again http://tempuri.org
    private static String SOAP_ACTION = "http://tempuri.org/";

    public static String getNotices(String schoolID, String lastID, String classid, String userid) {
        String resTxt = null;
        String LineNo = null;
        String webMethName = null;

        webMethName = "GetNotices";

        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        request.addProperty("schoolID",schoolID );
        request.addProperty("lastID", lastID);
        request.addProperty("classid", classid);
        request.addProperty("userid", userid);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        //Set envelope as dotNet
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            // Assign it to resTxt variable static variable
            resTxt = response.toString();

        } catch (Exception e) {
            resTxt = "Check that you have an active Internet connection on your device. " + e.getMessage();
        }
        //Return resTxt to calling object
        return resTxt;
    }
}
