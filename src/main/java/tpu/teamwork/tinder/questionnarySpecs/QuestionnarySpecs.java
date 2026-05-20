package tpu.teamwork.tinder.questionnarySpecs;

import org.springframework.data.jpa.domain.Specification;
import tpu.teamwork.tinder.entity.Questionnary;

import java.util.UUID;

public class QuestionnarySpecs {
    public static Specification<Questionnary> isActive() {
        return (root, query, cb) -> cb.isTrue(root.get("isActive"));
    }

    public static Specification<Questionnary> hasDormitory(UUID dormitoryId) {
        return (root, query, cb) -> {
            if (dormitoryId == null) return null;
            return cb.equal(root.get("dormitory"), dormitoryId);
        };
    }

    public static Specification<Questionnary> hasGender(Character gender) {
        return (root, query, cb) -> {
            if (gender == null) return null;
            return cb.equal(root.get("gender"), gender);
        };
    }

    public static Specification<Questionnary> notSelf(UUID userId) {
        return (root, query, cb) -> {
            if (userId == null) return null;
            return cb.notEqual(root.get("id"), userId);
        };
    }
}