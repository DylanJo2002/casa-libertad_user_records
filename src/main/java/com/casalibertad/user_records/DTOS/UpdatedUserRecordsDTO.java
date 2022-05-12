package com.casalibertad.user_records.DTOS;

import java.util.List;

import lombok.Data;

@Data
public class UpdatedUserRecordsDTO {
	
	private NewCourtRecordDTO court_records_id;
	private String freedom_date;
	private int months_sentence;
	private int prison_establishment_id;
	private String another_prison_establishment;
	private int legal_status_id;
	private int apprehended_teenager;
	private int apprehended_adult;
	private List<Integer> crimes;
	private List<String> other_crimes;
	private Character actual_process;
	
}
