package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 빈 컨테이너에 적재
public class BlogServiceImpl implements BlogService {

    BlogRepository blogRepository;

    ReplyRepository replyRepository;

    // 추가 기능 : 목록에서 글 클릭해서 detail 페이지로 조회 시 조회수 카운트해줌.
    @Override
    public long viewUpdate(Blog blog) {
        return blogRepository.viewUpdate(blog);
    }

    @Autowired // 생성자 주입이 속도가 더 빠름.
    public BlogServiceImpl(BlogRepository blogRepository, ReplyRepository replyRepository){
        this.blogRepository = blogRepository;
        this.replyRepository = replyRepository;
    }

    @Override
    public List<Blog> findAll() {
        //List<Blog> blogList = blogRepository.findAll();
        //return blogList;
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(long blogId) {
        return blogRepository.findById(blogId);
    }

    @Transactional // 둘다 실행되던지 둘 다 실행 안되던지
    @Override
    public void deleteById(long blogId) {
        replyRepository.deleteByBlogId(blogId);
        blogRepository.deleteById(blogId);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void update(Blog blog) {
        blogRepository.update(blog);
    }

}
