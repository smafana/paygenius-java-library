package za.co.paygenius.developer.paygeniusjavalibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.CreatePaymentRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.ExecutePaymentRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.PartialRefundRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.CreatePaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.TransactionResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.feign.CardPaymentRandFeignClient;

import java.util.Map;

import static za.co.paygenius.developer.paygeniusjavalibrary.service.GenerateSignature.getSignature;
import org.apache.commons.httpclient.*;


@Service
@Slf4j
public class CardPaymentRandService {
    private static final Logger log = LoggerFactory.getLogger(CardPaymentRandService.class);

    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;
    @Value("${paygenius.token}")
    private String token;

    @Autowired
    CardPaymentRandFeignClient cardPaymentRandFeignClient;

    @Autowired
    GenerateSignature generateSignature;
    private <T> T send(AbstractRequest request, Class<T> responseType)  throws Exception {
        String url = baseUrl + request.getEndPoint();

        HttpHeaders updatedHeaders = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();

        if (request.getMethod().equals(Request.HttpMethod.POST)) {
            String jsonPayload = objectMapper.writeValueAsString(request);
            updatedHeaders.set("X-Signature", generateSignature.getSignature(jsonPayload, url));
        } else {
            updatedHeaders.set("X-Signature", generateSignature.getSignature(null, url));
        }


        updatedHeaders.set("X-Token", token);

        T response = this.sendHttpRequest(jsonPayload, url, updatedHeaders);

        return response;
    }

    public <T> T sendHttpRequest(String data, String url, HttpHeaders headers) {


        int status = 0;
        String responseBody = "";
        PostMethod method = null;
        HostConfiguration hostConf = new HostConfiguration();
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

        try {
            HttpConnectionManagerParams connParam = new HttpConnectionManagerParams();
            connParam.setMaxConnectionsPerHost(hostConf, 20);
            connParam.setMaxTotalConnections(40);
            connParam.setStaleCheckingEnabled(true);
            connParam.setSoTimeout(60 * 1000);
            connectionManager.setParams(connParam);

            HttpClient client = new HttpClient(connectionManager);
            client.setHostConfiguration(hostConf);

            String putData = data;
            client.getParams().setAuthenticationPreemptive(true);



            method = new PostMethod(fullUrl);
            method.addRequestHeader("Content-Type", "application/json");
            method.addRequestHeader("X-Token", paygeniusToken);
            method.addRequestHeader("Accept", "application/json");
            method.addRequestHeader("X-Signature", signRequest(fullUrl, putData, true));

            StringRequestEntity requestEntity = new StringRequestEntity(putData, "application/json", "UTF-8");
            method.setRequestEntity(requestEntity);
            method.setRequestHeader("Connection", "close");

            status = client.executeMethod(method);
            LOGGER.info("Status payment:" + status);
            responseBody = method.getResponseBodyAsString();



            LOGGER.info("payment response -> " + responseBody);
        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {

            if (method != null) {
                try {
                    method.releaseConnection();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (status == 200) {
                //Record the report in the database so it is not done again for this site
                return responseBody;
            }
        }
        return responseBody;
    }


    public CreatePaymentResponse createPayment(final CreatePaymentRequest createPaymentRequest) throws Exception {
        CreatePaymentResponse createPaymentResponse = this.send(createPaymentRequest, CreatePaymentResponse.class);
        return createPaymentResponse;
    }

    public TransactionResponse partialRefund(final PartialRefundRequest partialRefundRequest, Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v2/payment/"+reference+"/refund";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(partialRefundRequest);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        TransactionResponse transactionResponse = cardPaymentRandFeignClient.refundPartialAmount(partialRefundRequest, reference, updatedHeaders);
        return transactionResponse;
    }

    public TransactionResponse fullRefund(Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v2/payment/"+reference+"/refund";
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(null,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling: "+url);
        TransactionResponse transactionResponse = cardPaymentRandFeignClient.refundFullAmount(reference, updatedHeaders);
        return transactionResponse;
    }

    public TransactionResponse executeTransaction(Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v2/payment/"+reference+"/execute";
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(null,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("Calling: "+url);
        TransactionResponse transactionResponse = cardPaymentRandFeignClient.executeTransaction(reference, updatedHeaders);
        return transactionResponse;
    }

    public TransactionResponse executeTransactionMinimum(String reference) throws Exception{
        TransactionResponse transactionResponse = this.send(new ExecutePaymentRequest(reference), TransactionResponse.class);
        return transactionResponse;
    }

    public TransactionResponse executePartTransaction(String reference) throws Exception{
        TransactionResponse transactionResponse = this.send(new ExecutePaymentRequest(reference), TransactionResponse.class);
        return transactionResponse;
    }
}
