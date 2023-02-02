package com.harishverma.blog.services;

import com.harishverma.blog.payloads.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategory(Integer id);
    List<CategoryDTO> getAllCategory();
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id);
    void delete(Integer id);

}
