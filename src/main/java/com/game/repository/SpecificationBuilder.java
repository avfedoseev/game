package com.game.repository;

import com.game.controller.RequestFilterParams;
import com.game.entity.Player;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SpecificationBuilder {
    public static Specification<Player> getSpecification(RequestFilterParams request){
        return (root, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (request.getName() != null) {
                predicateList.add(builder.like(root.get("name"), "%" + request.getName() + "%"));
            }
            if (request.getRace() != null) {
                predicateList.add(builder.equal(root.get("race"), request.getRace()));
            }
            if (request.getProfession() != null) {
                predicateList.add(builder.equal(root.get("profession"), request.getProfession()));
            }
            if (request.getTitle() != null) {
                predicateList.add(builder.like(root.get("title"), "%" + request.getTitle() + "%"));
            }
            if (request.getBanned() != null) {
                predicateList.add(builder.equal(root.get("banned"), request.getBanned()));
            }
            if (request.getMinExperience() != null) {
                predicateList.add(builder.greaterThanOrEqualTo(root.get("experience"), request.getMinExperience()));
            }
            if (request.getMinLevel() != null) {
                predicateList.add(builder.greaterThanOrEqualTo(root.get("level"), request.getMinLevel()));
            }
            if (request.getMaxExperience() != null) {
                predicateList.add(builder.lessThanOrEqualTo(root.get("experience"), request.getMaxExperience()));
            }
            if (request.getMaxLevel() != null) {
                predicateList.add(builder.lessThanOrEqualTo(root.get("level"), request.getMaxLevel()));
            }
            if (request.getBefore() != null) {
                predicateList.add(builder.lessThanOrEqualTo(root.get("birthday"), new Date(request.getBefore())));
            }
            if (request.getAfter() != null) {
                predicateList.add(builder.greaterThanOrEqualTo(root.get("birthday"), new Date(request.getAfter())));
            }

            Predicate[] predicates = new Predicate[predicateList.size()];
            predicateList.toArray(predicates);

            return builder.and(predicates);
        };
    }
}
