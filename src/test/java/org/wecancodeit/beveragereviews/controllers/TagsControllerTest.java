package org.wecancodeit.beveragereviews.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.wecancodeit.beveragereviews.controllers.TagController;
import org.wecancodeit.beveragereviews.models.Tag;
import org.wecancodeit.beveragereviews.repositories.TagRepository;

public class TagsControllerTest {
	
	@InjectMocks
	private TagController underTest;

	@Mock 
	private Tag tag;
	
	@Mock
	private Tag tagone;

	@Mock
	private Tag tagtwo;

	@Mock
	private Model model;

	@Mock
	private TagRepository tagRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void shouldAddAllTagsToModel() {
		Collection<Tag> allTags = Arrays.asList(tagone, tagtwo);
		when(tagRepo.findAll()).thenReturn(allTags);
		
		underTest.findAllTags(model);
		verify(model).addAttribute("tags", allTags);
	}
	
	@Test
	public void shouldAddSingleTagToModel() throws TagNotFoundException {
		long arbitraryTagId = 1;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		
		underTest.findOneTag(arbitraryTagId, model);
		verify(model).addAttribute("tags", tag);
	}

}