package com.w2.client.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.IamportPaycoClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.AgainPaymentData;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.request.CardInfo;
import com.siot.IamportRestClient.request.ExtraNaverUseCfmEntry;
import com.siot.IamportRestClient.request.GetScheduleData;
import com.siot.IamportRestClient.request.escrow.EscrowLogisData;
import com.siot.IamportRestClient.request.escrow.EscrowLogisInvoiceData;
import com.siot.IamportRestClient.request.escrow.EscrowLogisPersonData;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.Certification;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.PagedDataList;
import com.siot.IamportRestClient.response.Payment;
import com.siot.IamportRestClient.response.PaymentBalance;
import com.siot.IamportRestClient.response.PaymentCancelDetail;
import com.siot.IamportRestClient.response.ScheduleList;
import com.siot.IamportRestClient.response.escrow.EscrowLogisInvoice;
import com.siot.IamportRestClient.response.payco.OrderStatus;

@Controller
public class PaymentCon {

    IamportClient client;
    public void setPayment(Payment result) {
        System.err.println("===== Payment =====");
        System.err.println("____getApplyNum : " + result.getApplyNum());
        System.err.println("____getBankCode : " + result.getBankCode());
        System.err.println("____getBankName : " + result.getBankName());
        System.err.println("____getBuyerAddr : " + result.getBuyerAddr());
        System.err.println("____getBuyerEmail : " + result.getBuyerEmail());
        System.err.println("____getBuyerName : " + result.getBuyerName());
        System.err.println("____getBuyerPostcode : " + result.getBuyerPostcode());
        System.err.println("____getBuyerTel : " + result.getBuyerTel());
        System.err.println("____getCancelReason : " + result.getCancelReason());
        System.err.println("____getCardCode : " + result.getCardCode());
        System.err.println("____getCardName : " + result.getCardName());
        System.err.println("____getCardNumber : " + result.getCardNumber());
        System.err.println("____getCardQuota : " + result.getCardQuota());
        System.err.println("____getCardType : " + result.getCardType());
        System.err.println("____getChannel : " + result.getChannel());
        System.err.println("____getCurrency : " + result.getCurrency());
        System.err.println("____getCustomData : " + result.getCustomData());
        System.err.println("____getCustomerUid : " + result.getCustomerUid());
        System.err.println("____getCustomerUidUsage : " + result.getCustomerUidUsage());
        System.err.println("____getEmbPgProvider : " + result.getEmbPgProvider());
        System.err.println("____getFailReason : " + result.getFailReason());
        System.err.println("____getImpUid : " + result.getImpUid());
        System.err.println("____getMerchantUid : " + result.getMerchantUid());
        System.err.println("____getName : " + result.getName());
        System.err.println("____getPayMethod : " + result.getPayMethod());
        System.err.println("____getPgProvider : " + result.getPgProvider());
        System.err.println("____getPgTid : " + result.getPgTid());
        System.err.println("____getReceiptUrl : " + result.getReceiptUrl());
        System.err.println("____getStartedAt : " + result.getStartedAt());
        System.err.println("____getStatus : " + result.getStatus());
        System.err.println("____getVbankCode : " + result.getVbankCode());
        System.err.println("____getVbankHolder : " + result.getVbankHolder());
        System.err.println("____getVbankIssuedAt : " + result.getVbankIssuedAt());
        System.err.println("____getAmount : " + result.getAmount());
        System.err.println("____getCancelAmount : " + result.getCancelAmount());
        System.err.println("____getPaidAt : " + result.getPaidAt());
        System.err.println("=================");
    }
    
