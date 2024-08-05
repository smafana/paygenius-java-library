package za.co.paygenius.developer.paygeniusjavalibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.responses.CreatePaymentOtherResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.responses.EditPaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.ChallengedResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.CreatePaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.NonChallengedResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.Response3DSecureV1;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardNumberRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardTokenRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardTransactionReferenceRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.RegisterCardRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.LookupCardResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.RegisterCardResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.UnregisterCardResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.TransactionResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.feign.CardPaymentOtherFeignClient;
import za.co.paygenius.developer.paygeniusjavalibrary.feign.CardPaymentRandFeignClient;
import za.co.paygenius.developer.paygeniusjavalibrary.feign.CardVaultMethodsFeignClient;

import java.util.Map;

import static za.co.paygenius.developer.paygeniusjavalibrary.service.GenerateSignature.getSignature;

@Service
@Slf4j
public class PayGeniusService {
    private static final Logger log = LoggerFactory.getLogger(PayGeniusService.class);
    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;

    @Autowired
    CardVaultMethodsFeignClient cardVaultMethodsFeignClient;

    @Autowired
    CardPaymentOtherFeignClient cardPaymentOtherFeignClient;

    @Autowired
    CardPaymentRandFeignClient cardPaymentRandFeignClient;

    @Autowired
    private RequestEnrichmentService enrichmentService;

    //CARD PAYMENTS ZAR METHODS

    public CreatePaymentResponse createPayment(final CreatePaymentRequest request, Map<String, String> headers) throws Exception {
        HttpHeaders httpHeaders = enrichmentService.prepareRequest(request, headers);
        return cardPaymentRandFeignClient.createPayment(request, httpHeaders);
    }

    public TransactionResponse executePartPayment(ExecutePaymentRequest request,Map<String, String> headers) throws Exception {
        request.setEndPoint("/v2/payment/"+request.getTransaction().getReference()+"/execute");
        HttpHeaders httpHeaders = enrichmentService.prepareRequest(request, headers);
        return cardPaymentRandFeignClient.executePartTransaction(request,request.getTransaction().getReference(), httpHeaders);
    }

    public TransactionResponse executeFullPayment(Map<String, String> headers,String reference) throws Exception {
        String url = baseUrl+"/v2/payment/"+reference+"/execute";
        HttpHeaders httpHeaders = enrichmentService.prepareGetRequest(url,headers);
        return cardPaymentRandFeignClient.executeTransaction(reference, httpHeaders);
    }

    public TransactionResponse partRefund(PartialRefundRequest request, Map<String, String> headers) throws Exception {
        request.setEndPoint("/v2/payment/"+request.getTransaction().getReference()+"/refund");
        HttpHeaders httpHeaders = enrichmentService.prepareRequest(request, headers);
        return cardPaymentRandFeignClient.refundPartialAmount(request,request.getTransaction().getReference(), httpHeaders);
    }

    public TransactionResponse fullRefund(Map<String, String> headers,String reference) throws Exception {
        String url = baseUrl+"/v2/payment/"+reference+"/refund";
        HttpHeaders httpHeaders = enrichmentService.prepareGetRequest(url,headers);
        return cardPaymentRandFeignClient.fullRefund(reference, httpHeaders);
    }

    public Response3DSecureV1 secureV1(Request3DSecureV1 request, Map<String, String> headers, String reference) throws Exception {
        request.setEndPoint("/v2/payment/"+reference+"/confirm");
        HttpHeaders httpHeaders = enrichmentService.prepareRequest(request, headers);
        return cardPaymentRandFeignClient.secureV1(request, reference, httpHeaders);
    }

    public ChallengedResponse secureV2(Request3DSecureV2 request, Map<String, String> headers) throws Exception {
        request.setEndPoint("/v2/payment/3dsv2/"+request.getReference()+"/confirm");
        HttpHeaders httpHeaders = enrichmentService.prepareRequest(request, headers);
        return cardPaymentRandFeignClient.secureV2(request, request.getReference() , httpHeaders);
    }

    public NonChallengedResponse secureV3(Request3DSecureV2 request, Map<String, String> headers) throws Exception {
        request.setEndPoint("/v2/payment/3dsv2/"+request.getReference()+"/confirm");
        HttpHeaders httpHeaders = enrichmentService.prepareRequest(request, headers);
        return cardPaymentRandFeignClient.secureV3(request, request.getReference() , httpHeaders);
    }
    //CARD PAYMENTS OTHER METHODS

