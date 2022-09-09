package com.gruppprojekt2.kvarteret;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class KvarteretApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void checkIfRepoIsInit()
	{
		ItemRepository itemRepository = new ItemRepository();
		List<Item> items = itemRepository.getItems();
		Assertions.assertNotNull(items);
	}
}
