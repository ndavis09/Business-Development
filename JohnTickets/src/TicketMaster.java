/**
 * 
 */
import java.io.IOException;
import java.io.InputStream;

import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
/**
 * @author nickdavis
 *
 */
public class TicketMaster {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try 
		{
	        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			
			// Send SOAP Message to SOAP Server
            String url = "http://api.affiliatewindow.com";
            SOAPMessage soapResponse = soapConnection.call(getSoapMessageFromString(), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);

            soapConnection.close();
			
		} 
		
		catch (UnsupportedOperationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (SOAPException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static SOAPMessage getSoapMessageFromString() throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		InputStream is = TicketMaster.class.getResourceAsStream("test.xml");
		SOAPMessage message = factory.createMessage(new MimeHeaders(), is);
		return message;
	}

	 /**
     * Method used to print the SOAP Response
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }

}
