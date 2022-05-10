package com.casalibertad.user_records.DTOS;

import lombok.Data;

@Data
public class CourtRecordDTO {
	private SelectionDTO record_policia;
	private SelectionDTO record_codigo;
	private SelectionDTO record_sisipec;
	private SelectionDTO record_personeria;
	private SelectionDTO record_procuraduria;
	private SelectionDTO record_contraloria;
	private SelectionDTO record_rama;	
}
