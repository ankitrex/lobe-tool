package xyz.qwerty.lobetoolapis.vo;

import java.util.List;

import lombok.Data;

@Data
public class DimensionVo {

	private Integer				id;

	private String				dimensionName;

	private List<QuestionVo>	questions;
}
