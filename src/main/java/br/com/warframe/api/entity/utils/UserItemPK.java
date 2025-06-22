package br.com.warframe.api.entity.utils;

import java.io.Serializable;
import java.util.Objects;

public class UserItemPK implements Serializable {

    private Long user;
    private Long item;

    public UserItemPK() {
    }

    public UserItemPK(Long user, Long item) {
        this.user = user;
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserItemPK))
            return false;
        UserItemPK that = (UserItemPK) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, item);
    }
}