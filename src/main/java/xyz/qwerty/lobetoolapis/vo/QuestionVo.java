package xyz.qwerty.lobetoolapis.vo;

import java.util.List;

import lombok.Data;

@Data
public class QuestionVo {

	private Integer			id;

	private String			question;

	private String			questionMeta;

	private String			score0;

	private List<String>	score0Images;

	private String			score1;

	private List<String>	score1Images;

	private String			score2;

	private List<String>	score2Images;

	private String			score3;

	private List<String>	score3Images;

	private boolean			optional;

	private Integer			dimensionId;

	private Integer			score;	
}
