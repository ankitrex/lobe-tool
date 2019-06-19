package xyz.qwerty.lobetoolapis.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.qwerty.lobetoolapis.entity.LearningObject;
import xyz.qwerty.lobetoolapis.repository.LearningObjectRepository;
import xyz.qwerty.lobetoolapis.repository.LobeScoresRepository;
import xyz.qwerty.lobetoolapis.service.AnalyticsService;
import xyz.qwerty.lobetoolapis.service.LobeService;
import xyz.qwerty.lobetoolapis.util.Constants;
import xyz.qwerty.lobetoolapis.vo.DimensionAggScoreVo;
import xyz.qwerty.lobetoolapis.vo.DimensionVo;
import xyz.qwerty.lobetoolapis.vo.LearningObjectVo;
import xyz.qwerty.lobetoolapis.vo.LobeSummaryVo;
import xyz.qwerty.lobetoolapis.vo.QuestionVo;
import xyz.qwerty.lobetoolapis.vo.StrengthWeaknessAnalysisVo;
import xyz.qwerty.lobetoolapis.vo.SwDimensionVo;
import xyz.qwerty.lobetoolapis.vo.SwQuestionVo;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

	@Autowired
	LearningObjectRepository	learningObjectRepository;

	@Autowired
	LobeScoresRepository		lobeScoresRepository;

	@Autowired
	LobeService					lobeService;

	@Override
	public List<LearningObjectVo> getAllLobes(String userId, String type, Integer rubrikId) {

		List<LearningObject> learningObjects;
		if (Constants.TYPE_GENERATOR.equals(type)) {
			learningObjects = learningObjectRepository.findAllByRubrikIdAndStatus(rubrikId, Constants.STATUS_COMPLETE);
		}
		else {
			learningObjects = learningObjectRepository.findAllByRubrikIdAndUser2EmailAndStatus(rubrikId, userId, Constants.STATUS_COMPLETE);
		}

		return learningObjects.stream().map(lo -> getLearningObjectVo(lo)).collect(Collectors.toList());
	}

	private LearningObjectVo getLearningObjectVo(LearningObject learningObject) {

		LearningObjectVo learningObjectVo = new LearningObjectVo();
		learningObjectVo.setId(learningObject.getId());
		learningObjectVo.setName(learningObject.getModuleName());

		return learningObjectVo;
	}

	@Override
	public List<LobeSummaryVo> getLobeSummary(List<Integer> lobeIds, String userId) {

		List<LearningObjectVo> learningObjects = new ArrayList<>();

		lobeIds.forEach(lobeId -> {
			learningObjects.add(lobeService.getLobeRubrik(userId, lobeId, true));
		});

		List<LobeSummaryVo> lobeSummaries = new ArrayList<>();

		learningObjects.forEach(learningObject -> {

			LobeSummaryVo lobeSummaryVo = new LobeSummaryVo();
			lobeSummaryVo.setLearningObjectName(learningObject.getName());
			lobeSummaryVo.setDimensionAggScores(new ArrayList<>());

			List<DimensionVo> dimensions = learningObject.getDimensionVos();

			dimensions.forEach(d -> {
				DimensionAggScoreVo dimensionAggScoreVo = new DimensionAggScoreVo();
				dimensionAggScoreVo.setDimensionId(d.getId());
				dimensionAggScoreVo.setDimensionName(d.getDimensionName());
				dimensionAggScoreVo.setMaxScore(d.getQuestions().size() * 3);
				dimensionAggScoreVo.setScoreObtained(0);
				d.getQuestions().forEach(q -> {
					dimensionAggScoreVo.setScoreObtained(dimensionAggScoreVo.getScoreObtained() + q.getScore());
				});

				lobeSummaryVo.getDimensionAggScores().add(dimensionAggScoreVo);
			});

			lobeSummaries.add(lobeSummaryVo);
		});

		return lobeSummaries;
	}

	@Override
	public List<StrengthWeaknessAnalysisVo> getStrengthWeaknessAnalysis(List<Integer> lobeIds, String userId) {

		List<StrengthWeaknessAnalysisVo> analysis = new ArrayList<>();

		StrengthWeaknessAnalysisVo green = new StrengthWeaknessAnalysisVo();
		green.setZone("green");

		SwDimensionVo greenSwDimension1 = new SwDimensionVo();
		greenSwDimension1.setSwQuestions(new ArrayList<>());
		greenSwDimension1.setDimensionId(1);
		greenSwDimension1.setDimensionName("Content Quality");

		SwDimensionVo greenSwDimension2 = new SwDimensionVo();
		greenSwDimension2.setSwQuestions(new ArrayList<>());
		greenSwDimension2.setDimensionId(2);
		greenSwDimension2.setDimensionName("Pedagogical Alignment");

		SwDimensionVo greenSwDimension3 = new SwDimensionVo();
		greenSwDimension3.setSwQuestions(new ArrayList<>());
		greenSwDimension3.setDimensionId(3);
		greenSwDimension3.setDimensionName("Design Efficacy");

		SwDimensionVo greenSwDimension4 = new SwDimensionVo();
		greenSwDimension4.setSwQuestions(new ArrayList<>());
		greenSwDimension4.setDimensionId(4);
		greenSwDimension4.setDimensionName("Technological Integration");

		green.setSwDimensions(Arrays.asList(greenSwDimension1, greenSwDimension2, greenSwDimension3, greenSwDimension4));

		StrengthWeaknessAnalysisVo yellow = new StrengthWeaknessAnalysisVo();
		yellow.setZone("yellow");

		SwDimensionVo yellowSwDimension1 = new SwDimensionVo();
		yellowSwDimension1.setSwQuestions(new ArrayList<>());
		yellowSwDimension1.setDimensionId(1);
		yellowSwDimension1.setDimensionName("Content Quality");

		SwDimensionVo yellowSwDimension2 = new SwDimensionVo();
		yellowSwDimension2.setSwQuestions(new ArrayList<>());
		yellowSwDimension2.setDimensionId(2);
		yellowSwDimension2.setDimensionName("Pedagogical Alignment");

		SwDimensionVo yellowSwDimension3 = new SwDimensionVo();
		yellowSwDimension3.setSwQuestions(new ArrayList<>());
		yellowSwDimension3.setDimensionId(3);
		yellowSwDimension3.setDimensionName("Design Efficacy");

		SwDimensionVo yellowSwDimension4 = new SwDimensionVo();
		yellowSwDimension4.setSwQuestions(new ArrayList<>());
		yellowSwDimension4.setDimensionId(4);
		yellowSwDimension4.setDimensionName("Technological Integration");

		yellow.setSwDimensions(Arrays.asList(yellowSwDimension1, yellowSwDimension2, yellowSwDimension3, yellowSwDimension4));

		StrengthWeaknessAnalysisVo orange = new StrengthWeaknessAnalysisVo();
		orange.setZone("orange");

		SwDimensionVo orangeSwDimension1 = new SwDimensionVo();
		orangeSwDimension1.setSwQuestions(new ArrayList<>());
		orangeSwDimension1.setDimensionId(1);
		orangeSwDimension1.setDimensionName("Content Quality");

		SwDimensionVo orangeSwDimension2 = new SwDimensionVo();
		orangeSwDimension2.setSwQuestions(new ArrayList<>());
		orangeSwDimension2.setDimensionId(2);
		orangeSwDimension2.setDimensionName("Pedagogical Alignment");

		SwDimensionVo orangeSwDimension3 = new SwDimensionVo();
		orangeSwDimension3.setSwQuestions(new ArrayList<>());
		orangeSwDimension3.setDimensionId(3);
		orangeSwDimension3.setDimensionName("Design Efficacy");

		SwDimensionVo orangeSwDimension4 = new SwDimensionVo();
		orangeSwDimension4.setSwQuestions(new ArrayList<>());
		orangeSwDimension4.setDimensionId(4);
		orangeSwDimension4.setDimensionName("Technological Integration");

		orange.setSwDimensions(Arrays.asList(orangeSwDimension1, orangeSwDimension2, orangeSwDimension3, orangeSwDimension4));

		StrengthWeaknessAnalysisVo red = new StrengthWeaknessAnalysisVo();
		red.setZone("red");

		SwDimensionVo redSwDimension1 = new SwDimensionVo();
		redSwDimension1.setSwQuestions(new ArrayList<>());
		redSwDimension1.setDimensionId(1);
		redSwDimension1.setDimensionName("Content Quality");

		SwDimensionVo redSwDimension2 = new SwDimensionVo();
		redSwDimension2.setSwQuestions(new ArrayList<>());
		redSwDimension2.setDimensionId(2);
		redSwDimension2.setDimensionName("Pedagogical Alignment");

		SwDimensionVo redSwDimension3 = new SwDimensionVo();
		redSwDimension3.setSwQuestions(new ArrayList<>());
		redSwDimension3.setDimensionId(3);
		redSwDimension3.setDimensionName("Design Efficacy");

		SwDimensionVo redSwDimension4 = new SwDimensionVo();
		redSwDimension4.setSwQuestions(new ArrayList<>());
		redSwDimension4.setDimensionId(4);
		redSwDimension4.setDimensionName("Technological Integration");

		red.setSwDimensions(Arrays.asList(redSwDimension1, redSwDimension2, redSwDimension3, redSwDimension4));

		analysis.add(green);
		analysis.add(yellow);
		analysis.add(orange);
		analysis.add(red);

		List<LearningObjectVo> learningObjects = new ArrayList<>();

		lobeIds.forEach(lobeId -> {
			learningObjects.add(lobeService.getLobeRubrik(userId, lobeId, true));
		});

		Map<Integer, SwQuestionVo> questionsAgg = getQuestionsAgg(learningObjects);

		for (Entry<Integer, SwQuestionVo> entry : questionsAgg.entrySet()) {

			SwQuestionVo swQuestionAgg = entry.getValue();
			Double score0 = swQuestionAgg.getScore0();
			Double score1 = swQuestionAgg.getScore1();
			Double score2 = swQuestionAgg.getScore2();
			Double score3 = swQuestionAgg.getScore3();

			Double total = score0 + score1 + score2 + score3;

			score0 = score0 * 100 / total;
			score1 = score1 * 100 / total;
			score2 = score2 * 100 / total;
			score3 = score3 * 100 / total;

			swQuestionAgg.setScore0(score0);
			swQuestionAgg.setScore1(score1);
			swQuestionAgg.setScore2(score2);
			swQuestionAgg.setScore3(score3);

			Boolean c0 = score1 + score2 == 0 && score0 == 0;
			Boolean c1 = score1 + score2 > score0;
			Boolean c2 = score1 + score2 < score0;

			if (score3 > 75) {
				if (c0 || c1) {
					// green
					if (swQuestionAgg.getDimensionId() == 1) {
						greenSwDimension1.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 2) {
						greenSwDimension2.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 3) {
						greenSwDimension3.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 4) {
						greenSwDimension4.getSwQuestions().add(swQuestionAgg);
					}
				}
				else if (c2) {
					// yellow
					if (swQuestionAgg.getDimensionId() == 1) {
						yellowSwDimension1.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 2) {
						yellowSwDimension2.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 3) {
						yellowSwDimension3.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 4) {
						yellowSwDimension4.getSwQuestions().add(swQuestionAgg);
					}
				}
				else {
					// orange
					if (swQuestionAgg.getDimensionId() == 1) {
						orangeSwDimension1.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 2) {
						orangeSwDimension2.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 3) {
						orangeSwDimension3.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 4) {
						orangeSwDimension4.getSwQuestions().add(swQuestionAgg);
					}
				}
			}
			else if (score3 <= 75 && score3 >= 25) {
				if (c1) {
					// yellow
					if (swQuestionAgg.getDimensionId() == 1) {
						yellowSwDimension1.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 2) {
						yellowSwDimension2.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 3) {
						yellowSwDimension3.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 4) {
						yellowSwDimension4.getSwQuestions().add(swQuestionAgg);
					}
				}
				else {
					// orange
					if (swQuestionAgg.getDimensionId() == 1) {
						orangeSwDimension1.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 2) {
						orangeSwDimension2.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 3) {
						orangeSwDimension3.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 4) {
						orangeSwDimension4.getSwQuestions().add(swQuestionAgg);
					}
				}
			}
			else {
				if (c1) {
					// orange
					if (swQuestionAgg.getDimensionId() == 1) {
						orangeSwDimension1.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 2) {
						orangeSwDimension2.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 3) {
						orangeSwDimension3.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 4) {
						orangeSwDimension4.getSwQuestions().add(swQuestionAgg);
					}
				}
				else {
					// red
					if (swQuestionAgg.getDimensionId() == 1) {
						redSwDimension1.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 2) {
						redSwDimension2.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 3) {
						redSwDimension3.getSwQuestions().add(swQuestionAgg);
					}
					else if (swQuestionAgg.getDimensionId() == 4) {
						redSwDimension4.getSwQuestions().add(swQuestionAgg);
					}
				}
			}
		}

		return analysis;
	}

	private Map<Integer, SwQuestionVo> getQuestionsAgg(List<LearningObjectVo> learningObjects) {

		Map<Integer, SwQuestionVo> questionsAgg = new HashMap<>();

		learningObjects.forEach(learningObject -> {

			List<DimensionVo> dimensions = learningObject.getDimensionVos();

			dimensions.forEach(d -> {

				List<QuestionVo> questions = d.getQuestions();

				questions.forEach(q -> {

					Integer questionId = q.getId();
					String question = q.getQuestion();
					Integer score = q.getScore();
					Integer dimensionId = q.getDimensionId();

					if (!questionsAgg.containsKey(q.getId())) {

						SwQuestionVo swQuestionVo = new SwQuestionVo();
						swQuestionVo.setQuestion(question);
						swQuestionVo.setScore0(0d);
						swQuestionVo.setScore1(0d);
						swQuestionVo.setScore2(0d);
						swQuestionVo.setScore3(0d);
						swQuestionVo.setDimensionId(dimensionId);

						questionsAgg.put(questionId, swQuestionVo);
					}
					SwQuestionVo swQuestionAgg = questionsAgg.get(questionId);
					if (score == 0) {
						swQuestionAgg.setScore0(swQuestionAgg.getScore0() + 1);
					}
					else if (score == 1) {
						swQuestionAgg.setScore1(swQuestionAgg.getScore1() + 1);
					}
					else if (score == 2) {
						swQuestionAgg.setScore2(swQuestionAgg.getScore2() + 1);
					}
					else if (score == 3) {
						swQuestionAgg.setScore3(swQuestionAgg.getScore3() + 1);
					}
				});
			});
		});
		return questionsAgg;
	}

}
