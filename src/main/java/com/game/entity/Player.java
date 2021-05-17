package com.game.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    private @Enumerated(EnumType.STRING) Race race;
    private @Enumerated(EnumType.STRING) Profession profession;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;
    private Date birthday;
    private Boolean banned;

    public Player() {
    }

    public Player(
            String name,
            String title,
            Race race,
            Profession profession,
            Integer experience,
            Date birthday,
            Boolean banned
    ) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.birthday = birthday;
        this.banned = banned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) && Objects.equals(name, player.name) && Objects.equals(title, player.title) && race == player.race && profession == player.profession && Objects.equals(experience, player.experience) && Objects.equals(level, player.level) && Objects.equals(untilNextLevel, player.untilNextLevel) && Objects.equals(birthday, player.birthday) && Objects.equals(banned, player.banned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, race, profession, experience, level, untilNextLevel, birthday, banned);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\n \"name\":" + name +
                ",\n \"title\":" + title +
                ",\n \"race\":" + race +
                ",\n \"profession\":" + profession +
                ",\n \"experience\":" + experience +
                ",\n \"level\":" + level +
                ",\n \"untilNextLevel\":" + untilNextLevel +
                ",\n \"birthday\":" + birthday +
                ",\n \"banned\":" + banned +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean isBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }
}