    /** getToken */
    public void testGetToken() {
        System.err.println(">>> testGetToken");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");

        try {
            IamportResponse<AccessToken> auth_response = client.getAuth();
            System.err.println("getMessage : " + auth_response.getMessage());
            System.err.println("getResponse : " + auth_response.getResponse());
            
            AccessToken result = auth_response.getResponse();
            System.err.println("___getToken : " + result.getToken());
            System.err.println("___getClass : " + result.getClass());
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    System.err.println("401");
                    break;
                case 500:
                    System.err.println("500");
                    break;
            }
        } catch (IOException e) {
            //서버 연결 실패
            e.printStackTrace();
        }
/**
>>> testGetToken
getMessage : null
getResponse : com.siot.IamportRestClient.response.AccessToken@2f0d43d0
___getToken : ad8afd471dbc6ab0bb43c238f095c3ceac41ae8d
___getClass : class com.siot.IamportRestClient.response.AccessToken
*/
    }

    /** 결제건 금액 상세정보를 가져오는 메소드 */
    public void testPaymentBalanceByImpUid() {
        System.err.println(">>> testPaymentBalanceByImpUid");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");

        String test_imp_uid = "imp_011115679124";
        try {
            IamportResponse<PaymentBalance> payment_response = client.paymentBalanceByImpUid(test_imp_uid);
            System.err.println("getMessage : " + payment_response.getMessage());
            System.err.println("getResponse : " + payment_response.getResponse());

            PaymentBalance result = payment_response.getResponse();
            System.err.println("___getAmount : " + result.getAmount());
            System.err.println("___getCashReceipt : " + result.getCashReceipt());
            System.err.println("___getDiscount : " + result.getDiscount());
            System.err.println("___getHistories : " + result.getHistories());
            System.err.println("___getPrimary : " + result.getPrimary());
            System.err.println("___getSecondary : " + result.getSecondary());
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
>>> testPaymentBalanceByImpUid
(현재는 PAYCO, KCP만 지원됩니다) 결제건에 대한 금액상세정보가 확인되지 않습니다. 결제 상태가 paid 또는 cancelled가 아닌 경우 확인되지 않습니다.
*/
    }

    /** 결제건의 상세 정보 조회 */
    public void testPaymentByImpUid() {
        System.err.println(">>> testPaymentByImpUid");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");

        String test_imp_uid = "imp_688863851303";
        try {
            IamportResponse<Payment> payment_response = client.paymentByImpUid(test_imp_uid);
            System.err.println("getMessage : " + payment_response.getMessage());
            System.err.println("getResponse : " + payment_response.getResponse());
            setPayment(payment_response.getResponse());
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String test_imp_uid_cancelled = "imp_980662878590";
        try {
            IamportResponse<Payment> cancelled_response = client.paymentByImpUid(test_imp_uid_cancelled);

            Payment cancelled = cancelled_response.getResponse();
            PaymentCancelDetail[] cancelDetail = cancelled.getCancelHistory();
            System.err.println("getMessage : " + cancelled_response.getMessage());
            System.err.println("getResponse : " + cancelled_response.getResponse());
            setPayment(cancelled_response.getResponse());

        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
1)
>>> testPaymentByImpUid
존재하지 않는 결제정보입니다.
존재하지 않는 결제정보입니다.

2)
>>> testPaymentByImpUid
getMessage : null
getResponse : com.siot.IamportRestClient.response.Payment@5ecfe258
===== Payment =====
____getApplyNum : 
____getBankCode : null
____getBankName : null
____getBuyerAddr : 주소
____getBuyerEmail : email@email.com
____getBuyerName : 이름
____getBuyerPostcode : 우편번호
____getBuyerTel : 전화번호
____getCancelReason : null
____getCardCode : null
____getCardName : null
____getCardNumber : null
____getCardQuota : 0
____getCardType : 0
____getChannel : pc
____getCurrency : KRW
____getCustomData : null
____getCustomerUid : null
____getCustomerUidUsage : null
____getEmbPgProvider : null
____getFailReason : null
____getImpUid : imp_688863851303
____getMerchantUid : nobody_1707901863097
____getName : 주문상품이름
____getPayMethod : point
____getPgProvider : kakaopay
____getPgTid : T5cc83a71c025b0fec7f
____getReceiptUrl : https://mockup-pg-web.kakao.com/v1/confirmation/p/T5cc83a71c025b0fec7f/fb33ec520cf52a3d0b07a007fd4dc4e486231c6f3579047b54b638b533e173a7
____getStartedAt : 1707901863
____getStatus : paid
____getVbankCode : null
____getVbankHolder : null
____getVbankIssuedAt : 0
____getAmount : 100
____getCancelAmount : 0
____getPaidAt : Wed Feb 14 18:11:12 KST 2024
=================
getMessage : null
getResponse : com.siot.IamportRestClient.response.Payment@3520d42c
===== Payment =====
____getApplyNum : 
____getBankCode : null
____getBankName : null
____getBuyerAddr : 주소
____getBuyerEmail : email@email.com
____getBuyerName : 이름
____getBuyerPostcode : 우편번호
____getBuyerTel : 전화번호
____getCancelReason : 취소요청api
____getCardCode : null
____getCardName : null
____getCardNumber : null
____getCardQuota : 0
____getCardType : 0
____getChannel : pc
____getCurrency : KRW
____getCustomData : null
____getCustomerUid : null
____getCustomerUidUsage : null
____getEmbPgProvider : null
____getFailReason : null
____getImpUid : imp_980662878590
____getMerchantUid : nobody_1707897662174
____getName : 주문상품이름
____getPayMethod : point
____getPgProvider : kakaopay
____getPgTid : T5cc733e196d5d205937
____getReceiptUrl : https://mockup-pg-web.kakao.com/v1/confirmation/p/T5cc733e196d5d205937/f89bf5079c7de4405b4d88a0c972c757aff224a9002f13c478879cd02b1b6197
____getStartedAt : 1707897662
____getStatus : cancelled
____getVbankCode : null
____getVbankHolder : null
____getVbankIssuedAt : 0
____getAmount : 100
____getCancelAmount : 30
____getPaidAt : Wed Feb 14 17:01:14 KST 2024
=================
*/
    }

    /** 결제 상태 조회 */
    public void testPaymentsByStatusAll() {
        System.err.println(">>> testPaymentsByStatusAll");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");
        try {
            IamportResponse<PagedDataList<Payment>> r_response = client.paymentsByStatus("ready");
            IamportResponse<PagedDataList<Payment>> p_response = client.paymentsByStatus("paid");
            IamportResponse<PagedDataList<Payment>> f_response = client.paymentsByStatus("failed");
            IamportResponse<PagedDataList<Payment>> c_response = client.paymentsByStatus("cancelled");
            IamportResponse<PagedDataList<Payment>> all_response = client.paymentsByStatus("all");
            
            System.err.println("R getMessage : " + r_response.getMessage());
            System.err.println("R getResponse : " + r_response.getResponse());
            System.err.println("++++++++++++++++");
            System.err.println("P getMessage : " + p_response.getMessage());
            System.err.println("P getResponse : " + p_response.getResponse());
            System.err.println("++++++++++++++++");
            System.err.println("F getMessage : " + f_response.getMessage());
            System.err.println("F getResponse : " + f_response.getResponse());
            System.err.println("++++++++++++++++");
            System.err.println("C getMessage : " + c_response.getMessage());
            System.err.println("C getResponse : " + c_response.getResponse());
            System.err.println("++++++++++++++++");
            System.err.println("ALL getMessage : " + all_response.getMessage());
            System.err.println("ALL getResponse : " + all_response.getResponse());
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
>>> testPaymentsByStatusAll
R getMessagenull
R getResponsecom.siot.IamportRestClient.response.PagedDataList@6bf5125f
++++++++++++++++
P getMessagenull
P getResponsecom.siot.IamportRestClient.response.PagedDataList@54c55e12
++++++++++++++++
F getMessagenull
F getResponsecom.siot.IamportRestClient.response.PagedDataList@fd6fef3
++++++++++++++++
C getMessagenull
C getResponsecom.siot.IamportRestClient.response.PagedDataList@4c92fcb8
++++++++++++++++
ALL getMessagenull
ALL getResponsecom.siot.IamportRestClient.response.PagedDataList@f36689f
*/
    }

    /** 결제 취소 */
    public void testCancelPaymentAlreadyCancelledImpUid() {
        System.err.println(">>> testCancelPaymentAlreadyCancelledImpUid");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");
        String test_already_cancelled_imp_uid = "imp_497911164010";
        CancelData cancel_data = new CancelData(test_already_cancelled_imp_uid, true); //imp_uid를 통한 전액취소

        try {
            IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);
            System.err.println("getMessage : " + payment_response.getMessage());
            System.err.println("getResponse : " + payment_response.getResponse());
            setPayment(payment_response.getResponse());

        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
1) 
>>> testCancelPaymentAlreadyCancelledImpUid
getMessage : 취소할 결제건이 존재하지 않습니다.
getResponse : null

2) 
>>> testCancelPaymentAlreadyCancelledImpUid
getMessage : null
getResponse : com.siot.IamportRestClient.response.Payment@263a4d24
===== Payment =====
____getApplyNum : 
____getBankCode : null
____getBankName : null
____getBuyerAddr : 주소
____getBuyerEmail : email@email.com
____getBuyerName : 이름
____getBuyerPostcode : 우편번호
____getBuyerTel : 전화번호
____getCancelReason : 취소요청api
____getCardCode : null
____getCardName : null
____getCardNumber : null
____getCardQuota : 0
____getCardType : 0
____getChannel : pc
____getCurrency : KRW
____getCustomData : null
____getCustomerUid : null
____getCustomerUidUsage : null
____getEmbPgProvider : null
____getFailReason : null
____getImpUid : imp_497911164010
____getMerchantUid : nobody_1707893910566
____getName : 주문상품이름
____getPayMethod : point
____getPgProvider : kakaopay
____getPgTid : T5cc64971c025b0feab6
____getReceiptUrl : https://mockup-pg-web.kakao.com/v1/confirmation/p/T5cc64971c025b0feab6/c78f2dc54c15183fbbfdc805b40bdfe1a05dbde062a8a9196ad6b9a715b6333b
____getStartedAt : 1707893911
____getStatus : cancelled
____getVbankCode : null
____getVbankHolder : null
____getVbankIssuedAt : 0
____getAmount : 100
____getCancelAmount : 100
____getPaidAt : Wed Feb 14 15:58:45 KST 2024
=================
*/
    }

    /** 결제 취소(검증) */
    public void testCancelPaymentChecksumByImpUid() {
        System.err.println(">>> testCancelPaymentChecksumByImpUid");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");
        String test_already_cancelled_imp_uid = "imp_688863851303";
        CancelData cancel_data = new CancelData(test_already_cancelled_imp_uid, true); //imp_uid를 통한 전액취소
        cancel_data.setChecksum(BigDecimal.valueOf(100)); // checksum 으로 검증 추가
        
        try {
            IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);
            System.err.println("getMessage : " + payment_response.getMessage());
            System.err.println("getResponse : " + payment_response.getResponse());
            setPayment(payment_response.getResponse());

        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
1) 
>>> testCancelPaymentChecksumByImpUid
getMessage : 취소할 결제건이 존재하지 않습니다.
getResponse : null
===== Payment =====

