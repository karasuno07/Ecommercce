package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.dto.filter.ReceiptFilter;
import com.techstore.ecommerce.object.dto.request.ReceiptRequest;
import com.techstore.ecommerce.object.dto.response.ReceiptResponse;
import com.techstore.ecommerce.object.mapper.ReceiptMapper;
import com.techstore.ecommerce.object.wrapper.AbstractResponse;
import com.techstore.ecommerce.object.wrapper.SuccessResponse;
import com.techstore.ecommerce.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipts")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService service;
    private final ReceiptMapper mapper;

    @PreAuthorize("hasAuthority('RECEIPT_READ')")
    @GetMapping
    AbstractResponse getAllReceipts(@RequestBody ReceiptFilter filter) {
        Page<ReceiptResponse> response = service.findAllReceipt(filter)
                                                .map(mapper::toResponseModel);

        return new SuccessResponse<>(response, null);
    }

    @PreAuthorize("hasAuthority('RECEIPT_READ')")
    @GetMapping("/{receiptId}")
    AbstractResponse getReceiptById(@PathVariable long receiptId) {
        ReceiptResponse response = mapper.toResponseModel(service.findReceiptById(receiptId));

        return new SuccessResponse<>(response, null);
    }

    @PreAuthorize("hasAuthority('RECEIPT_CREATE')")
    @PostMapping
    AbstractResponse createReceipt(@RequestBody ReceiptRequest request) {
        ReceiptResponse response = mapper.toResponseModel(service.createReceipt(request));

        return new SuccessResponse<>(response, null);
    }

    @PreAuthorize("hasAuthority('RECEIPT_UPDATE')")
    @PutMapping("/{receiptId}")
    AbstractResponse updateReceipt(@PathVariable long receiptId,
                                   @RequestBody ReceiptRequest request) {
        ReceiptResponse response = mapper.toResponseModel(service.updateReceipt(receiptId, request));

        return new SuccessResponse<>(response, null);
    }
}
