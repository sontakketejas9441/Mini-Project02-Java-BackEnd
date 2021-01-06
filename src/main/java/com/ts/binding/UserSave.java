package com.ts.binding;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSave {

		private Integer id;
	    private String password;
	    private String isActive;
}