2)
>>> testCancelPaymentChecksumByImpUid
getMessage : null
getResponse : com.siot.IamportRestClient.response.Payment@7664415
===== Payment =====
____getApplyNum : 
____getBankCode : null
____getBankName : null
____getBuyerAddr : 주소
____getBuyerEmail : email@email.com
____getBuyerName : 이름
____getBuyerPostcode : 우편번호
____getBuyerTel : 전화번호
____getCancelReason : 취소요청api
____getCardCode : null
____getCardName : null
____getCardNumber : null
____getCardQuota : 0
____getCardType : 0
____getChannel : pc
____getCurrency : KRW
____getCustomData : null
____getCustomerUid : null
____getCustomerUidUsage : null
____getEmbPgProvider : null
____getFailReason : null
____getImpUid : imp_688863851303
____getMerchantUid : nobody_1707901863097
____getName : 주문상품이름
____getPayMethod : point
____getPgProvider : kakaopay
____getPgTid : T5cc83a71c025b0fec7f
____getReceiptUrl : https://mockup-pg-web.kakao.com/v1/confirmation/p/T5cc83a71c025b0fec7f/fb33ec520cf52a3d0b07a007fd4dc4e486231c6f3579047b54b638b533e173a7
____getStartedAt : 1707901863
____getStatus : cancelled
____getVbankCode : null
____getVbankHolder : null
____getVbankIssuedAt : 0
____getAmount : 100
____getCancelAmount : 100
____getPaidAt : Wed Feb 14 18:11:12 KST 2024
=================
*/
    }

    /** 결제 취소 */
    public void testCancelPaymentAlreadyCancelledMerchantUid() {
        System.err.println(">>> testCancelPaymentAlreadyCancelledMerchantUid");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");
        String test_already_cancelled_merchant_uid = "nobody_1707893778305";
        CancelData cancel_data = new CancelData(test_already_cancelled_merchant_uid, false); //merchant_uid를 통한 전액취소
        cancel_data.setEscrowConfirmed(true); //에스크로 구매확정 후 취소인 경우 true설정

        try {
            IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);

            System.err.println(payment_response.getMessage());
            System.err.println("getMessage : " + payment_response.getMessage());
            System.err.println("getResponse : " + payment_response.getResponse());
            setPayment(payment_response.getResponse());
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
1)
>>> testCancelPaymentAlreadyCancelledMerchantUid
취소할 결제건이 존재하지 않습니다.
getMessage : 취소할 결제건이 존재하지 않습니다.
getResponse : null
===== Payment =====

