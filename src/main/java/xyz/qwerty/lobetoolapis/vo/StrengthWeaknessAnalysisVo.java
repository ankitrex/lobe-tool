package xyz.qwerty.lobetoolapis.vo;

import java.util.List;

import lombok.Data;

@Data
public class StrengthWeaknessAnalysisVo {

	private String				zone;

	private List<SwDimensionVo>	swDimensions;
}
