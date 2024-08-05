package com.retail.store.discount.utils;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ObjectMapperUtil {

	private ObjectMapperUtil() {

	}

	private static ObjectMapper objectMapper;

	public static ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			// support Java 8 date time apis
			objectMapper.registerModule(new JavaTimeModule());
		}
		return objectMapper;

	}

	public static String convertObjectToJson(Object object) {
		try {
			if (object != null) {
				return getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
			}
		} catch (JsonProcessingException e) {
			log.error("Exception occurred while converting object into Json format: ", e);
		}

		throw new NullPointerException();
	}

}