2)
>>> testCancelPaymentAlreadyCancelledMerchantUid
null
getMessage : null
getResponse : com.siot.IamportRestClient.response.Payment@6bd7154a
===== Payment =====
____getApplyNum : 
____getBankCode : null
____getBankName : null
____getBuyerAddr : 주소
____getBuyerEmail : email@email.com
____getBuyerName : 이름
____getBuyerPostcode : 우편번호
____getBuyerTel : 전화번호
____getCancelReason : 취소요청api
____getCardCode : null
____getCardName : null
____getCardNumber : null
____getCardQuota : 0
____getCardType : 0
____getChannel : pc
____getCurrency : KRW
____getCustomData : null
____getCustomerUid : null
____getCustomerUidUsage : null
____getEmbPgProvider : null
____getFailReason : null
____getImpUid : imp_089778900116
____getMerchantUid : nobody_1707893778305
____getName : 주문상품이름
____getPayMethod : point
____getPgProvider : kakaopay
____getPgTid : T5cc6412196d5d20582d
____getReceiptUrl : https://mockup-pg-web.kakao.com/v1/confirmation/p/T5cc6412196d5d20582d/2cdc5bb902c98c0b4b3979e33accf0b6f3c66d88df80700d6d1a8cbd92527729
____getStartedAt : 1707893778
____getStatus : cancelled
____getVbankCode : null
____getVbankHolder : null
____getVbankIssuedAt : 0
____getAmount : 100
____getCancelAmount : 100
____getPaidAt : Wed Feb 14 15:56:36 KST 2024
=================
*/
    }

    /** 결제 부분 취소 */
    public void testPartialCancelPaymentAlreadyCancelledImpUid() {
        System.err.println(">>> testPartialCancelPaymentAlreadyCancelledImpUid");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");
        String test_already_cancelled_imp_uid = "imp_136646192122";
        CancelData cancel_data = new CancelData(test_already_cancelled_imp_uid, true, BigDecimal.valueOf(50)); //imp_uid를 통한 50원 부분취소

        try {
            IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);
            System.err.println(payment_response.getMessage());
            System.err.println("getMessage : " + payment_response.getMessage());
            System.err.println("getResponse : " + payment_response.getResponse());
            setPayment(payment_response.getResponse());
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
1)
>>> testPartialCancelPaymentAlreadyCancelledImpUid
취소할 결제건이 존재하지 않습니다.
getMessage : 취소할 결제건이 존재하지 않습니다.
getResponse : null
===== Payment =====

