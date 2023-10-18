package com.javajober.snsBlock.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.javajober.snsBlock.domain.SNSBlock;
import com.javajober.snsBlock.domain.SNSType;
import com.javajober.snsBlock.dto.request.SNSBlockDeleteRequest;
import com.javajober.snsBlock.dto.request.SNSBlockSaveRequest;
import com.javajober.snsBlock.dto.request.SNSBlockSaveRequests;
import com.javajober.snsBlock.dto.request.SNSBlockUpdateRequest;
import com.javajober.snsBlock.dto.response.SNSBlockResponse;
import com.javajober.snsBlock.dto.response.SNSBlockResponses;
import com.javajober.snsBlock.repository.SNSBlockRepository;

@Service
public class SNSBlockService {

	private final SNSBlockRepository snsBlockRepository;

	public SNSBlockService(final SNSBlockRepository snsBlockRepository) {
		this.snsBlockRepository = snsBlockRepository;
	}

	@Transactional
	public void save(final SNSBlockSaveRequests<SNSBlockSaveRequest> snsBlockSaveRequests) {

		snsBlockSaveRequests.getSubData().forEach(snsBlockRequest -> {
			SNSBlock snsBlock = SNSBlockSaveRequest.toEntity(snsBlockRequest);
			snsBlockRepository.save(snsBlock);
		});
	}

	public SNSBlockResponses find(final List<Long> snsBlockIds) {

		List<SNSBlockResponse> snsBlocks =
			snsBlockRepository.findAllById(snsBlockIds).stream()
				.map(SNSBlockResponse::from)
				.collect(Collectors.toList());

		return new SNSBlockResponses(snsBlocks);
	}

	@Transactional
	public void update(@RequestBody final SNSBlockSaveRequests<SNSBlockUpdateRequest> snsBlockSaveRequests) {

		snsBlockSaveRequests.getSubData().forEach(snsBlockRequest -> {

			SNSBlock snsBlock = snsBlockRepository.findSNSBlock(snsBlockRequest.getSnsBlockId());

			SNSType snsType = SNSType.findSNSTypeByString(snsBlockRequest.getSnsType());

			snsBlock.update(snsBlockRequest.getSnsUUID(), snsType, snsBlockRequest.getSnsURL());

			snsBlockRepository.save(snsBlock);
		});
	}

	@Transactional
	public void delete(final SNSBlockDeleteRequest snsBlockDeleteRequest) {

		List<SNSBlock> snsBlocks = snsBlockRepository.findAllById(snsBlockDeleteRequest.getSnsBlockIds());

		for (SNSBlock snsBlock: snsBlocks) {
			snsBlock.updateTimeOnDelete();
		}
		snsBlockRepository.saveAll(snsBlocks);
	}
}