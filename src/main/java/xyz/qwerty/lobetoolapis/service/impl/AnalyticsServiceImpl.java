package xyz.qwerty.lobetoolapis.service.impl;

import java.util.ArrayList;
import java.util.List;
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

}