2)
>>> testPartialCancelPaymentAlreadyCancelledImpUid
null
getMessage : null
getResponse : com.siot.IamportRestClient.response.Payment@61c953eb
===== Payment =====
____getApplyNum : 
____getBankCode : null
____getBankName : null
____getBuyerAddr : 주소
____getBuyerEmail : email@email.com
____getBuyerName : 이름
____getBuyerPostcode : 우편번호
____getBuyerTel : 전화번호
____getCancelReason : 취소요청api
____getCardCode : null
____getCardName : null
____getCardNumber : null
____getCardQuota : 0
____getCardType : 0
____getChannel : pc
____getCurrency : KRW
____getCustomData : null
____getCustomerUid : null
____getCustomerUidUsage : null
____getEmbPgProvider : null
____getFailReason : null
____getImpUid : imp_136646192122
____getMerchantUid : nobody_1707897645494
____getName : 주문상품이름
____getPayMethod : point
____getPgProvider : kakaopay
____getPgTid : T5cc732e196d5d205936
____getReceiptUrl : https://mockup-pg-web.kakao.com/v1/confirmation/p/T5cc732e196d5d205936/81f949266ab9eaacca9c13323396be5ab9553a620c85d7b641d88b2ad547642f
____getStartedAt : 1707897646
____getStatus : cancelled
____getVbankCode : null
____getVbankHolder : null
____getVbankIssuedAt : 0
____getAmount : 100
____getCancelAmount : 50
____getPaidAt : Wed Feb 14 17:01:00 KST 2024
=================
*/
    }

    /** 결제 부분 취소 */
    public void testPartialCancelPaymentAlreadyCancelledMerchantUid() {
        System.err.println(">>> testPartialCancelPaymentAlreadyCancelledMerchantUid");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");
        String test_already_cancelled_merchant_uid = "nobody_1707897645494";
        CancelData cancel_data = new CancelData(test_already_cancelled_merchant_uid, false, BigDecimal.valueOf(10)); //merchant_uid를 통한 500원 부분취소

        try {
            IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);

            System.err.println(payment_response.getMessage());
            System.err.println("getMessage : " + payment_response.getMessage());
            System.err.println("getResponse : " + payment_response.getResponse());
            setPayment(payment_response.getResponse());
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
1)
>>> testPartialCancelPaymentAlreadyCancelledMerchantUid
취소할 결제건이 존재하지 않습니다.
getMessage : 취소할 결제건이 존재하지 않습니다.
getResponse : null
===== Payment =====

