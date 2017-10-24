package com.sinotrans.gd.wlp.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.sinotrans.gd.wlp.common.web.RcUtil;

public class CustomDateYMDHMSSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat(
				RcUtil.yyyy_MM_dd_HH_mm_ss);
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);
	}
}
