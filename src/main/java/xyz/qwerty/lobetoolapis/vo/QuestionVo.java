package xyz.qwerty.lobetoolapis.vo;

import lombok.Data;

@Data
public class QuestionVo {

	private Integer	id;

	private String	question;

	private String	questionMeta;

	private String	score0;

	private String	score0Images;

	private String	score1;

	private String	score1Images;

	private String	score2;

	private String	score2Images;

	private String	score3;

	private String	score3Images;

	private boolean	optional;

	private Integer	dimensionId;

	private Integer	score;
}
