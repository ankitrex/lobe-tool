package xyz.qwerty.lobetoolapis.vo;

import java.util.List;

import lombok.Data;

@Data
public class ComparativeAnalysisVo {

	private String					repositoryName;

	private List<CmpDimensionVo>	cmpDimensions;
}
