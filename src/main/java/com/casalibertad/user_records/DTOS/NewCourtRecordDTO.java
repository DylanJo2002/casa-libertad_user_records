package com.casalibertad.user_records.DTOS;

import lombok.Data;

@Data
public class NewCourtRecordDTO {
	private int record_policia_id;
	private int record_codigo_id;
	private int record_sisipec_id;
	private int record_personeria_id;
	private int record_procuraduria_id;
	private int record_contraloria_id;
	private int record_rama_id;
}
