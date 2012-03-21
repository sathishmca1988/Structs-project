package android.websevice.client.samples;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidClientService extends Activity {

	private static final String SOAP_ACTION = "http://www.naveenbalani.com/webservices/WassupAndroidService/todaysMessage";

	private static final String OPERATION_NAME = "todaysMessage";

	private static final String WSDL_TARGET_NAMESPACE = "http://www.naveenbalani.com/webservices/WassupAndroidService/";

	private static final String SOAP_ADDRESS = "http://naveenbalani.com/WassupAndroid.asmx";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(this);

		setContentView(textView);

		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
				OPERATION_NAME);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);

		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

		try

		{

			httpTransport.call(SOAP_ACTION, envelope);

			Object response = envelope.getResponse();

			textView.setText(response.toString());

		}

		catch (Exception exception)

		{

			textView.setText(exception.toString());

		}

	}
}