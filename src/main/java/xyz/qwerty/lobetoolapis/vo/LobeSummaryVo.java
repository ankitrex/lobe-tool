package xyz.qwerty.lobetoolapis.vo;

import java.util.List;

import lombok.Data;

@Data
public class LobeSummaryVo {

	private String					learningObjectName;

	private List<DimensionAggScoreVo>	dimensionAggScores;
}
