package ru.sorago.homeinv.core;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.sorago.homeinv.data.UserPrincipal;
import ru.sorago.homeinv.model.User;

public class ContextUtilities {
    private ContextUtilities() {
        throw new IllegalStateException("Utility class");
    }

    public static int getCurrentUserId() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUser().getId();
    }

    public static User getCurrentUser() {
        return ((UserPrincipal) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal()).getUser();
    }
}

