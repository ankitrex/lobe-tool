package xyz.qwerty.lobetoolapis.service;

import java.util.List;

import xyz.qwerty.lobetoolapis.vo.ComparativeAnalysisVo;
import xyz.qwerty.lobetoolapis.vo.LearningObjectVo;
import xyz.qwerty.lobetoolapis.vo.LobeSummaryVo;
import xyz.qwerty.lobetoolapis.vo.StrengthWeaknessAnalysisVo;

public interface AnalyticsService {

	List<LearningObjectVo> getAllLobes(String userId, String type, Integer rubrikId);

	List<LobeSummaryVo> getLobeSummary(List<Integer> lobeIds, String userId);

	List<StrengthWeaknessAnalysisVo> getStrengthWeaknessAnalysis(List<Integer> lobeIds, String userId);

	List<ComparativeAnalysisVo> getComparativeAnalysis(List<Integer> lobeIds, String userId);
}
