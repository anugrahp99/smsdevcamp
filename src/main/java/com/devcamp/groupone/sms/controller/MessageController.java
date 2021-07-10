package com.devcamp.groupone.sms.controller;

import com.devcamp.groupone.sms.constant.ApiPath;
import com.devcamp.groupone.sms.payload.request.SendMessageRequest;
import com.devcamp.groupone.sms.service.MessageService;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anugrah Prasetia
 * @date 10/07/2021 0:56
 */

@RestController
public class MessageController {

    @Autowired
    private MessageService service;

    @GetMapping(value = "/api/send")
    public String sendMessage(@RequestBody SendMessageRequest request) throws URISyntaxException {
        return service.sendMessage(request);
    }


    @PostMapping(value = "api/callback/products")
    public String trxSearch(Object data) throws URISyntaxException {
        SendMessageRequest request = SendMessageRequest
                .builder()
                .message("1. INDOMIE REBUS | Rp3.000 | Garut 2. MIE SEDAP REBUS | Rp4.000 | Garut 3. POP MIE REBUS | Rp5.000 | Garut")
                .number("081937579076")
                .build();
        return service.sendMessage(request);
    }

    @PostMapping(value = "api/callback/transactions")
    public String makeTrx(Object data) throws URISyntaxException {
        SendMessageRequest request = SendMessageRequest
                .builder()
                .message("BERIKUT DETAIL TRANSAKSI ANDA. NOMOR: 1, NAMA: INDOMIE GORENG, PEMBAYARAN: COD, ALAMAT: JL.CIBADUY, TOTAL BIAYA: Rp60.000. Apakah anda yakin?")
                .number("081937579076")
                .build();
        return service.sendMessage(request);
    }

    @PostMapping(value = "api/callback/confirmations")
    public String trxConfirmation(Object data) throws URISyntaxException {
        SendMessageRequest request = SendMessageRequest
                .builder()
                .message("TRANSAKSI ANDA TELAH TERKONFIRMASI. BARANG AKAN SEGERA DIKIRIM PENJUAL KEPADA ANDA. TERIMAKASIH")
                .number("081937579076")
                .build();

        SendMessageRequest request2 = SendMessageRequest
                .builder()
                .message("UPDATE TRANSAKSI ID:221. BARANG SUDAH DIKIRIM TOKO KE EKSPEDISI")
                .number("081937579076")
                .build();

        SendMessageRequest request3 = SendMessageRequest
                .builder()
                .message("KURIR COD SEDANG DALAM PERJALANAN KETEMPAT KAMU")
                .number("081937579076")
                .build();

        SendMessageRequest request4 = SendMessageRequest
                .builder()
                .message("TRANSAKSI ANDA TELAH SELESAI. SEMOGA TRANSAKSI ANDA MENYENANGKAN")
                .number("081937579076")
                .build();
        try {
            service.sendMessage(request);
            Thread.sleep(5 * 1000);
            service.sendMessage(request2);
            Thread.sleep(5 * 1000);
            service.sendMessage(request3);
            Thread.sleep(5 * 1000);

        } catch (Exception e){
            e.printStackTrace();
        }
        return service.sendMessage(request4);
    }

    @PostMapping(value = "api/callback/transactions/details")
    public String trxDetail(Object data) throws URISyntaxException {
        SendMessageRequest request = SendMessageRequest
                .builder()
                .message("STATUS TRANSAKSI ANDA: KURIR SEDANG KETEMPATMU")
                .number("081937579076")
                .build();
        return service.sendMessage(request);
    }

}
