package com.javajober.template.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.template.domain.Template;

public interface TemplateRepository extends Repository<Template, Long> {

	List<Template> findBySpaceWallCategoryId(final Long templateCategoryId);

	List<Template> findByTemplateTitleContaining(final String keyword);

	default List<Template> getBySpaceWallCategoryId(final Long templateCategoryId){
		List<Template> templates = findBySpaceWallCategoryId(templateCategoryId);

		if(templates == null || templates.isEmpty()){
			throw new ApplicationException(ApiStatus.NOT_FOUND, "추천 템플릿 그룹을 찾을 수 없습니다.");
		}
		return templates;
	}

	default List<Template> getTemplateTitle(final String keyword){
		List<Template> templates = findByTemplateTitleContaining(keyword);

		if(templates == null || templates.isEmpty()){
			throw new ApplicationException(ApiStatus.NOT_FOUND, "해당 검색어를 찾을 수 없습니다.");
		}
		return templates;
	}
}