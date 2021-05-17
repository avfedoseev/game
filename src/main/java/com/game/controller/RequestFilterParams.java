package com.game.controller;

import com.game.entity.Profession;
import com.game.entity.Race;

public class RequestFilterParams {
    private final String name;
    private final String title;
    private final Race race;
    private final Profession profession;
    private final Long after;
    private final Long before;
    private final Boolean banned;
    private final Integer minExperience;
    private final Integer maxExperience;
    private final Integer minLevel;
    private final Integer maxLevel;

    public RequestFilterParams(
            String name,
            String title,
            Race race,
            Profession profession,
            Long after,
            Long before,
            Boolean banned,
            Integer minExperience,
            Integer maxExperience,
            Integer minLevel,
            Integer maxLevel
    ) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.banned = banned;
        this.after = after;
        this.before = before;
        this.minExperience = minExperience;
        this.minLevel = minLevel;
        this.maxExperience = maxExperience;
        this.maxLevel = maxLevel;
    }

    public boolean notEmpty() {
        return name != null
                && title != null
                && race != null
                && profession != null
                && after != null
                && before != null
                && banned != null
                && minExperience != null
                && maxExperience != null
                && minLevel != null
                && maxLevel != null;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public Long getAfter() {
        return after;
    }

    public Long getBefore() {
        return before;
    }

    public Boolean getBanned() {
        return banned;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }
}
