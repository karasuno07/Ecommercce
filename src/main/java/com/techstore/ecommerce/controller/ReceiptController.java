package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.dto.request.ReceiptRequest;
import com.techstore.ecommerce.object.dto.response.ReceiptResponse;
import com.techstore.ecommerce.object.entity.jpa.ReceiptMapper;
import com.techstore.ecommerce.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/receipts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReceiptController {

    private final ReceiptService service;
    private final ReceiptMapper mapper;

    //    @PreAuthorize("hasAuthority('RECEIPT_READ')")
    @GetMapping
    ResponseEntity<?> getAllReceipts() {
        List<ReceiptResponse> response = service.findAllReceipt().stream()
                                                .map(mapper::toResponseModel).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    //    @PreAuthorize("hasAuthority('RECEIPT_READ')")
    @GetMapping("/{receiptId}")
    ResponseEntity<?> getReceiptById(@PathVariable long receiptId) {
        ReceiptResponse response = mapper.toResponseModel(service.findReceiptById(receiptId));
        return ResponseEntity.ok(response);
    }

    //    @PreAuthorize("hasAuthority('RECEIPT_CREATE')")
    @PostMapping
    ResponseEntity<?> createReceipt(@RequestBody ReceiptRequest request) {
        ReceiptResponse response = mapper.toResponseModel(service.createReceipt(request));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('RECEIPT_UPDATE')")
    @PutMapping("/{receiptId}")
    ResponseEntity<?> updateReceipt(@PathVariable long receiptId,
                                    @RequestBody ReceiptRequest request) {
        ReceiptResponse response = mapper.toResponseModel(service.updateReceipt(receiptId, request));
        return ResponseEntity.ok(response);
    }
}
