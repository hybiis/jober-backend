package com.javajober.freeBlock.service;

import com.javajober.freeBlock.domain.FreeBlock;
import com.javajober.freeBlock.dto.request.FreeBlockSaveRequests;
import com.javajober.freeBlock.dto.request.FreeBlockUpdateRequests;
import com.javajober.freeBlock.dto.response.FreeBlockResponse;
import com.javajober.freeBlock.dto.request.FreeBlockSaveRequest;
import com.javajober.freeBlock.dto.request.FreeBlockUpdateRequest;
import com.javajober.freeBlock.dto.response.FreeBlockResponses;
import com.javajober.freeBlock.repository.FreeBlockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreeBlockService {

    private final FreeBlockRepository freeBlockRepository;

    public FreeBlockService(final FreeBlockRepository freeBlockRepository) {
        this.freeBlockRepository = freeBlockRepository;
    }

    @Transactional
    public void save(final FreeBlockSaveRequests saveRequests) {

        saveRequests.getSubData().stream()
                .map(FreeBlockSaveRequest::toEntity)
                .forEach(freeBlockRepository::save);
    }

    public FreeBlockResponses find(final List<Long> freeIds) {

        List<FreeBlockResponse> freeBlockResponses = new ArrayList<>();
        for (Long freeId : freeIds) {
            FreeBlock freeBlock = freeBlockRepository.findFreeBlock(freeId);
            FreeBlockResponse response = FreeBlockResponse.from(freeBlock);
            freeBlockResponses.add(response);

        }

        return new FreeBlockResponses(freeBlockResponses);
    }

    @Transactional
    public void update(final FreeBlockUpdateRequests updateRequests) {

        for (FreeBlockUpdateRequest updateRequest : updateRequests.getSubData()) {
            FreeBlock freeBlock = freeBlockRepository.findFreeBlock(updateRequest.getFreeBlockId());
            freeBlock.update(updateRequest.getFreeTitle(), updateRequest.getFreeContent());
            freeBlockRepository.save(freeBlock);
        }
    }
}