    public CreatePaymentOtherResponse createPaymentOther(final CreatePaymentOtherRequest request, Map<String, String> headers) throws Exception {
        HttpHeaders httpHeaders = enrichmentService.prepareRequest(request, headers);
        return cardPaymentOtherFeignClient.createPaymentWithAuthorize(request, httpHeaders);
    }

    public TransactionResponse executePartOther(ExecutePaymentOtherRequest request, Map<String, String> headers) throws Exception {
        request.setEndPoint("/v3/payment/"+request.getTransaction().getReference()+"/execute");
        HttpHeaders httpHeaders = enrichmentService.prepareRequest(request, headers);
        return cardPaymentOtherFeignClient.partialExecutePayment(request, httpHeaders,request.getTransaction().getReference());
    }

    //403(unforbidden access)
    public TransactionResponse executeFullPaymentOther(Map<String, String> headers,String reference) throws Exception {
        String url = baseUrl+"/v3/payment/"+reference+"/execute";
        HttpHeaders httpHeaders = enrichmentService.prepareGetRequest(url,headers);
        return cardPaymentOtherFeignClient.executePayment(reference, httpHeaders);
    }

    public TransactionResponse fullRefundOther(Map<String, String> headers,String reference) throws Exception {
        String url = baseUrl+"/v3/payment/"+reference+"/refund";
        HttpHeaders httpHeaders = enrichmentService.prepareGetRequest(url,headers);
        return cardPaymentOtherFeignClient.fullRefundPayment(reference, httpHeaders);
    }

    public TransactionResponse partRefundOther(RefundPaymentOtherRequest request, Map<String, String> headers) throws Exception {
        request.setEndPoint("/v3/payment/"+request.getTransaction().getReference()+"/refund");
        HttpHeaders httpHeaders = enrichmentService.prepareRequest(request, headers);
        return cardPaymentOtherFeignClient.partialRefundPayment(request, httpHeaders, request.getTransaction().getReference());
    }

    public EditPaymentResponse editPayment(EditPaymentRequest request, Map<String, String> headers, String reference) throws Exception{
        request.setEndPoint("/v3/payment/"+reference+"/edit");
        HttpHeaders httpHeaders = enrichmentService.prepareRequest(request, headers);
        return cardPaymentOtherFeignClient.editPayment(request, httpHeaders, reference);
    }

    public EditPaymentResponse editPaymentAdvanced(EditPaymentAdvancedRequest request, Map<String, String> headers,String reference) throws Exception{
        HttpHeaders httpHeaders = enrichmentService.prepareRequest(request, headers);
        return cardPaymentOtherFeignClient.editPaymentAdvanced(request, httpHeaders, reference);
    }





























/*


    public LookupCardResponse lookupCardNumber(final LookupCardNumberRequest request, Map<String, String> headers) throws Exception {
        String url = baseUrl+"/v2/card/lookup";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("Calling: "+url);
        LookupCardResponse response = cardVaultMethodsFeignClient.lookupCardByNumber(request, updatedHeaders);
        return response;
    }

    public LookupCardResponse lookupCardToken(final LookupCardTokenRequest request, Map<String, String> headers) throws Exception {
        String url = baseUrl+"/v2/card/lookup";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("Calling: "+url);
        LookupCardResponse response = cardVaultMethodsFeignClient.lookupCardByToken(request, updatedHeaders);
        return response;
    }

    public LookupCardResponse lookupCardTransactionReference(final LookupCardTransactionReferenceRequest request, Map<String, String> headers) throws Exception {
        String url = baseUrl+"/v2/card/lookup";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("Calling: "+url);
        LookupCardResponse response = cardVaultMethodsFeignClient.lookupCardByTransactionReference(request, updatedHeaders);
        return response;
    }

    public UnregisterCardResponse unregisterCard(final LookupCardTokenRequest request, Map<String, String> headers) throws Exception {
        String url = baseUrl+"/v2/card/unregister";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload, url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        UnregisterCardResponse response = cardVaultMethodsFeignClient.unregisterCard(request, updatedHeaders);
        return response;
    }*/

}
