package hp.sfs.sales.dashboard.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import lombok.Getter;
import lombok.Setter;
import java.lang.reflect.Type;

@Getter
@Setter
public class SalesRecordDto {
	private String startTime;
	private String endTime;
	private String product;
	private Double rate;
	private Double startReading;
	private Double endReading;
	private Double salesVolume;
	private Double pumpTestVolume;
	private Double amount;
}

