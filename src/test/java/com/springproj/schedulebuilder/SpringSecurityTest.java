package com.springproj.schedulebuilder;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class SpringSecurityTest {

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void testTimeIntervalsForbidden() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/sub/time-intervals"))
				.andExpect(status().isForbidden())
				.andReturn();
	}

	@Test
	public void testDaysForbidden() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/sub/days"))
				.andExpect(status().isForbidden())
				.andReturn();
	}

	@Test
	public void testSubjectsForbidden() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/sub/all"))
				.andExpect(status().isForbidden())
				.andReturn();
	}

	@Test
	public void testSlotsForbidden() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/sub/1/slot"))
				.andExpect(status().isForbidden())
				.andReturn();
	}

	@Test
	public void testTimetableForbidden() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/app/timetable"))
				.andExpect(status().isForbidden())
				.andReturn();
	}

	@Test
	@WithMockUser(username = "user1", password = "Pas21word", roles = "USER")
	public void myTest1() throws Exception {
		mockMvc.perform(get("/api/v1/sub/days"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "user1", password = "Pas21word", roles = "USER")
	public void testTimeIntervalsOk() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/sub/time-intervals"))
				.andExpect(status().isOk())
				.andReturn();

		Assertions.assertEquals("application/json",
				mvcResult.getResponse().getContentType());
	}

	@Test
	@WithMockUser(username = "user1", password = "Pas21word", roles = "USER")
	public void testDaysOk() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/sub/days"))
				.andExpect(status().isOk())
				.andReturn();

		Assertions.assertEquals("application/json",
				mvcResult.getResponse().getContentType());
	}

	@Test
	@WithMockUser(username = "user1", password = "Pas21word", roles = "USER")
	public void testSubjectsOk() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/sub/all"))
				.andExpect(status().isOk())
				.andReturn();

		Assertions.assertEquals("application/json",
				mvcResult.getResponse().getContentType());
	}

	@Test
	@WithMockUser(username = "user1", password = "Pas21word", roles = "USER")
	public void testTimetableOk() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/app/timetable"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		Assertions.assertEquals("application/json",
				mvcResult.getResponse().getContentType());
	}

	@Test
	@WithMockUser(username = "user1", password = "Pas21word", roles = "USER")
	public void testTimeIntervalsHeaders() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/sub/time-intervals"))
				.andExpect(status().isOk())
				.andReturn();

		Assertions.assertNull(mvcResult.getResponse().getErrorMessage());

		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-Content-Type-Options"), "nosniff");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-XSS-Protection"), "1; mode=block");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("Cache-Control"), "no-cache, no-store, max-age=0, must-revalidate");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("Pragma"), "no-cache");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-Frame-Options"), "DENY");
	}

	@Test
	@WithMockUser(username = "user1", password = "Pas21word", roles = "USER")
	public void testDaysHeaders() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/sub/days"))
				.andExpect(status().isOk())
				.andReturn();

		Assertions.assertNull(mvcResult.getResponse().getErrorMessage());

		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-Content-Type-Options"), "nosniff");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-XSS-Protection"), "1; mode=block");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("Cache-Control"), "no-cache, no-store, max-age=0, must-revalidate");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("Pragma"), "no-cache");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-Frame-Options"), "DENY");
	}

	@Test
	@WithMockUser(username = "user1", password = "Pas21word", roles = "USER")
	public void testSubjectsHeaders() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/sub/all"))
				.andExpect(status().isOk())
				.andReturn();

		Assertions.assertNull(mvcResult.getResponse().getErrorMessage());

		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-Content-Type-Options"), "nosniff");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-XSS-Protection"), "1; mode=block");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("Cache-Control"), "no-cache, no-store, max-age=0, must-revalidate");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("Pragma"), "no-cache");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-Frame-Options"), "DENY");
	}

	@Test
	@WithMockUser(username = "user1", password = "Pas21word", roles = "USER")
	public void testTimetableHeaders() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/app/timetable"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		Assertions.assertNull(mvcResult.getResponse().getErrorMessage());

		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-Content-Type-Options"), "nosniff");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-XSS-Protection"), "1; mode=block");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("Cache-Control"), "no-cache, no-store, max-age=0, must-revalidate");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("Pragma"), "no-cache");
		Assertions.assertEquals(mvcResult.getResponse().getHeader("X-Frame-Options"), "DENY");
	}
}
