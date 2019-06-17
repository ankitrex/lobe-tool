package xyz.qwerty.lobetoolapis.service;

import java.util.List;

import xyz.qwerty.lobetoolapis.vo.LearningObjectVo;
import xyz.qwerty.lobetoolapis.vo.LobeSummaryVo;

public interface AnalyticsService {

	List<LearningObjectVo> getAllLobes(String userId, String type, Integer rubrikId);

	List<LobeSummaryVo> getLobeSummary(List<Integer> lobeIds, String userId);
}
