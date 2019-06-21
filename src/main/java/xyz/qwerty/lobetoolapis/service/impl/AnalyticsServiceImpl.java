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
import xyz.qwerty.lobetoolapis.vo.CmpDimensionVo;
import xyz.qwerty.lobetoolapis.vo.CmpQuestionVo;
import xyz.qwerty.lobetoolapis.vo.ComparativeAnalysisVo;
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
		learningObjectVo.setRepositoryName(learningObject.getRepositoryName());

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

		List<LearningObjectVo> learningObjects = new ArrayList<>();

		lobeIds.forEach(lobeId -> {
			learningObjects.add(lobeService.getLobeRubrik(userId, lobeId, true));
		});

		List<StrengthWeaknessAnalysisVo> analysis = getInitializedAnalysisVo();

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

			int zoneIndex;
			int dimensionIndex;

			if (swQuestionAgg.getDimensionId() == 1) {
				dimensionIndex = 0;
			}
			else if (swQuestionAgg.getDimensionId() == 2) {
				dimensionIndex = 1;
			}
			else if (swQuestionAgg.getDimensionId() == 3) {
				dimensionIndex = 2;
			}
			else {
				dimensionIndex = 3;
			}

			Boolean c0 = score2 + score1 == 0 && score0 == 0;
			Boolean c1 = score2 + score1 > score0 && score2 > score1;
			Boolean c2 = score2 + score1 > score0 && score2 <= score1;
			Boolean c3 = score2 + score1 == score0;
			Boolean c4 = score2 + score1 > score0;

			if (score3 >= 90) {
				// green
				zoneIndex = 0;
			}
			else if (score3 > 75 && score3 < 90) {
				if (c0 || c1) {
					// green
					zoneIndex = 0;
				}
				else if (c2) {
					// teal
					zoneIndex = 1;
				}
				else if (c3) {
					// yellow
					zoneIndex = 2;
				}
				else {
					// orange
					zoneIndex = 3;
				}
			}
			else if (score3 <= 75 && score3 >= 50) {
				if (c1) {
					// teal
					zoneIndex = 1;
				}
				else if (c2) {
					// yellow
					zoneIndex = 2;
				}
				else {
					// orange
					zoneIndex = 3;
				}
			}
			else if (score3 < 50 && score3 >= 25) {
				if (c1) {
					// yellow
					zoneIndex = 2;
				}
				else if (c2 || c3) {
					// orange
					zoneIndex = 3;
				}
				else {
					// red
					zoneIndex = 4;
				}
			}
			else if (score3 < 25 && score3 > 10) {
				if (c4) {
					// yellow
					zoneIndex = 2;
				}
				else if (c3) {
					// orange
					zoneIndex = 3;
				}
				else {
					// red
					zoneIndex = 4;
				}
			}
			else {
				zoneIndex = 4;
			}

			analysis.get(zoneIndex).getSwDimensions().get(dimensionIndex).getSwQuestions().add(swQuestionAgg);
		}

		return analysis;
	}

	private List<StrengthWeaknessAnalysisVo> getInitializedAnalysisVo() {

		List<StrengthWeaknessAnalysisVo> analysis = new ArrayList<>();

		StrengthWeaknessAnalysisVo green = new StrengthWeaknessAnalysisVo();
		green.setZone("green");
		green.setSwDimensions(getInitializedDimensions());

		StrengthWeaknessAnalysisVo teal = new StrengthWeaknessAnalysisVo();
		teal.setZone("teal");
		teal.setSwDimensions(getInitializedDimensions());

		StrengthWeaknessAnalysisVo yellow = new StrengthWeaknessAnalysisVo();
		yellow.setZone("yellow");
		yellow.setSwDimensions(getInitializedDimensions());

		StrengthWeaknessAnalysisVo orange = new StrengthWeaknessAnalysisVo();
		orange.setZone("orange");
		orange.setSwDimensions(getInitializedDimensions());

		StrengthWeaknessAnalysisVo red = new StrengthWeaknessAnalysisVo();
		red.setZone("red");
		red.setSwDimensions(getInitializedDimensions());

		analysis.add(green);
		analysis.add(teal);
		analysis.add(yellow);
		analysis.add(orange);
		analysis.add(red);

		return analysis;
	}

	private List<SwDimensionVo> getInitializedDimensions() {

		SwDimensionVo swDimension1 = new SwDimensionVo();
		swDimension1.setSwQuestions(new ArrayList<>());
		swDimension1.setDimensionId(1);
		swDimension1.setDimensionName("Content Quality");

		SwDimensionVo swDimension2 = new SwDimensionVo();
		swDimension2.setSwQuestions(new ArrayList<>());
		swDimension2.setDimensionId(2);
		swDimension2.setDimensionName("Pedagogical Alignment");

		SwDimensionVo swDimension3 = new SwDimensionVo();
		swDimension3.setSwQuestions(new ArrayList<>());
		swDimension3.setDimensionId(3);
		swDimension3.setDimensionName("Design Efficacy");

		SwDimensionVo swDimension4 = new SwDimensionVo();
		swDimension4.setSwQuestions(new ArrayList<>());
		swDimension4.setDimensionId(4);
		swDimension4.setDimensionName("Technological Integration");

		return Arrays.asList(swDimension1, swDimension2, swDimension3, swDimension4);
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

	@Override
	public List<ComparativeAnalysisVo> getComparativeAnalysis(List<Integer> lobeIds, String userId) {

		List<LearningObjectVo> learningObjects = new ArrayList<>();

		lobeIds.forEach(lobeId -> {
			learningObjects.add(lobeService.getLobeRubrik(userId, lobeId, true));
		});

		Map<String, Map<Integer, SwQuestionVo>> repoQuestionsAgg = getRepowiseAggQuestions(learningObjects);

		List<ComparativeAnalysisVo> analysis = new ArrayList<>();

		for (Entry<String, Map<Integer, SwQuestionVo>> repoAgg : repoQuestionsAgg.entrySet()) {

			String repositoryName = repoAgg.getKey();

			Map<Integer, SwQuestionVo> questionsAgg = repoAgg.getValue();

			ComparativeAnalysisVo cmpAnalysisVo = new ComparativeAnalysisVo();
			cmpAnalysisVo.setRepositoryName(repositoryName);

			List<CmpDimensionVo> cmpDimension = getInitializedCmpDimensions();
			cmpAnalysisVo.setCmpDimensions(cmpDimension);

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

				String zone;
				int dimensionIndex;

				if (swQuestionAgg.getDimensionId() == 1) {
					dimensionIndex = 0;
				}
				else if (swQuestionAgg.getDimensionId() == 2) {
					dimensionIndex = 1;
				}
				else if (swQuestionAgg.getDimensionId() == 3) {
					dimensionIndex = 2;
				}
				else {
					dimensionIndex = 3;
				}

				Boolean c0 = score2 + score1 == 0 && score0 == 0;
				Boolean c1 = score2 + score1 > score0 && score2 > score1;
				Boolean c2 = score2 + score1 > score0 && score2 <= score1;
				Boolean c3 = score2 + score1 == score0;
				Boolean c4 = score2 + score1 > score0;

				if (score3 >= 90) {
					zone = "green";
				}
				else if (score3 > 75 && score3 < 90) {
					if (c0 || c1) {
						zone = "green";
					}
					else if (c2) {
						zone = "teal";
					}
					else if (c3) {
						zone = "yellow";
					}
					else {
						zone = "orange";
					}
				}
				else if (score3 <= 75 && score3 >= 50) {
					if (c1) {
						zone = "teal";
					}
					else if (c2) {
						zone = "yellow";
					}
					else {
						zone = "orange";
					}
				}
				else if (score3 < 50 && score3 >= 25) {
					if (c1) {
						zone = "yellow";
					}
					else if (c2 || c3) {
						zone = "orange";
					}
					else {
						zone = "red";
					}
				}
				else if (score3 < 25 && score3 > 10) {
					if (c4) {
						zone = "yellow";
					}
					else if (c3) {
						zone = "orange";
					}
					else {
						zone = "red";
					}
				}
				else {
					zone = "red";
				}

				CmpQuestionVo cmpQuestionVo = new CmpQuestionVo();
				cmpQuestionVo.setQuestion(swQuestionAgg.getQuestion());
				cmpQuestionVo.setZone(zone);
				cmpDimension.get(dimensionIndex).getCmpQuestions().add(cmpQuestionVo);
			}

			analysis.add(cmpAnalysisVo);
		}

		return analysis;
	}

	private Map<String, Map<Integer, SwQuestionVo>> getRepowiseAggQuestions(List<LearningObjectVo> learningObjects) {

		Map<String, Map<Integer, SwQuestionVo>> questionsAgg = new HashMap<>();

		learningObjects.forEach(learningObject -> {

			String repositoryName = learningObject.getRepositoryName();

			List<DimensionVo> dimensions = learningObject.getDimensionVos();

			dimensions.forEach(d -> {

				List<QuestionVo> questions = d.getQuestions();
				questions.forEach(q -> {

					Integer questionId = q.getId();
					String question = q.getQuestion();
					Integer score = q.getScore();
					Integer dimensionId = q.getDimensionId();

					if (!questionsAgg.containsKey(repositoryName)) {

						questionsAgg.put(repositoryName, new HashMap<>());
					}
					Map<Integer, SwQuestionVo> repositoryAgg = questionsAgg.get(repositoryName);
					if (!repositoryAgg.containsKey(q.getId())) {

						SwQuestionVo swQuestionVo = new SwQuestionVo();
						swQuestionVo.setQuestion(question);
						swQuestionVo.setScore0(0d);
						swQuestionVo.setScore1(0d);
						swQuestionVo.setScore2(0d);
						swQuestionVo.setScore3(0d);
						swQuestionVo.setDimensionId(dimensionId);

						questionsAgg.get(repositoryName).put(questionId, swQuestionVo);
					}
					SwQuestionVo swQuestionAgg = repositoryAgg.get(questionId);
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

	private List<CmpDimensionVo> getInitializedCmpDimensions() {

		CmpDimensionVo cmpDimension1 = new CmpDimensionVo();
		cmpDimension1.setCmpQuestions(new ArrayList<>());
		cmpDimension1.setDimensionId(1);
		cmpDimension1.setDimensionName("Content Quality");

		CmpDimensionVo cmpDimension2 = new CmpDimensionVo();
		cmpDimension2.setCmpQuestions(new ArrayList<>());
		cmpDimension2.setDimensionId(2);
		cmpDimension2.setDimensionName("Pedagogical Alignment");

		CmpDimensionVo cmpDimension3 = new CmpDimensionVo();
		cmpDimension3.setCmpQuestions(new ArrayList<>());
		cmpDimension3.setDimensionId(3);
		cmpDimension3.setDimensionName("Design Efficacy");

		CmpDimensionVo cmpDimension4 = new CmpDimensionVo();
		cmpDimension4.setCmpQuestions(new ArrayList<>());
		cmpDimension4.setDimensionId(4);
		cmpDimension4.setDimensionName("Technological Integration");

		return Arrays.asList(cmpDimension1, cmpDimension2, cmpDimension3, cmpDimension4);
	}

}
