package com.javajober.template.repository;


import java.util.List;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.entity.Template;

public interface TemplateRepository extends Repository<Template, Long> {

	List<Template> findBySpaceWallCategoryId(Long templateCategoryId);

	default List<Template> getBySpaceWallCategoryId(final Long templateCategoryId){
		List<Template> templates = findBySpaceWallCategoryId(templateCategoryId);

		if(templates == null || templates.isEmpty()){
			throw new Exception404("추천 템플릿 그룹을 찾을 수 없습니다.");
		}

		return templates;
	}

}

