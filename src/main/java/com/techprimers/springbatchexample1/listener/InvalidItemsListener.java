package com.techprimers.springbatchexample1.listener;

import java.util.List;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.techprimers.springbatchexample1.dto.LineDTO;

@Component
@Scope("prototype")
public class InvalidItemsListener implements ItemProcessListener<LineDTO, LineDTO> {
	
	private List<LineDTO> invalidLines;
	
	@Override
	public void beforeProcess(LineDTO lineDTO) {
	}

	@Override
	public void afterProcess(LineDTO lineDTO, LineDTO result) {
		if (result == null) {
			System.out.println(lineDTO + " has been filtered because it is invalid");
			invalidLines.add(lineDTO);
		}
	}

	@Override
	public void onProcessError(LineDTO lineDTO, Exception e) {
		System.out.println(lineDTO + " is invalid due to " + e.getMessage() );
	}

	public void setInvalidLines(List<LineDTO> invalidLines) {
		this.invalidLines = invalidLines;
	}
}
