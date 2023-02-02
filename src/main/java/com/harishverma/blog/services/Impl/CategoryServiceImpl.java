package com.harishverma.blog.services.Impl;

import com.harishverma.blog.entities.Category;
import com.harishverma.blog.exceptions.ResourceNotFoundException;
import com.harishverma.blog.payloads.CategoryDTO;
import com.harishverma.blog.repositories.CategoryRepo;
import com.harishverma.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategory(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDTO> categoryDTOList = categories.stream().map((category -> modelMapper.map(category, CategoryDTO.class)))
                .collect(Collectors.toList());
        return categoryDTOList;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public void delete(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepo.delete(category);
    }
}
