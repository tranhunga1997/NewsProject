package com.news.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.news.exceptions.BusinessException;
import com.news.forms.PostFilterForm;
import com.news.models.Datas;
import com.news.models.Post;

@Service
public class PostService {
	
	private Set<Post> posts;
	
	public PostService(Datas datas) {
		posts = datas.getPosts();
	}

	/**
	 * Filter tin tức
	 * @param form filter form
	 * @param currentPage trang hiện tại (bắt đầu từ 1)
	 * @param limit số tin tức hiện trong 1 trang
	 * @return danh sách tin tức
	 */
	public List<Post> filter(PostFilterForm form, int currentPage, int limit) {
		Stream<Post> postStream = posts.stream();
		if (null == form) {
			// form is null
			postStream = postStream.skip((currentPage - 1) * limit).limit(limit);
			return postStream.collect(Collectors.toList());
		}

		if (!ObjectUtils.isEmpty(form.getId()) && form.getId() != 0) {
			// id filter
			postStream = postStream.filter(p -> p.getId() == form.getId());
		}
		if (StringUtils.hasLength(form.getTitle())) {
			// title filter
			postStream = postStream.filter(p -> p.getTitle().contains(form.getTitle()));
		}
		if (null != form.getCategory()) {
			// category filter
			postStream = postStream.filter(p -> p.getCategory().contains(form.getCategory()));
		}
		
		postStream = postStream.skip((currentPage - 1) * limit).limit(limit);
		return postStream.collect(Collectors.toList());
	}

	/**
	 * Thêm tin tức
	 * @param post
	 * @return post vừa thêm
	 * @throws BusinessException
	 */
	public Post add(Post post) throws BusinessException {
		Stream<Post> postStream = posts.stream();
		boolean isExists = postStream.anyMatch(p -> p.equalsTitle(post));
		if (isExists) {
			throw new BusinessException("bài post đã tồn tại");
		}
		post.setCreateDatetime(LocalDateTime.now());
		post.setUpdateDatetime(LocalDateTime.now());
		posts.add(post);
		return post;
	}

	/**
	 * Sửa tin tức
	 * @param post
	 * @return post vừa sửa
	 * @throws BusinessException
	 */
	public Post update(Post post) throws BusinessException {
		Stream<Post> postStream = posts.stream();
		Optional<Post> oldPostOptional = postStream.filter(p -> p.getId() == post.getId()).findAny();
		Post oldPost = oldPostOptional.orElseThrow(() -> new BusinessException("bài post không tồn tại."));
		BeanUtils.copyProperties(post, oldPost);
		posts.add(oldPost);
		return oldPost;
	}
	
	/**
	 * Xóa tin tức
	 * @param id
	 * @return <code>true</code> xóa thành công <br> <code>false</code> xóa thất bại
	 */
	public boolean delete(long id) {
		Stream<Post> postStreams = posts.stream();
		Optional<Post> optionPost = postStreams.filter(p -> p.getId() == id).findAny();
		try {
			posts.remove(optionPost.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