2)
>>> testPartialCancelPaymentAlreadyCancelledMerchantUid
null
getMessage : null
getResponse : com.siot.IamportRestClient.response.Payment@500ebc6
===== Payment =====
____getApplyNum : 
____getBankCode : null
____getBankName : null
____getBuyerAddr : 주소
____getBuyerEmail : email@email.com
____getBuyerName : 이름
____getBuyerPostcode : 우편번호
____getBuyerTel : 전화번호
____getCancelReason : 취소요청api
____getCardCode : null
____getCardName : null
____getCardNumber : null
____getCardQuota : 0
____getCardType : 0
____getChannel : pc
____getCurrency : KRW
____getCustomData : null
____getCustomerUid : null
____getCustomerUidUsage : null
____getEmbPgProvider : null
____getFailReason : null
____getImpUid : imp_136646192122
____getMerchantUid : nobody_1707897645494
____getName : 주문상품이름
____getPayMethod : point
____getPgProvider : kakaopay
____getPgTid : T5cc732e196d5d205936
____getReceiptUrl : https://mockup-pg-web.kakao.com/v1/confirmation/p/T5cc732e196d5d205936/81f949266ab9eaacca9c13323396be5ab9553a620c85d7b641d88b2ad547642f
____getStartedAt : 1707897646
____getStatus : cancelled
____getVbankCode : null
____getVbankHolder : null
____getVbankIssuedAt : 0
____getAmount : 100
____getCancelAmount : 70
____getPaidAt : Wed Feb 14 17:01:00 KST 2024
=================
*/
    }

    /** 결제 부분 취소 */
    public void testCancelVbankPaymentAlreadyCancelledImpUid() {
        System.err.println(">>> testCancelVbankPaymentAlreadyCancelledImpUid");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");
        String test_already_cancelled_imp_uid = "imp_540676469599";
        CancelData cancel_data = new CancelData(test_already_cancelled_imp_uid, true, BigDecimal.valueOf(40)); //imp_uid를 통한 500원 부분취소

        try {
            IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);

            System.err.println(payment_response.getMessage());
            System.err.println("getMessage : " + payment_response.getMessage());
            System.err.println("getResponse : " + payment_response.getResponse());
            setPayment(payment_response.getResponse());
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
 * 1)
>>> testCancelVbankPaymentAlreadyCancelledImpUid
취소할 결제건이 존재하지 않습니다.
getMessage : 취소할 결제건이 존재하지 않습니다.
getResponse : null
===== Payment =====

2)
>>> testCancelVbankPaymentAlreadyCancelledImpUid
null
getMessage : null
getResponse : com.siot.IamportRestClient.response.Payment@4d4bb2a1
===== Payment =====
____getApplyNum : 
____getBankCode : null
____getBankName : null
____getBuyerAddr : 주소
____getBuyerEmail : email@email.com
____getBuyerName : 이름
____getBuyerPostcode : 우편번호
____getBuyerTel : 전화번호
____getCancelReason : 취소요청api
____getCardCode : null
____getCardName : null
____getCardNumber : null
____getCardQuota : 0
____getCardType : 0
____getChannel : pc
____getCurrency : KRW
____getCustomData : null
____getCustomerUid : null
____getCustomerUidUsage : null
____getEmbPgProvider : null
____getFailReason : null
____getImpUid : imp_540676469599
____getMerchantUid : nobody_1707897675810
____getName : 주문상품이름
____getPayMethod : point
____getPgProvider : kakaopay
____getPgTid : T5cc734c196d5d205938
____getReceiptUrl : https://mockup-pg-web.kakao.com/v1/confirmation/p/T5cc734c196d5d205938/4f792a2b3cae32f69a3b93682ba06fa5e42cb5a473d3cad8937c09bd29df1030
____getStartedAt : 1707897676
____getStatus : cancelled
____getVbankCode : null
____getVbankHolder : null
____getVbankIssuedAt : 0
____getAmount : 100
____getCancelAmount : 40
____getPaidAt : Wed Feb 14 17:01:28 KST 2024
=================
*/
    }

    /** 결제 부분 취소 */
    public void testPartialCancelVbankPaymentAlreadyCancelledMerchantUid() {
        System.err.println(">>> testPartialCancelVbankPaymentAlreadyCancelledMerchantUid");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");
        String test_already_cancelled_merchant_uid = "nobody_1707897662174";
        CancelData cancel_data = new CancelData(test_already_cancelled_merchant_uid, false, BigDecimal.valueOf(30)); //merchant_uid를 통한 500원 부분취소

        try {
            IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);

            System.err.println(payment_response.getMessage());
            System.err.println("getMessage : " + payment_response.getMessage());
            System.err.println("getResponse : " + payment_response.getResponse());
            setPayment(payment_response.getResponse());
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
1)
>>> testPartialCancelVbankPaymentAlreadyCancelledMerchantUid
취소할 결제건이 존재하지 않습니다.
getMessage : 취소할 결제건이 존재하지 않습니다.
getResponse : null
===== Payment =====

