package xyz.qwerty.lobetoolapis.vo;

import java.util.List;

import lombok.Data;

@Data
public class SwDimensionVo {

	private Integer				dimensionId;

	private String				dimensionName;

	private List<SwQuestionVo>	swQuestions;
}
