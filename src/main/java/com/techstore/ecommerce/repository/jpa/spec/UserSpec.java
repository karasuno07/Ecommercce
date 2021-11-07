package com.techstore.ecommerce.repository.jpa.spec;

import com.techstore.ecommerce.object.dto.filter.UserFilter;
import com.techstore.ecommerce.object.entity.jpa.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.Date;

public final class UserSpec {

    public static Specification<User> getSpecification(UserFilter filter) {
        return Specification.where(hasUsername(filter.getUsername()))
                            .and(hasFullNameContaining(filter.getFullName()))
                            .and(hasGender(filter.getGender()))
                            .and(hasDobBetween(filter.getDobFrom(), filter.getDobTo()))
                            .and(hasEmail(filter.getEmail()))
                            .and(hasPhoneNumber(filter.getPhoneNumber()))
                            .and(hasAddressContaining(filter.getAddress()));
    }

    private static Specification<User> hasUsername(String username) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(username)
                ? builder.conjunction()
                : builder.equal(root.get("username"), username);
    }


    private static Specification<User> hasFullNameContaining(String fullName) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(fullName)
                ? builder.conjunction()
                : builder.like(root.get("fullName"), "%" + fullName + "%");
    }

    private static Specification<User> hasGender(Boolean gender) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(gender)
                ? builder.conjunction()
                : builder.equal(root.get("gender"), gender);
    }

    private static Specification<User> hasDobBetween(Date from, Date to) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(from) && ObjectUtils.isEmpty(to)
                ? builder.conjunction()
                : ObjectUtils.isEmpty(from)
                  ? builder.lessThanOrEqualTo(root.get("dateOfBirth"), to)
                  : ObjectUtils.isEmpty(to)
                    ? builder.greaterThanOrEqualTo(root.get("dateOfBirth"), from)
                    : builder.between(root.get("dateOfBirth"), from, to);
    }

    private static Specification<User> hasEmail(String email) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(email)
                ? builder.conjunction()
                : builder.equal(root.get("email"), email);
    }

    private static Specification<User> hasPhoneNumber(String phoneNumber) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(phoneNumber)
                ? builder.conjunction()
                : builder.equal(root.get("phoneNumber"), phoneNumber);
    }

    private static Specification<User> hasAddressContaining(String address) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(address)
                ? builder.conjunction()
                : builder.like(root.get("address"), "%" + address + "%");
    }
}
