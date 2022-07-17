package com.news.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.news.exceptions.BusinessException;
import com.news.forms.PostFilterForm;
import com.news.models.Datas;
import com.news.models.Post;

@Service
public class PostService {

	@Autowired
	private Datas datas;

	// filter
	public List<Post> filter(PostFilterForm form, int currentPage, int limit) {
		Stream<Post> postStream = datas.getPosts().stream();
		if (null == form) {
			postStream = postStream.skip((currentPage - 1) * limit).limit(limit);
			return postStream.collect(Collectors.toList());
		}

		if (!ObjectUtils.isEmpty(form.getId()) && form.getId() != 0) {
			postStream = postStream.filter(p -> p.getId() == form.getId());
		}
		if (StringUtils.hasLength(form.getTitle())) {
			postStream = postStream.filter(p -> p.getTitle().contains(form.getTitle()));
		}

		postStream = postStream.skip((currentPage - 1) * limit).limit(limit);
		return postStream.collect(Collectors.toList());
	}

	// add
	public void add(Post post) throws BusinessException {
		Stream<Post> postStream = datas.getPosts().stream();
		boolean isExists = postStream.anyMatch(p -> p.equalsTitle(post));
		if (isExists) {
			throw new BusinessException("bài post đã tồn tại");
		}
		datas.getPosts().add(post);
	}

	// update

	// delete
	public boolean delete(long id) {
		Stream<Post> postStreams = datas.getPosts().stream();
		Optional<Post> optionPost = postStreams.filter(p -> p.getId() == id).findAny();
		try {
			datas.getPosts().remove(optionPost.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
