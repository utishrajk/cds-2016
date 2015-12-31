package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.RouteCode;
import com.feisystems.bham.domain.reference.RouteCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RouteCodeServiceImpl implements RouteCodeService {

	@Autowired
    RouteCodeRepository routeCodeRepository;

	public long countAllRouteCodes() {
        return routeCodeRepository.count();
    }

	public void deleteRouteCode(RouteCode routeCode) {
        routeCodeRepository.delete(routeCode);
    }

	public RouteCode findRouteCode(Long id) {
        return routeCodeRepository.findOne(id);
    }

	public List<RouteCode> findAllRouteCodes() {
        return routeCodeRepository.findAll();
    }

	public List<RouteCode> findRouteCodeEntries(int firstResult, int maxResults) {
        return routeCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveRouteCode(RouteCode routeCode) {
        routeCodeRepository.save(routeCode);
    }

	public RouteCode updateRouteCode(RouteCode routeCode) {
        return routeCodeRepository.save(routeCode);
    }
}
