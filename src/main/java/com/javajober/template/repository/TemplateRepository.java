package com.javajober.template.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.spaceWallCategory.domain.SpaceWallCategoryType;
import com.javajober.template.domain.Template;

public interface TemplateRepository extends Repository<Template, Long> {

	List<Template> findBySpaceWallCategoryId(final Long templateCategoryId);

	List<Template> findByTemplateTitleContaining(final String keyword);

	default List<Template> getBySpaceWallCategoryId(final Long templateCategoryId){
		List<Template> templates = findBySpaceWallCategoryId(templateCategoryId);

		if(templates == null || templates.isEmpty()){
			throw new Exception404(ErrorMessage.TEMPLATE_RECOMMEND_NOT_FOUND);
		}
		return templates;
	}

	default List<Template> getTemplateTitle(final String keyword){
		List<Template> templates = findByTemplateTitleContaining(keyword);

		if(templates == null || templates.isEmpty()){
			throw new Exception404( ErrorMessage.TEMPLATE_SEARCH_NOT_FOUND);
		}
		return templates;
	}
}