package com.techprimers.springbatchexample1.listener;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import com.techprimers.springbatchexample1.dto.LineDTO;

@Component
public class InvalidItemsListener implements ItemProcessListener<LineDTO, LineDTO> {

	@Override
	public void beforeProcess(LineDTO lineDTO) {
	}

	@Override
	public void afterProcess(LineDTO lineDTO, LineDTO result) {
		if (result == null) {
			System.out.println(lineDTO + " has been filtered because it is invalid");
		}
	}

	@Override
	public void onProcessError(LineDTO lineDTO, Exception e) {
		System.out.println(lineDTO + " is invalid due to " + e.getMessage() );
	}
}
