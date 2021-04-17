package com.smarthost.codingchallenge;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class CodingchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingchallengeApplication.class, args);
	}

	@SuppressWarnings("deprecation")
	@Bean
	public List<Integer> guestData(ObjectMapper objectMapper) throws Exception {

		Resource resource = new ClassPathResource("data/input");
		InputStream inputStream = resource.getInputStream();
		byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
		String data = new String(bdata, StandardCharsets.UTF_8);

		final JsonNode jsonNode = new ObjectMapper().configure(JsonParser.Feature.ALLOW_TRAILING_COMMA, true).readTree(data);
		final Integer[] o = objectMapper.readerFor(Integer[].class).readValue(jsonNode);
		final List<Integer> bids = Arrays.asList(o);
		bids.sort(Comparator.comparingInt(b -> -b));
		return bids;
	}

}
