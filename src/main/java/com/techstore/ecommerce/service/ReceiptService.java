package com.techstore.ecommerce.service;

import com.techstore.ecommerce.object.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.request.ReceiptRequest;
import com.techstore.ecommerce.object.entity.jpa.Receipt;
import com.techstore.ecommerce.object.entity.jpa.ReceiptDetail;
import com.techstore.ecommerce.object.dto.mapper.ReceiptDetailMapper;
import com.techstore.ecommerce.object.dto.mapper.ReceiptMapper;
import com.techstore.ecommerce.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiptService {
    private final ReceiptRepository receiptRepo;
    private final ReceiptMapper receiptMapper;
    private final ReceiptDetailMapper receiptDetailMapper;

    public List<Receipt> findAllReceipt() {
        return receiptRepo.findAll();
    }

    public Receipt findReceiptById(long id) {
        return receiptRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any receipt with id " + id));
    }

    public Receipt createReceipt(ReceiptRequest request) {
        Receipt receipt = receiptMapper.createEntityFromRequest(request);

        List<ReceiptDetail> details = request.getDetails().stream()
                                             .map(receiptDetailMapper::createEntityFromRequest)
                                             .collect(Collectors.toList());
        receipt.setDetails(details);

        return receiptRepo.save(receipt);
    }

    public Receipt updateReceipt(long id, ReceiptRequest request) {
        Receipt receipt = findReceiptById(id);

        receiptMapper.update(receipt, request);

        List<ReceiptDetail> details = request.getDetails().stream()
                                             .map(receiptDetailMapper::createEntityFromRequest)
                                             .collect(Collectors.toList());
        receipt.setDetails(details);

        return receiptRepo.save(receipt);
    }
}