2)
>>> testPartialCancelVbankPaymentAlreadyCancelledMerchantUid
null
getMessage : null
getResponse : com.siot.IamportRestClient.response.Payment@4c4db6cf
===== Payment =====
____getApplyNum : 
____getBankCode : null
____getBankName : null
____getBuyerAddr : 주소
____getBuyerEmail : email@email.com
____getBuyerName : 이름
____getBuyerPostcode : 우편번호
____getBuyerTel : 전화번호
____getCancelReason : 취소요청api
____getCardCode : null
____getCardName : null
____getCardNumber : null
____getCardQuota : 0
____getCardType : 0
____getChannel : pc
____getCurrency : KRW
____getCustomData : null
____getCustomerUid : null
____getCustomerUidUsage : null
____getEmbPgProvider : null
____getFailReason : null
____getImpUid : imp_980662878590
____getMerchantUid : nobody_1707897662174
____getName : 주문상품이름
____getPayMethod : point
____getPgProvider : kakaopay
____getPgTid : T5cc733e196d5d205937
____getReceiptUrl : https://mockup-pg-web.kakao.com/v1/confirmation/p/T5cc733e196d5d205937/f89bf5079c7de4405b4d88a0c972c757aff224a9002f13c478879cd02b1b6197
____getStartedAt : 1707897662
____getStatus : cancelled
____getVbankCode : null
____getVbankHolder : null
____getVbankIssuedAt : 0
____getAmount : 100
____getCancelAmount : 30
____getPaidAt : Wed Feb 14 17:01:14 KST 2024
=================
*/
    }

    /** 페이코 상태 업데이트 */
    public void testPaycoUpdateOrderStatus() {
        System.err.println(">>> testPaycoUpdateOrderStatus");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");
        String test_api_key = "2966106337421865";
        String test_api_secret = "rZfEkQvBL6hRaWexFEQQwrrylIHTnSzORPscFYNq54hf1wNHaDyH4ZfqHXj5PTTHJmTokHPpM6FNDsvN";
        IamportPaycoClient payco = new IamportPaycoClient(test_api_key, test_api_secret);

        //유효하지 않은 status
        try {
            IamportResponse<OrderStatus> payment_response = payco.updateOrderStatus("imp_436389624339", "asdf");

            payment_response = payco.updateOrderStatus("imp_436389624339", "CANCELED");
            System.err.println("getMessage : " + payment_response.getMessage());
            System.err.println("getResponse : " + payment_response.getResponse());
            OrderStatus result = payment_response.getResponse();
            System.err.println("_____getStatus : " + result.getStatus());
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
>>> testPaycoUpdateOrderStatus
파라미터 정보가 유효하지 않습니다. orderProductStatus field reject, expected OrderProductStatusType{PAYMENT_WAITNG,PAYED,DELIVERY_READY,DELIVERY_START,DELIVERING,DELIVERY_COMPLETE,CANCEL_REQUEST,EXCHANGE_WAIT,EXCHANGE_COMPLETE,RETURN_WAIT,RETURN_COMPLETE,PURCHASE_DECISION,OUTSELLER_REFUND,CANCELED}, but [asdf]
*/
    }

    @RequestMapping("t13.do")
    public void testCertificationByImpUid() {
        System.err.println(">>> testCertificationByImpUid");
        client = new IamportClient("imp_apikey", "ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f");
//		String test_imp_uid = "imp_339323965143";
        // origin 검증을 위해 is not null인 imp_uid로 변경
        String test_imp_uid = "imp_992536806181";

        try {
            IamportResponse<Certification> certification_response = client.certificationByImpUid(test_imp_uid);
            System.err.println("getMessage : " + certification_response.getMessage());
            System.err.println("getResponse : " + certification_response.getResponse());
            Certification result = certification_response.getResponse();
            System.err.println("_____getCarrier : " + result.getCarrier());
            System.err.println("_____getGender : " + result.getGender());
            System.err.println("_____getImpUid : " + result.getImpUid());
            System.err.println("_____getMerchantUid : " + result.getMerchantUid());
            System.err.println("_____getName : " + result.getName());
            System.err.println("_____getOrigin : " + result.getOrigin());
            System.err.println("_____getPgProvider : " + result.getPgProvider());
            System.err.println("_____getPgTid : " + result.getPgTid());
            System.err.println("_____getPhone : " + result.getPhone());
            System.err.println("_____getUniqueInSite : " + result.getUniqueInSite());
            System.err.println("_____getUniqueInSite : " + result.getUniqueInSite());
            System.err.println("_____getUniqueKey : " + result.getUniqueKey());
            System.err.println("_____getBirth : " + result.getBirth());
            System.err.println("_____getCertifiedAt : " + result.getCertifiedAt());
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
1)
>>> testCertificationByImpUid
인증결과가 존재하지 않습니다.

2)
>>> testCertificationByImpUid
getMessage : null
getResponse : com.siot.IamportRestClient.response.Certification@59837be3
_____getCarrier : null
_____getGender : null
_____getImpUid : imp_992536806181
_____getMerchantUid : merchant_1545326536779
_____getName : null
_____getOrigin : http://kicc.iamport.kr/pages/certi
_____getPgProvider : danal
_____getPgTid : null
_____getPhone : null
_____getUniqueInSite : null
_____getUniqueInSite : null
_____getUniqueKey : null
_____getBirth : Thu Jan 01 09:00:00 KST 1970
_____getCertifiedAt : Thu Jan 01 09:00:00 KST 1970
*/
    }

    @RequestMapping("t14.do")
    public void testPostEscrowLogis() {
        System.err.println(">>> testPostEscrowLogis");
        client = new IamportClient("2454632160547764", "n76Tu4kTNmhPGfd7l3eiQt6YU6shguRQhEjr82EPITQS17NHC2CMHvRLaohElRGrwyxHFosL2ve54Dr6");
        String imp_uid = "imp_803850029015";

        EscrowLogisPersonData sender = new EscrowLogisPersonData("가맹점", "02-1234-1234", "서울 용산구", "12345");
        EscrowLogisPersonData receiver = new EscrowLogisPersonData("홍길동", "010-1234-5678", "서울 강남구 삼성동", "98765");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2018);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 03);

        EscrowLogisInvoiceData invoice = new EscrowLogisInvoiceData("LOGEN", "123456789", cal.getTime()); //택배사 코드표 : https://github.com/iamport/iamport-manual/blob/master/RESTAPI/logis.md

        try {
            IamportResponse<EscrowLogisInvoice> response = client.postEscrowLogis(imp_uid, new EscrowLogisData(invoice, receiver, sender));

            System.err.println("getMessage : " + response.getMessage());
            System.err.println("getResponse : " + response.getResponse());
            EscrowLogisInvoice result = response.getResponse();
            System.err.println("EscrowLogisInvoice : " + result);
        } catch (IamportResponseException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/**
1) 
>>> testPostEscrowLogis
존재하지 않는 결제정보입니다.

2) 
>>> testPostEscrowLogis
해당거래건은 배송정보를 등록할 수 없습니다.

3)
>>> testPostEscrowLogis
현재 지원되지 않는 PG사입니다.
*/
    }

    @RequestMapping("t15.do")
    public void testAgainPayment() throws IOException, IamportResponseException {
        System.err.println(">>> testAgainPayment");
        client = new IamportClient("imp_apikey", "ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f");
        String test_customer_uid = "customer_123456";
        CardInfo card = new CardInfo("1234123412341234", "201901", "801231", "00");

        AgainPaymentData again_data = new AgainPaymentData(test_customer_uid, getRandomMerchantUid(), BigDecimal.valueOf(1005));
        again_data.setExtra(new ExtraNaverUseCfmEntry("20200101"));
        ExtraNaverUseCfmEntry extra = again_data.getExtra();
        IamportResponse<Payment> payment_response = client.againPayment(again_data);
        System.err.println("getMessage : " + payment_response.getMessage());
        System.err.println("getResponse : " + payment_response.getResponse());
        setPayment(payment_response.getResponse());
/**
>>> testAgainPayment
getMessage : 등록되지 않은 구매자입니다.
getResponse : null
===== Payment =====
*/
    }

    @RequestMapping("t16.do")
    public void testGetPaymentSchedule() throws IOException, IamportResponseException {
        System.err.println(">>> testGetPaymentSchedule");
        client = new IamportClient("imp_apikey", "ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f");

        GetScheduleData getScheduleData = new GetScheduleData(1643497892, 1648595492, null, 1, 20);

        IamportResponse<ScheduleList> schedule_response = client.getPaymentSchedule(getScheduleData);
        System.err.println(schedule_response.getResponse().getList().get(0).getCustomerUid());
        System.err.println("getMessage : " + schedule_response.getMessage());
        System.err.println("getResponse : " + schedule_response.getResponse());
        
        ScheduleList result = schedule_response.getResponse();
        System.err.println("_____getNext : " + result.getNext());
        System.err.println("_____getPrevious : " + result.getPrevious());
        System.err.println("_____getTotal : " + result.getTotal());
        System.err.println("_____getList : " + result.getList());
/**
>>> testGetPaymentSchedule
*/
    }

    @RequestMapping("t17.do")
    private String getRandomMerchantUid() {
        DateFormat df = new SimpleDateFormat("$$hhmmssSS");
        int n = (int) (Math.random() * 100) + 1;

        return df.format(new Date()) + "_" + n;
    }

}
